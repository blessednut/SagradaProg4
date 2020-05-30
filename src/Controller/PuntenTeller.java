package Controller;

import java.util.ArrayList;

import model.PuntenTellerModel;

public class PuntenTeller {
	private GameController gamecontroller;
	private WindowPatternSquareController[][] fieldController;
	private PuntenTellerModel ptm;
	
	private int scorePublicCards;
	private int scorePrivateCard;
	private int scoreFavorTokens;
	private int emptySquares;
	private int totalScore;
	
	public PuntenTeller(GameController gamecontroller) {
		this.ptm = new PuntenTellerModel(this);
		this.gamecontroller = gamecontroller;
		this.fieldController = this.gamecontroller.getPlayerController().getPatternCard().getFieldController();
		scorePublicCards = 0;
		scorePrivateCard = 0;
		scoreFavorTokens = 0;
	}
	
	public void countEmptySquares() {
		for(int x = 0; x < fieldController.length; x++) {
			for(int y = 0; y < fieldController[x].length; y++) {
				if(fieldController[x][y].getSquare().getDice() == null) {
					emptySquares++;
				}
			}
		}
	}
	
	public void privateObjScore(String privateObjColor) {
		switch(privateObjColor) {
		case "purple":
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
						scorePrivateCard = scorePrivateCard + fieldController[x][y].getDice().valueProperty().getValue();
					}
				}
			}
			break;
		case "green":
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
						scorePrivateCard = scorePrivateCard + fieldController[x][y].getDice().valueProperty().getValue();
					}
				}
			}
			break;
		case "red":
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
						scorePrivateCard = scorePrivateCard + fieldController[x][y].getDice().valueProperty().getValue();
					}
				}
			}
			break;
		case "yellow":
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
						scorePrivateCard = scorePrivateCard + fieldController[x][y].getDice().valueProperty().getValue();
					}
				}
			}
			break;
		case "blue":
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
						scorePrivateCard = scorePrivateCard + fieldController[x][y].getDice().valueProperty().getValue();
					}
				}
			}
			break;
		}
	}

	public void publicObjScore(String publicObjCard) {
		switch(publicObjCard) {
		case "Colomn Color Variety":
			ArrayList<String> ColorColomn = new ArrayList<>();
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null) {
						if(!ColorColomn.contains(fieldController[x][y].getDice().getColor())) {
							ColorColomn.add(fieldController[x][y].getDice().getColor());
							if(y == 3) {
								scorePublicCards = scorePublicCards + 5;
								ColorColomn.clear();
								if(x > 4) {
									break;
								}
							}
						}
						else {
							ColorColomn.clear();
							x++;
							y = -1;
							if(x > 4) {
								break;
							}
						}
					}else {
						ColorColomn.clear();
						x++;
						y = -1;
						if(x > 4) {
							break;
						}
					}

						
				}
			}
		case "Colomn Shade Variety":
			ArrayList<Integer> IntColomn = new ArrayList<>();
			for(int x = 4; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if((fieldController[x][y].getDice().valueProperty().getValue() != 0)) {
						if(!IntColomn.contains(fieldController[x][y].getDice().valueProperty().getValue())) {
							IntColomn.add(fieldController[x][y].getDice().valueProperty().getValue());
							if(y == 3) {
								scorePublicCards = scorePublicCards + 4;
								IntColomn.clear();
								if(x > 4) {
									break;
								}
							}
						}
						else {
							IntColomn.clear();
							x++;
							y = -1;
							if(x > 4) {
								break;
							}
						}
					}
					else {
						IntColomn.clear();
						x++;
						y = -1;
						if(x > 4) {
							break;
						}
					}
				}
			}
		case "Dark Shades":
			int amountOfFive = 0;
			int amountOfSix = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().valueProperty().getValue() == 5) {
						amountOfFive++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 6) {
						amountOfSix++;
					}
				}
			}
			int lowestAmount = 100;
			if(amountOfFive < lowestAmount) {
				lowestAmount = amountOfFive;
			}
			if(amountOfSix < lowestAmount) {
				lowestAmount = amountOfSix;
			}
			scorePublicCards = scorePublicCards + (2 * lowestAmount);
//		case "Diagonals":
// TODO punten berekening voor kaart maken
//			for(int x = 0; x < raamVenster.length; x++) {
//				for(int y = 0; y < raamVenster[x].length; y++) {
//				}
//			}
		case "Light Shades":
			int amountOfOne = 0;
			int amountOfTwo = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().valueProperty().getValue() == 1) {
						amountOfOne++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 2) {
						amountOfTwo++;
					}
				}
			}
			int SetAmount = 100;
			if(amountOfOne < SetAmount) {
				SetAmount = amountOfOne;
			}
			if(amountOfTwo < SetAmount) {
				SetAmount = amountOfTwo;
			}
			scorePublicCards = scorePublicCards + (2 * SetAmount);	
		case "Medium Shades":
			int amountOfThree = 0;
			int amountOfFour = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().valueProperty().getValue() == 3) {
						amountOfThree++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 4) {
						amountOfFour++;
					}
				}
			}
			int SetsAmount = 100;
			if(amountOfThree < SetsAmount) {
				lowestAmount = amountOfThree;
			}
			if(amountOfFour < SetsAmount) {
				lowestAmount = amountOfFour;
			}
			scorePublicCards = scorePublicCards + (2 * SetsAmount);
		case "Row Color Variety":
			ArrayList<String> ColorRow = new ArrayList<>();
			for(int y = 0; y < fieldController.length -1; y++) {
				for(int x = 0; x < fieldController.length; x++) {
					if(fieldController[x][y].getSquare().getDice() != null) {
						if(!ColorRow.contains(fieldController[x][y].getDice().colorProperty().getValue())) {
							ColorRow.add(fieldController[x][y].getDice().colorProperty().getValue());
							if(x == 4) {
								scorePublicCards = scorePublicCards + 5;
								ColorRow.clear();
							}
						}
						else {
							ColorRow.clear();
							y++;
							x = -1;
							if(x == 4) {
								break;
								
							}
						}
					}else {
						ColorRow.clear();
						y++;
						x = -1;
						break;
					}
				}
			}
		case "Row Shade Variety":
			ArrayList<Integer> ValueRow = new ArrayList<>();
			for(int y = 3; y < fieldController.length -1; y++) {
				for(int x = 0; x < fieldController.length; x++) {
					if(fieldController[x][y].getSquare().getDice() != null) {
						if(!ValueRow.contains(fieldController[x][y].getSquare().getDice().valueProperty().getValue())) {
							ValueRow.add(fieldController[x][y].getSquare().getDice().valueProperty().getValue());
							if(x == 4) {
								scorePublicCards = scorePublicCards + 5;
								ValueRow.clear();
								break;
							}
						}
						else {
							ValueRow.clear();
							y++;
							x = -1;
							if(y == 4) {
								break;
								
							}
						}
					}
					else {
						ValueRow.clear();
						y++;
						x = -1;
						if(y == 4) {
							break;
							
						}
					}
				}
			}
		case "Shade Variety":
			int amountOfOnes = 0;
			int amountOfTwos = 0;
			int amountOfThrees = 0;
			int amountOfFours = 0;
			int amountOfFives = 0;
			int amountOfSixs = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getDice().valueProperty().getValue() == 1) {
						amountOfOnes++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 2) {
						amountOfTwos++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 3) {
						amountOfThrees++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 4) {
						amountOfFours++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 5) {
						amountOfFives++;
					}
					else if(fieldController[x][y].getDice().valueProperty().getValue() == 6) {
						amountOfSixs++;
					}
				}
			}
			int LeastOf = 100;
			if(amountOfOnes < LeastOf) {
				LeastOf = amountOfOnes;
			}
			if(amountOfTwos < LeastOf) {
				LeastOf = amountOfTwos;
			}
			if(amountOfThrees < LeastOf) {
				LeastOf = amountOfThrees;
			}
			if(amountOfFours < LeastOf) {
				LeastOf = amountOfFours;
			}
			if(amountOfFives < LeastOf) {
				LeastOf = amountOfFives;
			}
			if(amountOfSixs < LeastOf) {
				LeastOf = amountOfSixs;
			}
			scorePublicCards = scorePublicCards + (5 * LeastOf);
		case "Color Variety":
			int amountOfYellow = 0;
			int amountOfGreen = 0;
			int amountOfBlue = 0;
			int amountOfPurple = 0;
			int amountOfRed = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("yellow")) {
						amountOfYellow++;
					}
					else if(fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("green")) {
						amountOfGreen++;
					}
					else if(fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("blue")) {
						amountOfBlue++;
					}
					else if(fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("purple")) {
						amountOfPurple++;
					}
					else if(fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("red")) {
						amountOfRed++;
					}
				}
			}
			int amountOfSets = 100;
			if(amountOfYellow < amountOfSets) {
				amountOfSets = 	amountOfYellow;
			}
			if(amountOfGreen < amountOfSets) {
				amountOfSets = amountOfGreen;
			}
			if(amountOfBlue < amountOfSets) {
				amountOfSets = amountOfBlue;
			}
			if(amountOfPurple < amountOfSets) {
				amountOfSets = amountOfPurple;
			}
			if(amountOfRed < amountOfSets) {
				amountOfSets = amountOfRed;
			}
			
			scorePublicCards = scorePublicCards + (4* amountOfSets);
		}
	}

	public int getEmptySquares() {
		return emptySquares;
	}

	public int getScorePublicCards() {
		return scorePublicCards;
	}

	public int getScorePrivateCard() {
		return scorePrivateCard;
	}

	public int getScoreFavorTokens() {
		return scoreFavorTokens;
	}
	
	public int getTotalScore() {
		totalScore = (scorePublicCards + scorePrivateCard +  scoreFavorTokens - emptySquares);
		return totalScore;
	}
	
	public void updateScoreInDataBase() {
		totalScore = (scorePublicCards + scorePrivateCard +  scoreFavorTokens - emptySquares);
		ptm.updateScore(totalScore, gamecontroller.getPlayerController().getPlayerID());
	}
}