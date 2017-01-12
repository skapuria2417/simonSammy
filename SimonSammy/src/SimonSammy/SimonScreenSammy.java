package SimonSammy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.ClickableScreen;
import gui.Components.Action;
import gui.Components.Button;
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
		
		    textLabel.setText("");
		    nextRound();
		

	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber ++;
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
		for(MoveInterfaceSammy m: move){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
		
	}

	private void changeText(String string) {
		try{
			textLabel.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
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

	public void addButtons(List<Visible> viewObjects) {
		
		Color[] colors = {Color.red, Color.blue,Color.green, Color.pink, Color.yellow, };
		String[] names = {"Red", "Blue", "Pink", "Green", "Yellow"};
		int[] x={400,302,265,360};
		int [] y={400,302,265,360};
		int numberOfButtons =6;
		for(int i =0; i< numberOfButtons;i++){
			final ButtonInterfaceSammy b = getAButton();
			b.setColor(colors[i]);
		    setX(x[i]);
		    setY(y[i]);
		    
		    b.setAction(new Action(){
		    	public void act(){
		    		
		    		Thread blink = new Thread(new Runnable(){

		    			public void run(){
		    			
		    				b.highlight();
		    				try {
								Thread.sleep(800);
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
							
		    			}});

		    		if(acceptingInput && move.get(sequenceIndex).getButton() == b){
						sequenceIndex++;
					}else if(acceptingInput){
						gameOver();
						return;
					}
					if(sequenceIndex == move.size()){
						Thread nextRound = new Thread(SimonScreenSammy.this);
						nextRound.start();
					}
		    	}
		    });
		    button[i]=b;
		    viewObjects.add(button[i]);
		    
		}
		
		
		
		
	}

	protected void gameOver() {
		progress.gameOver();
		
	}

	private void setY(int i) {
		// TODO Auto-generated method stub
		
	}

	private void setX(int i) {
		// TODO Auto-generated method stub
		
	}

	private ButtonInterfaceSammy getAButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
