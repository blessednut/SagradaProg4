package model;

import java.util.Random;

import DataBase.Public_Objective_CardDB;

public class Public_Objective_Card_Model {
	private Public_Objective_CardDB pocDB;

	
	public Public_Objective_Card_Model() {
		pocDB = new Public_Objective_CardDB();
	}
	
	public String getCard() {
		String cardName;
		Random rand = new Random();
		int max = 10;
		int min = 1;
		int poCardID = rand.nextInt((max - min) + 1) + min;
		cardName=pocDB.getCardName(poCardID);
		System.out.println(cardName);
		return cardName;
		
		
	}

}
