package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.DiceModel;

public class DiceDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public DiceDB () {
		this.st = DBCon.getInstance().getSt();
	}
	
	public DiceModel[] importDie() {
		try {
			DiceModel[] die = new DiceModel[getDieAmount()];
			
			String query = "SELECT *\r\n" + 
					"FROM die;";
			rs = st.executeQuery(query);

			int i = 0;
			while (rs.next()) {
				die[i] = new DiceModel(rs.getInt("number"), rs.getString("color"));
				i++;
			}
			return die;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private int getDieAmount() {
		try {
			int value = 0;
			String query = "SELECT COUNT(*) \r\n" + 
					"FROM die";
			rs = st.executeQuery(query);

			while (rs.next()) {
				value = rs.getInt(1);
			}
			
			return value;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
