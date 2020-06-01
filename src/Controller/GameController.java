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

	private int amountOfDice = 0;

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

		// gamemodel get usernames
		opponents = new ArrayList<>();
		for (String opponentName : gameModel.getOpponentNames(playerController.getPlayerID())) {
			opponents.add(new PlayerController(this, gameModel.getGameId(), opponentName, false));
		}

		// Maak opponents View
		// addOpponentView();
		int draftPoolRoundID = gameModel.getRoundID();
		if (draftPoolRoundID % 2 == 0) {
			draftPoolRoundID = draftPoolRoundID - 1;
		}
		this.draftpoolController = new DraftpoolController(this);
		this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(), draftPoolRoundID);
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), false);

		this.checkPlayerTurn();
		System.out.println("isPlayerTurn = " + isTurn);
	}

	public void createGamePane(int oldGameID) {
		System.out.println("CREATEGAMEPANE MET OLD ID = " + oldGameID);
		this.gameModel.setGameId(oldGameID);
		this.gamePane = new GamePane(this);
		myScene.getMyscene().switchPane(gamePane);

		this.dice = new DiceModel(this);
		this.playerController = new PlayerController(this, gameModel.getGameId(), c_login.getUsername(), true, true);
		playerController.loadCards();
		System.out.println("OUDE SPEL STARTEN SPELER ID = " + playerController.getPlayerID());
		// playerController.loadDice

		// gamemodel get usernames
		opponents = new ArrayList<>();
		for (String opponentName : gameModel.getOpponentNames(playerController.getPlayerID())) {
			opponents.add(new PlayerController(this, gameModel.getGameId(), opponentName, false));
		}

		// Maak opponents View
		int draftPoolRoundID = gameModel.getRoundID();
		if (draftPoolRoundID % 2 == 0) {
			draftPoolRoundID = draftPoolRoundID - 1;
		}
		this.draftpoolController = new DraftpoolController(this);
		this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(), draftPoolRoundID);
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), false);

		this.checkPlayerTurn();
		System.out.println("isPlayerTurn = " + isTurn);
		this.gamePane.createGamePane();
		playerController.getPatternCard().reloadDice();
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

		if (this.amountOfDice == 0) {
			if (this.playerController.getPatternCard().placeDice(dice)) {
				amountOfDice++;
				this.setEndTurnText();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

//		return this.playerController.getPatternCard().placeDice(dice);
//		System.out.println("GameController:");
//		System.out.println(dice.getDieNumber());
	}

	private int getRandomInt(int min, int max) {
		return (int) Math.floor((Math.random() * ((max - min) + 1)) + min);
	}

	public void endTurn() {
		this.amountOfDice = 0;
		this.TCC.setUseCardInTurn(false);
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
		} else { // <--- Goed
			if (playerSeqnr == 1) {
				// Dobbelstenen wegschrijven naar rondespoor
				this.draftpoolController.moveToRoundtrack(gameModel.getRoundNR());
				this.roundtrackController.fillRoundtrack();

				// RoundID + 1 wanneer < 20 veranderen
				if (roundID == 20) {
					// Set playerstatus = finished
					gameModel.setPlayStatusFinished(playerController.getPlayerID());
					for (PlayerController opponent : this.opponents) {
						gameModel.setPlayStatusFinished(opponent.getPlayerID());
					}
					// Count Score
					// Current player
					gameModel.updateScore(playerController.getPlayerID(),
							this.getPrivateScore(this.getCurrentPlayerName()));
					// Opponents
					for (PlayerController opponent : this.opponents) {
						int id = opponent.getPlayerID();
						String name = gameModel.getPlayerName(id);
						int score = this.getPrivateScore(name);
						gameModel.updateScore(id, score);
					}
					// Bepaal winnaar
					gamePane.showWinner(gameModel.getWinner());
				} else {
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

					// Nieuwe turn speler met seqnr 1
					int newPlayerID = gameModel.getPlayerID(1);
					gameModel.changeTurnPlayerID(newPlayerID);
					gameModel.setRoundID();
				}
				this.draftpoolController.createDraftPool(highestSeqnr, gameModel.getRoundID());
				gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), true);
			} else {
				int newPlayerID = gameModel.getPlayerID(playerSeqnr - 1);
				gameModel.changeTurnPlayerID(newPlayerID);
			}
		}
		checkPlayerTurn();
		// this.refresh();
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
		if (this.getTCC().getEmpty(gameModel.getGameId())) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean publicObjectiveCardExists() {
		if (this.getPublic_OCC().getEmpty(gameModel.getGameId())) {
			return false;
		} else {
			return true;
		}
	}

	public void refresh() {
		this.amountOfDice = 0;
		this.setEndTurnText();
		if (gameModel.gameEnded()) {
			this.isTurn = false;
		}

		if (getIsTurn() == false) {
			this.draftpoolController.loadDice(gameModel.getGameId());
			gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()), true);
		}

		checkPlayerTurn();
		this.roundtrackController.fillRoundtrack();
		// this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(),
		// gameModel.getRoundID());

		for (PlayerController opponent : opponents) {
			opponent.getPatternCard().loadChosenCard();
		}

		loadOpponent();

		// Laad huidige speler moet nog goed getest worden
		playerController.getPatternCard().loadPatternCard();
		gamePane.updateScore();
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
			// toolCardsAdded = true;
		}

//		Public objective cards
		else if (publicCardsAdded == false) {
			this.getPublic_OCC().getCards(gameModel.getGameId());
			for (int i = 0; i < this.getPublic_OCC().getPanes().size(); i++) {
				gamePane.getGamePaneCenter().getChildren().add(this.getPublic_OCC().getPanes().get(i));
			}
			// publicCardsAdded = true;
		}
		if (toolCardExists() && publicObjectiveCardExists()) {
			this.gamePane.getEndTurn().setVisible(true);
		}
	}

	public void resetCardBooleans() {
		toolCardsAdded = false;
		publicCardsAdded = false;
	}

	public void setToolCardsAdded(boolean bool) {
		this.toolCardsAdded = bool;
	}

	public void setPublicCardsAdded(boolean bool) {
		this.publicCardsAdded = bool;
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

	public void loadOpponent() {
		int counter = 0;
		for (PlayerController opponent : opponents) {

			if (opponent.getPatternCard().getChosenCard() != null) {
				gamePane.setOpponentWindow(counter,
						opponent.getPatternCard().makeView(opponent.getPatternCard().getChosenCard()));
				opponent.getPatternCard().loadPatternCard();
			} else {
				// System.out.println("PANIEK PANIEK PANIEK!");
			}
			counter++;
		}
	}

	public int getNumOpponents() {
		return opponents.size();
	}

	public String getOpponentName(int index) {
		return gameModel.getPlayerName(opponents.get(index).getPlayerID());
	}

	public int publicScore(int index) {
		String privateObj = private_OCC.getColor(gameModel.getGameId(), this.getOpponentName(index));
		String[] publicObj = public_OCC.getNames(gameModel.getGameId());
		PuntenTeller punt = new PuntenTeller(this);

		if (this.opponents.get(index) != null && this.opponents.get(index).getPatternCard().getChosenCard() != null) {
			int score = punt.getPublicScore(privateObj, publicObj, this.opponents.get(index));
			System.out.println("PUBLIC SCORE = " + score);
			return score;
		} else {
			System.out.println("SCORE IS 0000");
			return 0;
		}
	}

	public String getCurrentPlayerName() {
		return this.gameModel.getPlayerName(playerController.getPlayerID());
	}

	public int getPrivateScore(String name) {
		PuntenTeller punt = new PuntenTeller(this);
		String privateObj = private_OCC.getColor(gameModel.getGameId(), getCurrentPlayerName());
		String[] publicObj = public_OCC.getNames(gameModel.getGameId());

		if (this.playerController.getPatternCard().getChosenCard() != null) {
			return punt.getTotalScore(privateObj, publicObj, playerController);
		} else {
			return 0;
		}
	}

//	public int getSeqNrFromIndex (int index) {
//		//opponents.get(index).getPlayerID();
//		return gameModel.getSeqNR(opponents.get(index).getPlayerID());
//	}

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

	public void setEndTurnText() {
		if (this.amountOfDice == 0) {
			this.gamePane.setEndTurnText(false);
		} else {
			this.gamePane.setEndTurnText(true);
		}
	}

	public void setOwnWindow(PatternCardModel chosenCard, PatternCardController patternCardController) {
		WindowPatternSquareController[][] squareController = null;
		squareController = playerController.getPatternCard().getSquareController();
		gamePane.setOwnWindow(
				new WindowPatternView(450, 300, chosenCard.nameProperty(), chosenCard.tokenAmount(), squareController));
	}

	public int getCurrentPlayerID() {
		return playerController.getPlayerID();
	}
}
