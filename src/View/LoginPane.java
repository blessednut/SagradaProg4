package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class LoginPane extends BorderPane {

	private static final int WHIDTH = 300;
	private static final int HEIGHT = 100;
	
	// layout items
	private VBox layout;
	// textfield items
	private TextField username;
	private TextField password;
	// Button items
	private HBox buttonBox;
	private Button login;
	private Button register;

	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;

	public LoginPane() {
		this.setMinSize(SAGRADAWIDTH, SAGRADAHEIGHT);
		this.setPrefSize(SAGRADAWIDTH, SAGRADAHEIGHT);
		this.setMaxSize(SAGRADAWIDTH, SAGRADAHEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		CreateLoginPane();

	}

	public void CreateLoginPane() {
		// layout items
		layout = new VBox();
		layout.setMinSize(WHIDTH, HEIGHT);
		layout.setPrefSize(WHIDTH, HEIGHT);
		layout.setMaxSize(WHIDTH, HEIGHT);
		this.setCenter(layout);

		buttonBox = new HBox();
		buttonBox.setMinSize(WHIDTH, HEIGHT);
		buttonBox.setPrefSize(WHIDTH, HEIGHT);
		buttonBox.setMaxSize(WHIDTH, HEIGHT);

		// textfield items
		username = new TextField();
		username.setText("Username");
		username.setMinSize(WHIDTH, HEIGHT);
		username.setPrefSize(WHIDTH, HEIGHT);
		username.setMaxSize(WHIDTH, HEIGHT);
		username.setOnMouseClicked(e -> username.clear());

		password = new TextField();
		password.setText("Password");
		password.setMinSize(WHIDTH, HEIGHT);
		password.setPrefSize(WHIDTH, HEIGHT);
		password.setMaxSize(WHIDTH, HEIGHT);
		password.setOnMouseClicked(e -> password.clear());

		// button items
		login = new Button("Log in");
		login.setMinSize(WHIDTH/2, HEIGHT);
		login.setPrefSize(WHIDTH/2, HEIGHT);
		login.setMaxSize(WHIDTH/2, HEIGHT);

		register = new Button("Registreer");
		register.setMinSize(WHIDTH/2, HEIGHT);
		register.setPrefSize(WHIDTH/2, HEIGHT);
		register.setMaxSize(WHIDTH/2, HEIGHT);

		// buttons toevoegen aan layout
		buttonBox.getChildren().addAll(login, register);
		layout.getChildren().addAll(username, password, buttonBox);
	}

	public void errorUsername() {
		username.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
	}

	public void errorPassword() {
		password.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
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

	public Button getRegister() {
		return register;
	}

}
