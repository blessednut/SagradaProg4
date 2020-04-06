package View;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class HomePane extends BorderPane {
	Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();


	public HomePane() {
		this.setBackground(new Background(new BackgroundImage(new Image("Resources/sagradaTitel.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));
		this.setMinSize(3000, 1700);
		this.setPrefSize(3000, 1700);
		this.setMaxSize(3000, 1700);
	}

}
