package View;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class StatisticsPane extends HBox{

	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();
	
	private VBox vbox;
	private VBox vbox2;
	
	private Text titel1;
	
	private Text titel2;
	private TextField naam;
	private Button zoek;
	
	public StatisticsPane() {
		this.setMinSize((screenX/8*7), screenY);
		this.setMaxSize((screenX/8*7), screenY); 
		this.setPrefSize((screenX/8*7), screenY);
		createPane();
	}
	
	private void createPane() {
		titel1 = new Text("ranglijst");
		
		titel2 = new Text("statestieken");
		
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
		
		vbox = new VBox(titel1);
		vbox2 = new VBox(titel2, naam, zoek);
		this.getChildren().addAll(vbox, vbox2);
	}
	
	public void showPane() {
		
	}
	
}
