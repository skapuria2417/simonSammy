package partner;

import SimonSammy.ButtonInterfaceSammy;
import SimonSammy.MoveInterfaceSammy;

public class Move implements MoveInterfaceSammy {
	
	private ButtonInterfaceSammy button;
	
	public Move(ButtonInterfaceSammy button) {
		this.button = button;
	}

	@Override
	public ButtonInterfaceSammy getButton() {
		return button;
	}

}
