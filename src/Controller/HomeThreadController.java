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
	
	public HomeThreadController(LogInController loginController, InviteController inviteController) {
		this.loginController = loginController;
		this.homeController = homeController;
		this.inviteController = inviteController;
		this.homeModel = new HomeThreadModel();
		challengedGameIDString = new ArrayList<String>();
		username = loginController.getUsername();
	}
	// de run methode is de methode die continue blijft draaien.
	public void run() {
		nameOfChallenger = "";
		IDOFChallenger = "";
		// door deze loop blijft het programma 1000.000 keer draaien.
		for(int x = 0; x < 1000000; x++) {
			int i = 0;			
			//try vanwege de mysql code.
			try {
				// vraagt je gamid op aan de hand van je username. username komt uit de login controller
				homeModel.getGameID(username);
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 5 000 miliseconde = 5 sec aan vertraging.
			try {
				Thread.sleep(5000);
			}
			catch(InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			x++;
		}
	}

	public HomeThreadModel getM_home() {
		return homeModel;
	}

}