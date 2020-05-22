package model;

import java.util.ArrayList;

public class DraftpoolModel {
	private ArrayList<GameDiceModel> draftpool;
	
	public DraftpoolModel () {
		this.draftpool = new ArrayList<GameDiceModel>();
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
			System.out.println("DraftpoolModel: Wordt er iets verwijderd");
		}
	}

	public ArrayList<GameDiceModel> getDraftpool() {
		return draftpool;
	}
	
}
