package Controller;

import View.HomePane;
import View.InvitePane;
import View.StatisticsPane;
import javafx.stage.Screen;

public class HomeController {

	private HomePane v_home;
	private InvitePane v_invite;
	private StatisticsPane v_statistics;
	private MySceneController myScene;

	public HomeController(MySceneController myScene) {
		this.myScene = myScene;
		v_home = new HomePane(this);
		v_invite = new InvitePane();
		v_statistics = new StatisticsPane();
		v_home.getVrienden().setOnAction(e -> openInvitePane());
		v_home.getStatistick().setOnAction(e -> openStatisticsPane());
	}

	public void openInvitePane() {
		v_home.makeReservedSpace(v_invite);
	}

	public void openStatisticsPane() {
		v_home.makeReservedSpace(v_statistics);
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
