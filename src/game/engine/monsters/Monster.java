package game.engine.monsters;
import game.engine.Constants;
import game.engine.Role;

public abstract class Monster implements Comparable<Monster>{
	private String name;
	private String description;
	private Role role;
	private Role originalRole;
	private int energy;
	private int position;
	private boolean frozen;
	private boolean shielded;
	private int confusionTurns;
	
	public Monster(String name, String description, Role originalRole, int energy) {
		super();
		this.name = name;
		this.description = description;
		this.originalRole = originalRole;
		this.energy = energy;
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
    	this.energy = Math.max(Constants.MIN_ENERGY, energy);
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
