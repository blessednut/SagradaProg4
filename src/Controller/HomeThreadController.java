package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;

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

	public void beginAgain() {
		this.homeModel = new HomeThreadModel();
		running = true;

	}

	public void terminate() {
		running = false;
	}
	// de run methode is de methode die continue blijft draaien.
	public void run() {
		nameOfChallenger = "";
		IDOFChallenger = "";
		// door deze loop blijft het programma oneindig keer draaien.
		while(running) {
			int i = 0;
			System.out.println("ik run weer ");
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
				System.out.println("slaap");
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
