package DataBase;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ToolCardDB {
	private ResultSet rs;
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
			System.out.println("ToolcardDB: de query doet het niet");
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
				System.out.println("ToolCardDB 51: "+ gametoolcardid);
			}
		}catch(Exception e) {
			System.out.println("Toolcard 58: dit is een foutmelding");
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
	
	


}
