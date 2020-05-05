package Controller;

import View.CreditsPane;
import View.HomePane;
import View.StatisticsPane;
import model.HomeThreadModel;

public class HomeController {

	private HomePane v_home;
	private InviteController c_Invite;
//	private InvitePane v_invite;
	private CreditsPane v_credits;
	private StatisticsPane v_statistics;
	private MySceneController myScene;
	private GameController c_game;
	private LogInController c_login;
<<<<<<< Updated upstream
	private HomeThreadModel m_home;

=======
	private homeThread lookingForInvite;
>>>>>>> Stashed changes

	
	
	public HomeController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;
		c_game = new GameController(myScene, c_login);
		v_home = new HomePane(this);
		c_Invite = new InviteController(c_game,this);
		v_credits = new CreditsPane();
		v_statistics = new StatisticsPane();
		lookingForInvite = new homeThread();
		lookingForInvite.start();
		try {
			homeThread.sleep(50000L);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		v_home.getVrienden().setOnAction(e -> openInvitePane());
		v_home.getStatistick().setOnAction(e -> openStatisticsPane());
		v_home.getCredits().setOnAction(e -> openCreditsPane());
		v_home.getGames().setOnAction(e -> openGamePane());
	}

	public void openGamePane() {
		c_game = new GameController(myScene, c_login);
	}

	public void openInvitePane() {
		v_home.makeReservedSpace(c_Invite.getV_InvitePane());
	}

	public void openStatisticsPane() {
		v_home.makeReservedSpace(v_statistics);
	}

	public void openCreditsPane() {
		v_home.makeReservedSpace(v_credits);
	}

	public HomePane getV_home() {
		return v_home;
	}

	public StatisticsPane getV_statistics() {
		return v_statistics;
	}

	public LogInController getC_login() {
		return c_login;
	}
	

}
