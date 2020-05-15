package Controller;

import model.HomeThreadModel;

public class HomeThreadController extends Thread {
	private HomeThreadModel m_home;
	private HomeController c_home;
	private InviteController c_Invite;
	private LogInController c_login;

	public HomeThreadController(LogInController c_login, InviteController c_invite) {
		this.c_login = c_login;
		this.c_home = c_home;
		this.c_Invite = c_invite;
		this.m_home = new HomeThreadModel();
	}
	
	// TODO COMBOBOX NOG CHECKEN OF HIJ LEEG IS VOOR DAT HIJ DE DATA INLAAD. AANDERS COMBOX LEEG MAKEN.

	// de run methode is de methode die continue blijft draaien.
	public void run() {
		// door deze loop blijft het programma 1000.000 keer draaien.
		for(int x = 0; x < 1000000; x++) {
			int i = 0;
			String nameOfChallenger = "";
			String IDOFChallenger = "";
			// hier kijkt de thread of de combobox leeg is.
			for(int c = 0; c < c_Invite.getV_InvitePane().getInvites().getItems().size(); c++) {
				// zo niet dan maakt hij deze leeg.
				if(!c_Invite.getV_InvitePane().getInvites().getItems().isEmpty()) {
					c_Invite.getV_InvitePane().getInvites().getItems().remove(c);
				}
			}
			//try vanwege de mysql code.
			try {
				// vraagt je gamid op aan de hand van je username. username komt uit de login controller
				m_home.getGameID(c_login.getUsername());
				// loop om je uitdaging
				while(i< m_home.getChallengedGameID().size()) {
					nameOfChallenger = m_home.getUsernameOfChallenger(m_home.getChallengedGameID().get(i));
					IDOFChallenger = m_home.getChallengedGameID().get(i).toString();
					c_Invite.getV_InvitePane().getInvites().getItems()
							.add(nameOfChallenger + "+" + IDOFChallenger);
					System.out.println("ik doe het");
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
