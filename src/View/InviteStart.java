package View;

import Controller.HomeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InviteStart extends Pane {
	private HomeController homeController;
	private Button startGame;
	private Label gameID;
	private Label numberRemaining;
	private static final int CONTENTWITDH = 150;
	private static final int CONTENTHEIGHT = 30;
	
	
	public InviteStart(String gameID) {
		this.setMaxSize(100, 100);
		this.setMinSize(100, 100);
		this.setPrefSize(100, 100);
		addInvite(gameID);
		//System.out.println("InviteStart: Ik wordt aangemaakt");
	}
	

	
	
	public void addInvite(String gameID) {
		VBox vbox = new VBox();
		vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		this.gameID = new Label();
		this.gameID.setText("gameID: "+gameID);
		this.gameID.setTextFill(Color.DARKRED);
		this.gameID.setFont(new Font("Ariel", 15));
		this.gameID.setStyle("-fx-font-weight: bold");
		this.gameID.setMaxSize(CONTENTWITDH, CONTENTHEIGHT);
		this.gameID.setMinSize(CONTENTWITDH, CONTENTHEIGHT);
		this.gameID.setPrefSize(CONTENTWITDH, CONTENTHEIGHT);
		
		this.numberRemaining = new Label();
		this.numberRemaining.setTextFill(Color.DARKRED);
		this.numberRemaining.setFont(new Font("Ariel", 15));
		this.numberRemaining.setStyle("-fx-font-weight: bold");
		this.numberRemaining.setMaxSize(CONTENTWITDH, CONTENTHEIGHT);
		this.numberRemaining.setMinSize(CONTENTWITDH, CONTENTHEIGHT);
		this.numberRemaining.setPrefSize(CONTENTWITDH, CONTENTHEIGHT);
		
		this.startGame = new Button("StartGame");
		this.startGame.setMaxSize(CONTENTWITDH, CONTENTHEIGHT);
		this.startGame.setMinSize(CONTENTWITDH, CONTENTHEIGHT);
		this.startGame.setPrefSize(CONTENTWITDH, CONTENTHEIGHT);
		this.startGame.setVisible(false);
		
		vbox.getChildren().addAll(this.gameID,this.numberRemaining, this.startGame);
		this.getChildren().add(vbox);
	}
	
	public HomeController getHomeController() {
		return homeController;
	}

	public Label getNumberRemaining() {
		return numberRemaining;
	}


	public void setNumberRemaining(Label numberRemaining) {
		this.numberRemaining = numberRemaining;
	}


	public Button getStartGame() {
		return startGame;
	}
}
