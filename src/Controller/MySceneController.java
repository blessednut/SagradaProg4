package Controller;

import View.MyScene;

public class MySceneController {
	
	private MyScene myscene;
	private GameController game;
	private LogInController loginCon = new LogInController();
	
	public MySceneController() {
		myscene = new MyScene(this);
	}

	public MyScene getMyscene() {
		return myscene;
	}

	public LogInController getLoginCon() {
		return loginCon;
	}
	
	
	
	
	

}
