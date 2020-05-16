package Controller;

import model.DraftpoolModel2;
import model.GameDiceModel;

public class DraftpoolController2 {
	private DraftpoolModel2 draftpoolModel;
	private GameController controller;
	private DraftpoolSquareController[] draftpoolController;
	
	public DraftpoolController2 (GameController controller) {
		this.draftpoolModel = new DraftpoolModel2();
		this.controller = controller;
	}
	
	public void createDraftPool (int numPlayers) {
		//Calculate number of dice
		int numDice = ((numPlayers * 2) + 1);
		
		//Get all dice from the bag
		for (int i = 0; i < numDice; i++) {
			GameDiceModel die = this.controller.pickDiceFromBag();
			
			//Add dice to DraftpoolModel
			this.draftpoolModel.addDiceToDraftpool(die);
		}
		
		//this.draftpoolModel.createDraftPool(numPlayers);
	}
	
	public DraftpoolSquareController[] getDraftPool () {
		draftpoolController = new DraftpoolSquareController[9];
		
		for (int i = 0; i < draftpoolController.length; i++) {
			if (i < this.draftpoolModel.getDraftpoolSize()) {
				draftpoolController[i] = new DraftpoolSquareController(this, this.draftpoolModel.getGameDiceModel(i));
			} else {
				draftpoolController[i] = new DraftpoolSquareController();
			}
		}
		
		return draftpoolController;
	}
}
