package Controller;

import model.GameAcceptionThreadModel;

public class GameAcceptionThreadController extends Thread {
	
	private LogInController logIn;
	private InviteController inVite;
	private GameAcceptionThreadModel gameThreadModel;
	private int amountInGame;
	
	public GameAcceptionThreadController(LogInController logIn, InviteController inVite) {
		this.logIn = logIn;
		this.inVite = inVite;
	}
	
	public void run() {
		System.out.println("IK RUN !!!!!!!!!!!!!!!");
		// eerst ophalen hoeveel mensen er in de game zijn uitgenodigd.
		int gameID = inVite.getC_game().getM_game().getGameId();
		try {
			amountInGame = gameThreadModel.getGameID(inVite.getC_game().getM_game().getGameId());
		} catch(Exception e) {
			
		}
		for (int x = 0; x < 1000000; x++) {
			int amountAccepted = gameThreadModel.getAmountAccepted(gameID);
			// vervolgens tellen hoeveel mensen geaccepteerd hebben.
			if(amountAccepted == amountInGame) {
				//als de hoeveelheid mensen geaccepteerd hebben gamepane openen.
				inVite.getC_game().createGamePane();
			}
			
			// checken in db of er iemand heeft gecanceld.
			int AmountRefused = gameThreadModel.getAmountRefused(gameID);
			
			if(AmountRefused > 0 ) {
				// alertBox maken voor refused
				// als iemand refused melden dat het spel niet zal starten en database updaten met finished.
			}
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) 
			{}
		}
	}

}
