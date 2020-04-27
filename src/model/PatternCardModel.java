package model;

import java.sql.ResultSet;
import java.sql.Statement;

import DataBase.DBCon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatternCardModel {
	private final int idPatternCard;
	//private final StringProperty name;
	private final String name;
	private final int difficulty;
	private final boolean standard;
//	private DBCon con;
//	private ResultSet rs;
//	private Statement st;

	public PatternCardModel(int idPatternCard, String name, int difficulty, boolean standard) {
//		this.con = new DBCon();
////		this.idPatternCard = idPatternCard;
////		this.getPatternCard(idPatternCard);
		this.idPatternCard = idPatternCard;
		//this.name = new SimpleStringProperty(this, "name", name);
		this.name = name;
		this.difficulty = difficulty;
		this.standard = standard;
	}

	public int getIdPatternCard() {
		return this.idPatternCard;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public boolean getStandard() {
		return this.standard;
	}
	
	public final StringProperty nameProperty() {
		return new SimpleStringProperty(this, "name", name);
	}
	
	public final IntegerProperty tokenAmount() {
		return new SimpleIntegerProperty(this, "difficulty", difficulty);
	}
	
//	public void getPatternCard(int idPatternCard) {
//		con.createConnection();
//
//		try {
//			String query = "SELECT * \r\n" + "FROM patterncard\r\n" + "WHERE idpatterncard = '" + idPatternCard + "';";
//			rs = st.executeQuery(query);
//
//			while (rs.next()) {
//				this.name = rs.getString("name");
//				this.difficulty = rs.getInt("difficulty");
//				this.standard = rs.getBoolean("standard");
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}
