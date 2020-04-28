package Controller;

import View.Private_Objective_Card;
import javafx.scene.layout.Pane;
import model.Private_Objective_Card_Model;

public class Private_Objective_Card_Controller {
	private Pane pane;
	private Private_Objective_Card_Model pocm;

	public Private_Objective_Card_Controller(String cardName, int IDPlayer) {
		Private_Objective_Card private_oc = new Private_Objective_Card(cardName);
		pane = private_oc;
		pocm = new Private_Objective_Card_Model();

	}
	
	
//	TODO een player meegeven
	public String getColor() {
		return pocm.getColor();

	}
}
