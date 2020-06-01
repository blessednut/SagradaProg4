package Controller;

import java.util.ArrayList;
import java.util.Random;

import View.ToolCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.GameDiceModel;
import model.PatternCardModel;
import model.ToolCardModel;

public class ToolCard_Controller {
	private ToolCardModel tcm;
	private ArrayList<String> cards;
	private ArrayList<ToolCard> panes;
	private Alert tcInstruction;
	private Random rand = new Random();

	private GameController gameController;

	private WindowPatternSquareController sender;
	private WindowPatternSquareController receiver;
	private WindowPatternSquareController[][] fieldController;

	private String activeToolCard;
	private boolean useCardInTurn = false;
	
	private int counter = 0;

	public ToolCard_Controller(GameController gameController) {
		cards = new ArrayList<String>();
		panes = new ArrayList<ToolCard>();
		tcm = new ToolCardModel();
		this.gameController = gameController;		
	}
	
	

	public boolean isUseCardInTurn() {
		return useCardInTurn;
	}

	public void setUseCardInTurn(boolean useCardInTurn) {
		this.useCardInTurn = useCardInTurn;
	}



	public void getCards() {
		String temp;
		while(cards.size() < 3) {
			boolean toolCardInGame = false;
			temp = tcm.getToolCard();
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).equals(temp)) {
					toolCardInGame = true;
				}
			}
			if(!toolCardInGame) {
				cards.add(temp);
				tcm.insertToolCardIntoGameToolCardTable(tcm.getToolCardID(), gameController.getM_game().getGameId());
			}
		}

		

	}
	
	public void getCards(int gameID) {
		cards.clear();
		tcm.setToolCardsInGame(gameID);
		String temp = "";
		for (int i = 0; i < tcm.getCardNamesPerGame().size(); i++) {
			System.out.println("CARDS PER GAME = " + tcm.getCardNamesPerGame().size());
			temp = tcm.getCardNamesPerGame().get(i);
			cards.add(temp);
			panes.add(new ToolCard(temp, this));
		}

		if (tcm.getCardNamesPerGame().size() != 0) {
			panes.get(0).getButton().setOnAction(Event -> useCard(cards.get(0)));
			panes.get(1).getButton().setOnAction(Event -> useCard(cards.get(1)));
			panes.get(2).getButton().setOnAction(Event -> useCard(cards.get(2)));
			gameController.setToolCardsAdded(true);
		} else {
			gameController.setToolCardsAdded(false);
		}
	}

	public void useCard(String cardName) {
		if (!this.useCardInTurn) {
			this.useCardInTurn = true;
			if (gameController.getIsTurn()) {
			if (gameController.getDraftpoolController().getSelectedDice() != null) {
				if (cardName.equals("Grozing Pliers")) {
					activeToolCard = cardName;
					tcInstruction = new Alert(AlertType.INFORMATION,
							"Druk op YES om de waarde van de dobbelsteen te verhogen. \n Druk op NO om de waarde van de dobbelsteen te verlagen",
							ButtonType.YES, ButtonType.NO);
					tcInstruction.showAndWait();
					if (tcInstruction.getResult() == ButtonType.YES) {
						if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() < 6) {
							gameController.getDraftpoolController().getSelectedDice().setEyes(
									(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue()
											+ 1));
							this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
						}
					} else if (tcInstruction.getResult() == ButtonType.NO) {
						if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() > 1) {
							gameController.getDraftpoolController().getSelectedDice().setEyes(
									(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue()
											- 1));
							this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
						}
					}
				} else if (cardName.equals("Flux Brush")) {
					activeToolCard = cardName;
					if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() != null) {
						int max = 6;
						int min = 1;
						int newEyesOfDice = rand.nextInt((max - min) + 1) + min;
						gameController.getDraftpoolController().getSelectedDice().setEyes(newEyesOfDice);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					} else {
						System.out.println("ToolCard_Controller: Dit werkt dus nie");
					}

				} else if (cardName.equals("Flux Remover")) {
					activeToolCard = cardName;
					String color = gameController.getDraftpoolController().getSelectedDice().getColor();
					int dienumber = gameController.getDraftpoolController().getSelectedDice().getDieNumber();
					int idgame = gameController.getDraftpoolController().getSelectedDice().getIdgame();
					tcm.removeDiceFromGameDie(idgame, dienumber, color);
					gameController.getDraftpoolController().getDraftpoolControllerSquareController().removeDice();

					gameController.getDraftpoolController().getDraftpoolControllerSquareController()
							.setDice(gameController.pickDiceFromBag());
					gameController.getDraftpoolController().getDraftpoolControllerSquareController().updateView();
				} else if (cardName.equals("Glazing Hammer")) {
					activeToolCard = cardName;
					gameController.getDraftpoolController().getDraftpoolModel().getDraftpool();
					for (int i = 0; i < gameController.getDraftpoolController().getDraftpoolModel().getDraftpool()
							.size(); i++) {
						int max = 6;
						int min = 1;
						int newEyesOfDice = rand.nextInt((max - min) + 1) + min;
						gameController.getDraftpoolController().getDraftpoolModel().getDraftpool().get(i)
								.setEyes(newEyesOfDice);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getDraftpoolModel().getDraftpool().get(i), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					}
				} else if (cardName.equals("Lens Cutter")) {
					activeToolCard = cardName;
					
				} else if (cardName.equals("Grinding Stone")) {
					activeToolCard = cardName;
					if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 1) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(6);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 2) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(5);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 3) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(4);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 4) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(3);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 5) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(2);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 6) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(1);
						this.tcm.setNewEyes(gameController.getDraftpoolController().getSelectedDice(), gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue());
					}
				}else {
					System.out.println("ToolCard_Controller: Er is iets mis gegaan");
				}

			} else if (gameController.getPlayerController().getPatternCard().getSelectedSquare() != null) {
				if (cardName.equals("Eglomise Brush")) {
					activeToolCard = cardName;

					// list van WindowPatternSquareController
					this.fieldController = this.gameController.getPlayerController()
							.getPatternCard().getFieldController();

					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].setToolCard(this);
						}
					}


				} else if (cardName.equals("Copper Foil Burnisher")) {
					activeToolCard = cardName;
					this.fieldController = this.gameController.getPlayerController()
							.getPatternCard().getFieldController();

					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].setToolCard(this);
						}
					}

				} else if (cardName.equals("Lathekin")) {
					activeToolCard = cardName;
					this.fieldController = this.gameController.getPlayerController().getPatternCard().getFieldController();
					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].setToolCard(this);
						}
					}
					
					
				} else if (cardName.equals("Running Pliers")) {
					activeToolCard = cardName;
				} else if (cardName.equals("Tap Wheel")) {
					activeToolCard = cardName;
					this.fieldController = this.gameController.getPlayerController().getPatternCard().getFieldController();
					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].setToolCard(this);
						}
					}
					
				} else if (cardName.equals("Cork-backed Straightedge")) {
					activeToolCard = cardName;
					this.fieldController = this.gameController.getPlayerController()
							.getPatternCard().getFieldController();

					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].setToolCard(this);
						}
					}
				}

			} else {
				System.out.println("ToolCardController: dit vakje is leeg bosmongool");
			}
			}
		}
		
	}

	public void setSquare(WindowPatternSquareController square, String cardName) {
		if (this.sender == null) {
			this.sender = square;
		} else {
			this.receiver = square;
			this.swapDice(cardName);
		}
	}

	private void swapDice(String cardName) {

		if (cardName.equals("Eglomise Brush")) {
			if (isValidEglomiseBrushPlacement()) {
				this.receiver.setDice(this.sender.getDice());
				this.sender.removeDice();

				for (int x = 0; x < fieldController.length; x++) {
					for (int y = 0; y < fieldController[x].length; y++) {
						fieldController[x][y].removeToolCard();
					}
				}
				this.sender = null;
				this.receiver = null;
			} else {
				this.sender = null;
				this.receiver = null;
			}
		} else if (cardName.equals("Copper Foil Burnisher")) {
			if (isValidCopperFoilBurnisherPlacement()) {
				this.receiver.setDice(this.sender.getDice());
				this.sender.removeDice();

				for (int x = 0; x < fieldController.length; x++) {
					for (int y = 0; y < fieldController[x].length; y++) {
						fieldController[x][y].removeToolCard();
					}
				}
				this.sender = null;
				this.receiver = null;
			} else {
				this.sender = null;
				this.receiver = null;
			}
		} else if (cardName.equals("Cork-backed Straightedge")) {
			if (isCorkBackedStraightedgePlacement()) {
				this.receiver.setDice(this.sender.getDice());
				this.sender.removeDice();

				for (int x = 0; x < fieldController.length; x++) {
					for (int y = 0; y < fieldController[x].length; y++) {
						fieldController[x][y].removeToolCard();
					}
				}
				this.sender = null;
				this.receiver = null;
			} else {
				this.sender = null;
				this.receiver = null;
			}
		} else if (cardName.equals("Lathekin")) {
			if (isValidLathekinPlacement()) {
				this.receiver.setDice(this.sender.getDice());
				this.sender.removeDice();
				
				if (this.counter == 1) {
					this.counter = 0;
					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].removeToolCard();
						}
					}
					this.sender = null;
					this.receiver = null;
				}else {
					this.counter++;
					this.sender = null;
					this.receiver = null;
				}
				
			} else {
				this.sender = null;
				this.receiver = null;
			}
		}else if (cardName.equals("Tap Wheel")) {
			if (isValidTapWheelPlacement()) {
				this.receiver.setDice(this.sender.getDice());
				this.sender.removeDice();
				
				if (this.counter == 1) {
					this.counter = 0;
					for (int x = 0; x < fieldController.length; x++) {
						for (int y = 0; y < fieldController[x].length; y++) {
							fieldController[x][y].removeToolCard();
						}
					}
					this.sender = null;
					this.receiver = null;
				}else {
					this.counter++;
					this.sender = null;
					this.receiver = null;
				}
				
			} else {
				this.sender = null;
				this.receiver = null;
			}
		}
	}

	private boolean isValidEglomiseBrushPlacement() {
		GameDiceModel dice = this.sender.getDice();
		int receiverX = this.receiver.getSquare().getX();
		int receiverY = this.receiver.getSquare().getY();

		if (receiverX == this.sender.getSquare().getX() && receiverY == this.sender.getSquare().getY()) {
			return false;
		}

		PatternCardModel chosenCard = gameController.getPlayerController().getPatternCard().getChosenCard();

		if (this.sender.getDice() != null) {
			if (!chosenCard.isWindowCardEmpty()) {
				if (chosenCard.hasDoubleSurroundingColor(receiverX, receiverY, dice.getColor())
						&& chosenCard.hasSurroundingDice(receiverX, receiverY)) {
					if (this.receiver.getSquare().getValue() == 0
							|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
						return true;
					}
				}
			} else {
				// TODO: remove magic numbers
				if ((receiverX == 1 || receiverX == 5) && (receiverY == 1 || receiverY == 4)) {
					if (this.receiver.getSquare().getValue() == 0
							|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isValidCopperFoilBurnisherPlacement() {
		GameDiceModel dice = this.sender.getDice();
		int receiverX = this.receiver.getSquare().getX();
		int receiverY = this.receiver.getSquare().getY();

		if (receiverX == this.sender.getSquare().getX() && receiverY == this.sender.getSquare().getY()) {
			return false;
		}

		PatternCardModel chosenCard = gameController.getPlayerController().getPatternCard().getChosenCard();

		if (this.sender.getDice() != null) {
			if (!chosenCard.isWindowCardEmpty()) {
				if (chosenCard.hasDoubleSurroundingColor(receiverX, receiverY, dice.getColor())
						&& chosenCard.hasSurroundingDice(receiverX, receiverY)) {
					if (this.receiver.getSquare().getColor() == null
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						return true;
					}
				}
			} else {
				if ((receiverX == 1 || receiverX == 5) && (receiverY == 1 || receiverY == 4)) {
					if (this.receiver.getSquare().getColor().equals(null)
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isCorkBackedStraightedgePlacement() {
		GameDiceModel dice = this.sender.getDice();
		int receiverX = this.receiver.getSquare().getX();
		int receiverY = this.receiver.getSquare().getY();

		if (receiverX == this.sender.getSquare().getX() && receiverY == this.sender.getSquare().getY()) {
			return false;
		}

		PatternCardModel chosenCard = gameController.getPlayerController().getPatternCard().getChosenCard();

		if (this.sender.getDice() != null) {
			if (!chosenCard.isWindowCardEmpty()) {
				if (chosenCard.hasDoubleSurroundingColor(receiverX, receiverY, dice.getColor())) {
					if (this.receiver.getSquare().getColor() == null
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						if (this.receiver.getSquare().getValue() == 0
								|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
							return true;
						}
					}
				}
			} else {
				// TODO: remove magic numbers
				if ((receiverX == 1 || receiverX == 5) && (receiverY == 1 || receiverY == 4)) {
					if (this.receiver.getSquare().getColor().equals(null)
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						if (this.receiver.getSquare().getValue() == 0
								|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean isValidLathekinPlacement() {
		GameDiceModel dice = this.sender.getDice();
		int receiverX = this.receiver.getSquare().getX();
		int receiverY = this.receiver.getSquare().getY();

		if (receiverX == this.sender.getSquare().getX() && receiverY == this.sender.getSquare().getY()) {
			return false;
		}

		PatternCardModel chosenCard = gameController.getPlayerController().getPatternCard().getChosenCard();

		if (this.sender.getDice() != null) {
			if (!chosenCard.isWindowCardEmpty()) {
				if (chosenCard.hasDoubleSurroundingColor(receiverX, receiverY, dice.getColor())
						&& chosenCard.hasSurroundingDice(receiverX, receiverY)) {
					if (this.receiver.getSquare().getColor() == null
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						if (this.receiver.getSquare().getValue() == 0
								|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
							return true;
						}
					}
				}
			} else {
				// TODO: remove magic numbers
				if ((receiverX == 1 || receiverX == 5) && (receiverY == 1 || receiverY == 4)) {
					if (this.receiver.getSquare().getColor().equals(null)
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						if (this.receiver.getSquare().getValue() == 0
								|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean isValidTapWheelPlacement() {
		ArrayList<String> roundtrackColors = this.tcm.getRoundtrackColors(this.gameController.getM_game().getGameId());
		GameDiceModel dice = this.sender.getDice();
		int receiverX = this.receiver.getSquare().getX();
		int receiverY = this.receiver.getSquare().getY();

		if (receiverX == this.sender.getSquare().getX() && receiverY == this.sender.getSquare().getY()) {
			return false;
		}

		PatternCardModel chosenCard = gameController.getPlayerController().getPatternCard().getChosenCard();

		if (this.sender.getDice() != null) {
			if (!chosenCard.isWindowCardEmpty()) {
				if (chosenCard.hasDoubleSurroundingColor(receiverX, receiverY, dice.getColor())
						&& chosenCard.hasSurroundingDice(receiverX, receiverY)) {
					if (this.receiver.getSquare().getColor() == null
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						if (this.receiver.getSquare().getValue() == 0
								|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
							if(roundtrackColors.contains(dice.getColor())) {
								return true;
							}
						}
					}
				}
			} else {
				// TODO: remove magic numbers
				if ((receiverX == 1 || receiverX == 5) && (receiverY == 1 || receiverY == 4)) {
					if (this.receiver.getSquare().getColor().equals(null)
							|| this.receiver.getSquare().getColor().equals(dice.colorProperty().getValue())) {
						if (this.receiver.getSquare().getValue() == 0
								|| this.receiver.getSquare().getValue() == dice.valueProperty().getValue()) {
							if(roundtrackColors.contains(dice.getColor())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public String getActiveToolCard() {
		return activeToolCard;
	}
	public ArrayList<ToolCard> getPanes() {
		return panes;
	}
	
	public ToolCardModel getTCM() {
		return tcm;
	}
	public Boolean getEmpty(int gameid) {
		return tcm.getEmpty(gameid);
		
	}

}
