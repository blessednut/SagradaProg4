package Controller;

import View.CreditsPane;
import View.HomePane;
import View.InviteStart;
import View.OpenGamesPane;
import View.StatisticsPane;

public class HomeController {

	private HomePane v_home;
	private InviteController c_Invite;
	private CreditsPane v_credits;
	private StatisticsPane v_statistics;
	private MySceneController myScene;
	private GameController c_game;
	private LogInController c_login;
	private HomeThreadController c_hometc;
	private OpenGamesController OGC;

	public HomeController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;
		//aan maak homepane.
		v_home = new HomePane(this);

		//aan maak gamecontroller.
		c_game = new GameController(myScene, c_login);

		// aan maak invitecontroller.
		c_Invite = new InviteController(c_game,this);
		
		//aan maak OpenGamesController.
		OGC = new OpenGamesController(c_login);

		// aan maak homethreadController.
		this.c_hometc = new HomeThreadController(c_login, c_Invite);

		c_hometc.setDaemon(true);
		c_hometc.start();


		// aan maak credits pane
		v_credits = new CreditsPane();
		// aan maak statspane.
		v_statistics = new StatisticsPane();

		// buttons
		
		v_home.getUitloggen().setOnAction(e -> myScene.getMyscene().switchPane(c_login.getLogin()));
		v_home.getVrienden().setOnAction(e -> {openInvitePane();v_home.makeInvites();});
		v_home.getStatistick().setOnAction(e -> openStatisticsPane());
		v_home.getCredits().setOnAction(e -> openCreditsPane());
		v_home.getGames().setOnAction(e -> { openOpenGamesPane();OGC.getOGM().GetOpenGameID(c_login.getUsername());OGC.fillGames();});

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
	public void openOpenGamesPane() {
		v_home.makeReservedSpace(OGC.getOGP());
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

	public HomeThreadController getC_hometc() {
		return c_hometc;
	}
	
	public InviteController getC_Invite() {
		return c_Invite;
	}

	public void addInviteStartPane(InviteStart inviteStart) {
		v_home.getHomePaneBottom().getChildren().add(inviteStart);
	}
	public void removeInviteStartPane(InviteStart inviteStart) {
		v_home.getHomePaneBottom().getChildren().remove(inviteStart);
	}
}
