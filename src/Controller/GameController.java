package Controller;

import DataBase.DBCon;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameController extends Application{
	private MySceneController myscene;
	private DBCon con = new DBCon();
	
	public GameController() {
		con.InsertRegister("Jeroen", "Hendrikus");
		
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
