package DataBase;

import java.sql.ResultSet;
import java.sql.Statement;

public class Private_Objective_CardDB {

	private Statement st;


	
	public Private_Objective_CardDB() {
		this.st = DBCon.getInstance().getSt();
	}
	
	public String getColor(int GameId, String username) {
		String cardColor = "";
		try {
			String query = "Select private_objectivecard_color from player where idgame = " + GameId + " and username = '" + username + "';";
			ResultSet resultset = st.executeQuery(query);
			if(resultset.next()) {
				cardColor = resultset.getString("private_objectivecard_color");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cardColor;
	}

}
