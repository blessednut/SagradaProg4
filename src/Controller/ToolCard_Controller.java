package Controller;

import java.util.ArrayList;

import View.ToolCard;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.ToolCardModel;

public class ToolCard_Controller {
	private ToolCardModel tcm;
	private ArrayList<String> cards;
	private ArrayList<ToolCard> panes;
	private Alert tcInstruction;
//	private ToolCard v_ToolCard;
//	private String cardname = "";

	public ToolCard_Controller() {
		cards = new ArrayList<String>();
		panes = new ArrayList<ToolCard>();
		tcm = new ToolCardModel();
		
//		v_ToolCard = new ToolCard(cardname, this);
//		v_ToolCard.getButton().setOnAction(Event -> useCard(cardName));
		
		getCards();
		
//		Elke toolcard krijgt krijgt nu mee welke methode ze moeten gebruiken als ze aangeklikt worden.
		panes.get(0).getButton().setOnAction(Event -> useCard(cards.get(0)));
		panes.get(1).getButton().setOnAction(Event -> useCard(cards.get(1)));
		panes.get(2).getButton().setOnAction(Event -> useCard(cards.get(2)));
		
		

	}
	private void getCards() {
		String temp;
		while (cards.size() <= 2) {
			temp = tcm.getCard();
			if (!cards.contains(temp)) {
				cards.add(temp);
				panes.add(new ToolCard(temp, this));
			}
		}

	}

	public ArrayList<ToolCard> getPanes() {
		return panes;
	}

	public void useCard(String cardName) {
		if(cardName.equals("Driepuntstang")) {
			System.out.println("i was pressed1");
			 tcInstruction = new Alert(AlertType.INFORMATION, "Selecteer een dobbelsteen om de waarde van te verhogen of te verlagen", ButtonType.CLOSE);
			 tcInstruction.showAndWait();
			 if(tcInstruction.getResult() == ButtonType.CLOSE) {
				 tcInstruction.close();
			 }
		}
		else if(cardName.equals("Fluxborstel")) {
			System.out.println("i was pressed2");
		}
		else if(cardName.equals("Églomisé Borstel")) {
			return;
		}
		else if(cardName.equals("Fluxverwijderaar")) {
			System.out.println("i was pressed3");
		}
		else if(cardName.equals("Folie-aandrukker")) {
			return;
		}
		else if(cardName.equals("Glasbreektang")) {
			return;
		}
		else if(cardName.equals("Loodhamer")) {
			return;
		}
		else if(cardName.equals("Loodopenhaler")) {
			return;
		}
		else if(cardName.equals("Olieglassnijder")) {
			return;
		}
		else if(cardName.equals("Rondesnijder")) {
			return;
		}
		else if(cardName.equals("Schuurblok")) {
			return;
		}
		else if(cardName.equals("Snijliniaal")) {
			return;
		}
		else {
//			TODO error message
			System.out.println("er gaat iets mis");
		}

	}
	

}
