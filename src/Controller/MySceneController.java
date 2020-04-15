package Controller;


import View.HomePane;
import View.MyScene;

public class MySceneController {
	
<<<<<<< Updated upstream
	private MyScene myscene;
	private GameController game;
	private LogInController loginCon = new LogInController();
	
	
	public MySceneController() {
		myscene = new MyScene(this);
=======
	private MyScene v_scene;
	private GameController c_game;
	private LogInController c_login = new LogInController(this);
	private MenuBarController c_menubar;
	private HomePane v_home;
	
	private ArrayList v_list = new ArrayList();
	
	
	public MySceneController() {
		v_scene = new MyScene(this);
		c_menubar = new MenuBarController();
		v_home = new HomePane();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	}

	public MyScene getMyscene() {
		return myscene;
	}
	


	public LogInController getLoginCon() {
		return loginCon;
	}
	
<<<<<<< Updated upstream
=======
	public void addToViewList() {
		// add panes to v_list to make switching panes easier.
	}

	public HomePane getV_home() {
		return v_home;
	}
	
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	
	
	
	

}
