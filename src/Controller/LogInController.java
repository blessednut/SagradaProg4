package Controller;

import View.LoginPane;
import javafx.stage.Stage;
import model.LoginModel;



public class LogInController {

<<<<<<< Updated upstream
	private LoginPane login;
	private LoginModel loginModel;

	public LogInController() {
		login = new LoginPane();
		loginModel = new LoginModel();
=======
	private LoginPane v_login;
	private LoginModel m_login;
	private MySceneController c_myscene;

	public LogInController(MySceneController c_myscene) {
		v_login = new LoginPane();
		m_login = new LoginModel();
		this.c_myscene = c_myscene;
>>>>>>> Stashed changes

		login.getLogin().setOnAction(e -> SetInlogInfo());
	}

	public LoginPane getLogin() {
		return login;
	}

	public void SetInlogInfo() {
		String username = login.getUsername().getText();
		String password = login.getPassword().getText();

		loginModel.setUsername(username);

		try {
<<<<<<< Updated upstream
			if (loginModel.getDbcon().getPassword(username).equals(password)) {
				Stage stage = new Stage();
				stage.show();
=======
			if (m_login.getDbcon().getPassword(username).equals(password)) {
				c_myscene.getMyscene().switchPane(c_myscene.getV_home());
>>>>>>> Stashed changes
			} else {
				login.errorPassword();
			}
		} catch (Exception ex) {
			login.errorUsername();

		}

	}

}
