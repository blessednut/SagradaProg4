package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.DiceModel;
import model.GameDiceModel;

public class DiceDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	public DiceDB() {
		this.st = DBCon.getInstance().getSt();
	}

	// New
	public void updatePlayerFrameField(int gameID, GameDiceModel dice, int playerID, int x, int y) {
		try {
			System.out.println(dice.colorProperty().getValue());
			String query = "update playerframefield set idgame = " + gameID + ", dienumber = " + dice.getDieNumber() + ", diecolor = '" + dice.colorProperty().getValue()  + "' where idplayer = " + playerID + " AND position_x = " + x + " AND position_y = " + y + ";";
			System.out.println("DiceDB:");
			System.out.println("Query = " + query);
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void insertGameDice (GameDiceModel dice) {
		try {
			String query = "insert into gamedie (idgame,dienumber,diecolor,eyes)values (" + dice.getIdgame() + ", " + dice.getDieNumber() + ", '" + dice.colorProperty().getValue() + "', " + dice.valueProperty().getValue() + ");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<GameDiceModel> getBag(int gameID) {
		try {
			ArrayList<GameDiceModel> bag = new ArrayList<GameDiceModel>();

			String query = " select * from die where (number, color) not in (select dienumber, diecolor from gamedie where idgame = "
					+ gameID + ") ;";
			System.out.println("DiceDB:");
			System.out.println("Query = " + query);
			
			rs = st.executeQuery(query);

			while (rs.next()) {
				bag.add(new GameDiceModel(gameID, rs.getInt("number"), rs.getString("color"), getRandomInt(1, 6), 0, 0));
			}
			return bag;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	private int getRandomInt(int min, int max) {
		return (int) Math.floor((Math.random() * ((max - min) + 1)) + min);
	}

	public ArrayList<GameDiceModel> importGameDice(int gameID) {
		try {
			ArrayList<GameDiceModel> die = new ArrayList<GameDiceModel>();

			String query = "SELECT * FROM gamedie WHERE idgame = " + gameID + ";";
			rs = st.executeQuery(query);

			while (rs.next()) {
				die.add(new GameDiceModel(gameID, rs.getInt("dienumber"), rs.getString("diecolor"), rs.getInt("eyes"),
						rs.getInt("roundtrack"), rs.getInt("roundID")));
			}
			return die;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	//Old
	public int countGameDice(int gameID) {
		try {
			String query = "select count(idgame) as numberOfDice from gamedie where idgame = " + gameID + ";";
			rs = st.executeQuery(query);

			int amount = 0;
			while (rs.next()) {
				amount = rs.getInt(1);
			}
			return amount;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public DiceModel[] importDie() {
		try {
			DiceModel[] die = new DiceModel[getDieAmount()];

			String query = "SELECT *\r\n" + "FROM die;";
			rs = st.executeQuery(query);

			int i = 0;
			while (rs.next()) {
				die[i] = new DiceModel(rs.getInt("number"), rs.getString("color"));
				i++;
			}
			return die;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private int getDieAmount() {
		try {
			int value = 0;
			String query = "SELECT COUNT(*) \r\n" + "FROM die";
			rs = st.executeQuery(query);

			while (rs.next()) {
				value = rs.getInt(1);
			}

			return value;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
