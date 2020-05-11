package Controller;

import View.Private_Objective_Card;
import javafx.scene.layout.Pane;
import model.Private_Objective_Card_Model;

public class Private_Objective_Card_Controller {
	private Pane pane;
	private Private_Objective_Card_Model pocm;

	public Private_Objective_Card_Controller(int GameId, String username) {
		pocm = new Private_Objective_Card_Model(GameId, username);
//		TODO: zorgen dat de cardName aan de hand van de IDPlayer kan worden opgehaald. dus niet in de constructor meegegeven wordt.
//		Private_Objective_Card private_oc = new Private_Objective_Card(pocm.getColor(GameId, username));
//		pane = private_oc;
	}

	public Pane getPane() {
		return pane;
	}
}
