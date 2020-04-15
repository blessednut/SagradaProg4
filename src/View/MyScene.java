package View;

import Controller.MySceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
	
	private MySceneController c_myScene;


	public MyScene(MySceneController c_myScene) {
		super(new Pane());
		this.c_myScene = c_myScene;

		setInlogPane();

	}
	
	private void setInlogPane() {
		this.setRoot(c_myScene.getLoginCon().getLogin());
	}
	
	public void switchPane(Pane pane) {
		this.setRoot(pane);
	}
	

}
