package Controller;

import java.util.ArrayList;

import View.ToolCard;
import model.ToolCardModel;

public class ToolCard_Controller {
	private ToolCardModel tcm;
	private ArrayList<String> cards;
	private ArrayList<ToolCard> panes;

	public ToolCard_Controller(String cardName) {
		cards = new ArrayList<String>();
		panes = new ArrayList<ToolCard>();
		tcm = new ToolCardModel();
		getCards();

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
			System.out.println("i was pressed");
		}
		else if(cardName.equals("Fluxborstel")) {
			return;
		}
		else if(cardName.equals("Églomisé Borstel")) {
			return;
		}
		else if(cardName.equals("Fluxverwijderaar")) {
			return;
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
