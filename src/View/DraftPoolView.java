package View;

import Controller.DraftpoolSquareController;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class DraftPoolView extends BorderPane {
	private final static CornerRadii RADIUS = new CornerRadii(10.00);
	private final static int SQUAREWIDTH = 3;

	public DraftPoolView(double width, double height, DraftpoolSquareController[] draftpool) {
		this.setPrefSize(width, height);
		this.setMaxSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, RADIUS, null)));

		GridPane window = new GridPane();

		window.setMaxSize(width - 50, height - 50);
		window.setVgap(3);
		window.setHgap(3);

		int diceCounter = 0;
		for (int x = 0; x < SQUAREWIDTH; x++) {
			for (int y = 0; y < SQUAREWIDTH; y++) {
				double squareWidth = (width - 56) / 3;
				double squareHeight = (height - 56) / 3;

				window.add(new DraftPoolSquareView(squareWidth, squareHeight, draftpool[diceCounter]), y, x);

				diceCounter++;
			}
		}

		this.setCenter(window);
	}
}
