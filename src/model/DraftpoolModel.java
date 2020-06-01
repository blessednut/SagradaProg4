package model;

import java.util.ArrayList;
import java.util.List;

import DataBase.DraftpoolDB;

public class DraftpoolModel {
	private ArrayList<GameDiceModel> draftpool;
	private DraftpoolDB daftPoolDB;
	
	public DraftpoolModel () {
		this.draftpool = new ArrayList<GameDiceModel>();
		this.daftPoolDB = new DraftpoolDB();
	}
	
	public void createDraftPool (int numPlayers) {
		
	}
	
	public void addDiceToDraftpool (GameDiceModel dice) {
		this.draftpool.add(dice);
	}
	
	public boolean draftpoolExists (int idgame, int roundID) {
		return daftPoolDB.draftpoolExists(idgame, roundID);
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
		}
	}

	public ArrayList<GameDiceModel> getDraftpool() {
		return draftpool;
	}

	public void moveToRoundtrack(int idgame, int roundtrack) {
		for (GameDiceModel dice : draftpool) {
			daftPoolDB.updateRountrack(idgame, dice.getDieNumber(), dice.colorProperty().getValue(), roundtrack);
		}
		
		draftpool.clear();
	}

	public List<GameDiceModel> loadDice(int idgame) {
		int roundID = daftPoolDB.getHighestRoundID(idgame);
		ArrayList<GameDiceModel> dice = daftPoolDB.loadDice(idgame, roundID);
		draftpool = dice;
		return dice;
	}
}
