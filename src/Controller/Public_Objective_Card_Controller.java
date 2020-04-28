package Controller;

import java.util.ArrayList;

import View.Public_Objective_Card;
import javafx.scene.layout.Pane;
import model.Public_Objective_Card_Model;

public class Public_Objective_Card_Controller {
	Pane pane;
	private Public_Objective_Card_Model pocm;
	private ArrayList<String> cards;
	private ArrayList<Public_Objective_Card> panes;

	public Public_Objective_Card_Controller() {
		cards = new ArrayList<>();
		panes = new ArrayList<>();
		pocm = new Public_Objective_Card_Model();
		getCards();

	}

	private void getCards() {
		String temp;
		while (cards.size() <= 2) {
			temp = pocm.getCard();
			if (!cards.contains(temp)) {
				cards.add(temp);
				panes.add(new Public_Objective_Card(temp));
			}
		}

	}

	public ArrayList<Public_Objective_Card> getPanes() {
		return panes;
	}
	
	
}
