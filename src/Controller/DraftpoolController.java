package Controller;

import model.DraftpoolModel;
import model.GameDiceModel;

public class DraftpoolController {
	private DraftpoolModel draftpoolModel;
	private GameController controller;
	private DraftpoolSquareController[] draftpoolController;
	private DraftpoolSquareController selectedSquare;
	private GameDiceModel selectedDice;

	public DraftpoolController(GameController controller) {
		this.draftpoolModel = new DraftpoolModel();
		this.controller = controller;
	}

	public void createDraftPool(int numPlayers, int roundID) {
		if (!draftpoolModel.draftpoolExists(controller.getM_game().getGameId(), roundID)) {
			// Calculate number of dice
			int numDice = ((numPlayers * 2) + 1);

			// Get all dice from the bag
			for (int i = 0; i < numDice; i++) {
				GameDiceModel die = this.controller.pickDiceFromBag();

				// Add dice to DraftpoolModel
				this.draftpoolModel.addDiceToDraftpool(die);
			}
		} else {
			//add dice to gamedice
			loadDice(controller.getM_game().getGameId());
		}
	}
	
	public void loadDice (int gameID) {
		this.draftpoolModel.loadDice(gameID);
	}

	public DraftpoolSquareController[] getDraftPool() {
		draftpoolController = new DraftpoolSquareController[9];

		for (int i = 0; i < draftpoolController.length; i++) {
			if (i < this.draftpoolModel.getDraftpoolSize()) {
				draftpoolController[i] = new DraftpoolSquareController(controller, this,
						this.draftpoolModel.getGameDiceModel(i));
			} else {
				draftpoolController[i] = new DraftpoolSquareController();
			}
		}

		return draftpoolController;
	}

	public void setSelectedDice(GameDiceModel dice, DraftpoolSquareController SquareController) {
		this.selectedDice = dice;
		this.selectedSquare = SquareController;

		if (this.controller.placeDice(dice)) {
			SquareController.removeDice();
			draftpoolModel.removeDiceFromDraftpool(dice);
			selectedDice = null;
		}
	}

	public GameDiceModel getSelectedDice() {
		return selectedDice;
	}

	public DraftpoolSquareController getDraftpoolControllerSquareController() {
		return selectedSquare;
	}

	public DraftpoolModel getDraftpoolModel() {
		return draftpoolModel;
	}

	public void moveToRoundtrack(int roundtrack) {
		this.draftpoolModel.moveToRoundtrack(controller.getM_game().getGameId(), roundtrack);

		for (DraftpoolSquareController SquareController : draftpoolController) {
			SquareController.removeDice();
		}
	}
}
