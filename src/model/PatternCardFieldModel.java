 package model;

import DataBase.DBCon;
import DataBase.DiceDB;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatternCardFieldModel {
	private final int idPatternCard;
	private final int x, y, value;
	private final String color;
	private GameDiceModel dice;
	
	//TODO: query verplaatsen naar andere database klasse
	private DiceDB con;
	
	public PatternCardFieldModel (int idPatternCard, int x, int y, String color, int value) {
		this.idPatternCard = idPatternCard;
		this.x = x;
		this.y = y;
		this.color = color;
		this.value = value;
		
		this.con = new DiceDB();
	}
	
	public void updatePlayerFrameField (int gameID, int playerID) {
		//BELANGRIJK wanneer het veld is veranderd naar een leegvak, wordt dit nog niet geupdate.
		if (dice != null) {
			con.updatePlayerFrameField(gameID, dice, playerID, x, y);
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
		this.dice = dice;
	}
	
	public boolean isEmpty () {
		return (dice == null) ? true : false;
	}
	
	public GameDiceModel getDice () {
		return this.dice;
	}
}
