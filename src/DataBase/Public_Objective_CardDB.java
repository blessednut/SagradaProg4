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
			//System.out.println(e);
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
					name[counter] = resultset.getString("name");
					counter++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public ArrayList<Integer> getCardIDsPerGame(int gameID){
		ArrayList<Integer> cardIDsPerGame = new ArrayList<Integer>();
		int result = 0;
		try {
			String query = "select idpublic_objectivecard from gameobjectivecard_public where idgame = "+gameID+";";
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				result = resultset.getInt("idpublic_objectivecard");
				cardIDsPerGame.add(result);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cardIDsPerGame;

	}
	
	public Boolean getEmpty(int gameID) {
		Boolean bl = true;
		try {
			String query = "select idpublic_objectivecard from gameobjectivecard_public where idgame = "+gameID+";";
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
