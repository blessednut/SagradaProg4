package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class InvitePane extends HBox {

	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();

	private VBox vbox;
	private VBox vbox2;
	private VBox vbox3;
	private VBox vbox4;

	private Label titel3;
	private Label titel4;
	private TextField gameId;

	private Label titel1;
	private Label titel2;
	private TextField Username;
	private TextField name1;
	private TextField name2;
	private TextField name3;
	private Button search;
	private Button inviteButton;

	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private ToggleGroup buttons;

	private Button accept;
	private Button refuse;
	private HBox hbox2;

	private ImageView labelBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	private ImageView labelNameBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	private ImageView labelGameIDBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	
	private ComboBox<String> invites;

	public InvitePane() {
		this.setMinSize((screenX / 8 * 7), screenY);
		this.setMaxSize((screenX / 8 * 7), screenY);
		this.setPrefSize((screenX / 8 * 7), screenY);
		createPane();
		showInvite();
		rb2.setOnAction(e -> {
			name1.setVisible(true);
			name2.setVisible(false);
			name3.setVisible(false);
		});
		rb3.setOnAction(e -> {
			name1.setVisible(true);
			name2.setVisible(true);
			name3.setVisible(false);
		});
		rb4.setOnAction(e -> {
			name1.setVisible(true);
			name2.setVisible(true);
			name3.setVisible(true);
		});
	}

	private void createPane() {
		// title
		labelBackground.setFitWidth(300);
		labelBackground.setFitHeight(100);
		titel1 = new Label("vrienden uitnodigen", labelBackground);
		titel1.setFont(new Font("Arial", 20));
		titel1.setStyle("-fx-font-weight: bold");
		titel1.setContentDisplay(ContentDisplay.CENTER);

		buttons = new ToggleGroup();
		rb2 = new RadioButton("twee");
		rb2.setTextFill(Color.MAROON);
		rb2.setToggleGroup(buttons);
		rb2.setSelected(true);
		rb2.setUserData("twee");

		rb3 = new RadioButton("drie");
		rb3.setTextFill(Color.MAROON);
		rb3.setToggleGroup(buttons);
		rb3.setUserData("drie");

		rb4 = new RadioButton("vier");
		rb4.setTextFill(Color.MAROON);
		rb4.setToggleGroup(buttons);
		rb4.setUserData("vier");

		name1 = new TextField();
		name1.setText("Gebruikersnaam 1");
		name1.setOnMouseClicked(e -> name1.clear());
		name1.setVisible(true);
		name1.setMinSize(300, 100);
		name1.setPrefSize(300, 100);
		name1.setMaxSize(300, 100);

		name2 = new TextField();
		name2.setText("Gebruikersnaam 2");
		name2.setOnMouseClicked(e -> name2.clear());
		name2.setVisible(false);
		name2.setMinSize(300, 100);
		name2.setPrefSize(300, 100);
		name2.setMaxSize(300, 100);

		name3 = new TextField();
		name3.setText("Gebruikersnaam 3");
		name3.setOnMouseClicked(e -> name3.clear());
		name3.setVisible(false);
		name3.setMinSize(300, 100);
		name3.setPrefSize(300, 100);
		name3.setMaxSize(300, 100);

		search = new Button();
		search.setText("zoek");
		search.setMinSize(150, 30);
		search.setPrefSize(150, 30);
		search.setMaxSize(150, 30);

		inviteButton = new Button();
		inviteButton.setText("uitnodigen");
		inviteButton.setVisible(false);
		inviteButton.setMinSize(15, 30);
		inviteButton.setPrefSize(150, 30);
		inviteButton.setMaxSize(150, 30);

		HBox hbox = new HBox(search, inviteButton);
		vbox3 = new VBox(rb2, rb3, rb4);
		vbox3.setAlignment(Pos.CENTER);
		vbox = new VBox(titel1, vbox3, name1, name2, name3, hbox);

		vbox2 = new VBox();
		this.getChildren().addAll(vbox, vbox2);
	}

	public void showInvite() {
		hbox2 = new HBox();
		vbox4 = new VBox();
		labelNameBackground.setFitWidth(300);
		labelNameBackground.setFitHeight(100);

		titel2 = new Label("openstaande uitnodigingen", labelNameBackground);
		titel2.setFont(new Font("Arial", 20));
		titel2.setStyle("-fx-font-weight: bold");
		titel2.setContentDisplay(ContentDisplay.CENTER);

//		Username = new TextField();
//		Username.setMinSize(300, 50);
//		Username.setPrefSize(300, 50);
//		Username.setMaxSize(300, 50);
//
//
//		gameId = new TextField();
//		gameId.setMinSize(300, 50);
//		gameId.setPrefSize(300, 50);
//		gameId.setMaxSize(300, 50);
		invites = new ComboBox<>();
		invites.setMinSize(300, 50);
		invites.setPrefSize(300, 50);
		invites.setMaxSize(300, 50);
		invites.setPromptText("Welke uitnodiging zou je willen accepteren/wijgeren");
		
		

		accept = new Button();
		accept.setText("accepteer");
		accept.setMinSize(150, 30);
		accept.setPrefSize(150, 30);
		accept.setMaxSize(150, 30);

		refuse = new Button();
		refuse.setText("afwijzen");
		refuse.setMinSize(150, 30);
		refuse.setPrefSize(150, 30);
		refuse.setMaxSize(150, 30);

		hbox2.getChildren().addAll(accept, refuse);
		vbox4.getChildren().addAll(invites);
		vbox2.getChildren().addAll(titel2, vbox4, hbox2);
	}

	public Button getSearch() {
		return search;
	}

	public ComboBox<String> getInvites() {
		return invites;
	}

	public Button getInviteButton() {
		return inviteButton;
	}

	public Button getAccept() {
		return accept;
	}

	public Button getRefuse() {
		return refuse;
	}

	public void invites(ComboBox<String> invites) {
		this.invites = invites;
	}

	public TextField getName1() {
		return name1;
	}

	public TextField getName2() {
		return name2;
	}

	public TextField getName3() {
		return name3;
	}

	public ToggleGroup getButtons() {
		return buttons;
	}

	public TextField getGameId() {
		return gameId;
	}

}
