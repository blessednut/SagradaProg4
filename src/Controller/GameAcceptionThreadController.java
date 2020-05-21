package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.GameAcceptionThreadModel;

public class GameAcceptionThreadController extends Thread {

	private LogInController logInController;
	private InviteController inViteController;
	private GameAcceptionThreadModel gameThreadModel;
	private int amountInGame;
	private int gameid;
	private Alert gamestart;

	public GameAcceptionThreadController(LogInController logInController, InviteController inViteController, int gameid
			) {
		this.logInController = logInController;
		this.inViteController = inViteController;
		gameThreadModel = new GameAcceptionThreadModel();
		this.gameid = gameid;
		gamestart = new Alert(AlertType.CONFIRMATION, "lets play a game", ButtonType.YES, ButtonType.NO);
	}

	public void run() {
		int amountRefused = 0;
		int amountAccepted = 1;
		System.out.println("gameid: "+gameid);
		
		for (int x = 0; x < 1000000; x++) {
			System.out.println("ik doe het");
			try {
				amountRefused = gameThreadModel.getAmountRefused(gameid);
				System.out.println("voor de if: " + amountRefused);
			} catch (Exception e) {

			}
			if (amountRefused == 1) {
				System.out.println("huil huil huil huil huil");
				gameThreadModel.setRefused(gameid);
				System.out.println("na refused: " + amountRefused);
				return;
			}
			try {
				amountAccepted = gameThreadModel.getAmountAccepted(gameid);
			} catch (Exception e) {

			}
			System.out.println("voor de alertbox: " + amountAccepted);
			if (amountAccepted == 0) {
				gamestart.showAndWait();
				if (gamestart.getResult() == ButtonType.YES) {
					inViteController.getC_game().createGamePane();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (Exception e) {

			}

		}

	}
}
