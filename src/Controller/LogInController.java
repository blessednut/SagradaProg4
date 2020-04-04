package Controller;

import java.sql.SQLException;

import DataBase.DBCon;
import View.LoginPane;

public class LogInController {

	private DBCon dbcon = new DBCon();
	private LoginPane login;

	public LogInController() {
		login = new LoginPane();
		login.getLogin().setOnAction(e -> {
			dbcon.getUsername();
			System.out.println("kut");
		});
	}

	public LoginPane getLogin() {
		return login;
	}

}
