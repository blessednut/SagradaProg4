package Controller;

import java.util.ArrayList;
import java.util.Random;

import View.DraftPoolView;
import View.GamePane;
import View.TokenPane;
import View.ToolCard;
import View.WindowPatternView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.DiceModel;
import model.GameDiceModel;
import model.GameModel;
import model.PatternCardModel;

public class GameController {

	private MySceneController mySceneController;
	private GamePane gamePane;
	private GameModel gameModel;
	private LogInController logInController;

	private Public_Objective_Card_Controller public_OCC;
	private Private_Objective_Card_Controller private_OCC;
	private ToolCard_Controller TCC;
	private ChatController CC;

	private PlayerController playerController;
	//TODO gebruik deze boolean voor de ingamethread.
	private boolean isTurn;
	private RoundtrackController roundtrackController;

	private DiceModel dice;
	private DraftpoolController draftpoolController;

	private ArrayList<PlayerController> opponents;

	private Boolean publicCardsAdded = false;
	private Boolean toolCardsAdded = false;

	private int amountOfDice = 0;
	private TokenController tokenController;
	private ArrayList<Integer> playerColors;

	private InGameThread inGameThread;

	public GameController(MySceneController mySceneController, LogInController logInController) {
		playerColors = new ArrayList<Integer>();
		this.mySceneController = mySceneController;
		this.logInController = logInController;
		inGameThread = new InGameThread(this);
		this.gameModel = new GameModel();
		mySceneController.getMyscene().addEventHandler(KeyEvent.KEY_PRESSED, new MyKeyHandler());


	}
	private class MyKeyHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ESCAPE) {
				inGameThread.terminateThread();
				Platform.exit();

			}
		}

	}

	public void switchBackToHome() {
		mySceneController.getMyscene().switchPane(logInController.getC_home().getV_home());
		closeChatThread();

	}

	public void makePublicOC() {
		this.public_OCC = new Public_Objective_Card_Controller(this);
	}

	public Private_Objective_Card_Controller getPrivate_OCC() {
		this.private_OCC = new Private_Objective_Card_Controller(gameModel.getGameId(), logInController.getUsername());
		return private_OCC;
	}

	public ToolCard_Controller getTCC() {
		return TCC;
	}

	public void createGamePane() {
		playerColors.clear();
		this.gamePane = new GamePane(this);
		mySceneController.getMyscene().switchPane(gamePane);

		this.dice = new DiceModel(this);
		this.playerController = new PlayerController(this, gameModel.getGameId(), logInController.getUsername(), true,
				generateRandomColor());

		// gamemodel get usernames
		opponents = new ArrayList<>();

		for (String opponentName : gameModel.getOpponentNames(playerController.getPlayerID())) {
			opponents
					.add(new PlayerController(this, gameModel.getGameId(), opponentName, false, generateRandomColor()));

		}

		// Maak opponents View
		int draftPoolRoundID = gameModel.getRoundID();
		if (draftPoolRoundID % 2 == 0) {
			draftPoolRoundID = draftPoolRoundID - 1;
		}
		this.draftpoolController = new DraftpoolController(this);
		this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(), draftPoolRoundID);
		gamePane.setDrafpool(new DraftPoolView(200, 200, draftpoolController.getDraftPool()), false);

		inGameThread.start();
//		inGameThread.setDaemon(true);
		this.checkPlayerTurn();
	}

	//if (PatternCard Al gekozen?) {
	//Laad alles normaal
//} else {
//	if (Zijn er opties gegenereerd?) {
//		//Laad opties
//	} else {
//		//Maak opties
//	}
//}

	public void createGamePane(int oldGameID) {
		playerColors.clear();
		this.gameModel.setGameId(oldGameID);

		//Check moet er nog keuzes worden weergegeven
		if (gameModel.isPatternCardChosen(gameModel.getPlayerID(logInController.getUsername(), oldGameID))) {
			this.gamePane = new GamePane(this);
			mySceneController.getMyscene().switchPane(gamePane);

			this.dice = new DiceModel(this);
			this.playerController = new PlayerController(this, gameModel.getGameId(), logInController.getUsername(), true,
					true, generateRandomColor());
			playerController.loadCards();

			// playerController.loadDice

			// gamemodel get usernames
			opponents = new ArrayList<>();

			for (String opponentName : gameModel.getOpponentNames(playerController.getPlayerID())) {
				opponents
						.add(new PlayerController(this, gameModel.getGameId(), opponentName, false, generateRandomColor()));
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
		} else {
			this.createGamePane();
		}
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
		return mySceneController;
	}

	public PatternCardController getC_patternCard() {
		return this.playerController.getPatternCard();
	}

	public LogInController getC_login() {
		return logInController;
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
				gamePane.setDrafpool(new DraftPoolView(200, 200, draftpoolController.getDraftPool()), true);
			} else {
				int newPlayerID = gameModel.getPlayerID(playerSeqnr - 1);
				gameModel.changeTurnPlayerID(newPlayerID);
			}
		}
		checkPlayerTurn();
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

	public Color generateRandomColor() {
		Color playerColor = null;
		Random rand = new Random();
		int max = 5;
		int min = 1;
		while (true) {
			int playerColorNumber = rand.nextInt((max - min) + 1) + min;
			if (!playerColors.contains(playerColorNumber)) {
				if (playerColorNumber == 1) {
					playerColors.add(playerColorNumber);
					playerColor = Color.GREEN;
				} else if (playerColorNumber == 2) {
					playerColors.add(playerColorNumber);
					playerColor = Color.BLUE;
				} else if (playerColorNumber == 3) {
					playerColors.add(playerColorNumber);
					playerColor = Color.YELLOW;
				} else if (playerColorNumber == 4) {
					playerColors.add(playerColorNumber);
					playerColor = Color.HOTPINK;
				} else if (playerColorNumber == 5) {
					playerColors.add(playerColorNumber);
					playerColor = Color.DARKRED;
				}
				break;
			}
		}
		return playerColor;

	}

	public void refresh() {
		this.amountOfDice = 0;
		this.setEndTurnText();
		if (gameModel.gameEnded()) {
			this.isTurn = false;
		}

		if (getIsTurn() == false) {
			this.draftpoolController.loadDice(gameModel.getGameId());
			gamePane.setDrafpool(new DraftPoolView(200, 200, draftpoolController.getDraftPool()), true);
		}

		checkPlayerTurn();
		this.roundtrackController.fillRoundtrack();
		// this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(),
		// gameModel.getRoundID());

		for (PlayerController opponent : opponents) {
			opponent.getPatternCard().loadChosenCard();
		}
		//System.out.println();

		loadOpponent();

		// Laad huidige speler moet nog goed getest worden
		playerController.getPatternCard().loadPatternCard();
		gamePane.updateScore();
		// this.draftpoolController.createDraftPool(gameModel.getHighestSeqnr(),
		// gameModel.getRoundID());
		this.draftpoolController.loadDice(gameModel.getGameId());
		gamePane.setDrafpool(new DraftPoolView(200, 200, draftpoolController.getDraftPool()), true);

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

//	tokens
		loadTokens();
	}

	public void loadTokens() {
		this.getTokenController().reloadRemainingDice(this.getPlayerController().getPlayerID(), false, this.getPlayerController().getPlayerColor());
		for(int i = 0; i < opponents.size(); i++) {
			opponents.get(i).getPatternCard().getTokenController().reloadRemainingDice(opponents.get(i).getPlayerID(), true, opponents.get(i).getPlayerColor());
		}
		for (int i = 2; i < 5; i++) {
			ToolCard toolCard = (ToolCard) this.getGamePane().getGamePaneBottom().getChildren().get(i);
			HBox toolCardHBox = (HBox) toolCard.getChildren().get(1);
			toolCardHBox.getChildren().clear();
			for (int j = 0; j < numberOfUses(toolCard); j++) {
				Color colorToAdd = this.getTokenColor(toolCard, j);
				toolCardHBox.getChildren().add(new TokenPane(colorToAdd));

			}
		}

	}

	public int numberOfUses(ToolCard toolCard) {
		int numberUsed = this.getTokenController().getUsedPerCard(this.getTokenController().getToolCardID(this.getM_game().getGameId(), toolCard.getCardName()),this.getM_game().getGameId());
		if (numberUsed != 0) {
			return numberUsed;
		} else {
			return 0;
		}
	}
	public Color getTokenColor(ToolCard toolCard, int index) {
		Color colorToAdd = null;
		int gameID = this.getM_game().getGameId();
		int toolCardID = this.getTokenController().getToolCardID(gameID, toolCard.getCardName());
		int playerID = this.getTokenController().getPlayerIDPerUsedToken(toolCardID, gameID).get(index);
		if(playerID == this.getPlayerController().getPlayerID()) {
			colorToAdd =  this.getPlayerController().getPlayerColor();
		}
		else {
			for (int i = 0; i < opponents.size(); i++) {
				if(playerID == opponents.get(i).getPlayerID()) {
					colorToAdd = opponents.get(i).getPlayerColor();
				}
			}
		}
		return colorToAdd;
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
			// System.out.println("PUBLIC SCORE = " + score);
			return score;
		} else {
			// System.out.println("SCORE IS 0000");
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
		gamePane.setOwnWindow(new WindowPatternView(250, 200, chosenCard.nameProperty(), chosenCard.tokenAmount(),
				squareController, playerController.getPlayerColor(),
				this.gameModel.getPlayerName(playerController.getPlayerID())));
	}

	public int getCurrentPlayerID() {
		return playerController.getPlayerID();
	}

	public void setTokenController(TokenController tokenController) {
		this.tokenController = tokenController;
	}

	public TokenController getTokenController() {
		return this.tokenController;
	}

	public InGameThread getInGameThread() {
		return inGameThread;
	}





}
