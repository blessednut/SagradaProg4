package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Public_Objective_CardDB {
	private ResultSet PublicObjectiveCardResultSet;
	private Statement st;
	private PreparedStatement ps;

	public Public_Objective_CardDB() {
		this.st = DBCon.getInstance().getSt();
	}

	public String getCardName(int cardID) {
		String cardName = "";
		try {
			String query = " select name from public_objectivecard where idpublic_objectivecard =" + cardID + ";";
			PublicObjectiveCardResultSet = st.executeQuery(query);
			if (PublicObjectiveCardResultSet.next()) {
				cardName = PublicObjectiveCardResultSet.getString("name");
				cardName.toString();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return cardName;
	}

	public void insertPublicOC(int idgame, int cardID) {
		try {
			String query = "INSERT INTO gameobjectivecard_public (idgame, idpublic_objectivecard) VALUES (" + idgame
					+ "," + cardID + ");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] getNames(int idgame) {
		String[] name = new String[3];
		int counter = 0;
		try {
			String query = "SELECT * FROM gameobjectivecard_public AS gp JOIN public_objectivecard AS po ON gp.idpublic_objectivecard = po.idpublic_objectivecard WHERE idgame = " + idgame;
			ResultSet resultset = (st.executeQuery(query));
			
			while (resultset.next()) {
				if (counter <= 2) {
					//System.out.println(resultset.getString("name"));
					name[counter] = resultset.getString("name");
					counter++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
