package View;

//import Controller.DraftpoolController;
import Controller.GameController;
import Controller.HomeThreadController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	private Button home;
	private Label isTurn;

	private HBox gamePaneCenter;
	private HBox gamePaneTop;
	private VBox gamePaneRight;
	
	

	public GamePane(GameController gameController) {
		this.gameController = gameController;
		this.setMinSize(900, 900);
		this.setPrefSize(900, 900);
		this.setMaxSize(900, 900);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		// createGamePane();
	}

	public GamePane() {
		this.setMinSize(900, 900);
		this.setPrefSize(900, 900);
		this.setMaxSize(900, 900);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		// createGamePane();
	}

//	public void createGamePane() {
//		this.getChildren().clear();
//		HBox gamePaneBottom = new HBox();
//		HBox gamePaneCenter = new HBox();
//		VBox gamePaneLeft = new VBox();
//		VBox gamePaneRight = new VBox();
//		this.setBottom(gamePaneBottom);
//		this.setCenter(gamePaneCenter);
//		this.setLeft(gamePaneLeft);
//		this.setRight(gamePaneRight);
//		gamePaneBottom.setAlignment(Pos.BOTTOM_CENTER);
//		gamePaneCenter.setAlignment(Pos.CENTER);
//		gamePaneLeft.setAlignment(Pos.TOP_LEFT);
//		gamePaneRight.setAlignment(Pos.TOP_RIGHT);
////		windowPatternCard
//		if (ownWindow == null) {
//
//		} else {
//			gamePaneBottom.getChildren().add(ownWindow);
//		}
////		Private Objective card
//		gamePaneBottom.getChildren().add(gameController.getPrivate_OCC().getPane());
//
////		Public Objective cards
////		TODO: hier komt het aanbod toevoegen
////		if (draftpool != null) {
////			gamePaneCenter.getChildren().add(draftpool);
////		}
////
////
////		for (int i = 0; i < gameController.getPublic_OCC().getPanes().size(); i++) {
////			gamePaneCenter.getChildren().add(gameController.getPublic_OCC().getPanes().get(i));
////		}
////		End turn button
//		Button endTurn = new Button("Einde beurt");
//		Button home = new Button("home");
//		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		home.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		home.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		home.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
//		
//		
//		
//		gamePaneLeft.getChildren().addAll(endTurn, home);
//
//		endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				onClickEndTurn();
//			}
//		});
//
////		roundCounter
//		Label roundCounter = new Label("Ronde: X");
//		roundCounter.setFont(new Font("Arial", 16));
//		gamePaneRight.getChildren().add(roundCounter);
//	}

	public void createGamePane2() {
		this.getChildren().clear();
		HBox gamePaneBottom = new HBox();

		gamePaneCenter = new HBox();
		VBox gamePaneLeft = new VBox();
		gamePaneRight = new VBox();
		gamePaneTop = new HBox();
		this.setBottom(gamePaneBottom);
		this.setCenter(gamePaneCenter);
		this.setLeft(gamePaneLeft);
		this.setRight(gamePaneRight);
		this.setTop(gamePaneTop);
		gamePaneBottom.setAlignment(Pos.BOTTOM_CENTER);
		gamePaneCenter.setAlignment(Pos.CENTER);
		gamePaneLeft.setAlignment(Pos.TOP_LEFT);
		gamePaneRight.setAlignment(Pos.TOP_RIGHT);
		gamePaneTop.setAlignment(Pos.CENTER);

		for (int i = 0; i < gameController.getNumOpponents(); i++) {
			addOpponentSquare();
		}

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

		if (draftpool != null) {
			gamePaneCenter.getChildren().add(draftpool);
		}

//		Public Objective cards	
		gameController.makePublicOC();

		for (int i = 0; i < gameController.getPublic_OCC().getPanes().size(); i++) {
			gamePaneCenter.getChildren().add(gameController.getPublic_OCC().getPanes().get(i));
		}
//		Chat
		gameController.makeCC();
		gamePaneBottom.getChildren().add(gameController.makeCC().getPane());

//		End turn button
		Button endTurn = new Button("Einde beurt");
		home = new Button("home");
		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		home.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		home.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		home.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);

		Button refresh = new Button("Refresh");
		refresh.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		refresh.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		refresh.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);

		gamePaneLeft.getChildren().addAll(endTurn, home, refresh);

		home.setOnMouseClicked(e -> {
			gameController.switchBackToHome();
			HomeThreadController home = new HomeThreadController(gameController.getC_login(),
					gameController.getC_login().getC_home().getC_Invite());
			home.start();
		});

		endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (gameController.getIsTurn()) {
					onClickEndTurn();
				}
			}
		});

		refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gameController.refresh();
			}
		});

		gamePaneRight.getChildren().add(gameController.getRoundtrackController().getRoundtrackPane());

		isTurn = new Label("");
		isTurn.setFont(new Font("Arial", 32));
		isTurn.setTextFill(Color.WHITE);
		updateIsTurn(gameController.getIsTurn());
		gamePaneRight.getChildren().addAll(isTurn);

		// Punten telling
		Label myPersonalScoreLabel = new Label("Mijn persoonlijke score:");
		myPersonalScoreLabel.setFont(new Font("Arial", 32));
		myPersonalScoreLabel.setTextFill(Color.WHITE);
		gamePaneRight.getChildren().add(myPersonalScoreLabel);
		gamePaneRight.getChildren().add(makePrivateScoreLabel());

		Label publicScore = new Label("Publieke score:");
		publicScore.setFont(new Font("Arial", 32));
		publicScore.setTextFill(Color.WHITE);
		gamePaneRight.getChildren().add(publicScore);
		for (int i = 0; i < gameController.getNumOpponents(); i++) {
			gamePaneRight.getChildren().add(publicScoreLabel(i));
		}

		// load Opponents
	}
	
	public void updateScore () {
		this.gamePaneRight.getChildren().set(3, makePrivateScoreLabel());
		
		int index = 5;
		
		for (int i = 0; i < gameController.getNumOpponents(); i++) {
			this.gamePaneRight.getChildren().set(index + i, publicScoreLabel(i));
		}
	}
	
	private Label makePrivateScoreLabel () {
		String name = gameController.getCurrentPlayerName();
		int score = gameController.getPrivateScore();
		Label privateScoreLabel = new Label(name + ": " + score);
		privateScoreLabel.setFont(new Font("Arial", 32));
		privateScoreLabel.setTextFill(Color.WHITE);
		return privateScoreLabel;
	}

	public void updateIsTurn(boolean isTurn) {
		if (this.isTurn != null) {
			if (isTurn) {
				this.isTurn.setText("Het is jouw beurt!");
			} else {
				this.isTurn.setText("Het is niet jouw beurt :,(!");
			}
		}
	}

	private void onClickEndTurn() {
//		System.out.println("GamePane:");
//		System.out.println("End Turn");
		this.gameController.endTurn();
	}

	public void createChoicePane(WindowPatternView card1, WindowPatternView card2, WindowPatternView card3,
			WindowPatternView card4) {
		this.getChildren().clear();

		// TODO: Magic numbers vervangen
		card1.setOnMouseClicked(e -> chooseCardEvent(0));
		card2.setOnMouseClicked(e -> chooseCardEvent(1));
		card3.setOnMouseClicked(e -> chooseCardEvent(2));
		card4.setOnMouseClicked(e -> chooseCardEvent(3));

		card1.setPadding(new Insets(10));
		card2.setPadding(new Insets(10));
		card3.setPadding(new Insets(10));
		card4.setPadding(new Insets(10));
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

	public void setOpponentWindow(int index, WindowPatternView opponentCard) {
		gamePaneTop.getChildren().set(index, opponentCard);
	}

	public void addOpponentWindow(WindowPatternView opponentCard) {
		gamePaneTop.getChildren().add(opponentCard);
	}

	public void addOpponentSquare() {
		Pane opponentSquare = new Pane();
		CornerRadii RADIUS = new CornerRadii(10.00);
		opponentSquare.setPrefSize(350, 250);
		opponentSquare.setMaxSize(350, 250);
		opponentSquare.setBackground(new Background(new BackgroundFill(Color.BLACK, RADIUS, null)));
		gamePaneTop.getChildren().add(opponentSquare);
	}

	public Label publicScoreLabel(int index) {
		String name = gameController.getOpponentName(index);
		int publicScore = gameController.publicScore(index);
		Label score = new Label(name + ": " + publicScore);
		score.setFont(new Font("Arial", 32));
		score.setTextFill(Color.WHITE);
		return score;
	}

	public void setDrafpool(DraftPoolView draftpool, boolean replace) {
		this.draftpool = draftpool;
		if (replace) {
			gamePaneCenter.getChildren().set(0, draftpool);
		}
	}

	public Button getHome() {
		return home;
	}

}
