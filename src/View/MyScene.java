package View;

import Controller.MySceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene{
	
	private MySceneController con;
	private HomePane hp = new HomePane();

	public MyScene(MySceneController con) {
		super(new Pane());
		this.con = con;

		setInlogPane();
		
	}
	
	public void setInlogPane() {
		this.setRoot(hp);
	}
	
	

}
