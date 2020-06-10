package View;

import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class CreditsPane extends HBox {

	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;

	public CreditsPane() {
		this.setMinSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setMaxSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setPrefSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		createPane();
	}

	private void createPane() {

	}

}
