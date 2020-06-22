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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class HomePane extends BorderPane {
	
	private static final int TEXT = 18;
	
	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;

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
	private Button disclaimer;
	private HBox HomePaneBottom;

	private HBox reservedSpace = new HBox();

	public HomePane() {
		this.setBackground(new Background(new BackgroundImage(new Image("Resources/sagradaTitel.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));
		this.setMinSize(SAGRADAWIDTH, SAGRADAHEIGHT);
		this.setPrefSize(SAGRADAWIDTH, SAGRADAHEIGHT);
		this.setMaxSize(SAGRADAWIDTH, SAGRADAHEIGHT);
		makeHomePane();

	}

	public void makeReservedSpace(HBox filler) {
		setReservedSpace(filler);
		reservedSpace.setMinSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		reservedSpace.setMaxSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		reservedSpace.setPrefSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setRight(reservedSpace);
	}

	public void makeHomePane() {
		VBox layout = new VBox();
		
		
		layout.setMinSize(200, SAGRADAHEIGHT);
		Font buttonFont = new Font("Algerian", TEXT);
		buttonOne.setFitWidth((SAGRADAWIDTH / 8));
		buttonOne.setFitHeight(SAGRADAHEIGHT / 5);

		buttonTwo.setFitWidth((SAGRADAWIDTH / 8));
		buttonTwo.setFitHeight(SAGRADAHEIGHT / 5);

		buttonThree.setFitWidth((SAGRADAWIDTH / 8));
		buttonThree.setFitHeight(SAGRADAHEIGHT / 5);

		buttonFour.setFitWidth((SAGRADAWIDTH / 8));
		buttonFour.setFitHeight(SAGRADAHEIGHT / 5);

		buttonFive.setFitWidth((SAGRADAWIDTH / 8));
		buttonFive.setFitHeight(SAGRADAHEIGHT / 5);
		
		buttonSix.setFitWidth((SAGRADAWIDTH / 8));
		buttonSix.setFitHeight(SAGRADAHEIGHT / 5);

		games = new Button("Games", buttonTwo);
		games.setMinSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		games.setPrefSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		games.setMaxSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		games.setFont(buttonFont);
		games.setContentDisplay(ContentDisplay.CENTER);
		games.setStyle("-fx-background-color: null");

		invite = new Button("uitnodigen", buttonThree);
		invite.setMinSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		invite.setPrefSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		invite.setMaxSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		invite.setFont(buttonFont);
		invite.setContentDisplay(ContentDisplay.CENTER);
		invite.setStyle("-fx-background-color: null");

		statistics = new Button("statistieken", buttonFour);
		statistics.setMinSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		statistics.setPrefSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		statistics.setMaxSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		statistics.setFont(buttonFont);
		statistics.setContentDisplay(ContentDisplay.CENTER);
		statistics.setStyle("-fx-background-color: null");

		disclaimer = new Button("disclaimer", buttonFive);
		disclaimer.setMinSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		disclaimer.setPrefSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		disclaimer.setMaxSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		disclaimer.setFont(buttonFont);
		disclaimer.setContentDisplay(ContentDisplay.CENTER);
		disclaimer.setStyle("-fx-background-color: null");
		
		uitloggen = new Button("uitloggen", buttonSix);
		uitloggen.setMinSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		uitloggen.setPrefSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		uitloggen.setMaxSize((SAGRADAWIDTH / 8), (SAGRADAHEIGHT / 5));
		uitloggen.setFont(buttonFont);
		uitloggen.setContentDisplay(ContentDisplay.CENTER);
		uitloggen.setStyle("-fx-background-color: null");

		layout.getChildren().addAll(uitloggen, games, invite, statistics, disclaimer);
		this.getChildren().addAll(layout);

	}
	public void makeInvites() {
		HomePaneBottom = new HBox();
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

	public Button getDisclaimer() {
		return disclaimer;
	}

	public HBox getHomePaneBottom() {
		return HomePaneBottom;
	}

	public Button getUitloggen() {
		return uitloggen;
	}
	
	
	
	
}
