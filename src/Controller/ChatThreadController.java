package Controller;

import View.ChatView;
import model.ChatModel;

public class ChatThreadController implements Runnable {
	private ChatModel ChatM;
	private ChatView ChatV;
	private int seconds;
	private String message;

	
	public ChatThreadController(ChatModel ChatM, ChatView ChatV) {
		this.ChatM = ChatM;
		this.ChatV = ChatV;
		message = "";
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}