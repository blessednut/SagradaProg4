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

	@Override
	public void run() {
		int i = 0;
		String nameOfChallenger = "";
		String IDOFChallenger = "";
		try {
			m_home.getGameID(c_login.getUsername());
			while(i< m_home.getChallengedGameID().size()) {
				nameOfChallenger = m_home.getUsernameOfChallenger(m_home.getChallengedGameID().get(i));
				IDOFChallenger = m_home.getChallengedGameID().get(i).toString();
				c_Invite.getV_InvitePane().getInvites().getItems()
						.add(nameOfChallenger + "+" + IDOFChallenger);
				i++;
			}
			
		} catch (Exception e) {
		}
	}

	public HomeThreadModel getM_home() {
		return m_home;
	}

}
