package SimonSammy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui6.ClickableScreen;
import gui6.components.Action;
import gui6.components.TextLabel;
import gui6.components.Visible;
import partner.Button;
import partner.Move;
import partner.Progress;

public class SimonScreenSammy extends ClickableScreen implements Runnable {

	private TextLabel textLabel;
	private ButtonInterfaceSammy[] button;
	private ProgressInterfaceSammy progress;
	private ArrayList<MoveInterfaceSammy> move;

	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenSammy(int width, int height) {
		super(width, height);

		move = new ArrayList<MoveInterfaceSammy>();

		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run() {
		textLabel.setText("");
		nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		progress.setRound(roundNumber);
		move.add(randomMove());
		progress.setSequenceLength(move.size());
		changeText("Simon's turn.");
		textLabel.setText("");
		showSequence();
		changeText("Your turn.");
		textLabel.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void showSequence() {
		ButtonInterfaceSammy b = null;
		for (MoveInterfaceSammy m : move) {
			if (b != null)
				b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long) (2000 * (2.0 / (roundNumber + 2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b.dim();
		}
	}

	private void changeText(String string) {
		try {
			textLabel.setText(string);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		addButtons(viewObjects);

		move = new ArrayList<MoveInterfaceSammy>();

		lastSelectedButton = -1;
		move.add(randomMove());
		roundNumber = 0;
		viewObjects.add(getProgress());

		textLabel = new TextLabel(100, 100, 300, 40, "Let's play Simon!");
		viewObjects.add(textLabel);
	}

	private MoveInterfaceSammy randomMove() {
		int select = (int) (Math.random() * button.length);
		while (select == lastSelectedButton) {
			select = (int) (Math.random() * button.length);
		}
		lastSelectedButton = select;
		return new Move(button[select]);
	}

	private ProgressInterfaceSammy getProgress() {
		if (progress == null) {
			progress = new Progress(100, 200);
		}

		return progress;
	}

	public void addButtons(List<Visible> viewObjects) {
		Color[] colors = { Color.red, Color.blue, Color.green, Color.pink, Color.yellow, Color.BLACK };
		String[] names = { "Red", "Blue", "Pink", "Green", "Yellow", "Black" };

		int[] x = { 350, 325, 300, 275, 250, 225 };
		int numberOfButtons = 6;
		button = new Button[6];
		System.out.println("PRINTING BUTTONS");
		for (int i = 0; i < numberOfButtons; i++) {
			final ButtonInterfaceSammy b = getAButton(x[i], 100);
			b.setColor(colors[i]);
			b.setName(names[i]);

			b.setAction(new Action() {
				public void act() {

					Thread blink = new Thread(new Runnable() {

						public void run() {
							b.highlight();
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
						}
					});
					blink.start();

					if (acceptingInput && move.get(sequenceIndex).getButton() == b) {
						sequenceIndex++;
					} else if (acceptingInput) {
						gameOver();
						return;
					}
					if (sequenceIndex == move.size()) {
						Thread nextRound = new Thread(SimonScreenSammy.this);
						nextRound.start();
					}
				}
			});

			button[i] = b;
			viewObjects.add(b);
		}
	}

	protected void gameOver() {
		progress.gameOver();

	}

	private ButtonInterfaceSammy getAButton(int x, int y) {
		// TODO Auto-generated method stub
		return new Button(x, y);
	}

}
