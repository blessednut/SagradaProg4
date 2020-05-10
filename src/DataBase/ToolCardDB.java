package DataBase;


import java.sql.ResultSet;
import java.sql.Statement;

public class ToolCardDB {
	private ResultSet rs;
	private Statement st;
	
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

}
