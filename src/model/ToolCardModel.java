package model;

import java.util.Random;

import DataBase.ToolCardDB;

public class ToolCardModel {
	private ToolCardDB tcDB;
	
	public ToolCardModel() {
		tcDB = new ToolCardDB();
	}
	
	public String getCardName() {
		String cardName;
		Random rand = new Random();
		int max = 12;
		int min = 1;
		int toolCardID = rand.nextInt((max - min) + 1) + min;
		cardName=tcDB.getCardName(toolCardID);
		return cardName;
	}
	
	public void removeDiceFromGameDie(int idgame, int dienumber, String color) {
		tcDB.removeDiceFromGameDie(idgame, dienumber, color);
	}
}
	
	
