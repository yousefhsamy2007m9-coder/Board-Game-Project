package game.engine.cells;

import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class ContaminationSock extends TransportCell implements CanisterModifier{
	
	public ContaminationSock(String name, int effect) {
		super(name, -Math.abs(effect));
	}
	
	public void modifyEnergy(Monster target, int amount) {
	    // Tbd
	}

}
