package game.engine.monsters;

import game.engine.Role;

public class Dasher extends Monster {
    private int momentumTurns;

    public Dasher(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
        this.momentumTurns = 0;
    }

    public int getMomentumTurns() {
        return momentumTurns;
    }

    public void setMomentumTurns(int momentumTurns) {
        this.momentumTurns = momentumTurns;
    }

    @Override
    public void move(int distance) {
        super.move(applySpeed(distance));
    }
    
    @Override
    public void executePowerupEffect(Monster opponentMonster) {
        this.momentumTurns = 3;
    }

    public int getSpeedMultiplier() {
        return (momentumTurns > 0) ? 3 : 2;
    }

    public int applySpeed(int diceRoll) {
        int multiplied = diceRoll * getSpeedMultiplier();
        if (momentumTurns > 0) {
            momentumTurns--;
        }
        return multiplied;
    }
}
