package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class InvitePane extends Pane{
	
	private HBox hbox;
	private VBox vbox;
	private VBox vbox2;
	
	private Text titel1;
	private TextField naam;
	private Button zoek;
	private Button uitnodigen;
	
	private Text titel2;
	private Text naam2;
	private Button accepteer;
	private Button negeer;
	private HBox hbox2;
	
	
	public InvitePane() {
		createPane();
		showInvite("piet");
	}
	
	private void createPane() {
		
		
		titel1 = new Text("vrienden uitnodigen");
		
		naam = new TextField();
		naam.setText("Gebruikersnaam");
		naam.setMinSize(300, 100);
		naam.setPrefSize(300, 100);
		naam.setMaxSize(300, 100);
		
		zoek = new Button();
		zoek.setText("zoek");
		zoek.setMinSize(300, 100);
		zoek.setPrefSize(300, 100);
		zoek.setMaxSize(300, 100);
		
		uitnodigen = new Button();
		uitnodigen.setText("uitnodigen");
		uitnodigen.setMinSize(300, 100);
		uitnodigen.setPrefSize(300, 100);
		uitnodigen.setMaxSize(300, 100);
		
		titel2 = new Text("openstaande uitnodigingen");
		
		vbox = new VBox(titel1, naam, zoek, uitnodigen);
		vbox2 = new VBox(titel2);
		hbox = new HBox(vbox, vbox2);
		this.getChildren().add(hbox);
	}
	
	public void showInvite(String username) {
		hbox2 = new HBox();
		
		naam2 = new Text();
		naam2.setText(username);
		
		accepteer = new Button();
		accepteer.setText("accepteer");
		accepteer.setMinSize(100, 100);
		accepteer.setPrefSize(100, 100);
		accepteer.setMaxSize(100, 100);
		
		negeer= new Button();
		negeer.setText("negeer");
		negeer.setMinSize(100, 100);
		negeer.setPrefSize(100, 100);
		negeer.setMaxSize(100, 100);
		
		hbox2.getChildren().addAll(naam2, accepteer, negeer);
		vbox2.getChildren().add(hbox2);
	}
	
	public void showPane(){
		
	}
	
}
