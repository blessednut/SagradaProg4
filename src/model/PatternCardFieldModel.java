 package model;

import DataBase.DiceDB;

public class PatternCardFieldModel {
	private final int idPatternCard;
	private final int x, y, value;
	private final String color;
	private GameDiceModel diceModel;
	
	private DiceDB diceDB;
	
	public PatternCardFieldModel (int idPatternCard, int x, int y, String color, int value) {
		this.idPatternCard = idPatternCard;
		this.x = x;
		this.y = y;
		this.color = color;
		this.value = value;
		
		this.diceDB = new DiceDB();
	}
	
	public void updatePlayerFrameField (int gameID, int playerID) {
		//BELANGRIJK wanneer het veld is veranderd naar een leegvak, wordt dit nog niet geupdate.
		if (diceModel != null) {
			diceDB.updatePlayerFrameField(gameID, diceModel, playerID, x, y);
		}
	}
	
	public int getIdPatternCard () {
		return this.idPatternCard;
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}
	
	public int getValue () {
		return this.value;
	}
	
	public String getColor () {
		return this.color;
	}

	public void setDice(GameDiceModel dice) {
		this.diceModel = dice;
	}
	
	public boolean isEmpty () {
		return (diceModel == null) ? true : false;
	}
	
	public GameDiceModel getDice () {
		return this.diceModel;
	}

	public void removeDice() {
		this.diceModel = null;
	}
	
	//Test
	public void loadDice (int idplayer) {
		removeDice();
		this.diceModel = diceDB.getDice(idplayer, x, y);
	}
}
