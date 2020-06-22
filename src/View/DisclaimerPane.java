package View;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DisclaimerPane extends HBox {

	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;
	private Label label;
	private Font disclaimerFont;

	public DisclaimerPane() {
		this.setMinSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setMaxSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setPrefSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		createPane();
	}

	private void createPane() {
		label = new Label();
		label.setText("// chat:\r\n" + 
				"* geen speciale tekens gebruiken in algemeen.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"// gameplay:\r\n" + 
				"* gereedschapskaart 5, 8 en 9 werken niet naar behoren.\r\n" + 
				"* er bestaat een kleine kans dat het aanbod niet zichtbaar is.\r\n" + 
				"	- workaround: pas tot een nieuwe ronde dit zorgt ervoor dat een nieuw aanbod wordt aangemaakt en getoond.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"// gebruik van gereedschapkaarten.\r\n" + 
				
				"* bij gereedschapkaarten die gebruik maken van het aanbod.\r\n" + 
				"	- klik eerst op de gewenste dobbelsteen en vervolgens op de gereedschapskaart.\r\n" + 
				"* bij gereedschapkaarten die dobbelstenen herplaatsen op de patroonkaart.\r\n" + 
				"	- klik eerst op de gereedschapkaart, vervolgens de dobbelsteen en tenslotte de positie waar de dobbelsteen heen moet.\r\n" + 
				"* word het niet in bovenstaande volgorde uitgevoerd maar er is wel op de gereedschapkaart geklikt,\r\n" + "dan is je mogelijkheid om een kaart te gebruiken verkeken " +
				"\r\n" + 
				"// puntentelling:\r\n" + 
				"* bij gebruik van de publieke doelkaart, kleurdiagonaal, wijkt de puntentelling af.\r\n" +
				"// invite:\r\n" + 
				"* Lijst met spelers wordt niet meer gerefresht na het inloggen.");
		disclaimerFont = new Font("Arial", 18);
		label.setFont(disclaimerFont);
		label.setStyle("-fx-font-wight: bold");
		label.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		this.getChildren().add(label);
	}

}
