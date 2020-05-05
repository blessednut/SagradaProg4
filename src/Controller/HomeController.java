package Controller;

import View.CreditsPane;
import View.HomePane;
import View.InvitePane;
import View.StatisticsPane;
import model.HomeThreadModel;

public class HomeController {

	private HomePane v_home;

	private InvitePane v_invite;

	private InviteController c_Invite;

	private CreditsPane v_credits;
	private StatisticsPane v_statistics;
	private MySceneController myScene;
	private GameController c_game;
	private LogInController c_login;
	private HomeThreadModel m_home;
	private HomeThreadController c_hometc;

	public HomeController(MySceneController myScene) {
		this.myScene = myScene;
		c_game = new GameController(myScene);
		v_home = new HomePane(this);
		v_invite = new InvitePane();
		v_credits = new CreditsPane();
		v_statistics = new StatisticsPane();
		this.c_hometc = new HomeThreadController(c_login, c_Invite);
		threadMethod();
		v_home.getVrienden().setOnAction(e -> openInvitePane());
		v_home.getStatistick().setOnAction(e -> openStatisticsPane());
		v_home.getCredits().setOnAction(e -> openCreditsPane());
		v_home.getGames().setOnAction(e -> c_game.setGamePane());
	}

	public void openInvitePane() {
		v_home.makeReservedSpace(v_invite);
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

	public InvitePane getV_invite() {
		return v_invite;
	}

	public StatisticsPane getV_statistics() {
		return v_statistics;
	}

	private void threadMethod() throws NullPointerException {
		System.out.println("Test");
		Thread th = new Thread(c_hometc);
		th.start();
	}

}
