package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlayerDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public PlayerDB () {
		this.st = DBCon.getInstance().getSt();
	}
	
	public void getInviteGameID(String username, String Playstatus, String challenger) {
		
	}
	

}
