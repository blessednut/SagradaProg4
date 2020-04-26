package model;

public class DiceModel {
	private int idgame;
	private int dienumber;
	private String diecolor;
	private int eyes;
	private int roundtrack;
	private int roundID;
	
	public DiceModel (int idgame, int dienumber, String diecolor, int eyes, int roundtrack, int roundID) {
		this.idgame = idgame;
		this.dienumber = dienumber;
		this.diecolor = diecolor;
		this.eyes = eyes;
		this.roundtrack = roundtrack;
		this.roundID = roundID;
	}
	
	public int getIdgame () {
		return this.idgame;
	}
}
