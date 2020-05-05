package model;

import DataBase.DBCon;

public class GameModel {
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
