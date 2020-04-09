package Controller;

import java.util.ArrayList;

import View.MyScene;

public class MySceneController {
	
	private MyScene v_scene;
	private GameController c_game;
	private LogInController c_login = new LogInController();
	private MenuBarController c_menubar;
	
	private ArrayList v_list = new ArrayList();
	
	
	public MySceneController() {
		v_scene = new MyScene(this);
		c_menubar = new MenuBarController();
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
	
	
	

}
