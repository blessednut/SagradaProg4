package Controller;

import View.CardPane;
import View.ToolCard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {

//		MySceneController myscene = new MySceneController();
//		GameController gameCon = new GameController();
//		stage.setTitle("Sagrada 2020");
////		stage.setAlwaysOnTop(true);
//		stage.setScene(myscene.getMyscene());
//		stage.show();
		ToolCard_Controller tc_Controller = new ToolCard_Controller("Driepuntstang");
		Private_Objective_Card_Controller private_oc_Controller = new Private_Objective_Card_Controller("private_geel", 0);
		Public_Objective_Card_Controller asddas = new Public_Objective_Card_Controller();
		stage.setTitle("CardPane");
		stage.setScene(new Scene(tc_Controller.getPanes().get(0)));
		stage.show();

	}

}
