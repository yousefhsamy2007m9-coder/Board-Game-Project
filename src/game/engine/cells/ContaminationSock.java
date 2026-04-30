package game.engine.cells;

import game.engine.Constants;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class ContaminationSock extends TransportCell implements CanisterModifier {

	public ContaminationSock(String name, int effect) {
		super(name, effect);
	}
	public void onLand(Monster landingMonster,Monster opponentMonster) {
		super.onLand(landingMonster, opponentMonster);
	transport(landingMonster);
	
     }
	
	public void transport(Monster monster) {
		monster.move(-getEffect());
		monster.alterEnergy(-Constants.SLIP_PENALTY);
	}
	 public void modifyCanisterEnergy(Monster monster, int value) {
	        monster.alterEnergy(value);
	    }
}