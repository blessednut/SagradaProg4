package model;

import java.util.ArrayList;

public class DraftpoolModel2 {
	private ArrayList<GameDiceModel> draftpool;
	
	public DraftpoolModel2 () {
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
}
