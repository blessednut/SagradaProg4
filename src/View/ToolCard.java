package View;

import Controller.ToolCard_Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolCard extends CardPane{
	private ToolCard_Controller tc_Controller;

	public ToolCard(String cardName, ToolCard_Controller tc_Controller) {
		super(cardName);
		this.tc_Controller = tc_Controller;
		createToolCard(cardName);
		
	}
	
	public void createToolCard(String cardName) {
		Image cardimage = new Image("Resources/" + cardName + ".PNG");
		Button useCard = new Button("");
		useCard.setGraphic(new ImageView(cardimage));
		useCard.setOnAction(Event -> tc_Controller.useCard(cardName));
		getChildren().add(useCard);
	}

}
