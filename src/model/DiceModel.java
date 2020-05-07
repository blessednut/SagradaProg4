package model;

public class DiceModel {
	private int number;
	private String color;
	
	public DiceModel (int number, String color) {
		this.number = number;
		this.color = color;
	}
	
	public int getNumber () {
		return this.number;
	}
	
	public String getColor () {
		return this.color;
	}
}
