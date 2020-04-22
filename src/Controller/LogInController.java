package Controller;

import View.GamePane;
import View.LoginPane;
import model.LoginModel;

public class LogInController {

	private LoginPane v_login;
	private LoginModel m_login;

	private MySceneController c_myscene;

	public LogInController(MySceneController c_myscene) {
		v_login = new LoginPane();
		m_login = new LoginModel();
		this.c_myscene = c_myscene;
		GamePane game = new GamePane();

		v_login.getLogin().setOnAction(e -> SetInlogInfo());
		v_login.getRegister().setOnAction(e -> m_login.getDbcon().registerLogin(v_login.getUsername().getText(),v_login.getPassword().getText()));
	}

	public LoginPane getLogin() {
		return v_login;
	}

	public void SetInlogInfo() {
		String username = v_login.getUsername().getText();
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

	}

}
