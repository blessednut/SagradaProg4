package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatternCardModel {
	private final int idPatternCard;
	//private final StringProperty name;
	private String name;
	private final int difficulty;
	private final boolean standard;

	public PatternCardModel(int idPatternCard, String name, int difficulty, boolean standard) {
		this.idPatternCard = idPatternCard;
		//this.name = new SimpleStringProperty(this, "name", name);
		this.name = name;
		this.difficulty = difficulty;
		this.standard = standard;
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
}
