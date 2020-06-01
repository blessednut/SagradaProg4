package Controller;

import View.CreditsPane;
import View.HomePane;
import View.InviteStart;
import View.OpenGamesPane;
import View.StatisticsPane;

public class HomeController {

	private HomePane homeView;
	private InviteController inviteController;
	private CreditsPane creditsView;
	private StatisticsPane statsView;
	private MySceneController mySceneController;
	private GameController gameController;
	private LogInController loginController;
	private HomeThreadController homeThreadController;
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
		OGC = new OpenGamesController(loginController);

		// aan maak homethreadController.
		this.homeThreadController = new HomeThreadController(loginController, inviteController);

		homeThreadController.setDaemon(true);
		homeThreadController.start();


		// aan maak credits pane
		creditsView = new CreditsPane();
		// aan maak statspane.
		statsView = new StatisticsPane();

		// buttons
		
		homeView.getUitloggen().setOnAction(e -> mySceneController.getMyscene().switchPane(loginController.getLogin()));
		homeView.getVrienden().setOnAction(e -> {openInvitePane();homeView.makeInvites();});
		homeView.getStatistick().setOnAction(e -> openStatisticsPane());
		homeView.getCredits().setOnAction(e -> openCreditsPane());
		homeView.getGames().setOnAction(e -> { openOpenGamesPane(); loadAllGames();});

	}

	public void openInvitePane() {
		homeView.makeReservedSpace(inviteController.getV_InvitePane());
	}

	public void openStatisticsPane() {
		homeView.makeReservedSpace(statsView);
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

	public StatisticsPane getV_statistics() {
		return statsView;
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
