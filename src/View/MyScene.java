package View;

import Controller.MySceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class MyScene extends Scene {
	
	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;
	private MySceneController mySceneController;
	
	public MyScene(MySceneController mySceneController) {
		super(new Pane(),SAGRADAWIDTH, SAGRADAHEIGHT, null);
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
