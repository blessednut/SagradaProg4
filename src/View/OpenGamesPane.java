package View;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class OpenGamesPane extends VBox {
	
	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();
	private static final int COMBOBOXWITDH = 500;
	private static final int COMBOBOXHEIGHT = 50;
	private static final int OPENGAMEBUTTONWITDH = 150;
	private static final int OPENGAMEBUTTONHEIGHT= 30;
	private Button openGame;
	private ComboBox<String> oldGames;
	
	public OpenGamesPane() {
		this.setMinSize((screenX / 8 * 7), screenY);
		this.setMaxSize((screenX / 8 * 7), screenY);
		this.setPrefSize((screenX / 8 * 7), screenY);
		showGames();
	}
	
	public void showGames() {
		oldGames = new ComboBox<>();
		oldGames.setMinSize(COMBOBOXWITDH, COMBOBOXHEIGHT);
		oldGames.setPrefSize(COMBOBOXWITDH, COMBOBOXHEIGHT);
		oldGames.setMaxSize(COMBOBOXWITDH, COMBOBOXHEIGHT);
		oldGames.setPromptText("Welke oude spel zou je willen openen?");
		
		openGame = new Button();
		openGame.setText("open spel");
		openGame.setMinSize(OPENGAMEBUTTONWITDH, OPENGAMEBUTTONHEIGHT);
		openGame.setPrefSize(OPENGAMEBUTTONWITDH, OPENGAMEBUTTONHEIGHT);
		openGame.setMaxSize(OPENGAMEBUTTONWITDH, OPENGAMEBUTTONHEIGHT);
		openGame.setVisible(false);
		
		this.getChildren().addAll(oldGames, openGame);
	}
	
	public ComboBox<String> getOldGamesBox(){
		return oldGames;
	}
	
	public Button getOpenGame() {
		return openGame;
	}
}
