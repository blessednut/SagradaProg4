package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.GameModel;

public class GameDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	private GameModel GM;
	
	public GameDB (GameModel GM) {
		this.st = DBCon.getInstance().getSt();
		this.GM = GM;
	}
	
	public int createGameRoom() {
		int GameId = 0;
		try {
			String query = "select MAX(idgame) as idgame from game;";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				GameId = resultset.getInt("idgame");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		GameId++;
		GM.setGameId(GameId);
//		System.out.println("GAMEDB");
//		System.out.println("GameID = " + GameId);
//		System.out.println("PlayerID = " + getPlayerID(GameId, 1));
		try {
			String query = "insert into game(idgame, current_roundID, creationdate) values(" + GameId + ", 1,now());";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			//ps.setInt(1, GameId);
			//ps.setInt(2, );
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return GameId;
	}
	
	private int getPlayerID (int idgame, int seqNR) {
		int playerID = 0;
		try {
			String query = "SELECT idplayer FROM player WHERE idgame = " + idgame + " AND seqnr = " + seqNR;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				playerID = resultset.getInt("idplayer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerID;
	}

	public void updatePlayerTurn(int seqNR) {
		int gameID = GM.getGameId();
		int playerID = getPlayerID(gameID, seqNR);
//		System.out.println("gameID = " + gameID);
//		System.out.println("playerID = " + playerID);
		try {
			String query = "update game set turn_idplayer = " + playerID + " WHERE idgame = " + gameID + ";";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
