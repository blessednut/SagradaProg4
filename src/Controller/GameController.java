package Controller;

import DataBase.DBCon;
import View.GamePane;

public class GameController {
	
	private MySceneController myscene;
	private GamePane v_game;
	private DBCon con;

	public GameController(MySceneController myscene) {
		this.v_game = new GamePane();
		this.myscene = myscene;
		con = new DBCon();
		v_game.getEndTurn().setOnAction(e -> con.getM_game().getTokenAmount());
		
	}
	
	public void setGamePane() {
		myscene.getMyscene().switchPane(v_game);
	}

	public LogInController getC_login() {
		return c_login;
	}

	public GameModel getM_game() {
		return m_game;
	}
	
	
	
	

}
