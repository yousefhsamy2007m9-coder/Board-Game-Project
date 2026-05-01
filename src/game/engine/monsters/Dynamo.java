package game.engine.monsters;

import game.engine.Role;

public class Dynamo extends Monster {

    public Dynamo(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
    }

    @Override
    public void setEnergy(int newEnergy) {
        int doubledChange = (newEnergy - getEnergy())*2;
        super.setEnergy(getEnergy() + doubledChange);
    }

    @Override
    public void executePowerupEffect(Monster opponentMonster) {
        opponentMonster.setFrozen(true);
    }
}
