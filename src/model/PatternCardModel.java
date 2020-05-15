package model;

import DataBase.WindowPatternDB;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatternCardModel {
	//private int player
	private final int idPatternCard;
	private String name;
	private int difficulty;
	private boolean standard;
	
	private PatternCardFieldModel[][] field;
	
	private WindowPatternDB con;

	public PatternCardModel(int idPatternCard) {
		this.idPatternCard = idPatternCard;
		
		this.con = new WindowPatternDB();
		this.importValues(); 
		this.importField();
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
	
	public PatternCardFieldModel[][] getField () {
		return this.field;
	}
}
