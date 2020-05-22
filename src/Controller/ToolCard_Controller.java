package Controller;

import java.util.ArrayList;
import java.util.Random;
import View.ToolCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.GameDiceModel;
import model.ToolCardModel;

public class ToolCard_Controller {
	private ToolCardModel tcm;
	private ArrayList<String> cards;
	private ArrayList<ToolCard> panes;
	private Alert tcInstruction;
	private Random rand = new Random();

	private GameController gameController;
	
	//test
	private WindowPatternSquareController sender;
	private WindowPatternSquareController receiver;

	public ToolCard_Controller(GameController gameController) {
		cards = new ArrayList<String>();
		panes = new ArrayList<ToolCard>();
		tcm = new ToolCardModel();
		this.gameController = gameController;
		getCards();

//		Elke toolcard krijgt krijgt nu mee welke methode ze moeten gebruiken als ze aangeklikt worden.
		panes.get(0).getButton().setOnAction(Event -> useCard(cards.get(0)));
		panes.get(1).getButton().setOnAction(Event -> useCard(cards.get(1)));
		panes.get(2).getButton().setOnAction(Event -> useCard(cards.get(2)));
	}

	private void getCards() {
//		String temp;
//		while(cards.size() < 3) {
//			boolean toolCardInGame = false;
//			temp = tcm.getToolCard();
//			System.out.println("CardName: " + temp);
//			for(int i = 0; i < cards.size(); i++) {
//				if(cards.get(i).equals(temp)) {
//					toolCardInGame = true;
//				}
//			}
//			if(!toolCardInGame) {
//				cards.add(temp);
//				panes.add(new ToolCard(temp, this));
//				tcm.insertToolCardIntoGameToolCardTable(tcm.getToolCardID(), gameController.getM_game().getGameId());
//			}
//		}
		cards.add("Flux Remover");
		cards.add("Grinding Stone");
		cards.add("Glazing Hammer");
		panes.add(new ToolCard("Flux Remover", this));
		panes.add(new ToolCard("Grinding Stone", this));
		panes.add(new ToolCard("Glazing Hammer", this));
	}

	public ArrayList<ToolCard> getPanes() {
		return panes;
	}

	public void useCard(String cardName) {
		if (gameController.getDraftpoolController().getSelectedDice() != null) {
			if (cardName.equals("Grozing Pliers")) {
				tcInstruction = new Alert(AlertType.INFORMATION,
						"Druk op YES om de waarde van de dobbelsteen te verhogen. \n Druk op NO om de waarde van de dobbelsteen te verlagen",
						ButtonType.YES, ButtonType.NO);
				tcInstruction.showAndWait();
				if (tcInstruction.getResult() == ButtonType.YES) {
					if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() < 6) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(
								(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue()
										+ 1));
					}
				} else if (tcInstruction.getResult() == ButtonType.NO) {
					if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() > 1) {
						gameController.getDraftpoolController().getSelectedDice().setEyes(
								(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue()
										- 1));
					}
				}
			} else if (cardName.equals("Flux Brush")) {
				if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() != null) {
					int max = 6;
					int min = 1;
					int newEyesOfDice = rand.nextInt((max - min) + 1) + min;
					gameController.getDraftpoolController().getSelectedDice().setEyes(newEyesOfDice);
					System.out.println(cardName + " ToolCard_Controller");
				} else {
					System.out.println("ToolCard_Controller 78: Dit werkt dus nie");
				}

			}

			else if(cardName.equals("Flux Remover")) {

				String color = gameController.getDraftpoolController().getSelectedDice().getColor();
				int dienumber = gameController.getDraftpoolController().getSelectedDice().getDieNumber();
				int idgame = gameController.getDraftpoolController().getSelectedDice().getIdgame();
				tcm.removeDiceFromGameDie(idgame, dienumber, color);
				gameController.getDraftpoolController().getDraftpoolControllerSquareController().removeDice();
				
				
				gameController.getDraftpoolController().getDraftpoolControllerSquareController().setDice(gameController.pickDiceFromBag());
				gameController.getDraftpoolController().getDraftpoolControllerSquareController().updateView();
				System.out.println(cardName + " ToolCard_Controller");
			}

			else if (cardName.equals("Glazing Hammer")) {

				gameController.getDraftpoolController().getDraftpoolModel().getDraftpool();
				for (int i = 0; i < gameController.getDraftpoolController().getDraftpoolModel().getDraftpool()
						.size(); i++) {
					int max = 6;
					int min = 1;
					int newEyesOfDice = rand.nextInt((max - min) + 1) + min;
					gameController.getDraftpoolController().getDraftpoolModel().getDraftpool().get(i)
							.setEyes(newEyesOfDice);
				}
				System.out.println(cardName + " ToolCard_Controller");
			} else if (cardName.equals("Lathekin")) {
				GameDiceModel dice = gameController.getPlayerController().getPatternCard().getSelectedSquare()
						.getDice();
				gameController.getPlayerController().getPatternCard().getSelectedSquare().setDice(null);
				gameController.getPlayerController().getPatternCard().setSelected(null);
				if (gameController.getPlayerController().getPatternCard().getSelectedSquare() != null) {
					gameController.getPlayerController().getPatternCard().getSelectedSquare().setDice(dice);
				}

				System.out.println(cardName + " ToolCard_Controller");
			}

			else if (cardName.equals("Lens Cutter")) {
				System.out.println(cardName + "ToolCard_Controller");
			} else if (cardName.equals("Grinding Stone")) {
				if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 1) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(6);
				} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 2) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(5);
				} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 3) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(4);
				} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 4) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(3);
				} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 5) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(2);
				} else if (gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 6) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(1);
				}
				System.out.println(cardName + " ToolCard_Controller");
			}

			else {

				System.out.println("ToolCard_Controller 89: Er is iets mis gegaan");
			}

		} else if (gameController.getPlayerController().getPatternCard().getSelectedSquare() != null) {
			if (cardName.equals("Copper Foil Burnisher")) {
				System.out.println(cardName + " ToolCard_Controller");

				// list van WindowPatternSquareController
				WindowPatternSquareController[][] fieldController = this.gameController.getPlayerController()
						.getPatternCard().getFieldController();
				
				for (int x = 0; x < fieldController.length; x++) {
					for (int y = 0; y < fieldController[x].length; y++) {
						fieldController[x][y].setToolCard(this);
					}
				}
				
				
				
//				this.resetField(fieldController);
//				
//				WindowPatternSquareController senderSquare = null;
//				WindowPatternSquareController receivingSquare = null;
//				
//				while (senderSquare == null) {
//					senderSquare = isSquareClicked(fieldController);
//				}
//				System.out.println("SENDER GESELECTEERD");
//				
//				this.resetField(fieldController);
//				
//				while (receivingSquare == null) {
//					receivingSquare = isSquareClicked(fieldController);
//				}
//				System.out.println("RECEIVER GESELECTEERD");
//				
//				System.out.println("HOERA! HET IS GELUKT!");
			} else if (cardName.equals("Eglomise Brush")) {
				System.out.println(cardName + " ToolCard_Controller");
			} else if (cardName.equals("Lathekin")) {
//DOET HET NIE
//				GameDiceModel dice = gameController.getPlayerController().getPatternCard().getSelectedSquare().getDice();
//				gameController.getPlayerController().getPatternCard().getSelectedSquare().setDice(null);
//				gameController.getPlayerController().getPatternCard().setSelected(null);
//				if(gameController.getPlayerController().getPatternCard().getSelectedSquare() != null) {
//					gameController.getPlayerController().getPatternCard().getSelectedSquare().setDice(dice);
//				}

				System.out.println(cardName + " ToolCard_Controller");
			} else if (cardName.equals("Running Pliers")) {
				System.out.println(cardName + " ToolCard_Controller");
			} else if (cardName.equals("Tap Wheel")) {
				System.out.println(cardName + " ToolCard_Controller");
			} else if (cardName.equals("Cork-backed Straightedge")) {
				System.out.println(cardName + " ToolCard_Controller");
			}

		} else {
			System.out.println("ToolCardController: dit vakje is leeg bosmongool");
		}

	}

//	private WindowPatternSquareController isSquareClicked(WindowPatternSquareController[][] fieldController) {
//		for (int x = 0; x < fieldController.length; x++) {
//			for (int y = 0; y < fieldController[x].length; y++) {
//				if (fieldController[x][y].isClicked()) {
//					return fieldController[x][y];
//				}
//			}
//		}
//		return null;
//	}
//
//	private void resetField(WindowPatternSquareController[][] fieldController) {
//		for (int x = 0; x < fieldController.length; x++) {
//			for (int y = 0; y < fieldController[x].length; y++) {
//				fieldController[x][y].resetClicked();
//			}
//		}
//	}
	
	public void setSquare (WindowPatternSquareController square) {
		if (this.sender == null) {
			this.sender = square;
			System.out.println("SENDER IS SET");
		} else {
			this.receiver = square;
			System.out.println("RECEIVER IS SET");
			this.swapDice();
		}
	}
	
	private void swapDice () {
		if(!gameController.getPlayerController().getPatternCard().getChosenCard().isWindowCardEmpty()) {
			if(this.sender.getDice() != null) {
				if(gameController.getPlayerController().getPatternCard().getChosenCard().hasDoubleSurroundingColor(this.receiver.getSquare().getX(), this.receiver.getSquare().getY(), this.receiver.getDice().getColor()) && gameController.getPlayerController().getPatternCard().getChosenCard().hasSurroundingDice(this.receiver.getSquare().getX(), this.receiver.getSquare().getY())) {
					if(this.sender.getSquare().getValue() == 0 || this.receiver.getSquare().getValue() == this.sender.getDice().valueProperty().getValue()) {
						
					}
				}
			}

		}else{
			if((this.receiver.getSquare().getX() == 1 || this.receiver.getSquare().getX() == 5) && (this.receiver.getSquare().getY() == 1 || this.receiver.getSquare().getY() == 4)) {
				
            }
		}
//		gameController.getPlayerController().getPatternCard().getChosenCard().hasDoubleSurroundingColor(x, y, color)
//		gameController.getPlayerController().getPatternCard().getChosenCard().hasSurroundingDice(x, y)
		System.out.println("SWAP SWAP!");
	}
}
