package View;

import Controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
//		Toolcards
		for (int i = 0; i < gameController.getTCC().getPanes().size(); i++) {
			gamePaneBottom.getChildren().add(gameController.getTCC().getPanes().get(i));
		}
//		Public Objective cards
//		TODO: hier komt het aanbod toevoegen		
		for (int i = 0; i < gameController.getPublic_OCC().getPanes().size(); i++) {
			gamePaneCenter.getChildren().add(gameController.getPublic_OCC().getPanes().get(i));
		}
//		End turn button
		Button endTurn = new Button("Einde beurt");
		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		gamePaneLeft.getChildren().add(endTurn);
//		roundCounter
		Label roundCounter = new Label("Ronde: X");
		roundCounter.setFont(new Font("Arial", 16));
		gamePaneRight.getChildren().add(roundCounter);
	}

//	public void createGamePane() {
//		this.getChildren().clear();
//		Rectangle2D psb = Screen.getPrimary().getVisualBounds();
//
//		Pane window = new Pane();
//		if (ownWindow == null) {
//			// PlaceHolder Eigen RaamPatroon
//			Rectangle eigenRaamPatroon = new Rectangle();
//			eigenRaamPatroon.setY(psb.getMaxY() - (HEIGHTODDPLAYER * 0.5));
//			eigenRaamPatroon.setX(psb.getMaxX() * 0.5 - (WIDTHODDPLAYER * 0.5));
//			eigenRaamPatroon.setWidth(WIDTHODDPLAYER);
//			eigenRaamPatroon.setHeight(HEIGHTODDPLAYER);
//			window.getChildren().add(eigenRaamPatroon);
//		} else {
//			window.setLayoutX(psb.getMaxX() * 0.50 - 500);
//			window.setLayoutY(psb.getMaxY() - 300);
//			window.getChildren().add(ownWindow);
//		}
//
//		// PlaceHolder RaamPatroon van speler 2
//		Rectangle speler2RaamPatroon = new Rectangle();
//		speler2RaamPatroon.setY(psb.getMaxY() * 0.5 - (HEIGHTEVENPLAYER * 0.5));
//		speler2RaamPatroon.setX(psb.getMinX());
//		speler2RaamPatroon.setWidth(WIDTHEVENPLAYER);
//		speler2RaamPatroon.setHeight(HEIGHTEVENPLAYER);
//
//		// Placeholder raampatroon van speler 3
//		Rectangle speler3RaamPatroon = new Rectangle();
//		speler3RaamPatroon.setY(psb.getMinY());
//		speler3RaamPatroon.setX(psb.getMaxX() * 0.5 - (WIDTHODDPLAYER * 0.5));
//		speler3RaamPatroon.setWidth(WIDTHODDPLAYER);
//		speler3RaamPatroon.setHeight(HEIGHTODDPLAYER);
//
//		// Placeholder raampatroon van speler 4
//		Rectangle speler4RaamPatroon = new Rectangle();
//		speler4RaamPatroon.setY(psb.getMaxY() * 0.5 - (HEIGHTEVENPLAYER * 0.5));
//		speler4RaamPatroon.setX(psb.getMaxX() - WIDTHEVENPLAYER);
//		speler4RaamPatroon.setWidth(WIDTHEVENPLAYER);
//		speler4RaamPatroon.setHeight(HEIGHTEVENPLAYER);
//
//		// Placeholder chatbox
//		Rectangle chatbox = new Rectangle(200, 50, Color.LIGHTSKYBLUE);
//		chatbox.setY(psb.getMaxY());
//		chatbox.setX(psb.getMinX());
//
//		Button endTurn = new Button("Einde beurt");
//		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		this.setLeft(endTurn);
//
//		Label roundCounter = new Label("Ronde: X");
//		roundCounter.setFont(new Font("Arial", 16));
//		this.setRight(roundCounter);
//
//		Rectangle privObjCard = new Rectangle(WIDTHODDPLAYER * 0.5, HEIGHTODDPLAYER * 0.66, Color.SALMON);
//		privObjCard.setY(psb.getMaxY() - (HEIGHTODDPLAYER * 0.34));
//		privObjCard.setX(psb.getMaxX() * 0.5 + WIDTHODDPLAYER * 0.5);
//
//		Rectangle scoreBoard = new Rectangle();
//		scoreBoard.setY(psb.getMaxY() - HEIGHTSCOREBOARD * 0.5);
//		scoreBoard.setX(psb.getMaxX() - WIDTHSCOREBOARD);
//		scoreBoard.setWidth(WIDTHSCOREBOARD);
//		scoreBoard.setHeight(HEIGHTSCOREBOARD);
//		scoreBoard.setFill(Color.BURLYWOOD);
//
//		Rectangle toolCards = new Rectangle();
//		toolCards.setY(psb.getMaxY() - HEIGHTODDPLAYER * 0.34);
//		toolCards.setX(psb.getMaxX() * 0.66);
//		toolCards.setWidth(WIDTHODDPLAYER * 1.5);
//		toolCards.setHeight(HEIGHTODDPLAYER * 0.66);
//		toolCards.setFill(Color.DARKORANGE);
//
//		Rectangle dice = new Rectangle();
//		dice.setY(psb.getMaxY() * 0.5 - DICESIZE * 0.5);
//		dice.setX(psb.getMaxX() * 0.33);
//		dice.setWidth(DICESIZE);
//		dice.setHeight(DICESIZE);
//		dice.setFill(Color.DARKVIOLET);
//
//		Rectangle pubObjCards = new Rectangle();
//		pubObjCards.setY(psb.getMaxY() * 0.5 - PUBOBJCARDSIZE * 0.5);
//		pubObjCards.setX(psb.getMaxX() * 0.5);
//		pubObjCards.setWidth(PUBOBJCARDSIZE * 3);
//		pubObjCards.setHeight(PUBOBJCARDSIZE);
//		pubObjCards.setFill(Color.CRIMSON);
//
//		this.getChildren().addAll(window, speler2RaamPatroon, speler3RaamPatroon, speler4RaamPatroon, chatbox,
//				privObjCard, scoreBoard, toolCards, dice, pubObjCards);
//	}

	public void createChoicePane(WindowPatternView card1, WindowPatternView card2, WindowPatternView card3,
			WindowPatternView card4) {
		this.getChildren().clear();

		card1.setOnMouseClicked(e -> chooseCardEvent(card1));
		card2.setOnMouseClicked(e -> chooseCardEvent(card2));
		card3.setOnMouseClicked(e -> chooseCardEvent(card3));
		card4.setOnMouseClicked(e -> chooseCardEvent(card4));

		card1.setPadding(new Insets(10, 10, 10, 10));
		card2.setPadding(new Insets(10, 10, 10, 10));
		card3.setPadding(new Insets(10, 10, 10, 10));
		card4.setPadding(new Insets(10, 10, 10, 10));
		HBox box = new HBox(card1, card2, card3, card4);
		box.setPadding(new Insets(0, 30, 0, 30));
		this.getChildren().add(box);
	}

	private void chooseCardEvent(WindowPatternView chosenCard) {
		gameController.getC_patternCard().setChosenCard(chosenCard);
	};

	public void setOwnWindow(WindowPatternView window) {
		this.ownWindow = window;
	}
}
