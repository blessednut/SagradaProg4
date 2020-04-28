package View;

import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class CreditsPane extends HBox {

	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();

	public CreditsPane() {
		this.setMinSize((screenX / 8 * 7), screenY);
		this.setMaxSize((screenX / 8 * 7), screenY);
		this.setPrefSize((screenX / 8 * 7), screenY);
		createPane();
	}

	private void createPane() {

	}

}
