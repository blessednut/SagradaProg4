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
	private Button home;
	private Label isTurn;
	private HBox gamePaneBottom;
	private VBox gamePaneLeft;
	private VBox gamePaneRight;
	private Button shuffleToolcards;
	private Button shufflePublicObjectiveCards;
	


	HBox gamePaneCenter;

	public GamePane(GameController gameController) {
		this.gameController = gameController;
		this.setMinSize(900, 900);
		this.setPrefSize(900, 900);
		this.setMaxSize(900, 900);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		//createGamePane();
	}

	public GamePane() {
		this.setMinSize(900, 900);
		this.setPrefSize(900, 900);
		this.setMaxSize(900, 900);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
		//createGamePane();
	}


	public void createGamePane() {
		this.getChildren().clear();
		gamePaneBottom = new HBox();
		gamePaneCenter = new HBox();
		gamePaneLeft = new VBox();
		gamePaneRight = new VBox();
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


		if (draftpool != null) {
			gamePaneCenter.getChildren().add(draftpool);
		}
		
//		Public Objective cards
		gameController.makePublicOC();


//		End turn button
		Button endTurn = new Button("Einde beurt");
		home = new Button("home");
		endTurn.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		endTurn.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		home.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		home.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		home.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		
		
		Button refresh = new Button("ververs");
		refresh.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		refresh.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		refresh.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		
		shuffleToolcards = new Button("pak gereedschaps kaarten");
		shuffleToolcards.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		shuffleToolcards.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		shuffleToolcards.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		if(gameController.getseqNumber() == 1 && gameController.getGameRound() == 1) {
			shuffleToolcards.setVisible(true);
		}
		else {
			shuffleToolcards.setVisible(false);
		}
		
		shufflePublicObjectiveCards = new Button("pak publieke doelkaarten");
		shufflePublicObjectiveCards.setMaxSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		shufflePublicObjectiveCards.setMinSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		shufflePublicObjectiveCards.setPrefSize(WIDTHENDTURNBUTTON, HEIGHTENDTURNBUTTON);
		if(gameController.getseqNumber() == 1 && gameController.getGameRound() == 1) {
			shufflePublicObjectiveCards.setVisible(true);
		}
		else {
			shufflePublicObjectiveCards.setVisible(false);
		}
		
		gamePaneLeft.getChildren().addAll(endTurn, home, refresh, shuffleToolcards, shufflePublicObjectiveCards);
		
		home.setOnMouseClicked(e -> {
			gameController.switchBackToHome();
			HomeThreadController home = new HomeThreadController(gameController.getC_login(), gameController.getC_login().getC_home().getC_Invite());
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
		
		shuffleToolcards.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gameController.getTCC().getCards();
				shuffleToolcards.setVisible(false);
			}
		});
		shufflePublicObjectiveCards.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gameController.getPublic_OCC().getCards();
				shufflePublicObjectiveCards.setVisible(false);
			}
		});

		gamePaneRight.getChildren().add(gameController.getRoundtrackController().getRoundtrackPane());
		
		isTurn = new Label ("");
		isTurn.setFont(new Font("Arial", 32));
		isTurn.setTextFill(Color.WHITE);
		updateIsTurn(gameController.getIsTurn());
		gamePaneRight.getChildren().addAll(isTurn);
	}
	


	public void updateIsTurn (boolean isTurn) {
		if (this.isTurn != null) {
			if (isTurn) {
				this.isTurn.setText("Het is jouw beurt!");
			} else {
				this.isTurn.setText("Het is niet jouw beurt :,(!");
			}
		}
	}

	private void onClickEndTurn() {
		System.out.println("GamePane:");
		System.out.println("End Turn");
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

	public void setDrafpool(DraftPoolView draftpool, boolean replace) {
		this.draftpool = draftpool;
		if (replace) { 
			gamePaneCenter.getChildren().set(0, draftpool);
		}
	}
	public HBox getGamePaneBottom() {
		return gamePaneBottom;
	}

	public VBox getGamePaneLeft() {
		return gamePaneLeft;
	}

	public VBox getGamePaneRight() {
		return gamePaneRight;
	}

	public Button getHome() {
		return home;
	}
	
	public HBox getGamePaneCenter() {
		return gamePaneCenter;
	}
	public Button getShuffleToolcards() {
		return shuffleToolcards;
	}
	public Button getShufflePublicObjectivecards() {
		return shufflePublicObjectiveCards;
	}

	

}
