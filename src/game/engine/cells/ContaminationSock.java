package game.engine.cells;

import game.engine.Constants;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class ContaminationSock extends TransportCell implements CanisterModifier {

    public ContaminationSock(String name, int effect) {
        super(name, effect);
    }

    @Override
    public void onLand(Monster landingMonster, Monster opponentMonster) {
        super.onLand(landingMonster, opponentMonster); // calls Cell.onLand + transport
    }

    @Override
    public void transport(Monster monster) {
        monster.setPosition(monster.getPosition() + getEffect()); // direct, no speed multiplier
        monster.alterEnergy(-Constants.SLIP_PENALTY);
    }

    @Override
    public void modifyCanisterEnergy(Monster monster, int value) {
        monster.alterEnergy(value);
    }
}