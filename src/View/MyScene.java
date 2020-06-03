package View;

import Controller.MySceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {

	private MySceneController mySceneController;

	public MyScene(MySceneController mySceneController) {
		super(new Pane());
		this.mySceneController = mySceneController;

		setInlogPane();

	}

	private void setInlogPane() {
		this.setRoot(mySceneController.getLoginCon().getLogin());
	}

	public void switchPane(Pane pane) {
		this.setRoot(pane);
	}
}
