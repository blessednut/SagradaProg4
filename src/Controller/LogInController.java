package Controller;

import View.LoginPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jdk.jfr.StackTrace;
import model.LoginModel;

public class LogInController {

	private LoginPane v_login;
	private LoginModel m_login;
	private MySceneController c_myscene;
	private String username;
	private HomeController c_home;
	private String password;

	public LogInController(MySceneController c_myscene) {
		this.c_myscene = c_myscene;
		m_login = new LoginModel();
		v_login = new LoginPane();
//		c_home = new HomeController(c_myscene, this);

		v_login.getLogin().setOnAction(e -> {
			username = v_login.getUsername().getText();
			password = v_login.getPassword().getText();
			m_login.setUsername(username);
			makeHomecontroller();
			SetInlogInfo();
		});
		v_login.getRegister().setOnAction(
				e -> m_login.getCon().registerLogin(v_login.getUsername().getText(), v_login.getPassword().getText()));
		v_login.addEventHandler(KeyEvent.KEY_PRESSED, new MyEnterHandler());
	}

	private class MyEnterHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ENTER) {
				username = v_login.getUsername().getText();
				password = v_login.getPassword().getText();
				m_login.setUsername(username);
				makeHomecontroller();
				SetInlogInfo();

			}
		}

	}

	public LoginPane getLogin() {
		return v_login;
	}

	public String SetInlogInfo() {
		try {

			if (m_login.getCon().getPassword(username).equals(password)) {
				c_myscene.getMyscene().switchPane(c_home.getV_home());

			} else {
				v_login.errorPassword();
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
		}
		return username;

	}

	public void makeHomecontroller() {
		c_home = new HomeController(c_myscene, this);
	}

	public String getUsername() {
		return username;
	}

	public HomeController getC_home() {
		return c_home;
	}



}
