package DataBase;


import java.sql.ResultSet;
import java.sql.Statement;

public class Public_Objective_CardDB {
	private ResultSet rs;
	private Statement st;
	
	public Public_Objective_CardDB() {
		this.st = DBCon.getInstance().getSt();
	}
	
	public String getCardName(int cardID) {
		String cardName = "";
		try {
			String query = " select name from public_objectivecard where idpublic_objectivecard =" + cardID + ";";
			rs = st.executeQuery(query);
			if(rs.next()) {
				cardName = rs.getString("name");
				System.out.println(cardName);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return cardName;
	}

}
