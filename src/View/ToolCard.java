package View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ToolCard extends CardPane{
	private Button useCard;
	private String cardName;

	public ToolCard(String cardName) {
		super();
		this.cardName = cardName;
		createToolCard(cardName);
		
	}
	
	public void createToolCard(String cardName) {
		HBox hbox = new HBox();
		Image cardimage = new Image("Resources/" + cardName + ".PNG");
		useCard = new Button("");
		useCard.setMaxSize(100, 200);
		useCard.setMinSize(100, 200);
		useCard.setPrefSize(100, 200);
		ImageView toolCard = new ImageView(cardimage);
		toolCard.setFitHeight(200);
		toolCard.setPreserveRatio(true);
		useCard.setGraphic(toolCard);
		getChildren().add(useCard);
		getChildren().add(hbox);
		
	}
	
	public Button getButton() {
		return useCard;
	}
	public String getCardName() {
		return cardName;
	}

}
