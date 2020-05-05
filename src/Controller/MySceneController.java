package Controller;

import View.MyScene;

public class MySceneController {

	private MyScene v_scene;
	private LogInController c_login = new LogInController(this);
	private HomeController c_home;

	public MySceneController() {
		v_scene = new MyScene(this);
<<<<<<< HEAD
<<<<<<< HEAD
		c_home = new HomeController(this, c_login);
		System.out.println("hoi");

=======
		c_home = new HomeController(this);
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread
=======
		c_home = new HomeController(this);
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread
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

}
