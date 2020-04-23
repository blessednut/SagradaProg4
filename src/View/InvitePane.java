package View;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class InvitePane extends HBox {

	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();

	private VBox vbox;
	private VBox vbox2;

	private Label titel1;
	private Label titel2;
	private TextArea Username;
	private TextField name;
	private Button search;
	private Button inviteButton;

//	private Text titel2;
	private Text name2;
	private Button accept;
	private Button ignore;
	private HBox hbox2;

	private ImageView labelBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	private ImageView labelNameBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));

	public InvitePane() {
		this.setMinSize((screenX - 200), screenY);
		this.setMaxSize((screenX - 200), screenY);
		this.setPrefSize((screenX - 200), screenY);
		createPane();
		showInvite("piet");
	}

	private void createPane() {
		labelBackground.setFitWidth(300);
		labelBackground.setFitHeight(100);
		titel1 = new Label("vrienden uitnodigen", labelBackground);
		titel1.setFont(new Font("Arial", 20));
		titel1.setStyle("-fx-font-weight: bold");
		titel1.setContentDisplay(ContentDisplay.CENTER);
//		titel1.setOpacity(0.4);

		name = new TextField();
		name.setText("Gebruikersnaam");
		name.setOnMouseClicked(e -> name.clear());
		name.setMinSize(300, 100);
		name.setPrefSize(300, 100);
		name.setMaxSize(300, 100);

		search = new Button();
		search.setText("zoek");
		search.setMinSize(150, 30);
		search.setPrefSize(150, 30);
		search.setMaxSize(150, 30);

		inviteButton = new Button();
		inviteButton.setText("uitnodigen");
		inviteButton.setMinSize(15, 30);
		inviteButton.setPrefSize(150, 30);
		inviteButton.setMaxSize(150, 30);
		
		HBox hbox = new HBox(search, inviteButton);
		vbox = new VBox(titel1, name, hbox);
		vbox2 = new VBox();
		this.getChildren().addAll(vbox, vbox2);
	}

	public void showInvite(String username) {
		hbox2 = new HBox();

		labelNameBackground.setFitWidth(300);
		labelNameBackground.setFitHeight(100);

		titel2 = new Label("openstaande uitnodigingen", labelNameBackground);
		titel2.setFont(new Font("Arial", 20));
		titel2.setStyle("-fx-font-weight: bold");
		titel2.setContentDisplay(ContentDisplay.CENTER);

		Username = new TextArea();
		Username.setMinSize(300, 100);
		Username.setPrefSize(300, 100);
		Username.setMaxSize(300, 100);
		Username.setText(username);

		accept = new Button();
		accept.setText("accepteer");
		accept.setMinSize(150, 30);
		accept.setPrefSize(150, 30);
		accept.setMaxSize(150, 30);

		ignore = new Button();
		ignore.setText("negeer");
		ignore.setMinSize(150, 30);
		ignore.setPrefSize(150, 30);
		ignore.setMaxSize(150, 30);

		hbox2.getChildren().addAll(accept, ignore);
		vbox2.getChildren().addAll(titel2, Username, hbox2);
	}

	public void showPane() {

	}

}
