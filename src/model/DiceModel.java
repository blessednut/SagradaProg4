package model;

import java.util.ArrayList;

import Controller.GameController;
import DataBase.DiceDB;

public class DiceModel {
	private ArrayList<GameDiceModel> dice;
	private GameController controller;
	private DiceDB con;
	
	public DiceModel (GameController controller) {
		this.controller = controller;
		this.dice = new ArrayList<GameDiceModel>();
		this.con = new DiceDB();
		
		this.importGameDice(controller.getM_game().getGameId());
	}
	
	public void addGameDice (GameDiceModel gameDice) {
		this.dice.add(gameDice);
	}
	
	public ArrayList<GameDiceModel> getBag () {
		return this.con.getBag(controller.getM_game().getGameId());
	}
	
	public void addDice (GameDiceModel gameDice, int roundID) {
		this.dice.add(gameDice);
		
		//insert Query
		this.con.insertGameDice(gameDice, roundID);
	}
	
	private void importGameDice (int gameID) {
		this.dice = con.importGameDice(gameID);
	}
	
	//Maken update Query
}
