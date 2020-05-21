package View;

//import Controller.DraftpoolController;
import Controller.GameController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GamePane extends BorderPane {
	private static final int HEIGHTODDPLAYER = 150;
	private static final int WIDTHODDPLAYER = 200;
	private static final int HEIGHTEVENPLAYER = 200;
	private static final int WIDTHEVENPLAYER = 150;
	private static final int HEIGHTENDTURNBUTTON = 50;
	private static final int WIDTHENDTURNBUTTON = 200;
	private static final int WIDTHSCOREBOARD = 150;
	private static final int HEIGHTSCOREBOARD = 100;
	private static final int DICESIZE = 200;
	private static final int PUBOBJCARDSIZE = 150;
	private GameController gameController;
	private WindowPatternView ownWindow;
	private DraftPoolView draftpool;

	public GamePane(GameController gameController) {
		this.gameController = gameController;
		this.setMinSize(900, 900);
		this.setPrefSize(900, 900);
		this.setMaxSize(900, 900);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		
		createGamePane();
	}

	public GamePane() {
		this.setMinSize(900, 900);
		this.setPrefSize(900, 900);
		this.setMaxSize(900, 900);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		createGamePane();
	}
	
	public void createGamePane() {
		this.getChildren().clear();
		HBox gamePaneBottom = new HBox();
		HBox gamePaneCenter = new HBox();
		VBox gamePaneLeft = new VBox();
		VBox gamePaneRight = new VBox();
		this.setBottom(gamePaneBottom);
		this.setCenter(gamePaneCenter);
		this.setLeft(gamePaneLeft);
		this.setRight(gamePaneRight);
		gamePaneBottom.setAlignment(Pos.BOTTOM_CENTER);
		gamePaneCenter.setAlignment(Pos.CENTER);
		gamePaneLeft.setAlignment(Pos.TOP_LEFT);
		gamePaneRight.setAlignment(Pos.TOP_RIGHT);
//		windowPatternCard
		if (ownWindow == null) {

		} else {
			gamePaneBottom.getChildren().add(ownWindow);
		}
//		Private Objective card
		gamePaneBottom.getChildren().add(gameController.getPrivate_OCC().getPane());
		

//		Public Objective cards
//		TODO: hier komt het aanbod toevoegen
		if (draftpool != null) {
			gamePaneCenter.getChildren().add(draftpool);
		}
		
		
		for (int i = 0; i < gameController.getPublic_OCC().getPanes().size(); i++) {
			gamePaneCenter.getChildren().add(gameController.getPublic_OCC().getPanes().get(i));
		}
//		End turn button
		Button endTurn = new Button("Einde beurt");
		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		gamePaneLeft.getChildren().add(endTurn);
		
		endTurn.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				onClickEndTurn();
			}
		});
		
		
		
//		roundCounter
		Label roundCounter = new Label("Ronde: X");
		roundCounter.setFont(new Font("Arial", 16));
		gamePaneRight.getChildren().add(roundCounter);
	}
	
	public void createGamePane2() {
		this.getChildren().clear();
		HBox gamePaneBottom = new HBox();
		HBox gamePaneCenter = new HBox();
		VBox gamePaneLeft = new VBox();
		VBox gamePaneRight = new VBox();
		this.setBottom(gamePaneBottom);
		this.setCenter(gamePaneCenter);
		this.setLeft(gamePaneLeft);
		this.setRight(gamePaneRight);
		gamePaneBottom.setAlignment(Pos.BOTTOM_CENTER);
		gamePaneCenter.setAlignment(Pos.CENTER);
		gamePaneLeft.setAlignment(Pos.TOP_LEFT);
		gamePaneRight.setAlignment(Pos.TOP_RIGHT);
//		windowPatternCard
		if (ownWindow == null) {

		} else {
			gamePaneBottom.getChildren().add(ownWindow);
		}
//		Private Objective card
		gamePaneBottom.getChildren().add(gameController.getPrivate_OCC().getPane());
		
//		Toolcards
		gameController.makeTCC();
		for (int i = 0; i < gameController.getTCC().getPanes().size(); i++) {
			gamePaneBottom.getChildren().add(gameController.getTCC().getPanes().get(i));
		}
//		Public Objective cards
//		TODO: hier komt het aanbod toevoegen
		if (draftpool != null) {
			gamePaneCenter.getChildren().add(draftpool);
		}
		
		
		for (int i = 0; i < gameController.getPublic_OCC().getPanes().size(); i++) {
			gamePaneCenter.getChildren().add(gameController.getPublic_OCC().getPanes().get(i));
		}
//		End turn button
		Button endTurn = new Button("Einde beurt");
		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		gamePaneLeft.getChildren().add(endTurn);
		
		endTurn.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				onClickEndTurn();
			}
		});
		
		
		
//		roundCounter
		Label roundCounter = new Label("Ronde: X");
		roundCounter.setFont(new Font("Arial", 16));
		gamePaneRight.getChildren().add(roundCounter);
	}
	
	
	private void onClickEndTurn() {
		System.out.println("GamePane:");
		System.out.println("End Turn");
		this.gameController.endTurn();
	}

	public void createChoicePane(WindowPatternView card1, WindowPatternView card2, WindowPatternView card3,
			WindowPatternView card4) {
		this.getChildren().clear();

		//TODO: Magic numbers vervangen
		card1.setOnMouseClicked(e -> chooseCardEvent(0));
		card2.setOnMouseClicked(e -> chooseCardEvent(1));
		card3.setOnMouseClicked(e -> chooseCardEvent(2));
		card4.setOnMouseClicked(e -> chooseCardEvent(3));

		card1.setPadding(new Insets(10, 10, 10, 10));
		card2.setPadding(new Insets(10, 10, 10, 10));
		card3.setPadding(new Insets(10, 10, 10, 10));
		card4.setPadding(new Insets(10, 10, 10, 10));
		HBox box = new HBox(card1, card2, card3, card4);
		box.setPadding(new Insets(0, 30, 0, 30));
		this.getChildren().add(box);
	}

	private void chooseCardEvent(int chosenCard) {
		gameController.getC_patternCard().setChosenCard(chosenCard);
	};

	public void setOwnWindow(WindowPatternView window) {
		this.ownWindow = window;
	}
	
	public void setDrafpool (DraftPoolView draftpool) {
		this.draftpool = draftpool;
	}


}
