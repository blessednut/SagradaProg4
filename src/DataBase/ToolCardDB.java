package DataBase;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			rs = st.executeQuery(query);
			if(rs.next()) {
				cardName = rs.getString("name");
			}
		}
		catch(Exception e){
			System.out.println(e);
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

}
