package model;

import java.util.ArrayList;
import java.util.List;

import DataBase.StatisticDB;

public class StatisticModel {
	private String playerName;
	private int wins;
	private int losses;
	private int highestScore;
	private String mostPlacedColor;
	private int mostPlacedValue;
	private int numOpponents;
	
	private StatisticDB con;
	
	public StatisticModel () {
		System.out.println();
		this.con = new StatisticDB ();
	}
	
	public ArrayList<String> getRankList (boolean isASC) {
		return con.getRankList(isASC);
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getWins() {
		return wins = con.getWins(playerName);
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses = con.getLosses(playerName);
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getHighestScore() {
		return highestScore = con.getHighestScore(playerName);
	}

	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}

	public String getMostPlacedColor() {
		return mostPlacedColor = con.getMostPlacedColor(playerName);
	}

	public void setMostPlacedColor(String mostPlacedColor) {
		this.mostPlacedColor = mostPlacedColor;
	}

	public int getMostPlacedValue() {
		return mostPlacedValue = con.getMostPlacedValue(this.playerName);
	}

	public void setMostPlacedValue(int mostPlacedValue) {
		this.mostPlacedValue = mostPlacedValue;
	}

	public int getNumOpponents() {
		return con.getNumOpponents(this.playerName);
	}

	public void setNumOpponents(int numOpponents) {
		this.numOpponents = numOpponents;
	}
	
	public boolean usernameExists (String username) {
		if (con.usernameExists(username)) {
			this.playerName = username;
			return true;
		} else {
			return false;
		}
	}
}
