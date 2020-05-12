package Controller;

import model.HomeThreadModel;

public class HomeThreadController implements Runnable {
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
	
	@Override
	public void run() {
		int i = 0;
		
		try {
			m_home.getGameID(c_login.getUsername());	
			// while loop omheen om de lijst uit te breiden.
			c_Invite.getV_InvitePane().getUsername().setText(m_home.getUsernameOfChallenger(m_home.getChallengedGameID().get(1)));
		} catch(Exception e) {
		}
	}
}
