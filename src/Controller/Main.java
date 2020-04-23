package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MySceneController myscene = new MySceneController();
		GameController gameCon = new GameController();
		stage.setTitle("Sagrada 2020");
//		stage.initStyle(StageStyle.UNDECORATED);
//		stage.setAlwaysOnTop(true);
		stage.setScene(myscene.getMyscene());
		stage.show();
		myscene.getMyscene().addEventHandler(KeyEvent.KEY_PRESSED, new MyKeyHandler());
	}

	private class MyKeyHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		}

	}

}
