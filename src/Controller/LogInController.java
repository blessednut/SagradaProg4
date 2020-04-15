package Controller;

import View.LoginPane;
import javafx.stage.Stage;
import model.LoginModel;



public class LogInController {

	private LoginPane v_login;
	private LoginModel m_login;
	private MySceneController c_myscene;

	public LogInController(MySceneController c_myscene) {
		v_login = new LoginPane();
		m_login = new LoginModel();
		this.c_myscene = c_myscene;


		v_login.getLogin().setOnAction(e -> SetInlogInfo());
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
				Stage stage = new Stage();
				stage.show();

			} else {
				v_login.errorPassword();
			}
		} catch (Exception ex) {
			v_login.errorUsername();

		}

	}

}
