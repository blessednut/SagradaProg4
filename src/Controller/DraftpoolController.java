//package Controller;
//
//import DataBase.DBCon;
//import DataBase.DiceDB;
//import model.DiceModel;
//import model.GameDiceModel;
//
//public class DraftpoolController {
//	// private list gameDie
//	//private DiceDB con;
//	private int numberPlayers;
//	//private DiceModel[] die;
//	//private GameDiceModel[] draft;
//	private DraftpoolSquareController[] draftpool;
//	private GameDiceModel selectedDice = null;
//	private GameController controller;
//
//	public DraftpoolController(GameController controller, int numberPlayers) {
//		this.con = new DiceDB();
//		this.numberPlayers = numberPlayers;
//		this.controller = controller;
//
//		importDice();
//		createDraftPool();
//	}
//	
////	public GameDiceModel[] getDraftPool () {
////		return draft;
////		//Is there a draftpool?
////		//	Get from database
////		//else
////		//	createDraftPool
////	}
//	
//	public void setSelectedDice(GameDiceModel dice) {
//		this.selectedDice = dice;
//		this.controller.placeDice(dice);
//	}
//	
//	public DraftpoolSquareController[] getDraftPool () {
//		draftpool = new DraftpoolSquareController[9];
//		
//		for (int i = 0; i < draftpool.length; i++) {
//			if (i < draft.length) {
//				draftpool[i] = new DraftpoolSquareController(this, draft[i]);
//			} else {
//				draftpool[i] = new DraftpoolSquareController();
//			}
//		}
//		
//		return draftpool;
//	}
//
//	private void importDice() {
//		// System.out.println(con.getDieAmount());
//		//this.die = con.importDie();
//		this.die = con.importDie();
//
//		for (DiceModel dice : die) {
//			System.out.println(dice.getNumber() + " - " + dice.getColor());
//		}
//	}
//
//	private int getDiceAmount() {
//		return ((numberPlayers * 2) + 1);
//	}
//
//	private void createDraftPool() {
//		this.draft = new GameDiceModel[getDiceAmount()];
//
//		for (int i = 0; i < draft.length; i++) {
//			DiceModel dice = getRandomDice();
//			
//			//TODO: IDGame, Roundtrack, RoundID waarde veranderen
//			draft[i] = new GameDiceModel(0, dice.getNumber(), dice.getColor(), getRandomInt(1, 6), 0, 0); 
//		}
//	}
//
//	private DiceModel getRandomDice() {
//		// choose random die
//		DiceModel dice = die[getRandomInt(0, die.length - 1)];
//		
//		// is duplicate?
//		return (!isDuplicate(dice))  ? dice : getRandomDice();
//	}
//
//	private boolean isDuplicate (DiceModel dice) {
//		for (int i = 0; i < draft.length; i++) {
//			if (draft[i] != null) {
//				if (draft[i].getDieNumber() == dice.getNumber() && draft[i].colorProperty().getValue() == dice.getColor()) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private int getRandomInt (int min, int max) {
//		return (int) Math.floor((Math.random() * ((max - min) + 1)) + min);
//	}
//
//
//}
