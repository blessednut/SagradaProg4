package model;

import java.util.ArrayList;
import java.util.List;

import DataBase.StatisticDB;

public class StatisticModel {
	private String playerName;
	private boolean isASC;
	
	private StatisticDB SDB;
	
	public StatisticModel () {
		this.isASC = false;
		this.SDB = new StatisticDB ();
	}
	
//	public ArrayList<String> getRankList (boolean isASC) {
//		return SDB.getRankList(isASC);
//	}

	public void setASC(boolean isASC) {
		this.isASC = isASC;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getWins() {
		return SDB.getWins(playerName);
	}

	public int getLosses() {
		return SDB.getLosses(playerName);
	}

	public int getHighestScore() {
		return SDB.getHighestScore(playerName);
	}

	public String getMostPlacedColor() {
		return SDB.getMostPlacedColor(playerName);
	}

	public int getMostPlacedValue() {
		return SDB.getMostPlacedValue(this.playerName);
	}

	public int getNumOpponents() {
		return SDB.getNumOpponents(this.playerName);
	}
	
	public boolean usernameExists (String username) {
		if (SDB.usernameExists(username)) {
			this.playerName = username;
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<String> getNames(){
		return SDB.getAllUsernames();
	}
	
	public ArrayList<String> searchNamesWithWins(){
		return SDB.searchNamesWithWins();
	}
	
	public ArrayList<Integer> searchAmountOfWins(){
		return SDB.searchAmountOfWins();
	}
}
