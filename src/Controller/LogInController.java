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
	private HomeController c_home;
	private String password;

	public LogInController(MySceneController c_myscene) {
		this.c_myscene = c_myscene;
		m_login = new LoginModel();
		v_login = new LoginPane();


		v_login.getLogin().setOnAction(e -> {
			username = v_login.getUsername().getText();
			password = v_login.getPassword().getText();
			m_login.setUsername(username);
			SetInlogInfo();
			System.out.println("LogINCOntroller 29: Test om te kijken of er iets 2 keer wordt aangemaakt");
			c_home = new HomeController(c_myscene, this);
		});
		v_login.getRegister().setOnAction(
				e -> m_login.getCon().registerLogin(v_login.getUsername().getText(), v_login.getPassword().getText()));
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

//		username = v_login.getUsername().getText();
//		String password = v_login.getPassword().getText();

//		m_login.setUsername(username);

		try {

			if (m_login.getCon().getPassword(username).equals(password)) {
				c_myscene.getMyscene().switchPane(c_home.getV_home());

			} else {
				v_login.errorPassword();
			}
		} catch (Exception ex) {
//			v_login.errorUsername();

		}
		return username;

	}

	public String getUsername() {
		return username;
	}
}
