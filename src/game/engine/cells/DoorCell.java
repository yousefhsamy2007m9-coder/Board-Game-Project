package game.engine.cells;

import game.engine.Board;
import game.engine.Role;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class DoorCell extends Cell implements CanisterModifier {
    private Role role;
    private int energy;
    private boolean activated;

    public DoorCell(String name, Role role, int energy) {
        super(name);
        this.role = role;
        this.energy = energy;
        this.activated = false;
    }

    public Role getRole() {
        return role;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean isActivated) {
        this.activated = isActivated;
    }

 // DoorCell.java
    @Override
    public void modifyCanisterEnergy(Monster monster, int canisterValue) {
        if (monster.getRole() == this.role) {
            monster.alterEnergy(canisterValue);   // same role = gain
        } else {
            monster.alterEnergy(-canisterValue);  // different role = penalty
        }
    }

    @Override
    public void onLand(Monster landingMonster, Monster opponentMonster) {
        super.onLand(landingMonster, opponentMonster);

        if (activated) return;

        boolean roleMatch = landingMonster.getRole() == this.role;

        if (roleMatch) {
            modifyCanisterEnergy(landingMonster, this.energy);
            for (Monster m : Board.getStationedMonsters()) {
                if (m.getRole() == this.role) {
                    modifyCanisterEnergy(m, this.energy);
                }
            }
            activated = true;
        } else {
            boolean wasShielded = landingMonster.isShielded();
            modifyCanisterEnergy(landingMonster, this.energy); // method handles negation

            if (!wasShielded) {
                for (Monster m : Board.getStationedMonsters()) {
                    if (m.getRole() == landingMonster.getRole()) {
                        modifyCanisterEnergy(m, this.energy);
                    }
                }
                activated = true;
            }
        }
    }
}