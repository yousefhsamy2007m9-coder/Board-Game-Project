package game.engine.monsters;
import game.engine.Constants;
import game.engine.Role;

public abstract class Monster implements Comparable<Monster>{
	private final String name;
	private final String description;
	private Role role;
	private final Role originalRole;
	private int energy;
	private int position;
	private boolean frozen;
	private boolean shielded;
	private int confusionTurns;
	
	public Monster(String name, String description, Role originalRole, int energy) {
		this.name = name;
		this.description = description;
		this.originalRole = originalRole;
		setEnergy(energy);
		role = originalRole;
		position = 0;
		confusionTurns = 0;
		frozen = false;
		shielded = false;
	}
	
	public int compareTo(Monster o) {
		return this.position - o.position;
	}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Role getRole() {
        return role;
    }

    public Role getOriginalRole() {
        return originalRole;
    }

    public int getEnergy() {
        return energy;
    }

    public int getPosition() {
        return position;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public boolean isShielded() {
        return shielded;
    }

    public int getConfusionTurns() {
        return confusionTurns;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnergy(int energy) {
        if (energy < 0) {
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    public void setPosition(int position) {
    	if (position >= 0)
    		this.position = position % Constants.BOARD_SIZE;
    	else
    		this.position = 0;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    public void setConfusionTurns(int confusionTurns) {
    	if (confusionTurns > 0)
    		this.confusionTurns = confusionTurns;
    	else
    		this.confusionTurns = 0;
    }
}
