package model;

import DataBase.DBCon;

public class GameModel {

	private DBCon con;
	private int GameId;

	public GameModel() {
		this.con = new DBCon();
	}
	
	public void creatGameRoom() {
		int x = 0;
		con.createConnection();
		try {
			String query = "select MAX(idgame) as idgame from game;";
			con.setRs(con.getSt().executeQuery(query));
			while(con.getRs().next()) {
				GameId = con.getRs().getInt("idgame");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		GameId++;
		con.createConnection();
		try {
			String query = "insert into game values(?,null,now());";
			con.setPs(con.getCon().prepareStatement(query));
			con.getPs().setInt(1, GameId);
			con.getPs().execute();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public int getGameId() {
		return GameId;
	}
	
	
	

}
