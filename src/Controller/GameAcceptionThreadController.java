package Controller;

import model.GameAcceptionThreadModel;

public class GameAcceptionThreadController extends Thread {
	
	private LogInController logInController;
	private InviteController inViteController;
	private GameAcceptionThreadModel gameThreadModel;
	private int amountInGame;
	
	public GameAcceptionThreadController(LogInController logInController, InviteController inViteController) {
		this.logInController = logInController;
		this.inViteController = inViteController;
	}
	
	public void run() {
		
	}

}
