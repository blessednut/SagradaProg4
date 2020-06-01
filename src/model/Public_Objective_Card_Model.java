package model;

import java.util.ArrayList;
import java.util.Random;

import DataBase.Public_Objective_CardDB;

public class Public_Objective_Card_Model {
	private Public_Objective_CardDB pocDB;
	private int poCardID;
	private ArrayList<String> cardNamesPerGame;

	
	public Public_Objective_Card_Model() {
		pocDB = new Public_Objective_CardDB();
	}
	
	public String getCard() {
		String cardName;
		Random rand = new Random();
		int max = 10;
		int min = 1;
		poCardID = rand.nextInt((max - min) + 1) + min;
		cardName=pocDB.getCardName(poCardID);
		return cardName;
		
		
	}
	public void setCardsInGame(int gameID) {
		ArrayList<Integer> cardIDsPerGame = pocDB.getCardIDsPerGame(gameID);
		cardNamesPerGame = new ArrayList<String>();
		for(int i = 0; i < cardIDsPerGame.size(); i++) {
			cardNamesPerGame.add(pocDB.getCardName(cardIDsPerGame.get(i)));
			}
	}
	
	public void insertPublicObjectiveCards(int gameID) {
		pocDB.insertPublicOC(gameID, poCardID);
	}
	
	public ArrayList<String> getCardNamesPerGame(){
		return cardNamesPerGame;
	}
	public Boolean getEmpty(int gameid) {
		return pocDB.getEmpty(gameid);
	}

	public String[] getNames (int idgame) {
		return pocDB.getNames(idgame);
	}
}

