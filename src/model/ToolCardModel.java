package model;

import java.util.Random;

import DataBase.ToolCardDB;

public class ToolCardModel {
	private ToolCardDB tcDB;
	private int toolCardID;
	
	public ToolCardModel() {
		tcDB = new ToolCardDB();
	}

	public String getToolCard() {
		String cardName;
		Random rand = new Random();
		int max = 12;
		int min = 1;
		toolCardID = rand.nextInt((max - min) + 1) + min;
		cardName=tcDB.getCardName(toolCardID);
		return cardName;
	}
	
	public void removeDiceFromGameDie(int idgame, int dienumber, String color) {
		tcDB.removeDiceFromGameDie(idgame, dienumber, color);
	}
	
	public void insertToolCardIntoGameToolCardTable(int idtoolcard, int idgame) {
		System.out.println("ToolcardModel: dsfhgbsoilbdv");
		tcDB.insertToolCardIntoGameToolCardTable(idtoolcard, idgame);
	}	

	public int getToolCardID() {
		return toolCardID;
	}
	
}
	
	
