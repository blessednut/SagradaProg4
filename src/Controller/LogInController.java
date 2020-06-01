package Controller;

import View.LoginPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.LoginModel;

public class LogInController {

	private LoginPane logInController;
	private LoginModel logInModel;
	private MySceneController mySceneController;
	private String username;
	private HomeController homeController;
	private String password;

	public LogInController(MySceneController mySceneController) {
		this.mySceneController = mySceneController;
		logInModel = new LoginModel();
		logInController = new LoginPane();
//		c_home = new HomeController(c_myscene, this);

		logInController.getLogin().setOnAction(e -> {
			username = logInController.getUsername().getText();
			password = logInController.getPassword().getText();
			logInModel.setUsername(username);
			makeHomecontroller();
			SetInlogInfo();
		});
		logInController.getRegister().setOnAction(
				e -> logInModel.getCon().registerLogin(logInController.getUsername().getText(), logInController.getPassword().getText()));
		logInController.addEventHandler(KeyEvent.KEY_PRESSED, new MyEnterHandler());
	}

	private class MyEnterHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ENTER) {
				username = logInController.getUsername().getText();
				password = logInController.getPassword().getText();
				logInModel.setUsername(username);
				makeHomecontroller();
				SetInlogInfo();

			}
		}

	}

	public LoginPane getLogin() {
		return logInController;
	}

	public String SetInlogInfo() {
		try {

			if (logInModel.getCon().getPassword(username).equals(password)) {
				mySceneController.getMyscene().switchPane(homeController.getV_home());

			} else {
				logInController.errorPassword();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return username;

	}

	public void makeHomecontroller() {
		homeController = new HomeController(mySceneController, this);
	}

	public String getUsername() {
		return username;
	}

	public HomeController getC_home() {
		return homeController;
	}



}
