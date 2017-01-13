package SimonSammy;

import java.awt.Color;

import gui6.components.Action;
import gui6.components.Clickable;



public interface ButtonInterfaceSammy extends Clickable {
	public  void setAction(Action a);

	public void setColor(Color color);
	
	public void highlight();

	public void dim();
}
