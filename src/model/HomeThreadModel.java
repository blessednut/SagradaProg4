package model;

import DataBase.DBCon;

public class HomeThreadModel {
	
	private int gameID;
	private String username;
	private DBCon con;

	
	
	public HomeThreadModel() {
		con =  new DBCon();
	}
	// methode om de gameID op te halen die de laatste creationdate heeft
	public int getGameID(String playstatus) {
		con.createConnection();
		gameID = 0;
		try {
			String query = "select game.creationdate, game.idgame from player\r\n" + 
					"left join game on player.idgame = game.idgame\r\n" + 
					"where creationdate = (select max(creationdate) from game)  and player.playstatus like '" + playstatus + "';";
			con.setRs(con.getSt().executeQuery(query));
			if(con.getRs().next()) {
				gameID = con.getRs().getInt("idgame");
				System.out.println(con.getRs().getTime("creationdate"));
				System.out.println(con.getRs().getDate("creationdate"));
				System.out.println(con.getRs().getInt("idgame"));
			}
//			con.getCon().close();
		}catch(Exception e){
			System.out.println(e);
		}
		return gameID;
	}
	//methode om de username van de challenger te vinden die de laatste game heeft aangemaakt
	public String getUsernameOfChallenger(String playstatus) {
		con.createConnection();
		
		try {
			String query = "select username from player\r\n" + 
					"where idgame = " + getGameID("Challengee") + " and playstatus like '" + playstatus + "';";
			con.setRs(con.getSt().executeQuery(query));
			if(con.getRs().next()) {
				username = con.getRs().getString("username");
				System.out.println(username);
			}
			con.getCon().close();
		}catch(Exception e){
			System.out.println(e);
		}
		return username;
	}
	public String getUsername() {
		return username;
	}
	
}
