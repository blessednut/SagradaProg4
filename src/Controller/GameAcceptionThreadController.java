package Controller;

import javafx.application.Platform;
import model.GameAcceptionThreadModel;

public class GameAcceptionThreadController extends Thread {

	private LogInController logInController;
	private InviteController inViteController;
	private GameAcceptionThreadModel gameThreadModel;
	private int amountInGame;
	private int gameid;
	private String amountNotAcceptedString;


	public GameAcceptionThreadController(LogInController logInController, InviteController inViteController, int gameid
			) {
		System.out.println("vooran constructor");
		this.logInController = logInController;
		this.inViteController = inViteController;
		gameThreadModel = new GameAcceptionThreadModel();
		this.gameid = gameid;
		System.out.println("achteraan constructor");
	}

	public void run() {
		int amountRefused = 0;
		int amountNotAccepted = 1;
		System.out.println("gameid: "+gameid);
		
		for (int x = 0; x < 1000000; x++) {
			System.out.println("ik doe het");
			try {
				amountRefused = gameThreadModel.getAmountRefused(gameid);
				System.out.println("voor de if: " + amountRefused);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (amountRefused == 1) {
				System.out.println("huil huil huil huil huil");
				gameThreadModel.setRefused(gameid);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						
						removeGameStart();
					}	
				});
				System.out.println("help ik stop");
				return;
			}
			try {
				amountNotAccepted = gameThreadModel.getAmountAccepted(gameid);
				amountNotAcceptedString = Integer.toString(amountNotAccepted);
			} catch (Exception e) {

			}
			System.out.println("voor de alertbox: " + amountNotAccepted);
			if (amountNotAccepted == 0) {
				System.out.println("in de if");
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						inViteController.getInviteStart().getNumberRemaining().setText("niet geaccepteed: "+amountNotAcceptedString);
						inViteController.getInviteStart().getStartGame().setVisible(true);	
					}
				});
				return;
			} else {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						inViteController.getInviteStart().getNumberRemaining().setText("niet geaccepteed: "+amountNotAcceptedString);
						
					}
				});
			}
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
		}
	}
	
	public void removeGameStart() {
		inViteController.getHome().removeInviteStartPane(inViteController.getInviteStart());
	}
}
