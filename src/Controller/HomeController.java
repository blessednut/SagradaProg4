package Controller;

import DataBase.DBCon;
import View.CreditsPane;
import View.GamePane;
import View.HomePane;
import View.InvitePane;
import View.StatisticsPane;

public class HomeController {

	private HomePane v_home;
	private InvitePane v_invite;
	private CreditsPane v_credits;
	private GamePane v_game;
	private StatisticsPane v_statistics;
	private MySceneController myScene;

	public HomeController(MySceneController myScene) {
		this.myScene = myScene;
		v_game = new GamePane();
		v_home = new HomePane(this);
		v_invite = new InvitePane();
		v_credits = new CreditsPane();
		v_statistics = new StatisticsPane();
		v_home.getVrienden().setOnAction(e -> openInvitePane());
		v_home.getStatistick().setOnAction(e -> openStatisticsPane());
		v_home.getCredits().setOnAction(e -> openCreditsPane());
		v_home.getGames().setOnAction(e -> openGamePane());
	}

	public void openGamePane() {
		// Wanneer een lijst van spellen getoont kan worden moet de gameController
		// aangemaakt worden wanneer er een spel is gekozen.
		
		// myScene.getMyscene().switchPane(v_game);
		GameController gameController = new GameController(myScene);
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

}
