package SimonSammy;

import java.awt.Color;

import gui.Components.Action;
import gui.Components.Clickable;

public interface ButtonInterfaceSammy extends Clickable {
	public  void setAction(Action a);
		
	

	public void setColor(Color color);
	
	public void highlight();



	public void dim();
}
