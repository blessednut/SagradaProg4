package Controller;

import View.LoginPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.LoginModel;

public class LogInController {

	private LoginPane v_login;
	private LoginModel m_login;
	private MySceneController c_myscene;
<<<<<<< HEAD
<<<<<<< HEAD
	private String username;
	private HomeThreadController c_hometc;

	private String username;
=======
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread
=======
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread

	public LogInController(MySceneController c_myscene) {
		v_login = new LoginPane();
		m_login = new LoginModel();
		this.c_myscene = c_myscene;
<<<<<<< HEAD
<<<<<<< HEAD
		v_login.getLogin().setOnAction(e -> SetInlogInfo());
		v_login.getRegister().setOnAction(e -> m_login.getDbcon().registerLogin(v_login.getUsername().getText(),
				v_login.getPassword().getText()));
=======
		GamePane game = new GamePane();
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread

		v_login.getLogin().setOnAction(e -> SetInlogInfo());
		v_login.getRegister().setOnAction(e -> m_login.getDbcon().registerLogin(v_login.getUsername().getText(),
				v_login.getPassword().getText()));
<<<<<<< HEAD

=======
		GamePane game = new GamePane();

		v_login.getLogin().setOnAction(e -> SetInlogInfo());
		v_login.getRegister().setOnAction(e -> m_login.getDbcon().registerLogin(v_login.getUsername().getText(),
				v_login.getPassword().getText()));
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread
=======
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread
		v_login.addEventHandler(KeyEvent.KEY_PRESSED, new MyEnterHandler());
	}

	private class MyEnterHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ENTER) {
				SetInlogInfo();
			}
		}

	}

	public LoginPane getLogin() {
		return v_login;
	}

	public String SetInlogInfo() {
		username = v_login.getUsername().getText();
		String password = v_login.getPassword().getText();

		m_login.setUsername(username);

		try {

			if (m_login.getDbcon().getPassword(username).equals(password)) {
				c_myscene.getMyscene().switchPane(c_myscene.getC_home().getV_home());

			} else {
				v_login.errorPassword();
			}
		} catch (Exception ex) {
			v_login.errorUsername();

		}
		return username;

	}
<<<<<<< HEAD
<<<<<<< HEAD

	public String getUsername() {
		return username;
	}
	
	
=======
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread
=======
>>>>>>> parent of 5e7dfa1... fixed the exception for the invite thread

}
