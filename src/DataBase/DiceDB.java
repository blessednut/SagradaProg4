package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.GameDiceModel;

public class DiceDB {
	private Statement st;
	private PreparedStatement ps;

	public DiceDB() {
		this.st = DBCon.getInstance().getSt();
	}

	// New
	public void updatePlayerFrameField(int gameID, GameDiceModel dice, int playerID, int x, int y) {
		try {
			String query = "update playerframefield set idgame = " + gameID + ", dienumber = " + dice.getDieNumber() + ", diecolor = '" + dice.colorProperty().getValue()  + "' where idplayer = " + playerID + " AND position_x = " + x + " AND position_y = " + y + ";";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertGameDice (GameDiceModel dice, int roundID) {
		try {
			String query = "insert into gamedie (idgame,dienumber,diecolor,eyes,roundID)values (" + dice.getIdgame() + ", " + dice.getDieNumber() + ", '" + dice.colorProperty().getValue() + "', " + dice.valueProperty().getValue() + ", " + roundID + ");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<GameDiceModel> getBag(int gameID) {
		try {
			ArrayList<GameDiceModel> bag = new ArrayList<GameDiceModel>();

			String query = " select * from die where (number, color) not in (select dienumber, diecolor from gamedie where idgame = "
					+ gameID + ") ;";
			
			ResultSet resultset = st.executeQuery(query);

			while (resultset.next()) {
				bag.add(new GameDiceModel(gameID, resultset.getInt("number"), resultset.getString("color"), getRandomInt(1, 6), 0, 0));
			}
			return bag;
		} catch (Exception e) {
			e.printStackTrace();
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
			ResultSet resultset = st.executeQuery(query);

			while (resultset.next()) {
				die.add(new GameDiceModel(gameID, resultset.getInt("dienumber"), resultset.getString("diecolor"), resultset.getInt("eyes"),
						resultset.getInt("roundtrack"), resultset.getInt("roundID")));
			}
			return die;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public GameDiceModel getDice (int idplayer, int x, int y) {
		GameDiceModel dice = null;
		try {
			String query = "select * from playerframefield p join gamedie g on p.idgame = g.idgame and p.dienumber = g.dienumber and p.diecolor = g.diecolor\r\n" + 
					"where p.idplayer = " + idplayer + " and position_x = " + x + " and position_y = " + y;
			ResultSet resultset = st.executeQuery(query);

			if (resultset.next()) {
				dice = new GameDiceModel(resultset.getInt("idgame"), resultset.getInt("dienumber"), resultset.getString("diecolor"), resultset.getInt("eyes"), resultset.getInt("roundtrack"), resultset.getInt("roundID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dice;
	}
}
