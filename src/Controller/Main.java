package Controller;

import DataBase.DBCon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private MySceneController myscene;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		myscene = new MySceneController();
		stage.setTitle("Sagrada 2020");
		stage.setResizable(false);

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

	@Override
	public void stop(){
	    //System.out.println("Stage is closing");
	    DBCon.getInstance().closeConnection();
	    Platform.exit();
	    System.exit(0);
	}
}
