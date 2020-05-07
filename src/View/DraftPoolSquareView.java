package View;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class DraftPoolSquareView extends StackPane {
	public DraftPoolSquareView (double width, double height) {
		this.setPrefSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}
}
