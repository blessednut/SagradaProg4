package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Private_Objective_Card_Model;

public class Private_Objective_CardDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	private Private_Objective_Card_Model POCM;
	
	public Private_Objective_CardDB(Private_Objective_Card_Model POCM) {
		this.POCM = POCM;
		this.st = DBCon.getInstance().getSt();
	}
	
	public String getColor(int GameId, String username) {
		String cardColor = "";
		try {
			String query = "Select private_objectivecard_color from player where idgame = " + GameId + " and username = '" + username + "';";
			rs = st.executeQuery(query);
			if(rs.next()) {
				cardColor = rs.getString("private_objectivecard_color");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cardColor;
	}

}
