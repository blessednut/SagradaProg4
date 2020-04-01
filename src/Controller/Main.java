package Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("SAGRADA");
		Scene scene = new Scene(new Pane());
		scene.setFill(Color.AQUAMARINE);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();

	}

}
