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
	private boolean running = true;


	public GameAcceptionThreadController(LogInController logInController, InviteController inViteController, int gameid
			) {
		System.out.println("vooran constructor");
		this.logInController = logInController;
		this.inViteController = inViteController;
		gameThreadModel = new GameAcceptionThreadModel();
		this.gameid = gameid;
		System.out.println("achteraan constructor");
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
		System.out.println("gameid: "+gameid);

		while(running) {
			try {
				amountRefused = gameThreadModel.getAmountRefused(gameid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (amountRefused == 1) {
				gameThreadModel.setRefused(gameid);
				Platform.runLater(new Runnable() {

					@Override
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

	public GameAcceptionThreadModel getGameThreadModel() {
		return gameThreadModel;
	}


}
