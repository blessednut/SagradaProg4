package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class PatternCardFieldModel {
	private final int idPatternCard;
	private final int x, y, value;
	private final String color;
	
	public PatternCardFieldModel (int idPatternCard, int x, int y, String color, int value) {
		this.idPatternCard = idPatternCard;
		this.x = x;
		this.y = y;
		this.color = color;
		this.value = value;
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
	
//	public final IntegerProperty IdProperty () {
//		return new SimpleIntegerProperty(this, "idPatternCard", idPatternCard);
//	}
//	
//	public final IntegerProperty
	
	public final StringProperty colorProperty () {
		return new SimpleStringProperty(this, "color", color);
	}
	
	public final IntegerProperty valueProperty () {
		return new SimpleIntegerProperty(this, "value", value);
	}
}
