package game.engine.dataloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.engine.exceptions.InvalidCSVFormat;
import game.engine.Role;
import game.engine.cards.*;
import game.engine.cells.*;
import game.engine.monsters.*;

public class DataLoader {
	private static final String CARDS_FILE_NAME = "cards.csv";
	private static final String CELLS_FILE_NAME = "cells.csv";
	private static final String MONSTERS_FILE_NAME = "monsters.csv";
	
	public static ArrayList<Card> readCards() throws IOException {
		ArrayList<Card> cards = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_NAME));
		String line;
		
		while (br.ready()) {
			line = br.readLine();
			String[] data = line.split(",");
			
			if (data.length != 4 && data.length != 5) {
				br.close();
				System.out.println(data.length);
				throw new InvalidCSVFormat(line);
			}
			
			switch(data[0]){
			case "SWAPPER":
				cards.add(new SwapperCard(data[1], data[2], Integer.parseInt(data[3])));
				break;
			case "SHIELD":
				cards.add(new ShieldCard(data[1], data[2], Integer.parseInt(data[3])));
				break;
			case "ENERGYSTEAL":
				cards.add(new EnergyStealCard(data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4])));
				break;
			case "STARTOVER":
				cards.add(new StartOverCard(data[1], data[2], Integer.parseInt(data[3]), Boolean.parseBoolean(data[4])));
				break;
			case "CONFUSION":
				cards.add(new ConfusionCard(data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4])));
				break;
			default:
				br.close();
				throw new InvalidCSVFormat("Unknown card type: " + data[0]);
			}
		}

		br.close();
		return cards;
	}
	
	public static ArrayList<Cell> readCells() throws IOException {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME));
		String line;
		
		while (br.ready()) {
			line = br.readLine();
			String[] data = line.split(",");
			
			if (data.length != 2 && data.length != 3) {
				br.close();
				throw new InvalidCSVFormat(line);
			}
			
			if (data.length == 2) 
				cells.add(Integer.parseInt(data[1]) > 0 ? new ConveyorBelt(data[0], Integer.parseInt(data[1])) : new ContaminationSock(data[0], Integer.parseInt(data[1])));
			else 
				cells.add(new DoorCell(data[0], Role.valueOf(data[1]), Integer.parseInt(data[2])));
		}
		
		br.close();
		return cells;
	}
	
	public static ArrayList<Monster> readMonsters() throws IOException {
		ArrayList<Monster> monsters = new ArrayList<Monster>();

		BufferedReader br = new BufferedReader(new FileReader(MONSTERS_FILE_NAME));

		while (br.ready()) {
			String nextLine = br.readLine();
			String[] data = nextLine.split(",");
			

			if (data.length != 5) {
				br.close();
				throw new InvalidCSVFormat(nextLine);
			}
			
			switch (data[0]) {
				case "DYNAMO":
					monsters.add(new Dynamo(data[1], data[2], Role.valueOf(data[3]), Integer.parseInt(data[4])));
					break;
				case "DASHER":
					monsters.add(new Dasher(data[1], data[2], Role.valueOf(data[3]), Integer.parseInt(data[4])));
					break;
				case "MULTITASKER":
					monsters.add(new MultiTasker(data[1], data[2], Role.valueOf(data[3]), Integer.parseInt(data[4])));
					break;
				case "SCHEMER":
					monsters.add(new Schemer(data[1], data[2], Role.valueOf(data[3]), Integer.parseInt(data[4])));
					break;
			default:
				throw new InvalidCSVFormat("Unknown monster type: " + data[0]);
			}
		}

		br.close();
		return monsters;
	}
	
}
