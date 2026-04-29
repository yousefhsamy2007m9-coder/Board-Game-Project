package game.engine.monsters;

import game.engine.Constants;
import game.engine.Role;

import java.util.ArrayList;

public class Schemer extends Monster {

    public Schemer(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
    }

    @Override
    public void setEnergy(int newEnergy) {
        int delta = newEnergy - getEnergy();
        if (delta != 0) {
            int sign = (delta > 0) ? 1 : -1;
            delta += sign * Constants.SCHEMER_STEAL;
        }
        super.setEnergy(getEnergy() + delta);
    }

    private int stealEnergyFrom(Monster target) {
        int stealAmount = Math.min(Constants.SCHEMER_STEAL, target.getEnergy());
        // Use setEnergy directly (bypass super passive bonus on target's side)
        target.setEnergy(target.getEnergy() - stealAmount);
        return stealAmount;
    }

    public void executePowerupEffect(Monster opponentMonster, ArrayList<Monster> stationedMonsters) {
        int totalStolen = 0;

        totalStolen += stealEnergyFrom(opponentMonster);

        if (stationedMonsters != null) {
            for (Monster m : stationedMonsters) {
                if (m != this) {
                    totalStolen += stealEnergyFrom(m);
                }
            }
        }

        setEnergy(getEnergy() + totalStolen);
    }

    @Override
    public void executePowerupEffect(Monster opponentMonster) {
        executePowerupEffect(opponentMonster, new ArrayList<>());
    }
}
