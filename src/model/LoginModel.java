package model;

import java.util.HashMap;

import Controller.LogInController;
import DataBase.DBCon;

public class LoginModel {
	private LogInController c_login;
	
	private DBCon dbcon;
	private String username;
	private String password;

	//HashMap<username,password>
	private HashMap<String, String> loginUser;

	public LoginModel() {
		dbcon = new DBCon();
	}

	
	public void fillHashMap() {
		loginUser = new HashMap<String,String>();
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


	public DBCon getDbcon() {
		return dbcon;
	}


	public LogInController getC_login() {
		return c_login;
	}
	
	
	

}
