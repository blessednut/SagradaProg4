package model;

import Controller.PatternCardController;
import DataBase.WindowPatternDB;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatternCardModel {
	private PatternCardController patternCardController;
	
	private int idPatternCard;
	private String name;
	private int difficulty;
	private boolean standard;
	
	private PatternCardFieldModel[][] field;
	
	private WindowPatternDB con;

	public PatternCardModel(PatternCardController patternCardController, int idPatternCard) {
		this.patternCardController = patternCardController;
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
	
	public boolean hasDoubleSurroundingColor (int x, int y, String color) {
		this.printField();
		//Check rechts
		x = x - 1;
		y = y - 1;
		int tempX = x + 1;
		int tempY = y;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				return false;
			}
		}
		
		//Check onder
		tempX = x;
		tempY = y + 1;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				return false;
			}
		}
		
		//Check links
		tempX = x - 1;
		tempY = y;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				return false;
			}
		}
		
		//Check rechts
		tempX = x;
		tempY = y - 1;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().colorProperty().getValue().equals(color)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean hasDoubleSurroundingValue (int x, int y, int value) {
		this.printField();
		//Check rechts
		x = x - 1;
		y = y - 1;
		int tempX = x + 1;
		int tempY = y;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value) {
				return false;
			}
		}
		
		//Check onder
		tempX = x;
		tempY = y + 1;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value) {
				return false;
			}
		}
		
		//Check links
		tempX = x - 1;
		tempY = y;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value) {
				return false;
			}
		}
		
		//Check rechts
		tempX = x;
		tempY = y - 1;
		if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
			if (field[tempX][tempY].getDice().valueProperty().getValue() == value) {
				return false;
			}
		}
		
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
				////System.out.println("i = " + i + "  j = " + j);
				tempX = x + i - 1;
				tempY = y + j - 1;
				////System.out.println("x = " + tempX + "  y = " + tempY);
				if (isFieldPos(tempX, tempY) && !field[tempX][tempY].isEmpty()) {
					////System.out.println("ER IS EEN SURROUNDING DICE!");
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


	public final StringProperty nameProperty() {
		return new SimpleStringProperty(this, "name", name);
	}

	public final IntegerProperty tokenAmount() {
		return new SimpleIntegerProperty(this, "difficulty", difficulty);
	}
	
	public void importValues() {
		this.name = con.getName(this.idPatternCard);
		this.difficulty = con.getDifficulty(this.idPatternCard);
		
		//Check uit database
		this.standard = true;
	}
	
	public void importField () {
		field = new PatternCardFieldModel[5][4];
		this.field = con.getField(this.idPatternCard);
	}
	
	public void makePlayerFrameField () {
		int playerID = patternCardController.getPlayerController().getPlayerID();
		
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				con.makePlayerFrameField(playerID, x+1, y+1);
			}
		}
		

	}
	
	public PatternCardFieldModel[][] getField () {
		return this.field;
	}
	
	private void printField () {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[y].length; y++) {
				if (!field[x][y].isEmpty()) {
					GameDiceModel fieldDice = field[x][y].getDice();
				}
			}
		}
	}
	
	public void addToPatternCardOption(int idPlayer, int PatternCardID) {
		this.con.addToPatternCardOption(idPlayer, PatternCardID);
	}
	
	public int getPlayerID (int gameID, String username) {
		return this.con.getPlayerID(gameID, username);
	}
	
	public void updatePatternCardIDToPlayer(int idplayer, int idpatterncard) {
		this.con.updatePatternCardIDToPlayer(idplayer, idpatterncard);
	}
	
	public void loadPatterncardFieldModel () {
		for (int x = 0; x < this.field.length; x++) {
			for (int y = 0; y < this.field[x].length; y++) {
				field[x][y].loadDice(patternCardController.getPlayerController().getPlayerID());
			}
		}
	}

	public GameDiceModel loadDice(int idplayer, int x, int y) {
		return this.con.loadDice(idplayer, x, y);
	}
}
