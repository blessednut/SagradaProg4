package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameController extends Application{
	private MySceneController myscene;
	
	public GameController() {
		
	}
	@Override
	public void start(Stage stage) throws Exception {
		myscene = new MySceneController();
		stage.setTitle("Sagrada 2020");
		stage.setScene(myscene.getMyscene());
		stage.show();
		
		
	}
	
	public void startProg() {
		launch();
	}
	public MySceneController getMyscene() {
		return myscene;
	}
	

}
