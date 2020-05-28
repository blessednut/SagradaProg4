package Controller;

import View.WindowPatternView;
import model.GameDiceModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class PatternCardController {
	private GameController c_game;
	private PlayerController playerController;
	private WindowPatternSquareController[][] fieldController;
	private WindowPatternSquareController selectedSquare = null;
	private PatternCardModel chosenCard;
	private PatternCardModel[] optionCard;

	public PatternCardController(GameController c_game, PlayerController playerController) {
		this.c_game = c_game;
		this.playerController = playerController;
		generatePatternCardChoice();
	}

	public void updatePlayerFrameField() {
		this.chosenCard.updatePlayerFrameField(c_game.getM_game().getGameId(), playerController.getPlayerID());
	}

	public PlayerController getPlayerController() {
		return this.playerController;
	}

	public boolean placeDice(GameDiceModel dice) {
		// Check of er een square is geselecteerd en of er geen andere dobbelsteen ligt
		if (selectedSquare != null && selectedSquare.getSquare().isEmpty()) {
			System.out.println("PatternCardController");
//			System.out.println("SelectedSquare = " + selectedSquare.getSquare().getColor());
//			System.out.println("Dice = " + dice.colorProperty().getValue());
//			System.out.println(selectedSquare.getSquare().getColor().toString());
//			System.out.println(dice.colorProperty().getValue().toString());
//			System.out.println(selectedSquare.getSquare().getColor().toString() == dice.colorProperty().getValue().toString());

			// System.out.println("IsBoardEmpty = " + this.chosenCard.isWindowCardEmpty());
			int x = this.selectedSquare.getSquare().getX();
			int y = this.selectedSquare.getSquare().getY();
//			System.out.println("x = " + x);
//			System.out.println("y = " + y);
			// chosenCard.hasSurroundingDice(x, y);
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

		// Check hier voor mogelijke dubbele patterncards
		int i = 0;
		while (i < optionCard.length) {
			PatternCardModel temp = new PatternCardModel(this, getRandomIntBetweenRange(1, 24));
			System.out.println(temp.getIdPatternCard() + "adafhvdjhgasdgfa");
			if (optionCard[i] == null) {
				this.optionCard[i] = temp;
			} else if (!(optionCard[i].getIdPatternCard() == temp.getIdPatternCard())) {
				this.optionCard[i] = temp;
				
			}
			i++;

		}

//		TODO: check voor vaker voorkomen views
//		TODO: opties wegschrijven naar de database
		this.c_game.getGamePane().createChoicePane(makeView(0), makeView(1), makeView(2), makeView(3));
	}

	private WindowPatternSquareController[][] makeSquareView(PatternCardFieldModel[][] field) {
		fieldController = new WindowPatternSquareController[5][4];

		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y] = new WindowPatternSquareController(this, field[x][y]);
			}
		}
		return fieldController;
	}

	private WindowPatternView makeView(int index) {
		// Magic Number weghalen
		return new WindowPatternView(450, 300, optionCard[index].nameProperty(), optionCard[index].tokenAmount(),
				makeSquareView(optionCard[index].getField()));
	}

	private WindowPatternView makeView(PatternCardModel card) {
		return new WindowPatternView(450, 300, card.nameProperty(), card.tokenAmount(),
				makeSquareView(card.getField()));
	}

	public int getRandomIntBetweenRange(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

	public void setChosenCard(int index) {
		this.chosenCard = this.optionCard[index];
		this.chosenCard.makePlayerFrameField();
		this.c_game.getGamePane().setOwnWindow(makeView(chosenCard));
		this.c_game.getGamePane().createGamePane2();
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

}