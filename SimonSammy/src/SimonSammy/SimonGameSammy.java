package SimonSammy;

import gui.GUIApplication;


public class SimonGameSammy extends GUIApplication {

	

	public SimonGameSammy(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SimonGameSammy sGame = new SimonGameSammy(800,500);
		Thread game = new Thread(sGame);
		game.start();

	}

	@Override
	public void initScreen() {
		SimonScreenSammy sScreen = new SimonScreenSammy(getWidth(),getHeight());
		setScreen(sScreen);
		
	}

}
