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
import javafx.stage.Screen;

public class StatisticsPane extends HBox{

	private double screenX = Screen.getPrimary().getVisualBounds().getWidth();
	private double screenY = Screen.getPrimary().getVisualBounds().getHeight();
	
	private VBox vbox;
	private VBox vbox2;
		
	private TextField naam;
	private Button zoek;
	
	private ImageView labelBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	private ImageView labelStatsBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	
	private Label titel1;
	private Label titel2;
	private TextArea rangText;
	
	public StatisticsPane() {
		this.setMinSize((screenX/8*7), screenY);
		this.setMaxSize((screenX/8*7), screenY); 
		this.setPrefSize((screenX/8*7), screenY);
		createPane();
		naam.setOnMouseClicked(e -> naam.clear());
	}
	
	private void createPane() {	
		labelBackground.setFitWidth(150);
		labelBackground.setFitHeight(100);
		titel1 = new Label("ranglijst", labelBackground);
		titel1.setFont(new Font("Arial", 20));
		titel1.setStyle("-fx-font-weight: bold");
		titel1.setContentDisplay(ContentDisplay.CENTER);
	
		rangText = new TextArea();
		rangText.setMinSize(150, 70);
		rangText.setPrefSize(150, 70);
		rangText.setMaxSize(150, 70);
		
		labelStatsBackground.setFitWidth(150);
		labelStatsBackground.setFitHeight(100);

		titel2 = new Label("statestieken", labelStatsBackground);
		titel2.setFont(new Font("Arial", 20));
		titel2.setStyle("-fx-font-weight: bold");
		titel2.setContentDisplay(ContentDisplay.CENTER);
		
		naam = new TextField();
		naam.setText("Gebruikersnaam");
		naam.setMinSize(150, 70);
		naam.setPrefSize(150, 70);
		naam.setMaxSize(150, 70);
		
		zoek = new Button();
		zoek.setText("zoek");
		zoek.setMinSize(150, 70);
		zoek.setPrefSize(150, 70);
		zoek.setMaxSize(150, 70);
		
		vbox = new VBox(titel1, rangText);
		vbox2 = new VBox(titel2, naam, zoek);
		this.getChildren().addAll(vbox, vbox2);
	}
	
	public void showPane() {
		
	}
	
}
