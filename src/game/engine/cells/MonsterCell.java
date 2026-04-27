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
	
	public void onland(Monster landingMonster,Monster opponentMonster) {
		super.onLand(landingMonster, opponentMonster);
		if(landingMonster.getEnergy()>cellMonster.getEnergy()) {
			int landingEnergy=landingMonster.getEnergy();
			int cellEnergy=cellMonster.getEnergy();
		}
		landingMonster.alterEnergy(cellEnergy-landingEnergy);
		cellMonster.setEnergy(landingEnergy);
	}
}