package View;

import Controller.ToolCard_Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

public class ToolCard extends CardPane{
	private ToolCard_Controller tc_Controller;
	private Button useCard;
	private Popup popup;

	public ToolCard(String cardName, ToolCard_Controller tc_Controller) {
		super();
		popup = new Popup(); 
		this.tc_Controller = tc_Controller;
		createToolCard(cardName);
		
	}
	
	public void createToolCard(String cardName) {
		Image cardimage = new Image("Resources/" + cardName + ".PNG");
		useCard = new Button("");
		useCard.setGraphic(new ImageView(cardimage));
		getChildren().add(useCard);
	}
	
	public Button getButton() {
		return useCard;
	}
	
//	public void showPopup(String text) {
//		Label tcInstruction = new Label(text);
//		popup.getContent().add(tcInstruction);
//		popup.show(Main.);
//	}

}
