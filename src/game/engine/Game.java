package game.engine;
import game.engine.dataloader.DataLoader;
import game.engine.monsters.*;
import java.io.*;
import java.util.*;

public class Game {
	private final Board board;
	private final ArrayList<Monster> allMonsters;
	private final Monster player;
	private final Monster opponent;
	private Monster current;
	
	public Game(Role playerRole) throws IOException {
		board = new Board(DataLoader.readCards());
		allMonsters = DataLoader.readMonsters();
		player = selectRandomMonsterByRole(playerRole);
		Role opponentRole = (playerRole == Role.SCARER) ? Role.LAUGHER : Role.SCARER;
		opponent = selectRandomMonsterByRole(opponentRole);
		current = player;
	}
	
	private Monster selectRandomMonsterByRole(Role role) {
		ArrayList<Monster> filtered = new ArrayList<>();
	    for (Monster m : allMonsters) {
	        if (m.getRole() == role) {
	            filtered.add(m);
	        }
	    }
	    int randomIndex = (int) (Math.random() * filtered.size());
	    return filtered.get(randomIndex);
	}

    public Board getBoard() {
        return board;
    }

    public ArrayList<Monster> getAllMonsters() {
        return allMonsters;
    }

    public Monster getPlayer() {
        return player;
    }

    public Monster getOpponent() {
        return opponent;
    }

    public Monster getCurrent() {
        return current;
    }

    public void setCurrent(Monster current) {
        this.current = current;
    }
}
