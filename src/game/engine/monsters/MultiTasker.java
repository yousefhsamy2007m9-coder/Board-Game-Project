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
        super.move(applySpeed(distance));
    }

    @Override
    public void setEnergy(int newEnergy) {
        int delta = newEnergy - getEnergy();
        if (delta != 0) {
            delta += Constants.MULTITASKER_BONUS; // always add +200, not sign * 200
        }
        super.setEnergy(getEnergy() + delta);
    }

    public int getSpeedDivisor() {
        return (normalSpeedTurns > 0) ? 1 : 2;
    }

    public int applySpeed(int diceRoll) {
        int divided = diceRoll / getSpeedDivisor();
        if (normalSpeedTurns > 0) {
            normalSpeedTurns--;
        }
        return divided;
    }

    @Override
    public void executePowerupEffect(Monster opponentMonster) {
        this.normalSpeedTurns = 2;
    }
}
