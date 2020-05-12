package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Private_Objective_Card extends CardPane{

	public Private_Objective_Card(String cardName) {
		super();
		createPrivate_Objective_Card(cardName);
	}
	
public void createPrivate_Objective_Card(String cardName) {
	try {
		Image cardimage = new Image("Resources/" + cardName + ".PNG");
		ImageView objevtiveCard = new ImageView(cardimage);
		getChildren().add(objevtiveCard);
	}catch(Exception e) {
		System.out.println(e);
	}
	}

}
