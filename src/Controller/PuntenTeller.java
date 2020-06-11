package Controller;

import java.util.ArrayList;

import model.PuntenTellerModel;

public class PuntenTeller {
	private GameController gamecontroller;

	private PuntenTellerModel ptm;

	
	public PuntenTeller(GameController gamecontroller) {
		this.ptm = new PuntenTellerModel();
		this.gamecontroller = gamecontroller;

	}
	
	private int countEmptySquares(WindowPatternSquareController[][] fieldController) {
		int emptySquares = 0;
		if (fieldController != null) {
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getSquare().getDice() == null) {
						emptySquares++;
					}
				}
			}
		}
		return emptySquares;
	}
	
	public int privateObjScore(String privateObjColor, WindowPatternSquareController[][] fieldController) {
		int scorePrivateCard = 0;
		if (fieldController != null) {
			switch(privateObjColor) {
			case "purple":
				for(int x = 0; x < fieldController.length; x++) {
					for(int y = 0; y < fieldController[x].length; y++) {
						if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
							scorePrivateCard += fieldController[x][y].getDice().valueProperty().getValue();
						}
					}
				}
				break;
			case "green":
				for(int x = 0; x < fieldController.length; x++) {
					for(int y = 0; y < fieldController[x].length; y++) {
						if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
							scorePrivateCard += fieldController[x][y].getDice().valueProperty().getValue();
						}
					}
				}
				break;
			case "red":
				for(int x = 0; x < fieldController.length; x++) {
					for(int y = 0; y < fieldController[x].length; y++) {
						if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
							scorePrivateCard += fieldController[x][y].getDice().valueProperty().getValue();
						}
					}
				}
				break;
			case "yellow":
				for(int x = 0; x < fieldController.length; x++) {
					for(int y = 0; y < fieldController[x].length; y++) {
						if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
							scorePrivateCard += fieldController[x][y].getDice().valueProperty().getValue();
						}
					}
				}
				break;
			case "blue":
				for(int x = 0; x < fieldController.length; x++) {
					for(int y = 0; y < fieldController[x].length; y++) {
						if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().getColor().equals(privateObjColor)) {
							scorePrivateCard += fieldController[x][y].getDice().valueProperty().getValue();
						}
					}
				}
				break;
			}
		}
		return scorePrivateCard;
	}

	public int publicObjScore(String publicObjCard, WindowPatternSquareController[][] fieldController) {
		int scorePublicCards = 0;
		switch(publicObjCard) {
		case "Colomn Color Variety":
			//System.out.println("Colomn Color Variety");
			ArrayList<String> ColorColomn = new ArrayList<>();
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null) {
						if(!ColorColomn.contains(fieldController[x][y].getDice().getColor())) {
							ColorColomn.add(fieldController[x][y].getDice().getColor());
							if(y == 3) {
								scorePublicCards += 5;
								ColorColomn.clear();
								break;
							}
						}
						else {
							ColorColomn.clear();
							break;
						}
					}else {
						ColorColomn.clear();
						break;
					}

						
				}
			}
		case "Colomn Shade Variety":
			//System.out.println("Colomn Shade Variety");
			ArrayList<Integer> IntColomn = new ArrayList<>();
			for(int x = 4; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && (fieldController[x][y].getDice().valueProperty().getValue() != 0)) {
						if(!IntColomn.contains(fieldController[x][y].getDice().valueProperty().getValue())) {
							IntColomn.add(fieldController[x][y].getDice().valueProperty().getValue());
							if(y == 3) {
								scorePublicCards += 4;
								IntColomn.clear();
								if(x > 4) {
									break;
								}
							}
						}
						else {
							IntColomn.clear();
							break;
						}
					}
					else {
						IntColomn.clear();
						break;
					}
				}
			}
		case "Dark Shades":
			//System.out.println("Dark Shades");
			int amountOfFive = 0;
			int amountOfSix = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 5) {
						amountOfFive++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 6) {
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
			scorePublicCards += (2 * lowestAmount);

		case "Light Shades":
			//System.out.println("Light Shades");
			int amountOfOne = 0;
			int amountOfTwo = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 1) {
						amountOfOne++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 2) {
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
			scorePublicCards += (2 * SetAmount);
			
		case "Medium Shades":
			//System.out.println("Medium Shades");
			int amountOfThree = 0;
			int amountOfFour = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 3) {
						amountOfThree++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 4) {
						amountOfFour++;
					}
				}
			}
			int SetsAmount = 100;
			if(amountOfThree < SetsAmount) {
				SetsAmount = amountOfThree;
			}
			if(amountOfFour < SetsAmount) {
				SetsAmount = amountOfFour;
			}
			scorePublicCards +=(2 * SetsAmount);
		case "Row Color Variety":
			//System.out.println("Row Color Variety");
			ArrayList<String> ColorRow = new ArrayList<>();
			for(int y = 0; y < fieldController.length -1; y++) {
				for(int x = 0; x < fieldController.length; x++) {
					if(fieldController[x][y].getSquare().getDice() != null) {
						if(!ColorRow.contains(fieldController[x][y].getDice().colorProperty().getValue())) {
							ColorRow.add(fieldController[x][y].getDice().colorProperty().getValue());
							if(x == 4) {
								//System.out.println(scorePublicCards + 5);
								scorePublicCards +=5;
								ColorRow.clear();
								break;
							}
						}
						else {
							ColorRow.clear();
							break;
						}
					}else {
						ColorRow.clear();
						break;
					}
				}
			}
		case "Row Shade Variety":
			//System.out.println("Row Shade Variety");
			ArrayList<Integer> ValueRow = new ArrayList<>();
			for(int y = 3; y < fieldController.length -1; y++) {
				for(int x = 0; x < fieldController.length; x++) {
					if(fieldController[x][y].getSquare().getDice() != null) {
						if(!ValueRow.contains(fieldController[x][y].getSquare().getDice().valueProperty().getValue())) {
							ValueRow.add(fieldController[x][y].getSquare().getDice().valueProperty().getValue());
							if(x == 4) {
								//System.out.println(scorePublicCards + 5);
								scorePublicCards +=5;
								ValueRow.clear();
								break;
							}
						}
						else {
							ValueRow.clear();
							break;
						}
					}
					else {
						ValueRow.clear();
						break;
					}
				}
			}
		case "Shade Variety":
			//System.out.println("Shade Variety");
			int amountOfOnes = 0;
			int amountOfTwos = 0;
			int amountOfThrees = 0;
			int amountOfFours = 0;
			int amountOfFives = 0;
			int amountOfSixs = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 1) {
						amountOfOnes++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 2) {
						amountOfTwos++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 3) {
						amountOfThrees++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 4) {
						amountOfFours++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 5) {
						amountOfFives++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getDice().valueProperty().getValue() == 6) {
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
			//System.out.println(scorePublicCards + (5 * LeastOf));
			scorePublicCards += (5 * LeastOf);
		case "Color Variety":
			//System.out.println("Color Variety");
			int amountOfYellow = 0;
			int amountOfGreen = 0;
			int amountOfBlue = 0;
			int amountOfPurple = 0;
			int amountOfRed = 0;
			for(int x = 0; x < fieldController.length; x++) {
				for(int y = 0; y < fieldController[x].length; y++) {
					if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("yellow")) {
						amountOfYellow++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("green")) {
						amountOfGreen++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("blue")) {
						amountOfBlue++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("purple")) {
						amountOfPurple++;
					}
					else if(fieldController[x][y].getSquare().getDice() != null && fieldController[x][y].getSquare().getDice().colorProperty().getValue().equals("red")) {
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
			
			//System.out.println(scorePublicCards + (4* amountOfSets));
			scorePublicCards +=(4* amountOfSets);
		}
		return scorePublicCards;
	}

	public int favorTokenScore(PlayerController playercontroller) {
		int scoreFavorTokens = playercontroller.getPatternCard().getChosenCard().getDifficulty();
		return scoreFavorTokens;
	}
	
	public int getPublicScore (String privateObjColor, String[] publicObjCardArray, PlayerController playerController) {
		//Null check
		if (privateObjColor == null) {
			return 0;
		}
		
		for (int i = 0; i < publicObjCardArray.length; i++) {
			if (publicObjCardArray[i] == null) {
				return 0;
			}
		}
		//berekenen punten van publieke kaarten
		int publicCardOne = this.publicObjScore(publicObjCardArray[0], playerController.getPatternCard().getFieldController());
		int publicCardTwo = this.publicObjScore(publicObjCardArray[1], playerController.getPatternCard().getFieldController());
		int publicCardThree = this.publicObjScore(publicObjCardArray[2], playerController.getPatternCard().getFieldController());
		
		//berekenen publieke score
		int publicScore = 0;
		publicScore = (favorTokenScore(playerController) + (publicCardOne+publicCardTwo+publicCardThree));
		return publicScore;
	}
	
	public int getTotalScore(String privateObjColor, String[] publicObjCardArray, PlayerController playerController) {
		int totalScore = 0;
		int privateScore = 0;
		int publicScore = 0;
		int favorScore = 0;

		//nullcheck
		if (privateObjColor == null) {
			return 0;
		}
		
		for (int i = 0; i < publicObjCardArray.length; i++) {
			if (publicObjCardArray[i] == null) {
				return 0;
			}
		}
		
		//berekenen individuele score componenten
		favorScore = favorTokenScore(playerController);
		privateScore = privateObjScore(privateObjColor, playerController.getPatternCard().getFieldController());
		for (int i = 0; i < publicObjCardArray.length; i++) {
			publicScore += publicObjScore(publicObjCardArray[i], playerController.getPatternCard().getFieldController());
		}
		
		//berekenen totaalscore
		totalScore = favorScore + privateScore + publicScore;
		return totalScore;
		
	}
	
	public int getEndScore(String privateObjColor, String[] publicObjCardArray, PlayerController playerController) {
		int endScore = 0;
		int privateScore = 0;
		int publicScore = 0;
		int favorScore = 0;
		int emptySquares = 0;

		//Null check
		if (privateObjColor == null) {
			return 0;
		}
		
		for (int i = 0; i < publicObjCardArray.length; i++) {
			if (publicObjCardArray[i] == null) {
				return 0;
			}
		}
		
		//berekenen individuele score componenten
		emptySquares = countEmptySquares(playerController.getPatternCard().getFieldController());
		favorScore = favorTokenScore(playerController);
		privateScore = privateObjScore(privateObjColor, playerController.getPatternCard().getFieldController());
		for (int i = 0; i < publicObjCardArray.length; i++) {
			publicScore += publicObjScore(publicObjCardArray[i], playerController.getPatternCard().getFieldController());
		}
		
		//berekenen eindscore
		endScore = favorScore + privateScore + publicScore - emptySquares;
		
		return endScore;
	}
	
	
	public void updateScoreInDataBase(String privateObjColor, String[] publicObjCardArray, PlayerController playerController) {
		ptm.updateScore(getEndScore(privateObjColor, publicObjCardArray,playerController), gamecontroller.getPlayerController().getPlayerID());
	}
}