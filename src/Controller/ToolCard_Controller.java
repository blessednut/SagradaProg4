package Controller;

import java.util.ArrayList;
import java.util.Random;
import View.ToolCard;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.ToolCardModel;

public class ToolCard_Controller {
	private ToolCardModel tcm;
	private ArrayList<String> cards;
	private ArrayList<ToolCard> panes;
	private Alert tcInstruction;
	private Random rand = new Random();
	
	private GameController gameController;

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
		String temp;
		while(cards.size() < 3) {
			boolean toolCardInGame = false;
			temp = tcm.getToolCard();
			System.out.println("CardName: " + temp);
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).equals(temp)) {
					toolCardInGame = true;
				}
			}
			if(!toolCardInGame) {
				System.out.println("CardName: " + temp + " is toegevoegd");
				cards.add(temp);
				panes.add(new ToolCard(temp, this));
				tcm.insertToolCardIntoGameToolCardTable(tcm.getToolCardID(), gameController.getM_game().getGameId());
				System.out.println("ToolCardid: " + tcm.getToolCardID() + "gameID: " + gameController.getM_game().getGameId());
			}
		}
	
	
	}
		

	public ArrayList<ToolCard> getPanes() {
		return panes;
	}

	public void useCard(String cardName) {
		if(gameController.getDraftpoolController().getSelectedDice() != null) {
			if(cardName.equals("Grozing Pliers")) {
				 tcInstruction = new Alert(AlertType.INFORMATION, "Druk op YES om de waarde van de dobbelsteen te verhogen. \n Druk op NO om de waarde van de dobbelsteen te verlagen", ButtonType.YES, ButtonType.NO);
				 tcInstruction.showAndWait();
				 if(tcInstruction.getResult() == ButtonType.YES) {
					 if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() < 6) {
						 gameController.getDraftpoolController().getSelectedDice().setEyes((gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() + 1)); 
					 }
				 }
				 else if(tcInstruction.getResult() == ButtonType.NO) {
					 if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() > 1) {
						 gameController.getDraftpoolController().getSelectedDice().setEyes((gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() - 1)); 
					 }
				 }
			}
			else if(cardName.equals("Flux Brush")) {
				if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() != null) {
					int max = 6;
					int min = 1;
					int newEyesOfDice = rand.nextInt((max - min) + 1) + min;
					gameController.getDraftpoolController().getSelectedDice().setEyes(newEyesOfDice);
					System.out.println(cardName + " ToolCard_Controller");
				}
				else{
					System.out.println("ToolCard_Controller 78: Dit werkt dus nie");
				}

			}
			else if(cardName.equals("Eglomise Brush")) {
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Flux Remover")) {

//				String color = gameController.getDraftpoolController().getSelectedDice().getColor();
//				int dienumber = gameController.getDraftpoolController().getSelectedDice().getDieNumber();
//				int idgame = gameController.getDraftpoolController().getSelectedDice().getIdgame();
//				tcm.removeDiceFromGameDie(idgame, dienumber, color);
				
				
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Copper Foil Burnisher")) {
				//kunnen we nog niet maken. moeten de spelregels van het windowpatterncard klaar voor zijn
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Running Pliers")) {
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Glazing Hammer")) {
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Lathekin")) {
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Tap Wheel")) {
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Lens Cutter")) {
				System.out.println(cardName + "ToolCard_Controller");
			}
			else if(cardName.equals("Grinding Stone")) {
				if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 1) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(6);
				}
				else if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 2) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(5);
				}
				else if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 3) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(4);
				}
				else if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 4) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(3);
				}
				else if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 5) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(2);
				}
				else if(gameController.getDraftpoolController().getSelectedDice().valueProperty().getValue() == 6) {
					gameController.getDraftpoolController().getSelectedDice().setEyes(1);
				}
				System.out.println(cardName + " ToolCard_Controller");
			}
			else if(cardName.equals("Cork-backed Straightedge")) {
				System.out.println(cardName + " ToolCard_Controller");
			}
			else {
//				TODO error message
				System.out.println("ToolCard_Controller 89: Er is iets mis gegaan");
			}
		}else {
			System.out.println("ToolCardController: dit vakje is leeg bosmongool");
		}
		

	}
	

}
