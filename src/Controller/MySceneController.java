package Controller;

import View.MyScene;

public class MySceneController {

	private MyScene mySceneView;
	private LogInController logInController = new LogInController(this);

	public MySceneController() {
		mySceneView = new MyScene(this);
	}

	public MyScene getMyscene() {
		return mySceneView;
	}

	public LogInController getLoginCon() {
		return logInController;
	}

}
