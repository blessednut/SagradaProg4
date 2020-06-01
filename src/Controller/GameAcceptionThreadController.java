package Controller;

import javafx.application.Platform;
import model.GameAcceptionThreadModel;

public class GameAcceptionThreadController extends Thread {

	private InviteController inViteController;
	private GameAcceptionThreadModel gameThreadModel;
	private int gameid;
	private String amountNotAcceptedString;
	private boolean running = true;


	public GameAcceptionThreadController(InviteController inViteController, int gameid
			) {

		this.inViteController = inViteController;
		gameThreadModel = new GameAcceptionThreadModel();
		this.gameid = gameid;

	}

	public void terminate() {
		running= false;
	}

	public void beginAgain() {
		running = true;
	}

	public void run() {
		int amountRefused = 0;
		int amountNotAccepted = 1;

		while(running) {
			try {
				amountRefused = gameThreadModel.getAmountRefused(gameid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (amountRefused == 1) {
				gameThreadModel.setRefused(gameid);
				Platform.runLater(new Runnable() {

					
					public void run() {

						removeGameStart();
					}
				});
				return;
			}
			try {
				amountNotAccepted = gameThreadModel.getAmountAccepted(gameid);
				amountNotAcceptedString = Integer.toString(amountNotAccepted);
			} catch (Exception e) {

			}
			if (amountNotAccepted == 0) {
				Platform.runLater(new Runnable() {

					
					public void run() {
						inViteController.getInviteStart().getNumberRemaining().setText("niet geaccepteed: "+amountNotAcceptedString);
						inViteController.getInviteStart().getStartGame().setVisible(true);
						gameThreadModel.setAccepted(gameid);
					}
				});
				return;
			} else {
				Platform.runLater(new Runnable() {

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

	public GameAcceptionThreadModel getGameThreadModel() {
		return gameThreadModel;
	}


}
