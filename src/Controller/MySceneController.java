package Controller;

import View.HomePane;
import View.MyScene;

public class MySceneController {
	
	private MyScene v_scene;
	private GameController c_game;
	private LogInController c_login = new LogInController(this);
	private HomeController c_home;
	
	public MySceneController() {
		v_scene = new MyScene(this);
		c_home = new HomeController(this);
	}

	public MyScene getMyscene() {
		return v_scene;
	}
	
	public LogInController getLoginCon() {
		return c_login;
	}
	
	public HomeController getC_home() {
		return c_home;
	}

	public void addToViewList() {
		// add panes to v_list to make switching panes easier.
	}

}
