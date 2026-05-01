package game.engine.monsters;

import game.engine.Constants;
import game.engine.Role;

public class MultiTasker extends Monster {
    private int normalSpeedTurns;

    public MultiTasker(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
        this.normalSpeedTurns = 0;
    }

    public int getNormalSpeedTurns() {
        return normalSpeedTurns;
    }

    public void setNormalSpeedTurns(int normalSpeedTurns) {
        this.normalSpeedTurns = normalSpeedTurns;
    }
    
    @Override
    public void move(int distance) {
    	int divided;
        if (normalSpeedTurns > 0) {
        	divided = distance;
            normalSpeedTurns--;
        } else {
        	divided = distance/2;
        }
        super.move(divided);
    }

    @Override
    public void setEnergy(int newEnergy) {
        super.setEnergy(newEnergy + Constants.MULTITASKER_BONUS);
    }

    @Override
    public void executePowerupEffect(Monster opponentMonster) {
        this.normalSpeedTurns = 2;
    }
}
