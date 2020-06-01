package Controller;

import java.util.ArrayList;

import View.Public_Objective_Card;
import View.ToolCard;
import javafx.scene.layout.Pane;
import model.Public_Objective_Card_Model;

public class Public_Objective_Card_Controller {
	Pane pane;
	private Public_Objective_Card_Model pocm;
	private ArrayList<String> cards;
	private ArrayList<Public_Objective_Card> panes;
	private GameController gamecontroller;

	public Public_Objective_Card_Controller(GameController gameController) {
		this.gamecontroller = gameController;
		cards = new ArrayList<>();
		panes = new ArrayList<>();
		pocm = new Public_Objective_Card_Model();

	}

	public void getCards() {
		String temp;
		while (cards.size() < 3) {
			temp = pocm.getCard();
			if (!cards.contains(temp)) {
				cards.add(temp);
				pocm.insertPublicObjectiveCards(gamecontroller.getM_game().getGameId());
			}
		}
	}
	public void getCards(int gameID) {
		pocm.setCardsInGame(gameID);
		String temp = "";
		cards.clear();
		for (int i = 0; i < pocm.getCardNamesPerGame().size(); i++) {
			temp = pocm.getCardNamesPerGame().get(i);
			cards.add(temp);
			panes.add(new Public_Objective_Card(temp));
		}
		
		if (pocm.getCardNamesPerGame().size() != 0) {
			this.gamecontroller.setPublicCardsAdded(true);
		} else {
			this.gamecontroller.setPublicCardsAdded(false);
		}
	}
	
	public ArrayList<Public_Objective_Card> getPanes() {
		return panes;
	}
	
	public String[] getNames (int idgame) {
		return this.pocm.getNames(idgame);
	}
}

