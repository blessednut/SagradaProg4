package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		MySceneController myscene = new MySceneController();
		GameController gameCon = new GameController();
		stage.setTitle("Sagrada 2020");
		stage.setScene(myscene.getMyscene());
		stage.show();

	}

}
