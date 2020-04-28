package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Public_Objective_Card extends CardPane {

	public Public_Objective_Card(String cardName) {
		super(cardName);
		createPublic_Objective_Card(cardName);
	}
	
public void createPublic_Objective_Card(String cardName) {
	Image cardimage = new Image("Resources/" + cardName + ".PNG");
	ImageView objevtiveCard = new ImageView(cardimage);
	getChildren().add(objevtiveCard);
	}

}
