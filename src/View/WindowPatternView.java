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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
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
	HBox tokens;
	private Color playerColor;
	
	public WindowPatternView(int width, int height, StringProperty title, IntegerProperty tokenAmount,
			WindowPatternSquareController[][] fieldView, Color playerColor, String username) {
		this.width = width;
		this.height = height;
		this.playerColor = playerColor;
		this.setPrefSize(width, height);
		this.setMaxSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, RADIUS, null)));
		
		HBox titleAndName = new HBox();
		
		Label cardTitle = new Label();
		cardTitle.textProperty().bind(title);
		cardTitle.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, FONTSIZE));
		cardTitle.setTextFill(Color.WHITE);

		BorderPane.setAlignment(titleAndName, Pos.CENTER);
		BorderPane.setMargin(titleAndName, TITLEINSET);
		
		Label playerNameOnCard = new Label();
		playerNameOnCard.setText(username);
		playerNameOnCard.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, FONTSIZE));
		playerNameOnCard.setTextFill(Color.WHITE);
		playerNameOnCard.setPadding(new Insets(0,0,0,20));
		
		
		titleAndName.getChildren().addAll(cardTitle, playerNameOnCard);
		
		this.setTop(titleAndName);

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

		// Luistert wanneer de tokenAmount verandert in het model
		final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				drawTokens(tokenAmount.getValue(), true);
			}
		};
		tokenAmount.addListener(changeListener);
		drawTokens(tokenAmount.getValue(), true);
		this.setBorder(new Border(
				new BorderStroke(playerColor, BorderStrokeStyle.SOLID, new CornerRadii(6), new BorderWidths(5))));
	}

	public WindowPatternView(int width, int height, StringProperty title, IntegerProperty tokenAmount,
			WindowPatternSquareController[][] fieldView) {
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

		// Luistert wanneer de tokenAmount verandert in het model
		final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				drawTokens(tokenAmount.getValue());
			}
		};
		tokenAmount.addListener(changeListener);
		drawTokens(tokenAmount.getValue());
	}

	
	private StackPane drawCircle(Color color) {
		StackPane pane = new StackPane();
		Circle circle = new Circle();
		circle.setRadius(TOKENRADIUS);
		circle.setFill(color);
		pane.getChildren().add(circle);
		return pane;
	}

	public HBox getTokens() {
		return tokens;
	}
	
	private void drawTokens(int tokenAmount, boolean isAfterChoice) {
        tokens = new HBox();
        System.out.println(tokenAmount);
        for (int i = 0; i < tokenAmount; i++) {
            TokenPane token;
            token = new TokenPane(playerColor);
            tokens.getChildren().add(token);
        }

        this.setBottom(tokens);
        tokens.setAlignment(Pos.CENTER_RIGHT);
    }
	
    private void drawTokens(int tokenAmount) {
        HBox tokens = new HBox();
        tokens.setAlignment(Pos.CENTER_RIGHT);

        for (int i = 0; i < tokenAmount; i++) {
            StackPane token;
            token = drawCircle(Color.GOLD);

            tokens.getChildren().add(token);
        }
        this.setBottom(tokens);
    }

    public void setTokens(int num, Color color) {
        tokens = new HBox();
        for(int i = 0; i< num; i++) {
            tokens.getChildren().add(drawCircle(color));
        }
        this.setBottom(tokens);
    }
}
