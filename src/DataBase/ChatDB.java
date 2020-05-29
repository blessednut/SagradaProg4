package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ChatDB {
	private Connection connection;
	private PreparedStatement ps;
	private Statement st;
	private DBCon dbcon;
	
	 
	public ChatDB() {
		DBCon dbcon = new DBCon();
		this.st = dbcon.getSt();
	}
	
	//query om de username van het chatbericht op te halen
	public String getUsernameForChat(int idgame) {
		String username = "";
		try {
			String query = "select player.username from player\r\n" + 
					"left join chatline on chatline.idplayer = player.idplayer\r\n" + 
					"where chatline.time = (select max(time) from chatline) and idgame =" + idgame + ";";
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()) {
				username = resultSet.getString("username");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return username;
	}
	
	//query om het meest recente bericht op te halen uit de databse
	public String getMessageForChat(int idgame) {
		String chatmessage = "";
		try {
			String query = "select chatline.message from player\r\n" + 
					"left join chatline on chatline.idplayer = player.idplayer\r\n" + 
					"where chatline.time = (select max(time) from chatline) and idgame =" + idgame + ";";
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()) {
				chatmessage = resultSet.getString("message");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return chatmessage;
	}
	
	//query om het bericht samen met de gebruiker weg te scrijven naar de database
	public void writeChatToDatabase(int idplayer, String message) {
		try {
			String query = "insert into chatline(idplayer, time, message) values("+ idplayer +" , now(), '" + message +"')";
			st.execute(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public DBCon getDbcon() {
		return dbcon;
	}
	
}