package Controller;

import DataBase.DBCon;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameController {
	
	private MySceneController myscene;
	private DBCon con;

	public GameController() {
		con = new DBCon();
	}

	public MySceneController getMyscene() {
		return myscene;
	}

}
