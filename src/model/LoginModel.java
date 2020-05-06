package model;

import java.util.HashMap;

import Controller.LogInController;
import DataBase.LoginDB;
import DataBase.InviteDB;

public class LoginModel {
	private LogInController c_login;

	private LoginDB con;
	private String username;
	private String password;

	// HashMap<username,password>
	private HashMap<String, String> loginUser;

	public LoginModel() {
		con = new LoginDB();
	}

	public void fillHashMap() {
		loginUser = new HashMap<String, String>();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public LoginDB getCon() {
		return this.con;
	}

	public LogInController getC_login() {
		return c_login;
	}
}
