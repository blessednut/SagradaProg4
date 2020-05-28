package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ChatDB {
	private Connection connection;
	private PreparedStatement ps;
	
	 
	public ChatDB() {
		this.connection = DBCon.getInstance().getCon();
	}
	//query om de username van het chatbericht op te halen
	public String getUsernameForChat() {
		String username = "";
		try {
			String query = "select player.username from player\r\n" + 
					"left join chatline on chatline.idplayer = player.idplayer\r\n" + 
					"where chatline.time = (select max(time) from chatline)";
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			while(resultSet.next()) {
				username = resultSet.getString("username");
				System.out.println(username);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return username;
	}
	
	//query om het meest recente bericht op te halen uit de databse
	public String getMessageForChat() {
		String chatmessage = "";
		try {
			String query = "select chatline.message from player\r\n" + 
					"left join chatline on chatline.idplayer = player.idplayer\r\n" + 
					"where chatline.time = (select max(time) from chatline)" ;
			ResultSet resultSet = connection.createStatement().executeQuery(query);
			while(resultSet.next()) {
				chatmessage = resultSet.getString("message");
				System.out.println(chatmessage);
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
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}