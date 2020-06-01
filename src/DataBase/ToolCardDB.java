package DataBase;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.GameDiceModel;

public class ToolCardDB {
	private Statement st;
	private PreparedStatement ps;
	
	public ToolCardDB() {
		this.st = DBCon.getInstance().getSt();

	}
	
	public String getCardName(int cardID) {
		String cardName = "";
		try {
			String query = "Select name from toolcard where idtoolcard =" +  cardID + ";";
			ResultSet resultset = st.executeQuery(query);
			if(resultset.next()) {
				cardName = resultset.getString("name");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cardName;
	}
	
	public void removeDiceFromGameDie(int idgame, int dienumber, String color) {
		try {
			String query = "DELETE FROM gamedie WHERE idgame= "+ idgame +" and dienumber= "+ dienumber +"  and diecolor like '" + color +"';";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private int generategametoolcardid() {
		int gametoolcardid = 0;
		try {
			String query = "SELECT \r\n" + 
					"    MAX(gametoolcard) AS maxtoolcardid\r\n" + 
					"FROM\r\n" + 
					"    gametoolcard";
			ResultSet resultset = st.executeQuery(query);
			if(resultset.next()) {
				gametoolcardid = (resultset.getInt("maxtoolcardid") + 1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gametoolcardid;
	}
	
	
	public void insertToolCardIntoGameToolCardTable(int idtoolcard, int idgame) {
		try {
			String query = "INSERT INTO gametoolcard (gametoolcard, idtoolcard, idgame) VALUES ("+ generategametoolcardid() +", "+ idtoolcard + ", "+ idgame +");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getCardIDsPerGame(int gameid) {
		ArrayList<Integer> cardIDsPerGame = new ArrayList<Integer>();
		int result = 0;
		try {
			String query = "select idtoolcard from gametoolcard where idgame = "+gameid+";";
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				result = resultset.getInt("idtoolcard");
				cardIDsPerGame.add(result);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cardIDsPerGame;
	}
	
	public ArrayList<String> getRoundtrackColor(int gameid) {
		ArrayList<String> colors = new ArrayList<>();
		String color = null;
		try {
			String query = "select * from gamedie where idgame = " + gameid + " and roundtrack = " + getRoundID(gameid);
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				color = resultset.getString("diecolor");
				colors.add(color);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return colors;
	}
	
	public int getRoundID (int idgame) {
		int roundID = 0;
		try {
			String query = "SELECT current_roundID FROM game WHERE idgame = " + idgame;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				roundID = resultset.getInt("current_roundID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundID;
	}
	
	public Boolean getEmpty(int gameid) {
		Boolean bl = true;
		try {
			String query = "select idtoolcard from gametoolcard where idgame = "+gameid+";";
			ResultSet resultset = st.executeQuery(query);
			if(resultset.next()) {
				bl = false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			bl = true;
		}
		return bl;
	}
	
	

	public void setNewEyes(GameDiceModel dice, int eyes) {
		try {
			String query = "update gamedie set eyes = " + eyes + " where idgame = " + dice.getIdgame() + " and dienumber = " + dice.getDieNumber() + " and diecolor like '" + dice.getColor()  + "';";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	} 

}
