package game.engine.dataloader;

import java.io.*;
import java.util.*;
import game.engine.Role;
import game.engine.cards.*;
import game.engine.cells.*;
import game.engine.monsters.*;
import game.engine.exceptions.InvalidCSVFormat;

public class DataLoader {
	private static final String CARDS_FILE_NAME = "cards.csv";
	private static final String CELLS_FILE_NAME = "cells.csv";
	private static final String MONSTERS_FILE_NAME = "monsters.csv";
	
	public static ArrayList<Card> readCards() throws IOException{
		ArrayList<Card> cards = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_NAME));
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			
			if (values.length < 4 || values.length > 5) {
                br.close();
                throw new InvalidCSVFormat(line);
            }
			
			try {
				switch(values[0]){
					case "SWAPPER":
						cards.add(new SwapperCard(values[1], values[2], Integer.parseInt(values[3])));
						break;
					case "SHIELD":
						cards.add(new ShieldCard(values[1], values[2], Integer.parseInt(values[3])));
						break;
					case "ENERGYSTEAL":
						cards.add(new EnergyStealCard(values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4])));
						break;
					case "STARTOVER":
						cards.add(new StartOverCard(values[1], values[2], Integer.parseInt(values[3]), Boolean.parseBoolean(values[4])));
						break;
					case "CONFUSION":
						cards.add(new ConfusionCard(values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4])));
						break;
					default:
						br.close();
						throw new InvalidCSVFormat(line);
				}
			} catch (NumberFormatException e) {
				br.close();
                throw new InvalidCSVFormat(line);
			}
		}
		br.close();
		return cards;
	}
	
	public static ArrayList<Cell> readCells() throws IOException{
		ArrayList<Cell> cells = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME));
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			
			if (values.length < 2 || values.length > 3) {
                br.close();
                throw new InvalidCSVFormat(line);
            }
			
			try {
				if(values.length == 3) {
					cells.add(new DoorCell(values[0], Role.valueOf(values[1]), Integer.parseInt(values[2])));
				}
				if(values.length == 2) {
					if(Integer.parseInt(values[1]) > 0)
						cells.add(new ConveyorBelt(values[0], Integer.parseInt(values[1])));
					else
						cells.add(new ContaminationSock(values[0], Integer.parseInt(values[1])));
				}
			} catch (IllegalArgumentException e) {
				br.close();
                throw new InvalidCSVFormat(line);
			}
		}
		br.close();
		return cells;
	}
	
	public static ArrayList<Monster> readMonsters() throws IOException{
		ArrayList<Monster> monsters = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(MONSTERS_FILE_NAME));
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			
			if (values.length != 5) {
                br.close();
                throw new InvalidCSVFormat(line);
            }
			
			try {
				switch(values[0]){
					case "DYNAMO":
						monsters.add(new Dynamo(values[1], values[2], Role.valueOf(values[3]), Integer.parseInt(values[4])));
						break;
					case "DASHER":
						monsters.add(new Dasher(values[1], values[2], Role.valueOf(values[3]), Integer.parseInt(values[4])));
						break;
					case "SCHEMER":
						monsters.add(new Schemer(values[1], values[2], Role.valueOf(values[3]), Integer.parseInt(values[4])));
						break;
					case "MULTITASKER":
						monsters.add(new MultiTasker(values[1], values[2], Role.valueOf(values[3]), Integer.parseInt(values[4])));
						break;
					default:
						br.close();
						throw new InvalidCSVFormat(line);
				}
			} catch (IllegalArgumentException e) {
				br.close();
                throw new InvalidCSVFormat(line);
			}
		}
		br.close();
		return monsters;
	}
}
