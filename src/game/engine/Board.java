package game.engine;

import java.util.*;
import game.engine.cards.Card;
import game.engine.cells.*;
import game.engine.monsters.Monster;
import game.engine.exceptions.*;

public class Board {
	private Cell[][] boardCells;
	private static ArrayList<Monster> stationedMonsters; 
	private static ArrayList<Card> originalCards;
	private static ArrayList<Card> cards;
	
	public Board(ArrayList<Card> readCards) {
		this.boardCells = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
		stationedMonsters = new ArrayList<Monster>();
		originalCards = readCards;
		cards = new ArrayList<Card>();
		setCardsByRarity();
		reloadCards();
	}
	
	public Cell[][] getBoardCells() {
		return boardCells;
	}
	
	public static ArrayList<Monster> getStationedMonsters() {
		return stationedMonsters;
	}
	
	public static void setStationedMonsters(ArrayList<Monster> stationedMonsters) {
		Board.stationedMonsters = stationedMonsters;
	}

	public static ArrayList<Card> getOriginalCards() {
		return originalCards;
	}
	
	public static ArrayList<Card> getCards() {
		return cards;
	}
	
	public static void setCards(ArrayList<Card> cards) {
		Board.cards = cards;
	}
	
	private int[] indexToRowCol(int index) {
		return new int[]{index/10, ((index/10)%2==0)?index%10:9-index%10};
	}
	
	private Cell getCell(int index) {
		return this.boardCells[indexToRowCol(index)[0]][indexToRowCol(index)[1]];
	}
	
	private void setCell(int index, Cell cell) {
		this.boardCells[indexToRowCol(index)[0]][indexToRowCol(index)[1]] = cell;
	}
	
	public void initializeBoard(ArrayList<Cell> specialCells) {
		for(int cellNo = 0; cellNo < 100; cellNo += 2) {
			setCell(cellNo,new Cell("Normal cell"));
		}
		
		int index = 0;
		for(int cellNo = 1; cellNo < 100; cellNo += 2) {
			while (!(specialCells.get(index) instanceof DoorCell))
			    index++;
			setCell(cellNo,specialCells.get(index));
			index++;
		}
		
		index = 0;
		for(int i : Constants.CONVEYOR_CELL_INDICES) {
			while (!(specialCells.get(index) instanceof ConveyorBelt))
			    index++;
			setCell(i,specialCells.get(index));
			index++;
		}
		
		index = 0;
		for(int i : Constants.SOCK_CELL_INDICES) {
			while (!(specialCells.get(index) instanceof ContaminationSock))
			    index++;
			setCell(i,specialCells.get(index));
			index++;
		}
		
		for(int i : Constants.CARD_CELL_INDICES) {
			setCell(i,new CardCell("Card cell"));
		}
		
		index = 0;
		for(int i : Constants.MONSTER_CELL_INDICES) {
			if (stationedMonsters != null && index < stationedMonsters.size()) {
				stationedMonsters.get(index).setPosition(i);
				setCell(i,new MonsterCell(stationedMonsters.get(index).getName(),stationedMonsters.get(index++)));
			}
		}
	}
	
	private void setCardsByRarity(){
		ArrayList<Card> updatedOriginalCards = new ArrayList<>();
		for(Card c : originalCards) {
			for(int i = 0; i < c.getRarity(); i++) {
				updatedOriginalCards.add(c);
			}
		}
		originalCards = updatedOriginalCards;
	}
	
	public static void reloadCards() {
	    ArrayList<Card> freshCards = new ArrayList<>(originalCards);
	    Collections.shuffle(freshCards);
	    setCards(freshCards);
	}
	
	public static Card drawCard() {
		if (cards.isEmpty())
			reloadCards();
		return Board.cards.remove(0);
	}
	
	public void moveMonster(Monster currentMonster, int roll, Monster opponentMonster) throws InvalidMoveException {
	    int old = currentMonster.getPosition();
	    currentMonster.move(roll);

	    if (currentMonster.getPosition() == opponentMonster.getPosition()) {
	        currentMonster.setPosition(old);
	        throw new InvalidMoveException();
	    }

	    getCell(currentMonster.getPosition()).onLand(currentMonster, opponentMonster);

	    if (currentMonster.isConfused()) {
	        currentMonster.decrementConfusion();
	        opponentMonster.decrementConfusion();
	    }

	    updateMonsterPositions(currentMonster, opponentMonster);
	}
	
	private void updateMonsterPositions(Monster player, Monster opponent) {
		for(int i = 0; i < 100; i++)
			getCell(i).setMonster(null);
		getCell(player.getPosition()).setMonster(player);
		getCell(opponent.getPosition()).setMonster(opponent);
	}
}
