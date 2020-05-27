package Controller;

import View.OpenGamesPane;
import model.OpenGamesModel;

public class OpenGamesController {
	private OpenGamesPane OGP;
	private OpenGamesModel OGM;
	
	public OpenGamesController(LogInController logInController) {
		OGM = new OpenGamesModel();
		OGP = new OpenGamesPane();
	}
	
	

	public OpenGamesPane getOGP() {
		return OGP;
	}

	public OpenGamesModel getOGM() {
		return OGM;
	}
	
	public void fillGames() {
		for (int i = 0; i < OGM.getOldGamesArray().size(); i++) {
			if(!OGP.getOldGamesBox().getItems().contains(OGM.getOldGamesArray().get(i))) {
				OGP.getOldGamesBox().getItems().add("Spel ID: "+ OGM.getOldGamesArray().get(i) +",		Datum van spelcreatie: "+ OGM.getOldGamesDateArray().get(i));
			}
		}
		
	}
	
	

}
