package View;

import javafx.geometry.Insets;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class HomePane extends BorderPane {
	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();
	
	private final ImageView buttonOne = new ImageView(new Image("Resources/button-image.png"));
	private final ImageView buttonTwo = new ImageView(new Image("Resources/button2.png"));
	private final ImageView buttonThree = new ImageView(new Image("Resources/button3.png"));
	private final ImageView buttonFour = new ImageView(new Image("Resources/button4.png"));

	private Insets buttonIns = new Insets(30, 0, 30, 0);
	
	private Button home;
	private Button games;
	private Button vrienden;
	private Button statistick;
	private Button credits;

	public HomePane() {
		this.setBackground(new Background(new BackgroundImage(new Image("Resources/sagradaTitel.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));
		this.setMinSize(screenX, screenY);
		this.setPrefSize(screenX, screenY);
		this.setMaxSize(screenX, screenY);
		makeHomePane();

	}

	public void makeHomePane() {
		VBox layout = new VBox();
		layout.setMinSize(200, screenY);
		Font buttonFont = new Font("Algerian", 24);
		buttonOne.setFitWidth(200);
		buttonOne.setFitHeight(screenY/5);
		
		buttonTwo.setFitWidth(200);
		buttonTwo.setFitHeight(screenY/5);
		
		buttonThree.setFitWidth(200);
		buttonThree.setFitHeight(screenY/5);
		
		buttonFour.setFitWidth(200);
		buttonFour.setFitHeight(screenY/5);
		
		

		home = new Button("Home", buttonOne);
		home.setMinSize(200, (screenY/5));
		home.setPrefSize(200, (screenY/5));
		home.setMaxSize(200, (screenY/5));
		home.setPadding(buttonIns);
		home.setFont(buttonFont);
		home.setContentDisplay(ContentDisplay.CENTER);
		
		games = new Button("Games", buttonTwo);
		games.setMinSize(200, (screenY/5));
		games.setPrefSize(200, (screenY/5));
		games.setMaxSize(200, (screenY/5));
		games.setFont(buttonFont);
		games.setContentDisplay(ContentDisplay.CENTER);
		
		vrienden = new Button("Vrienden", buttonThree);
		vrienden.setMinSize(200, (screenY/5));
		vrienden.setPrefSize(200, (screenY/5));
		vrienden.setMaxSize(200, (screenY/5));
		vrienden.setFont(buttonFont);
		vrienden.setContentDisplay(ContentDisplay.CENTER);
		
		statistick = new Button("stats",buttonFour);
		statistick.setMinSize(200, (screenY/5));
		statistick.setPrefSize(200, (screenY/5));
		statistick.setMaxSize(200, (screenY/5));
		statistick.setFont(buttonFont);
		statistick.setContentDisplay(ContentDisplay.CENTER);
		
		credits = new Button("credits");
		credits.setMinSize(200, (screenY/5));
		credits.setPrefSize(200, (screenY/5));
		credits.setMaxSize(200, (screenY/5));
		credits.setFont(buttonFont);
		credits.setContentDisplay(ContentDisplay.CENTER);
		
		layout.getChildren().addAll(home, games, vrienden, statistick, credits);
		this.getChildren().add(layout);

	}

	public Button getHome() {
		return home;
	}

	public Button getGames() {
		return games;
	}

	public Button getVrienden() {
		return vrienden;
	}

	public Button getStatistick() {
		return statistick;
	}

	public Button getCredits() {
		return credits;
	}
	
	

}
