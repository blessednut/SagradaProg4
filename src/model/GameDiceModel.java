package model;

public class GameDiceModel {
	private int idgame;
	private int dieNumber;
	private String dieColor;
	private int eyes;
	private int roundtrack;
	private int roundID;
	
	public GameDiceModel (int idgame, int dieNumber, String dieColor, int eyes, int roundtrack, int roundID) {
		this.idgame = idgame;
		this.dieNumber = dieNumber;
		this.dieColor = dieColor;
		this.eyes = eyes;
		this.roundtrack = roundtrack;
		this.roundID = roundID;
	}
	
	public int getIdgame () {
		return this.idgame;
	}
	
	public int getDieNumber () {
		return this.dieNumber;
	}
	
	public String getDieColor () {
		return this.dieColor;
	} 
}
