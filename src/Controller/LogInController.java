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
	private String username;
	private HomeThreadController c_hometc;

	private String username;

	public LogInController(MySceneController c_myscene) {
		v_login = new LoginPane();
		m_login = new LoginModel();
		this.c_myscene = c_myscene;
		v_login.getLogin().setOnAction(e -> SetInlogInfo());
		v_login.getRegister().setOnAction(e -> m_login.getDbcon().registerLogin(v_login.getUsername().getText(),
				v_login.getPassword().getText()));

		v_login.getLogin().setOnAction(e -> SetInlogInfo());
		v_login.getRegister().setOnAction(e -> m_login.getDbcon().registerLogin(v_login.getUsername().getText(),
				v_login.getPassword().getText()));

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

	public String getUsername() {
		return username;
	}
	
	

}
