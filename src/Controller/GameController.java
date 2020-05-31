package Controller;

import java.util.ArrayList;

import View.DraftPoolView;
import View.GamePane;
import View.WindowPatternView;
import model.DiceModel;
import model.GameDiceModel;
import model.GameModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class GameController {

	private MySceneController myScene;
	private GamePane gamePane;
	private GameModel gameModel;
	private LogInController c_login;

	private Public_Objective_Card_Controller public_OCC;
	private Private_Objective_Card_Controller private_OCC;
	private ToolCard_Controller TCC;
	private ChatController CC;

	private PlayerController playerController;
	private boolean isTurn;
	private RoundtrackController roundtrackController;

	private DiceModel dice;
	private DraftpoolController draftpoolController;

	private ArrayList<PlayerController> opponents;

	private Boolean publicCardsAdded = false;
	private Boolean toolCardsAdded = false;

	public GameController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;
		this.gameModel = new GameModel();
	}

	public void switchBackToHome() {
		myScene.getMyscene().switchPane(c_login.getC_home().getV_home());
		closeChatThread();

	}

	public void makePublicOC() {
		this.public_OCC = new Public_Objective_Card_Controller(this);
	}

	public Private_Objective_Card_Controller getPrivate_OCC() {
		this.private_OCC = new Private_Objective_Card_Controller(gameModel.getGameId(), c_login.getUsername());
		return private_OCC;
	}

	public ToolCard_Controller getTCC() {
		return TCC;
	}

	public void createGamePane() {
		this.gamePane = new GamePane(this);
		myScene.getMyscene().switchPane(gamePane);

		this.dice = new DiceModel(this);
		this.playerController = new PlayerController(this, gameModel.getGameId(), c_login.getUsername(), true);

		//gamemodel get usernames
		opponents = new ArrayList<>();
		for (String opponentName : gameModel.getOpponentNames(playerController.getPlayerID())) {
			opponents.add(new PlayerController(this, gameModel.getGameId(), opponentName, false));
		}

		//Maak opponents View
		//addOpponentView();

		this.draftpoolController = new DraftpoolController(this);

		this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(), gameModel.getRoundID());
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), false);

		this.checkPlayerTurn();
		System.out.println("isPlayerTurn = " + isTurn);
	}
	public void createGamePane(int oldGameID) {
		this.gameModel.setGameId(oldGameID);
		this.gamePane = new GamePane(this);
		myScene.getMyscene().switchPane(gamePane);

		this.dice = new DiceModel(this);
		System.out.println(c_login.getUsername() + "asbdisgaihgsdiufhsaoiughfsdaihafgsiy");
		this.playerController = new PlayerController(this, gameModel.getGameId(), c_login.getUsername(), true);

		//gamemodel get usernames
		opponents = new ArrayList<>();
		for (String opponentName : gameModel.getOpponentNames(playerController.getPlayerID())) {
			opponents.add(new PlayerController(this, gameModel.getGameId(), opponentName, false));
		}

		//Maak opponents View
		//addOpponentView();

		this.draftpoolController = new DraftpoolController(this);

		this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(), gameModel.getRoundID());
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), false);

		this.checkPlayerTurn();
		System.out.println("isPlayerTurn = " + isTurn);
		this.gamePane.createGamePane();
	}

	public GameDiceModel pickDiceFromBag() {
		// Vraag om een lijst van dobbelstenen die nog in de zak zitten
		ArrayList<GameDiceModel> diceBag = this.dice.getBag();
		// Selecteer willekeurig een dice uit de zak
		GameDiceModel gameDice = diceBag.get(getRandomInt(0, diceBag.size() - 1));
		// Voeg de dice toe aan het diceModel
		this.dice.addDice(gameDice, gameModel.getRoundID());
		// Return de geselecteerde dice
		return gameDice;
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public MySceneController getMyscene() {
		return myScene;
	}

	public PatternCardController getC_patternCard() {
		return this.playerController.getPatternCard();
	}

	public LogInController getC_login() {
		return c_login;
	}

	public GameModel getM_game() {
		return gameModel;
	}

	public boolean placeDice(GameDiceModel dice) {
		return this.playerController.getPatternCard().placeDice(dice);
//		System.out.println("GameController:");
//		System.out.println(dice.getDieNumber());
	}

	private int getRandomInt(int min, int max) {
		return (int) Math.floor((Math.random() * ((max - min) + 1)) + min);
	}

	public void endTurn() {
		this.playerController.updatePlayerFrameField();

		int roundID = gameModel.getRoundID();
		int playerID = gameModel.getTurnPlayerID();
		int playerSeqnr = gameModel.getSeqNR(playerID);
		int highestSeqnr = gameModel.getHighestSeqnr();
		boolean isClockwise = gameModel.getClockwise();

		if (isClockwise) {
			if (playerSeqnr == highestSeqnr) {
				// RoundID + 1 wanneer < 20 veranderen
				gameModel.setRoundID();
				// Zelfde speler blijft aan de beurt
			} else {
				// Turn player id aanpassen
				// met playerSeqnr + 1
				int newPlayerID = gameModel.getPlayerID(playerSeqnr + 1);
				gameModel.changeTurnPlayerID(newPlayerID);
			}
		} else {
			if (playerSeqnr == 1) {
				// Dobbelstenen wegschrijven naar rondespoor
				System.out.println("GAMECONTROLLER MOVE TO ROUND TRACK");
				System.out.println("ROUND ID = " + gameModel.getRoundID());
				System.out.println("Round NUMBER = " + gameModel.getRoundNR());
				this.draftpoolController.moveToRoundtrack(gameModel.getRoundNR());
				this.roundtrackController.fillRoundtrack();

				// Make new draftpool
				this.draftpoolController.createDraftPool(highestSeqnr, gameModel.getRoundID());
				gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), true);

				// RoundID + 1 wanneer < 20 veranderen
				if (roundID == 20) {
					// Set playerstatus = finished
					// Tel punten
					// bepaal winnaar
				} else {
					// Nieuwe segNr zetten
					System.out.println("UPDATE SEQNR");
					int[] playerIDs = new int[highestSeqnr];
					for (int i = 0; i < playerIDs.length; i++) {
						playerIDs[i] = gameModel.getPlayerID(i + 1);
					}

					for (int j = 0; j < playerIDs.length; j++) {
						if (j == 0) {
							gameModel.updateSeqNR(playerIDs[j], highestSeqnr);
						} else {
							gameModel.updateSeqNR(playerIDs[j], j);
						}
					}

//					for (int i = 1; i < highestSeqnr; i++) {
//						int player = gameModel.getPlayerID(i);
//						if (i == 1) {
//							gameModel.updateSeqNR(player, highestSeqnr);
//						} else {
//							gameModel.updateSeqNR(player, i--);
//						}
//					}

					// Nieuwe turn speler met seqnr 1
					int newPlayerID = gameModel.getPlayerID(1);
					gameModel.changeTurnPlayerID(newPlayerID);
					gameModel.setRoundID();
				}
			} else {
				// Turn player id aanpassen
				// met playerSeqnr - 1
				int newPlayerID = gameModel.getPlayerID(playerSeqnr - 1);
				gameModel.changeTurnPlayerID(newPlayerID);
			}
		}

		checkPlayerTurn();
		System.out.println("isPlayerTurn = " + isTurn);
	}

	public void checkPlayerTurn() {
		if (gameModel.getTurnPlayerID() == playerController.getPlayerID()) {
			this.isTurn = true;
		} else {
			this.isTurn = false;
		}
		this.gamePane.updateIsTurn(isTurn);
	}

	public DraftpoolController getDraftpoolController() {
		return draftpoolController;
	}

	public void makeTCC() {
		this.TCC = new ToolCard_Controller(this);
	}

	public Public_Objective_Card_Controller getPublic_OCC() {
		return public_OCC;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}
	
	public Boolean toolCardExists() {
		if(this.getTCC().getEmpty(gameModel.getGameId())) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public Boolean publicObjectiveCardExists() {
		if(this.getPublic_OCC().getEmpty(gameModel.getGameId())) {
			return false;
		}else {
			return true;
		}
	}

	public void refresh() {
		System.out.println("refreshknop: " + gameModel.getGameId());
		if (getIsTurn() == false) {
			this.draftpoolController.loadDice(gameModel.getGameId());
			gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), true);
		}

		checkPlayerTurn();
		this.roundtrackController.fillRoundtrack();
		//this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(), gameModel.getRoundID());

		for (PlayerController opponent : opponents) {
			opponent.getPatternCard().loadChosenCard();
		}

		loadOpponent();

		//Laad huidige speler moet nog goed getest worden
		playerController.getPatternCard().loadPatternCard();
		// this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(),
		// gameModel.getRoundID());
		this.draftpoolController.loadDice(gameModel.getGameId());
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), true);

//		toolcards
		if (toolCardsAdded == false) {
			this.getTCC().getCards(gameModel.getGameId());
			for (int i = 0; i < this.getTCC().getPanes().size(); i++) {
				gamePane.getGamePaneBottom().getChildren().add(this.getTCC().getPanes().get(i));

			}
			toolCardsAdded = true;
		}

//		Public objective cards
		else if (publicCardsAdded == false) {
			this.getPublic_OCC().getCards(gameModel.getGameId());
			for (int i = 0; i < this.getPublic_OCC().getPanes().size(); i++) {
				gamePane.getGamePaneCenter().getChildren().add(this.getPublic_OCC().getPanes().get(i));
			}
			publicCardsAdded = true;
		}
		if(toolCardExists() && publicObjectiveCardExists()) {
			this.gamePane.getEndTurn().setVisible(true);
		}

	}
	
	public void resetCardBooleans() {
		toolCardsAdded = false;
		publicCardsAdded = false;
	}

	public boolean getIsTurn() {
		return this.isTurn;
	}

	public RoundtrackController getRoundtrackController() {
        this.roundtrackController = new RoundtrackController(gameModel.getGameId());
        return roundtrackController;
    }
	public ChatController makeCC() {
		return this.CC = new ChatController(this);
	}

	public void closeChatThread() {
		CC.getThread().getModel().getDBCOn().closeConnection();
		CC.getThread().terminate();
	}



	public void loadOpponent () {
		int counter = 0;
		for (PlayerController opponent : opponents) {

			if (opponent.getPatternCard().getChosenCard() != null) {
				gamePane.setOpponentWindow(counter, opponent.getPatternCard().makeView(opponent.getPatternCard().getChosenCard()));
				opponent.getPatternCard().loadPatternCard();
			} else {
				System.out.println("PANIEK PANIEK PANIEK!");
			}
			counter++;
		}
	}

	public int getNumOpponents() {
		return opponents.size();
	}

//	public void addOpponentView () {
//		for (PlayerController opponent : opponents) {
//			if (opponent.getPatternCard().getChosenCard() != null) {
//				gamePane.addOpponentWindow(opponent.getPatternCard().makeView(opponent.getPatternCard().getChosenCard()));
//			} else {
//				//add Black rectangle
//				gamePane.addOpponentSquare();
//			}
//		}
//	}

	public int getseqNumber() {
		return gameModel.getSeqNR(playerController.getPlayerID());
	}

	public int getGameRound() {
		return gameModel.getRoundNR();
	}

	public void setOwnWindow(PatternCardModel chosenCard, PatternCardController patternCardController) {
		gamePane.setOwnWindow(new WindowPatternView(450, 300, chosenCard.nameProperty(), chosenCard.tokenAmount(),
					this.makeSquareView(chosenCard.getField(), patternCardController)));
		
	}
	public WindowPatternSquareController[][] makeSquareView(PatternCardFieldModel[][] field, PatternCardController patternCardController ) {
		WindowPatternSquareController[][] fieldController = new WindowPatternSquareController[5][4];

		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y] = new WindowPatternSquareController(this, patternCardController, field[x][y]);
			}
		}
		return fieldController;
	}

	public int getCurrentPlayerID() {
		// TODO Auto-generated method stub
		return playerController.getPlayerID();
	}
}
