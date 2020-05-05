package Controller;

import View.MyScene;

public class MySceneController {
	
	private MyScene v_scene;
	private GameController c_game;
	private LogInController c_login = new LogInController(this);
	private HomeController c_home;
	
	public MySceneController() {
		v_scene = new MyScene(this);
<<<<<<< Updated upstream
		c_home = new HomeController(this);
=======
		c_home = new HomeController(this, c_login);

>>>>>>> Stashed changes
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
