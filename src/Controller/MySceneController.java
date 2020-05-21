package Controller;

import View.MyScene;

public class MySceneController {

	private MyScene v_scene;
	private LogInController c_login = new LogInController(this);
//	private HomeController c_home;

	public MySceneController() {
		v_scene = new MyScene(this);

	}

	public MyScene getMyscene() {
		return v_scene;
	}

	public LogInController getLoginCon() {
		return c_login;
	}

//	public HomeController getC_home() {
//		return c_home;
//	}

}
