package View;

import Controller.WindowPatternSquareController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WindowPatternView extends BorderPane {
	private final static int FONTSIZE = 16;
	private final static CornerRadii RADIUS = new CornerRadii(10.00);
	private final static Insets TITLEINSET = new Insets(3, 0, 0, 0);
	private final static int BORDERSIZE = 20;
	private final static int GAP = 3;
	private final static int TOKENRADIUS = 8;
	private GridPane window;
	private int width, height;
	
	public WindowPatternView (int width, int height, StringProperty title, IntegerProperty tokenAmount, WindowPatternSquareController[][] fieldView) {
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
				
				WindowPatternSquareView square = new WindowPatternSquareView(fieldView[x][y]);
				square.setSize(fieldWidth, fieldHeight);
				window.add(square, x, y);
			}
		}
		
		this.setCenter(window);
		
		//Luistert wanneer de tokenAmount verandert in het model
		final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				drawTokens(tokenAmount.getValue());
			}
		};
		tokenAmount.addListener(changeListener);
		drawTokens(tokenAmount.getValue());
	}
	
	private void drawTokens (int tokenAmount) {
		HBox tokens = new HBox();
		tokens.setAlignment(Pos.CENTER_RIGHT);

		for (int i = 0; i < tokenAmount; i++) {
			StackPane token;
			token = drawCircle(Color.GOLD);
//			if (i >= tokenAmount - tokensUsed) {
//				token = drawCircle(Color.LIGHTGRAY);
//			} else {
//				token = drawCircle(Color.GOLD);
//			}
//
//			if (i == tokenAmount - 1) {
//				token.setPadding(new Insets(4, BORDERSIZE, 4, 4));
//			} else {
//				token.setPadding(new Insets(4));
//			}

			tokens.getChildren().add(token);
		}
		this.setBottom(tokens);
	}
	
	private StackPane drawCircle(Color color) {
		StackPane pane = new StackPane();
		Circle circle = new Circle();
		circle.setRadius(TOKENRADIUS);
		circle.setFill(color);
		pane.getChildren().add(circle);
		return pane;
	}
}
