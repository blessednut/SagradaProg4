package model;

import DataBase.ChatDB;

public class ChatModel {
	private ChatDB ChatDB;
	
	
	public ChatModel(){
		this.ChatDB = new ChatDB();
	}
	
	public void writeChatToDatabase(int idplayer, String message) {
		ChatDB.writeChatToDatabase(idplayer, message);
	}
}