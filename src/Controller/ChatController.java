package Controller;

import View.ChatView;
import javafx.scene.layout.Pane;
import model.ChatModel;

public class ChatController {
	private String chatMessage;
	private String chatFromDB;
	private ChatModel ChatM;
	private ChatView ChatV;
	private GameController gamecontroller;

	private Pane pane;
	
	public ChatController(GameController gamecontroller) {
		this.gamecontroller = gamecontroller;
		this.ChatM = new ChatModel();
		this.ChatV = new ChatView(this);
		Thread fetchRecentChat = new Thread(new ChatThreadController(ChatM, this.ChatV));
		fetchRecentChat.start();
		pane = this.ChatV;

	}

	//zorgt er voor dat het game model de pane op kan halen en plaatsen
	public Pane getPane() {
		return pane;
	}
	//Haalt de chat op uit de chatView
	//Geeft de chat door aan het model
	public void getChatMessage(String text) {
		int idplayer = gamecontroller.getPlayerController().getPlayerID();
		ChatM.writeChatToDatabase(idplayer, text);
	}
}
