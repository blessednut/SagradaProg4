package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class HomePane extends BorderPane {
	
	private static final int TEXT = 24;
	
	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();

	private final ImageView buttonOne = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonTwo = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonThree = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonFour = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonFive = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonSix = new ImageView(new Image("Resources/button3.png"));

	private Insets buttonIns = new Insets(30, 0, 30, 0);

	private Button uitloggen;
	private Button home;
	private Button games;
	private Button invite;
	private Button statistics;
	private Button credits;
	private HBox HomePaneBottom;

	private HBox reservedSpace = new HBox();

	public HomePane() {
		this.setBackground(new Background(new BackgroundImage(new Image("Resources/sagradaTitel.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));
		this.setMinSize(screenX, screenY);
		this.setPrefSize(screenX, screenY);
		this.setMaxSize(screenX, screenY);
		makeHomePane();

	}

	public void makeReservedSpace(HBox filler) {
		setReservedSpace(filler);
		reservedSpace.setMinSize((screenX / 8 * 7), screenY);
		reservedSpace.setMaxSize((screenX / 8 * 7), screenY);
		reservedSpace.setPrefSize((screenX / 8 * 7), screenY);
		this.setRight(reservedSpace);
	}

	public void makeHomePane() {
		VBox layout = new VBox();
		
		
		layout.setMinSize(200, screenY);
		Font buttonFont = new Font("Algerian", TEXT);
		buttonOne.setFitWidth((screenX / 8));
		buttonOne.setFitHeight(screenY / 6);

		buttonTwo.setFitWidth((screenX / 8));
		buttonTwo.setFitHeight(screenY / 6);

		buttonThree.setFitWidth((screenX / 8));
		buttonThree.setFitHeight(screenY / 6);

		buttonFour.setFitWidth((screenX / 8));
		buttonFour.setFitHeight(screenY / 6);

		buttonFive.setFitWidth((screenX / 8));
		buttonFive.setFitHeight(screenY / 6);
		
		buttonSix.setFitWidth((screenX / 8));
		buttonSix.setFitHeight(screenY / 6);

		home = new Button("Home", buttonOne);
		home.setMinSize((screenX / 8), (screenY / 6));
		home.setPrefSize((screenX / 8), (screenY / 6));
		home.setMaxSize((screenX / 8), (screenY / 6));
		home.setPadding(buttonIns);
		home.setFont(buttonFont);
		home.setContentDisplay(ContentDisplay.CENTER);
		home.setStyle("-fx-background-color: null");

		games = new Button("Games", buttonTwo);
		games.setMinSize((screenX / 8), (screenY / 6));
		games.setPrefSize((screenX / 8), (screenY / 6));
		games.setMaxSize((screenX / 8), (screenY / 6));
		games.setFont(buttonFont);
		games.setContentDisplay(ContentDisplay.CENTER);
		games.setStyle("-fx-background-color: null");

		invite = new Button("uitnodigen", buttonThree);
		invite.setMinSize((screenX / 8), (screenY / 6));
		invite.setPrefSize((screenX / 8), (screenY / 6));
		invite.setMaxSize((screenX / 8), (screenY / 6));
		invite.setFont(buttonFont);
		invite.setContentDisplay(ContentDisplay.CENTER);
		invite.setStyle("-fx-background-color: null");

		statistics = new Button("statistieken", buttonFour);
		statistics.setMinSize((screenX / 8), (screenY / 6));
		statistics.setPrefSize((screenX / 8), (screenY / 6));
		statistics.setMaxSize((screenX / 8), (screenY / 6));
		statistics.setFont(buttonFont);
		statistics.setContentDisplay(ContentDisplay.CENTER);
		statistics.setStyle("-fx-background-color: null");

		credits = new Button("credits", buttonFive);
		credits.setMinSize((screenX / 8), (screenY / 6));
		credits.setPrefSize((screenX / 8), (screenY / 6));
		credits.setMaxSize((screenX / 8), (screenY / 6));
		credits.setFont(buttonFont);
		credits.setContentDisplay(ContentDisplay.CENTER);
		credits.setStyle("-fx-background-color: null");
		
		uitloggen = new Button("uitloggen", buttonSix);
		uitloggen.setMinSize((screenX / 8), (screenY / 6));
		uitloggen.setPrefSize((screenX / 8), (screenY / 6));
		uitloggen.setMaxSize((screenX / 8), (screenY / 6));
		uitloggen.setFont(buttonFont);
		uitloggen.setContentDisplay(ContentDisplay.CENTER);
		uitloggen.setStyle("-fx-background-color: null");

		layout.getChildren().addAll(uitloggen, home, games, invite, statistics, credits);
		this.getChildren().addAll(layout);

	}
	public void makeInvites() {
		HomePaneBottom = new HBox();
		HomePaneBottom.setPadding(new Insets(0, 0, 100, 0));
		this.setBottom(HomePaneBottom);
		HomePaneBottom.setAlignment(Pos.TOP_CENTER);
	}

	public void setReservedSpace(HBox reservedSpace) {
		this.reservedSpace = reservedSpace;
	}

	public Button getHome() {
		return home;
	}

	public Button getGames() {
		return games;
	}

	public Button getVrienden() {
		return invite;
	}

	public Button getStatistick() {
		return statistics;
	}

	public Button getCredits() {
		return credits;
	}

	public HBox getHomePaneBottom() {
		return HomePaneBottom;
	}

	public Button getUitloggen() {
		return uitloggen;
	}
	
	
	
	
}
