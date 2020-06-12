package Controller;

import java.util.ArrayList;

import View.WindowPatternView;
import model.GameDiceModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class PatternCardController {
	private GameController gameController;
	private PlayerController playerController;
	private WindowPatternSquareController[][] fieldController;
	private WindowPatternSquareController selectedSquare = null;
	private PatternCardModel chosenCard;
	private PatternCardModel[] optionCard;

	private ArrayList<Integer> idpatterncardoptions;

	private static final int PATTERNVIEWHEIGHT = 250;
	private static final int PATTERNVIEWWIDTH = 1280/4;

	public PatternCardController(GameController gameController, PlayerController playerController) {
		this.gameController = gameController;
		this.playerController = playerController;

	}

	public void loadChosenCard() {
		if (playerController.getPlayerModel().patterncardExists()) {
			chosenCard = new PatternCardModel(this, playerController.getPlayerModel().getPatterncardID());

			if(playerController.getPlayerID() == gameController.getCurrentPlayerID()) {
				gameController.setOwnWindow(chosenCard, this);
			}
		} else {
			if (playerController.getPlayerModel().getIsCurrentPlayer()) {
				generatePatternCardChoice();
			}
		}
	}

	public void updatePlayerFrameField() {
		this.chosenCard.updatePlayerFrameField(gameController.getM_game().getGameId(), playerController.getPlayerID());
	}

	public PlayerController getPlayerController() {
		return this.playerController;
	}

	public boolean placeDice(GameDiceModel dice) {
		// Check of er een square is geselecteerd en of er geen andere dobbelsteen ligt
		if (selectedSquare != null && selectedSquare.getSquare().isEmpty()) {
			int x = this.selectedSquare.getSquare().getX();
			int y = this.selectedSquare.getSquare().getY();

			if (this.chosenCard.isWindowCardEmpty()) {
				if (x > 1 && x < 5) {
					if (y > 1 && y < 4) {
						return false;
					}
				}
				// Plaat eerste steen
				// Check op kleur
				if (selectedSquare.getSquare().getColor() == null || selectedSquare.getSquare().getColor().toString()
						.equals(dice.colorProperty().getValue().toString())) {
					// Check op waarde
					if (selectedSquare.getSquare().getValue() == 0
							|| selectedSquare.getSquare().getValue() == dice.valueProperty().getValue()) {
						this.selectedSquare.setDice(dice);
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if (this.chosenCard.hasSurroundingDice(x, y)) {
					// Check voor dubbele waarde
					if (this.chosenCard.hasDoubleSurroundingColor(x, y, dice.colorProperty().getValue())
							&& this.chosenCard.hasDoubleSurroundingValue(x, y, dice.valueProperty().getValue())) {
						// Check op kleur
						if (selectedSquare.getSquare().getColor() == null || selectedSquare.getSquare().getColor()
								.toString().equals(dice.colorProperty().getValue().toString())) {
							// Check op waarde
							if (selectedSquare.getSquare().getValue() == 0
									|| selectedSquare.getSquare().getValue() == dice.valueProperty().getValue()) {
								this.selectedSquare.setDice(dice);
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}

	}

	private void generatePatternCardChoice() {
		this.optionCard = new PatternCardModel[4];
		idpatterncardoptions = new ArrayList<Integer>();

		// Check hier voor mogelijke dubbele patterncards
		int i = 0;
		while (true) {
			int randomNumber = getRandomIntBetweenRange(1, 24);
			PatternCardModel temp = new PatternCardModel(this, randomNumber);

			if (optionCard[i] == null && !idpatterncardoptions.contains(temp.getIdPatternCard())) {
				this.optionCard[i] = temp;
				//System.out.println(gameController.getM_game().getGameId() + ": gameid");
				//System.out.println(gameController.getC_login().getUsername() + ":username");
				this.optionCard[i].addToPatternCardOption(optionCard[i]
						.getPlayerID(gameController.getM_game().getGameId(), gameController.getC_login().getUsername()),
						randomNumber);
				idpatterncardoptions.add(temp.getIdPatternCard());
				if (optionCard[3] != null) {
					break;
				}
				i++;
			} else {

			}
		}
		this.gameController.getGamePane().createChoicePane(makeView(0), makeView(1), makeView(2), makeView(3));
	}

	public WindowPatternSquareController[][] makeSquareView(PatternCardFieldModel[][] field) {
		fieldController = new WindowPatternSquareController[5][4];

		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y] = new WindowPatternSquareController(gameController, this, field[x][y]);
			}
		}
		return fieldController;
	}

	private WindowPatternView makeView(int index) {
        // Magic Number weghalen
    return new WindowPatternView(PATTERNVIEWWIDTH, PATTERNVIEWHEIGHT, optionCard[index].nameProperty(), optionCard[index].tokenAmount(),
        makeSquareView(optionCard[index].getField()));
    }

	 public WindowPatternView makeView(PatternCardModel card) {
	        if (card == null) {
	            //System.out.println("PANIEK DE CARD IS NULL PANIEK");
	        }
	        WindowPatternView windowPaternView = new WindowPatternView(250, 200, card.nameProperty(), card.tokenAmount(),
	                makeSquareView(card.getField()), playerController.getPlayerColor(), this.gameController.getM_game().getPlayerName(playerController.getPlayerID()));
	        System.out.println(playerController.getPlayerID());
	        System.out.println(playerController.getPlayerModel().getUsername());
	        TokenController tokenController = new TokenController(card.tokenAmount().getValue(), gameController.getM_game().getGameId(), playerController.getPlayerID(), gameController, windowPaternView);
	        return windowPaternView;
	    }

	public int getRandomIntBetweenRange(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

	public void setChosenCard(int index) {
		this.chosenCard = this.optionCard[index];
		this.chosenCard.makePlayerFrameField();
		// TODO add choice to playertable
		this.chosenCard.updatePatternCardIDToPlayer(optionCard[index]
				.getPlayerID(gameController.getM_game().getGameId(), gameController.getC_login().getUsername()),
				optionCard[index].getIdPatternCard());
		this.gameController.getGamePane().setOwnWindow(makeView(chosenCard));
		this.gameController.getGamePane().createGamePane();
	}

	public void setSelected(WindowPatternSquareController selectedSquare) {
		this.selectedSquare = selectedSquare;
	}

	public WindowPatternSquareController getSelectedSquare() {
		return selectedSquare;
	}

	// test
	public WindowPatternSquareController[][] getFieldController() {
		return this.fieldController;
	}

	public PatternCardModel getChosenCard() {
		return chosenCard;
	}

	public void loadPatternCard() {
		if (chosenCard != null) {
			chosenCard.loadPatterncardFieldModel();

			if (fieldController != null) {
				for (int x = 0; x < fieldController.length; x++) {
					for (int y = 0; y < fieldController[x].length; y++) {
						fieldController[x][y].removeDiceFromView();
						GameDiceModel dice = chosenCard.getField()[x][y].getDice();

						if (dice != null) {
							fieldController[x][y].setDice(dice);
						}
					}
				}
			}
		}
	}

	public void reloadDice() {
		//System.out.println("RELOAD DICE RELOAD DICE RELOAD DICE");
		//System.out.println("FIELDCONTROLLER LENGT = " + fieldController.length);
		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y].removeDice();
				fieldController[x][y].removeDiceFromView();

				GameDiceModel dice = chosenCard.loadDice(playerController.getPlayerID(), x + 1, y + 1);
				if (dice != null) {
					fieldController[x][y].setDice(dice);
				}
			}
		}
	}

	public WindowPatternSquareController[][] getSquareController() {
		fieldController = new WindowPatternSquareController[5][4];
		PatternCardFieldModel[][] field = chosenCard.getField();

		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y] = new WindowPatternSquareController(gameController, this, field[x][y]);
			}
		}
		return this.fieldController;
	}
}
