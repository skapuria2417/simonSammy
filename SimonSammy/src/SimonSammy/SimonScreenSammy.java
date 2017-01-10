package SimonSammy;

import java.util.ArrayList;

import gui.ClickableScreen;
import gui.Components.TextLabel;
import gui.Components.Visible;

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
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		textLabel = new TextLabel(130, 230, 300, 40, "Let's play Simon!");
		move = new ArrayList<MoveInterfaceSammy>();
		// add 2 moves to start
		lastSelectedButton = -1;
		move.add(randomMove());
		move.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(textLabel);

	}

	private MoveInterfaceSammy randomMove() {
		return getMove(button[(int) (Math.random() * button.length)]);
	}

	private MoveInterfaceSammy getMove(ButtonInterfaceSammy b) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProgressInterfaceSammy getProgress() {
		/**
		 * Placeholder until partner finishes implementation of
		 * ProgressInterface
		 */
		return null;
	}

	private void addButtons() {
		// TODO Auto-generated method stub

	}

}
