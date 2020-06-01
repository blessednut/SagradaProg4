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
	
	
	
	//private StatisticsPane v_statistics;
	private StatisticController statisticController;
	
	
	
	private MySceneController myScene;
	private GameController c_game;
	private LogInController c_login;
	private HomeThreadController c_hometc;
	private OpenGamesController OGC;

	public HomeController(MySceneController mySceneController, LogInController loginController) {
		this.mySceneController = mySceneController;
		this.loginController = loginController;
		//aan maak homepane.
		homeView = new HomePane(this);

		//aan maak gamecontroller.
		gameController = new GameController(mySceneController, loginController);

		// aan maak invitecontroller.
		inviteController = new InviteController(gameController,this);
		
		//aan maak OpenGamesController.
		OGC = new OpenGamesController(c_login, c_game);

		// aan maak homethreadController.
		this.homeThreadController = new HomeThreadController(loginController, inviteController);

		homeThreadController.setDaemon(true);
		homeThreadController.start();


		// aan maak credits pane
		v_credits = new CreditsPane();
		
		//v_statistics = new StatisticsPane();
		statisticController = new StatisticController();
		v_home.getUitloggen().setOnAction(e -> myScene.getMyscene().switchPane(c_login.getLogin()));
		v_home.getVrienden().setOnAction(e -> {openInvitePane();v_home.makeInvites();});
		v_home.getStatistick().setOnAction(e -> openStatisticsPane());
		v_home.getCredits().setOnAction(e -> openCreditsPane());
		v_home.getGames().setOnAction(e -> { openOpenGamesPane();OGC.getOGM().GetOpenGameID(c_login.getUsername());OGC.fillGames();});

	}

	public void openInvitePane() {
		homeView.makeReservedSpace(inviteController.getV_InvitePane());
	}

	public void openStatisticsPane() {
		v_home.makeReservedSpace(statisticController.getView());
	}

	public void openCreditsPane() {
		homeView.makeReservedSpace(creditsView);
	}
	public void openOpenGamesPane() {
		homeView.makeReservedSpace(OGC.getOGP());
	}
	
	public void loadAllGames() {
		OGC.getOGM().getOwnGamesID(loginController.getUsername());
		OGC.getOGM().getGamesIDS();
		OGC.getOGM().GetOpenGameID(loginController.getUsername());
		OGC.fillGames();  
		OGC.fillAllGames();
	}

	public HomePane getV_home() {
		return homeView;
	}

	public LogInController getC_login() {
		return loginController;
	}

	public HomeThreadController getC_hometc() {
		return homeThreadController;
	}
	
	public InviteController getC_Invite() {
		return inviteController;
	}

	public void addInviteStartPane(InviteStart inviteStart) {
		homeView.getHomePaneBottom().getChildren().add(inviteStart);
	}
	public void removeInviteStartPane(InviteStart inviteStart) {
		homeView.getHomePaneBottom().getChildren().remove(inviteStart);
	}
}
