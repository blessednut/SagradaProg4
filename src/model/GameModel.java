package model;

import DataBase.DBCon;

public class GameModel {

	private DBCon con;
	private int GameId;

	public GameModel() {
		this.con = new DBCon();
	}

	// haalt max(idgame) op uit de db en verhoogd deze met 1 en insert deze vervolgens.
	public void creatGameRoom() {
		int x = 0;
		con.createConnection();
		try {
			String query = "select MAX(idgame) as idgame from game;";
			con.setRs(con.getSt().executeQuery(query));
			while (con.getRs().next()) {
				GameId = con.getRs().getInt("idgame");
			}
		con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		GameId++;
		con.createConnection();
		try {
			String query = "insert into game values(?,null,now());";
			con.setPs(con.getCon().prepareStatement(query));
			con.getPs().setInt(1, GameId);
			con.getPs().execute();
			con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public int getGameId() {
		return GameId;
	}
	
	
	

	private DBCon dbcon;
	private String title;
	private String result;
	private int TokenAmount;


	public GameModel(DBCon dbcon) {
		this.dbcon = dbcon;

	}

	// stil need the patterncardID as a int.
	public String cardTitle() {
		String result = null;
		dbcon.createConnection();
		try {
			String query = "select name from patterncard where idpatterncard = 1;";
			dbcon.setRs(dbcon.getSt().executeQuery(query));
			while (dbcon.getRs().next()) {
				result = dbcon.getRs().getString("name");
				this.result = result;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	// still need the pattercardID as a int
	public int getTokenAmount() {
		int TokenAmount = 0;
		dbcon.createConnection();
		try {
			String query = " Select difficulty from patterncard where idpatterncard = 1; ";
			dbcon.setRs(dbcon.getSt().executeQuery(query));
			while(dbcon.getRs().next()) {
				TokenAmount = dbcon.getRs().getInt("difficulty");
				this.TokenAmount = TokenAmount;
				System.out.println(this.TokenAmount);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return TokenAmount;
	}

}
