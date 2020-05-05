package View;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class DraftPoolView extends GridPane {
	private double width, height;
	private final static int SQUAREWIDTH = 3;
	
	public DraftPoolView (double width, double height) {
		this.setPrefSize(width, height);
		this.setMinSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));
		
//		for (int x = 0; x < SQUAREWIDTH; x++) {
//			for (int y = 0; y < SQUAREWIDTH; y++) {
//				double squareWidth = ((width - 50) / 3);
//				double squareHeight = ((height - 50) / 3);
//				
//				this.add(new DraftPoolSquareView(squareWidth, squareHeight), x, y);
//			}
//		}
	}
}
