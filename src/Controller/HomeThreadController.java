package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;

import model.HomeThreadModel;

public class HomeThreadController extends Thread {
	private HomeThreadModel m_home;
	private HomeController c_home;
	private InviteController c_Invite;
	private LogInController c_login;
	private ArrayList<String> challengedGameIDString; 
	private String username;
	
	public HomeThreadController(LogInController c_login, InviteController c_invite) {
		this.c_login = c_login;
		this.c_home = c_home;
		this.c_Invite = c_invite;
		this.m_home = new HomeThreadModel();
		challengedGameIDString = new ArrayList<String>();
		username = c_login.getUsername();
	}
	
	// TODO COMBOBOX NOG CHECKEN OF HIJ LEEG IS VOOR DAT HIJ DE DATA INLAAD. AANDERS COMBOX LEEG MAKEN.
	// TODO ook een mogelijkheid om de combox new aan te maken elke als hij of je uitnodigingen hebt.

	// de run methode is de methode die continue blijft draaien.
	public void run() {
		String nameOfChallenger = "";
		String IDOFChallenger = "";
		// door deze loop blijft het programma 1000.000 keer draaien.
		for(int x = 0; x < 1000000; x++) {
			int i = 0;			
			//try vanwege de mysql code.
			try {
				// vraagt je gamid op aan de hand van je username. username komt uit de login controller
				m_home.getGameID(c_login.getUsername());
				// loop om je uitdaging
				while(i< m_home.getChallengedGameID().size()) {
					System.out.println("homeThreadController: " + m_home.getChallengedGameID().get(i));
					nameOfChallenger = m_home.getUsernameOfChallenger(m_home.getChallengedGameID().get(i));
					IDOFChallenger = m_home.getChallengedGameID().get(i).toString();
					if(!challengedGameIDString.contains(IDOFChallenger)) {
						challengedGameIDString.add(IDOFChallenger);
						c_Invite.getV_InvitePane().getInvites().getItems()
						.add(nameOfChallenger + "+" + IDOFChallenger);
						
					}
					
					i++;
				}
				
			} catch (Exception e) {
			}
			// 2 000 miliseconde = sec aan vertraging.
			try {
				Thread.sleep(5000);
			}
			catch(InterruptedException e) 
			{}
			
			x++;
		}
	}

	public HomeThreadModel getM_home() {
		return m_home;
	}

}