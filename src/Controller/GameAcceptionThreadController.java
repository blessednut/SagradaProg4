package Controller;

import javax.swing.ButtonModel;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.GameAcceptionThreadModel;

public class GameAcceptionThreadController extends Thread {
	
	private LogInController logInController;
	private InviteController inViteController;
	private GameAcceptionThreadModel gameThreadModel;
	private int amountInGame;
	private int gameid;
	
	public GameAcceptionThreadController(LogInController logInController, InviteController inViteController, int gameid) {
		this.logInController = logInController;
		this.inViteController = inViteController;
		this.gameid = gameid;
	}
	
	public void run() {
		
		for(int x = 0; x < 1000000; x++) {
			int amountRefused = gameThreadModel.getAmountRefused(gameid);
			if(amountRefused > 0) {
				Alert exception = new Alert(AlertType.ERROR,
						"Het spel gaat helaas niet door. \n Iemand heeft gerefused.",
						ButtonType.OK);
				exception.showAndWait();
				
				gameThreadModel.setRefused(gameid);
				return;
				
			}
			int amountAccepted = gameThreadModel.getAmountAccepted(gameid);
			if(amountAccepted == 0 ) {
				Alert gamestart = new Alert(AlertType.CONFIRMATION, "het spel is klaar om te beginnen", ButtonType.OK);
				gamestart.showAndWait();
				if(gamestart.getResult() == ButtonType.OK) {
					inViteController.getC_game().createGamePane();
				}
			}
			
		}
		
	}

}
