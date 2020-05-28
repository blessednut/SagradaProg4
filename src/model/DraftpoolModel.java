package model;

import java.util.ArrayList;

import DataBase.DraftpoolDB;

public class DraftpoolModel {
	private ArrayList<GameDiceModel> draftpool;
	private DraftpoolDB con;
	
	public DraftpoolModel () {
		this.draftpool = new ArrayList<GameDiceModel>();
		this.con = new DraftpoolDB();
	}
	
	public void createDraftPool (int numPlayers) {
		
	}
	
	public void addDiceToDraftpool (GameDiceModel dice) {
		this.draftpool.add(dice);
	}
	
	//Wordt niet gebruikt?
	public GameDiceModel[] getGameDiceModelArray() {
		GameDiceModel[] gameDice = new GameDiceModel[draftpool.size()];
		
		for (int i = 0; i < gameDice.length; i++) {
			gameDice[i] = draftpool.get(i);
		}
		
		return gameDice;
	}
	
	public int getDraftpoolSize() {
		return this.draftpool.size();
	}
	
	public GameDiceModel getGameDiceModel (int index) {
		return this.draftpool.get(index);
	}
	
	public void removeDiceFromDraftpool(GameDiceModel dice) {
		if(draftpool.contains(dice)) {
			draftpool.remove(dice);
			//System.out.println("DraftpoolModel: Wordt er iets verwijderd");
		}
	}

	public ArrayList<GameDiceModel> getDraftpool() {
		return draftpool;
	}

	public void moveToRoundtrack(int idgame, int roundtrack) {
		//System.out.println("MOVE TO ROUNDTRACK DRAFTPOOL LENGTH = " + draftpool.size());
		for (GameDiceModel dice : draftpool) {
			//System.out.println("MOVE TO ROUNDTRACK = " + dice.getDieNumber());
			con.updateRountrack(idgame, dice.getDieNumber(), dice.colorProperty().getValue(), roundtrack);
		}
		
		draftpool.clear();
	}
}
