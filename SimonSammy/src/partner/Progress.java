package partner;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import SimonSammy.ProgressInterfaceSammy;
import gui6.components.Component;

public class Progress extends Component implements ProgressInterfaceSammy {

	private static final int WIDTH = 100, HEIGHT = 100;

	private boolean gameOver;
	private int round, sequence;

	public Progress(int x, int y) {
		super(x, y, WIDTH, HEIGHT);
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public void gameOver() {
		gameOver = true;
		update();
	}

	@Override
	public void setRound(int roundNumber) {
		this.round = roundNumber;
		update();
	}

	@Override
	public void setSequenceLength(int size) {
		this.sequence = size;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if (gameOver) {
			g.setColor(Color.RED);
			g.drawString("u lose", (WIDTH - fm.stringWidth("u lose")) / 2, 20);
			g.drawString("seq" + sequence, (WIDTH - fm.stringWidth("seq" + sequence)) / 2, 40);
		} else {
			g.setColor(Color.BLACK);
			if (round > 0 && sequence > 0) {
				g.drawString("round " + round, (WIDTH - fm.stringWidth("round " + round)) / 2, 20);
				g.drawString("seq " + sequence, (WIDTH - fm.stringWidth("seq " + sequence)) / 2, 40);
			}
		}
	}

}
