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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
//		stage.setAlwaysOnTop(true);
>>>>>>> Stashed changes
=======
//		stage.setAlwaysOnTop(true);
>>>>>>> Stashed changes
=======
//		stage.setAlwaysOnTop(true);
>>>>>>> Stashed changes
=======
//		stage.setAlwaysOnTop(true);
>>>>>>> Stashed changes
=======
//		stage.setAlwaysOnTop(true);
>>>>>>> Stashed changes
		stage.setScene(myscene.getMyscene());
		stage.show();

	}

}
