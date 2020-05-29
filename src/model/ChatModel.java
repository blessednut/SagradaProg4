package model;

import DataBase.ChatDB;
import DataBase.DBCon;

public class ChatModel {
	private ChatDB ChatDB;
	
	
	public ChatModel(){
		this.ChatDB = new ChatDB();
	}
	
	public void writeChatToDatabase(int idplayer, String message) {
		ChatDB.writeChatToDatabase(idplayer, message);
	}
	
	public String getUsername(int idgame) {
		return ChatDB.getUsernameForChat(idgame);
	}
	
	public String getMessage(int idgame) {
		return ChatDB.getMessageForChat(idgame);
	}
	
	public DBCon getDBCOn() {
		return ChatDB.getDbcon();
	}
}