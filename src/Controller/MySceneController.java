package Controller;

import java.util.ArrayList;

import java.util.ArrayList;

import View.HomePane;
import View.MyScene;

public class MySceneController {
	


	private MyScene v_scene;
	private GameController c_game;
	private LogInController c_login = new LogInController(this);

	private HomePane v_home;
	
	private ArrayList v_list = new ArrayList();
	
	
	public MySceneController() {
		v_scene = new MyScene(this);


		v_home = new HomePane();

	}

	public MyScene getMyscene() {
		return v_scene;
	}
	


	public LogInController getLoginCon() {
		return c_login;
	}
	


	public void addToViewList() {
		// add panes to v_list to make switching panes easier.
	}

	public HomePane getV_home() {
		return v_home;
	}

}
