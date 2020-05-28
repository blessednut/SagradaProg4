package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameDiceModel {
	private int idgame;
	private int dieNumber;
	private StringProperty dieColor = new SimpleStringProperty(this, "dieColor", null);
	private IntegerProperty eyes = new SimpleIntegerProperty(this, "eyes", 0);
	private int roundtrack;
	private int roundID;
	private String color;
	
	public GameDiceModel (int idgame, int dieNumber, String dieColor, int eyes, int roundtrack, int roundID) {
		this.idgame = idgame;
		this.dieNumber = dieNumber;
		this.dieColor.set(dieColor);
		this.eyes.set(eyes);
		this.roundtrack = roundtrack;
		this.roundID = roundID;
		this.color = dieColor;
	}
	
	public String getColor() {
		return color;
	}

	public int getIdgame () {
		return this.idgame;
	}
	
	public int getDieNumber () {
		return this.dieNumber;
	}
	
	public final StringProperty colorProperty () {
		return dieColor;
	}
	
	public final IntegerProperty valueProperty () {
		return eyes;
	}

	public void setDieColor(StringProperty dieColor) {
		this.dieColor = dieColor;
	}

	public void setEyes(int eyes) {
		this.eyes.setValue(eyes);;
	}
	
	public int getRoundtrack() {
		return this.roundtrack;
	}
}
