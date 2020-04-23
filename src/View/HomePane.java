package View;

import Controller.HomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();
	
	private final ImageView buttonOne = new ImageView(new Image("Resources/button7.png"));
	private final ImageView buttonTwo = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonThree = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonFour = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonFive = new ImageView(new Image("Resources/button3.png"));
	
	private Insets buttonIns = new Insets(30, 0, 30, 0);
	
	private Button home;
	private Button games;
	private Button invite;
	private Button statistics;
	private Button credits;
	
	private HBox reservedSpace = new HBox();
	private HomeController homeController;

	public HomePane(HomeController homeController) {
		this.homeController = homeController;
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
		reservedSpace.setMinSize((screenX/8*7), screenY);
		reservedSpace.setMaxSize((screenX/8*7), screenY);
		reservedSpace.setPrefSize((screenX/8*7), screenY);
		this.setRight(reservedSpace);
	}
	
	public void makeHomePane() {
		VBox layout = new VBox();
		layout.setMinSize(200, screenY);
		Font buttonFont = new Font("Algerian", 24);
		buttonOne.setFitWidth((screenX/8));
		buttonOne.setFitHeight(screenY/5);
		
		buttonTwo.setFitWidth((screenX/8));
		buttonTwo.setFitHeight(screenY/5);
		
		buttonThree.setFitWidth((screenX/8));
		buttonThree.setFitHeight(screenY/5);
		
		buttonFour.setFitWidth((screenX/8));
		buttonFour.setFitHeight(screenY/5);
		
		buttonFive.setFitWidth((screenX/8));
		buttonFive.setFitHeight(screenY/5);
		
		home = new Button("Home", buttonOne);
		home.setMinSize((screenX/8), (screenY/5));
		home.setPrefSize((screenX/8), (screenY/5));
		home.setMaxSize((screenX/8), (screenY/5));
		home.setPadding(buttonIns);
		home.setFont(buttonFont);
		home.setContentDisplay(ContentDisplay.CENTER);
		home.setStyle("-fx-background-color: null");
		
		
		games = new Button("Games", buttonTwo);
		games.setMinSize((screenX/8), (screenY/5));
		games.setPrefSize((screenX/8), (screenY/5));
		games.setMaxSize((screenX/8), (screenY/5));
		games.setFont(buttonFont);
		games.setContentDisplay(ContentDisplay.CENTER);
		games.setStyle("-fx-background-color: null");
		
		invite = new Button("uitnodigen", buttonThree);
		invite.setMinSize((screenX/8), (screenY/5));
		invite.setPrefSize((screenX/8), (screenY/5));
		invite.setMaxSize((screenX/8), (screenY/5));
		invite.setFont(buttonFont);
		invite.setContentDisplay(ContentDisplay.CENTER);
		invite.setStyle("-fx-background-color: null");		
		
		statistics = new Button("statistieken", buttonFour);
		statistics.setMinSize((screenX/8), (screenY/5));
		statistics.setPrefSize((screenX/8), (screenY/5));
		statistics.setMaxSize((screenX/8), (screenY/5));
		statistics.setFont(buttonFont);
		statistics.setContentDisplay(ContentDisplay.CENTER);
		statistics.setStyle("-fx-background-color: null");
		
		credits = new Button("credits", buttonFive);
		credits.setMinSize((screenX/8), (screenY/5));
		credits.setPrefSize((screenX/8), (screenY/5));
		credits.setMaxSize((screenX/8), (screenY/5));
		credits.setFont(buttonFont);
		credits.setContentDisplay(ContentDisplay.CENTER);
		credits.setStyle("-fx-background-color: null");
		
		layout.getChildren().addAll(home, games, invite, statistics, credits);
		this.getChildren().addAll(layout);

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
	
	
	

}
