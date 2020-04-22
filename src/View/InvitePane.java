package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class InvitePane extends HBox{
	
	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();
	
	private VBox vbox;
	private VBox vbox2;
	
	private Text titel1;
	private TextField name;
	private Button search;
	private Button inviteButton;
	
	private Text titel2;
	private Text name2;
	private Button accept;
	private Button ignore;
	private HBox hbox2;
	
	
	public InvitePane() {
		this.setMinSize((screenX/8*7), screenY);
		this.setMaxSize((screenX/8*7), screenY); 
		this.setPrefSize((screenX/8*7), screenY);
		createPane();
		showInvite("piet");
	}
	
	private void createPane() {
		titel1 = new Text("vrienden uitnodigen");
		
		name = new TextField();
		name.setText("Gebruikersnaam");
		name.setMinSize(300, 100);
		name.setPrefSize(300, 100);
		name.setMaxSize(300, 100);
		
		search = new Button();
		search.setText("zoek");
		search.setMinSize(300, 100);
		search.setPrefSize(300, 100);
		search.setMaxSize(300, 100);
		
		inviteButton = new Button();
		inviteButton.setText("uitnodigen");
		inviteButton.setMinSize(300, 100);
		inviteButton.setPrefSize(300, 100);
		inviteButton.setMaxSize(300, 100);
		
		titel2 = new Text("openstaande uitnodigingen");
		
		vbox = new VBox(titel1, name, search, inviteButton);
		vbox2 = new VBox(titel2);
		this.getChildren().addAll(vbox, vbox2);
	}
	
	public void showInvite(String username) {
		hbox2 = new HBox();
		
		name2 = new Text();
		name2.setText(username);
		
		accept = new Button();
		accept.setText("accepteer");
		accept.setMinSize(100, 100);
		accept.setPrefSize(100, 100);
		accept.setMaxSize(100, 100);
		
		ignore= new Button();
		ignore.setText("negeer");
		ignore.setMinSize(100, 100);
		ignore.setPrefSize(100, 100);
		ignore.setMaxSize(100, 100);
		
		hbox2.getChildren().addAll(name2, accept, ignore);
		vbox2.getChildren().add(hbox2);
	}
	
	public void showPane(){
		
	}
	
}
