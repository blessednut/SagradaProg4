package model;

import java.util.ArrayList;
import java.util.Random;

import DataBase.ToolCardDB;

public class ToolCardModel {
	private ToolCardDB tcDB;
	private int toolCardID;
	private ArrayList<String> cardNamesPerGame;
	
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
	public void setToolCardsInGame(int gameID) {
		ArrayList<Integer> cardIDsPerGame = tcDB.getCardIDsPerGame(gameID);
		cardNamesPerGame = new ArrayList<String>();
		for (int i = 0; i < cardIDsPerGame.size(); i++) {
			cardNamesPerGame.add(tcDB.getCardName(cardIDsPerGame.get(i)));
		}
		
	}
	
	public void removeDiceFromGameDie(int idgame, int dienumber, String color) {
		tcDB.removeDiceFromGameDie(idgame, dienumber, color);
	}
	
	public void insertToolCardIntoGameToolCardTable(int idtoolcard, int idgame) {
		tcDB.insertToolCardIntoGameToolCardTable(idtoolcard, idgame);
	}	

	public int getToolCardID() {
		return toolCardID;
	}
	
	public ArrayList<String> getCardNamesPerGame(){
		return cardNamesPerGame;
	}
	
	public ArrayList<String> getRoundtrackColors(int gameid){
		return tcDB.getRoundtrackColor(gameid);
	} 
	
}
	
	
