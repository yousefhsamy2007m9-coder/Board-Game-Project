package game.engine.monsters;

import game.engine.Constants;
import game.engine.Board;
import game.engine.Role;

import java.util.ArrayList;

public class Schemer extends Monster {

    public Schemer(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
    }

    @Override
    public void setEnergy(int newEnergy) {
        int change = newEnergy - getEnergy();
        if (change >= 0)
        	super.setEnergy(newEnergy + 10);
        else
        	super.setEnergy(newEnergy - 10);
        
    }

    private int stealEnergyFrom(Monster target) {
        int stealAmount = Math.min(Constants.SCHEMER_STEAL, target.getEnergy());
        target.setEnergy(target.getEnergy() - stealAmount);
        return stealAmount;
    }

    public void executePowerupEffect(Monster opponentMonster, ArrayList<Monster> stationedMonsters) {
        int totalStolen = 0;

        totalStolen += stealEnergyFrom(opponentMonster);

        if (stationedMonsters != null) {
            for (Monster m : stationedMonsters) {
                totalStolen += stealEnergyFrom(m);
            }
        }

        setEnergy(getEnergy() + totalStolen);
    }

    @Override
    public void executePowerupEffect(Monster opponentMonster) {
        executePowerupEffect(opponentMonster, Board.getStationedMonsters());
    }
    
}
