package View;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WindowPatternView2 extends BorderPane {
	private final static int FONTSIZE = 16;
	private final static CornerRadii RADIUS = new CornerRadii(10.00);
	private final static Insets TITLEINSET = new Insets(3, 0, 0, 0);
	private final static int BORDERSIZE = 20;
	private final static int GAP = 3;
	private GridPane window;
	private int width, height;
	
	public WindowPatternView2 (int width, int height, StringProperty title, squareView[][] fieldView) {
		this.width = width;
		this.height = height;
		this.setPrefSize(width, height);
		this.setMaxSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, RADIUS, null)));
		
		Label cardTitle = new Label();
		cardTitle.textProperty().bind(title);
		cardTitle.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, FONTSIZE));
		cardTitle.setTextFill(Color.WHITE);

		BorderPane.setAlignment(cardTitle, Pos.CENTER);
		BorderPane.setMargin(cardTitle, TITLEINSET);
		this.setTop(cardTitle);
		
		window = new GridPane();
		window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		window.setPrefSize(width - (2 * BORDERSIZE), height - (2 * BORDERSIZE));
		window.setMaxSize(width - (2 * BORDERSIZE), height - (2 * BORDERSIZE));
		window.setVgap(GAP);
		window.setHgap(GAP);
		
		for (int x = 0; x < fieldView.length; x++) {
			for (int y = 0; y < fieldView[x].length; y++) {
				double fieldWidth = (width - (2 * BORDERSIZE)) / fieldView.length;
				double fieldHeight = (height - (2 * BORDERSIZE)) / fieldView[x].length;

				fieldView[x][y].setSize(fieldWidth, fieldHeight);
				window.add(fieldView[x][y], x, y);
			}
		}
		
		this.setCenter(window);
	}
}
