package model;

import Controller.PatternCardController;
import DataBase.WindowPatternDB;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatternCardModel {
	private PatternCardController controller;
	
	private final int idPatternCard;
	private String name;
	private int difficulty;
	private boolean standard;
	
	private PatternCardFieldModel[][] field;
	
	private WindowPatternDB con;

	public PatternCardModel(PatternCardController controller, int idPatternCard) {
		this.controller = controller;
		this.idPatternCard = idPatternCard;
		
		this.con = new WindowPatternDB();
		this.importValues(); 
		this.importField();
	}
	
	public void updatePlayerFrameField (int gameID, int playerID) {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				field[x][y].updatePlayerFrameField(gameID, playerID);
			}
		}
	}
	
	public boolean hasDoubleSurroundingColorAndValue (int x, int y, String color, int value) {
		this.printField();
		//Check rechts
		x = x - 1;
		y = y - 1;
		int tempX = x + 1;
		int tempY = y;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value || field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				System.out.println("FALSE");
				return false;
			}
		}
		
		//Check onder
		tempX = x;
		tempY = y + 1;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value || field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				System.out.println("FALSE");
				return false;
			}
		}
		
		//Check links
		tempX = x - 1;
		tempY = y;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value || field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				System.out.println("FALSE");
				return false;
			}
		}
		
		//Check rechts
		tempX = x;
		tempY = y - 1;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value || field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				System.out.println("FALSE");
				return false;
			}
		}
		
		System.out.println("TRUE");
		System.out.println("Kleur = " + color);
		System.out.println("Value = " + value);
		return true;
	}
	
	public boolean isWindowCardEmpty () {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				if (!field[x][y].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean hasSurroundingDice (int x, int y) {
		int tempX;
		int tempY;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				//System.out.println("i = " + i + "  j = " + j);
				tempX = x + i - 1;
				tempY = y + j - 1;
				//System.out.println("x = " + tempX + "  y = " + tempY);
				if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
					//System.out.println("ER IS EEN SURROUNDING DICE!");
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isFieldPos (int x, int y) {
		if (x >= 0 && x < 5) {
			if (y >= 0 && y < 4) {
				return true;
			}
		}
		return false;
	}

	public int getIdPatternCard() {
		return this.idPatternCard;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public boolean getStandard() {
		return this.standard;
	}

	//TODO PROPERTIES WEGHALEN
	public final StringProperty nameProperty() {
		return new SimpleStringProperty(this, "name", name);
	}

	public final IntegerProperty tokenAmount() {
		return new SimpleIntegerProperty(this, "difficulty", difficulty);
	}
	
	public void importValues() {
		this.name = con.getName(this.idPatternCard);
		this.difficulty = con.getDifficulty(this.getDifficulty());
		
		//Check uit database
		this.standard = true;
	}
	
	public void importField () {
		field = new PatternCardFieldModel[5][4];
		this.field = con.getField(this.idPatternCard);
	}
	
	public void makePlayerFrameField () {
		int playerID = controller.getPlayerController().getPlayerID();
		
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				con.makePlayerFrameField(playerID, x+1, y+1);
			}
		}
		
//		System.out.println("PatternCardModel:");
//		System.out.println("PlayerID = " + playerID);
	}
	
	public PatternCardFieldModel[][] getField () {
		return this.field;
	}
	
	private void printField () {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[y].length; y++) {
				if (!field[x][y].isEmpty()) {
					GameDiceModel fieldDice = field[x][y].getDice();
					System.out.println("FieldDice = " + fieldDice.getDieNumber());
				}
			}
		}
	}
}
