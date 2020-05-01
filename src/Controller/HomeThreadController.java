package Controller;

import model.HomeThreadModel;

public class HomeThreadController implements Runnable {
	private HomeThreadModel m_home;
	private HomeController c_home;
	private InviteController c_Invite;
	private LogInController c_login;
	
	public HomeThreadController(LogInController c_login) {
		this.c_login = c_login;
		this.c_home = c_home;
		this.m_home = new HomeThreadModel();
	}
	
	@Override
	public void run() {
		try {
			m_home.getUsernameOfChallenger("challengee");
			c_Invite.getV_InvitePane().getUsername().setText(m_home.getUsernameOfChallenger("challenger"));
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
