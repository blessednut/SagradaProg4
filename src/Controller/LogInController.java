package Controller;

import View.LoginPane;
import javafx.stage.Stage;


public class LogInController {

	private LoginPane login;
	private LoginModel loginModel;

	public LogInController() {
		login = new LoginPane();
		loginModel = new LoginModel();

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
			if (loginModel.getDbcon().getPassword(username).equals(password)) {
				Stage stage = new Stage();
				stage.show();
			} else {
				login.errorPassword();
			}
		} catch (Exception ex) {
			login.errorUsername();

		}

	}

}
