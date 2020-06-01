package View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolCard extends CardPane{
	private Button useCard;

	public ToolCard(String cardName) {
		super();
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

}
