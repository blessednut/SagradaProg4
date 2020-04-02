package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginPane extends BorderPane {
	
	// layout items
	private VBox layout;
	// textfield items
	private TextField username;
	private TextField password;
	//Button items
	private HBox buttonBox;
	private Button login;
	private Button register;

	public LoginPane() {

		this.setMinSize(300, 300);
		this.setPrefSize(300, 300);
		this.setMaxSize(300, 300);
		this.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		CreateLoginPane();

	}

	public void CreateLoginPane() {
		// layout items
		layout = new VBox();
		layout.setMinSize(300, 100);
		layout.setPrefSize(300, 100);
		layout.setMaxSize(300, 100);

		buttonBox = new HBox();
		buttonBox.setMinSize(300, 100);
		buttonBox.setPrefSize(300, 100);
		buttonBox.setMaxSize(300, 100);
		// textfield items
		username = new TextField();
		username.setText("Username");
		username.setMinSize(300, 100);
		username.setPrefSize(300, 100);
		username.setMaxSize(300, 100);

		password = new TextField();
		password.setText("Password");
		password.setMinSize(300, 100);
		password.setPrefSize(300, 100);
		password.setMaxSize(300, 100);

		//button items
		login = new Button("Log in");
		login.setMinSize(150, 100);
		login.setPrefSize(150, 100);
		login.setMaxSize(150, 100);

		register = new Button("Registreer");
		register.setMinSize(150, 100);
		register.setPrefSize(150, 100);
		register.setMaxSize(150, 100);
				
		//buttons toevoegen aan layout
		buttonBox.getChildren().addAll(login, register);
		
		layout.getChildren().addAll(username,password,buttonBox);

		this.getChildren().add(layout);

	}

	public TextField getUsername() {
		return username;
	}

	public TextField getPassword() {
		return password;
	}

	public Button getLogin() {
		return login;
	}
	
	

}
