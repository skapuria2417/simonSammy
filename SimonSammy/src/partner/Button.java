package partner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import SimonSammy.ButtonInterfaceSammy;
import gui6.components.Action;
import gui6.components.Component;

public class Button extends Component implements ButtonInterfaceSammy {
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;

	public Button(int x, int y) {
		super(x, y, WIDTH, HEIGHT);
	}

	public void act() {
		System.out.println("CLICKED");
		action.act();
	}

	public void setColor(Color color) {
		this.c = color;
		displayColor = c;
		update();
	}

	public void highlight() {
		highlight = true;
		update();
	}

	public void dim() {
		highlight = false;
		update();
	}

	public boolean isHovered(int x, int y) {
		return this.getX() < x && this.getX() + this.getWidth() > x && this.getY() < y
				&& this.getY() + this.getHeight() > y;
	}

	public void setAction(Action a) {
		this.action = a;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (displayColor != null)
			g.setColor(displayColor);
		else
			g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (highlight) {
			g.setColor(Color.white);
			g.fillOval(0, 0, 25, 25);
		}

	}

}
