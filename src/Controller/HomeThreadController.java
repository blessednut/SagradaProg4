package Controller;

import java.util.ArrayList;

import model.HomeThreadModel;

public class HomeThreadController extends Thread {
	private HomeThreadModel homeModel;
	private HomeController homeController;
	private InviteController inviteController;
	private LogInController loginController;
	private ArrayList<String> challengedGameIDString;
	private String username;
	private String nameOfChallenger;
	private String IDOFChallenger;
	private boolean running = true;

	public HomeThreadController(LogInController loginController, InviteController inviteController) {
		this.loginController = loginController;
		this.homeController = homeController;
		this.inviteController = inviteController;
		this.homeModel = new HomeThreadModel();
		challengedGameIDString = new ArrayList<String>();
		username = loginController.getUsername();
	}

	// TODO COMBOBOX NOG CHECKEN OF HIJ LEEG IS VOOR DAT HIJ DE DATA INLAAD. AANDERS COMBOX LEEG MAKEN.
	// TODO ook een mogelijkheid om de combox new aan te maken elke als hij of je uitnodigingen hebt.

	public void beginAgain() {
		this.homeModel = new HomeThreadModel();
		running = true;

	}

	public void terminate() {
		running = false;
	}
	// de run methode is de methode die continue blijft draaien.
	public void run() {
		inviteController.getV_InvitePane().getInvites().getItems().clear();
		nameOfChallenger = "";
		IDOFChallenger = "";
		// door deze loop blijft het programma oneindig keer draaien.
		while(running) {
			int i = 0;
			////System.out.println("ik run weer ");
			//try vanwege de mysql code.
			try {
				// vraagt je gamid op aan de hand van je username. username komt uit de login controller
				homeModel.getGameID(loginController.getUsername());
				// loop om je uitdaging
				while(i< homeModel.getChallengedGameID().size()) {
					nameOfChallenger = homeModel.getUsernameOfChallenger(homeModel.getChallengedGameID().get(i));
					IDOFChallenger = homeModel.getChallengedGameID().get(i).toString();
					if(!challengedGameIDString.contains(IDOFChallenger)) {
						challengedGameIDString.add(IDOFChallenger);
						inviteController.getV_InvitePane().getInvites().getItems()
						.add(nameOfChallenger + "+" + IDOFChallenger);

					}
					i++;
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			// 5 000 miliseconde = 5 sec aan vertraging.
			try {
				Thread.sleep(1000);

			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}

	public HomeThreadModel getM_home() {
		return homeModel;
	}


}
