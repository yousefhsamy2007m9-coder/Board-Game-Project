package game.engine.cells;

import game.engine.monsters.*;

public class MonsterCell extends Cell {
	private Monster cellMonster;
	private int landingEnergy;
	private int cellEnergy;

	public MonsterCell(String name, Monster cellMonster) {
		super(name);
		this.cellMonster = cellMonster;
	}

	public Monster getCellMonster() {
		return cellMonster;
	}
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {
	    super.onLand(landingMonster, opponentMonster);
	    
	    if (landingMonster.getRole() == cellMonster.getRole()) {
	        landingMonster.executePowerupEffect(opponentMonster);
	    } 
	    
	    else if (landingMonster.getEnergy() > cellMonster.getEnergy()) {
	        int oldLandingEnergy = landingMonster.getEnergy();
	        int oldCellEnergy = cellMonster.getEnergy();
	        landingMonster.alterEnergy(oldCellEnergy - oldLandingEnergy);
	        cellMonster.setEnergy(oldLandingEnergy);
	    }
	}
}