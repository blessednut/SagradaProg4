package View;

import Controller.StatisticController;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class StatisticsPane extends HBox {

	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;

	private VBox vbox;
	private VBox vbox2;
	private VBox vbox3;
	
	private VBox rankedList;

	private TextField naam;
	private Button zoek;

	private ImageView labelBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	private ImageView labelStatsBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));

	private Label titel1;
	private Label titel2;

	private Label playerNameLabel;
	private Label winsLabel;
	private Label lossesLabel;
	private Label highestScoreLabel;
	private Label mostPlacedColorLabel;
	private Label mostPlacedValue;
	private Label numOpponents;

	private StatisticController controller;

	public StatisticsPane(StatisticController controller) {
		this.controller = controller;
		this.rankedList = new VBox();
		
		this.setMinSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setMaxSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setPrefSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		createPane();
		naam.setOnMouseClicked(e -> naam.clear());
	}
	
	private void getRank (boolean orderASC) {
		rankedList.getChildren().clear();
		if(orderASC == false) {
			for(int i = 0; i < controller.getNames().size(); i++) {
				if(controller.searchNamesWithWins().contains(controller.getNames().get(i))) {
					Label text = new Label();
					int index = controller.searchNamesWithWins().indexOf(controller.getNames().get(i));
					text.setText(controller.searchNamesWithWins().get(index) + " - " + controller.searchAmountOfWins().get(index));
					rankedList.getChildren().add(text);
				}
			}
			for(int i = 0; i < controller.getNames().size(); i++) {
				if(!controller.searchNamesWithWins().contains(controller.getNames().get(i))) {
					Label text = new Label();
					text.setText(controller.getNames().get(i) + " - 0");
					rankedList.getChildren().add(text);
				}
			}
		}
		else {
			for(int i = controller.getNames().size() - 1; i > -1 ; i--) {
				if(!controller.searchNamesWithWins().contains(controller.getNames().get(i))) {
					Label text = new Label();
					text.setText(controller.getNames().get(i) + " - 0");
					rankedList.getChildren().add(text);
				}
			}
			for(int i = controller.getNames().size() - 1; i > -1 ; i--) {
				if(controller.searchNamesWithWins().contains(controller.getNames().get(i))) {
					Label text = new Label();
					int index = controller.searchNamesWithWins().indexOf(controller.getNames().get(i));
					text.setText(controller.searchNamesWithWins().get(index) + " - " + controller.searchAmountOfWins().get(index));
					rankedList.getChildren().add(text);
				}
			}
			
		}

		

		
		rankedList.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}
	

	private void createPane() {
		getRank(false);
		// vbox
		labelBackground.setFitWidth(150);
		labelBackground.setFitHeight(100);
		titel1 = new Label("ranglijst", labelBackground);
		titel1.setFont(new Font("Arial", 20));
		titel1.setStyle("-fx-font-weight: bold");
		titel1.setContentDisplay(ContentDisplay.CENTER);

		Button OrderRank = new Button();
		OrderRank.setText("ASC");
		OrderRank.setMinSize(150, 70);
		OrderRank.setPrefSize(150, 70);
		OrderRank.setMaxSize(150, 70);
		OrderRank.setOnAction(e -> {
			if (OrderRank.getText().equals("ASC")) {
				OrderRank.setText("DESC");
				this.getRank(true);
			} else {
				OrderRank.setText("ASC");
				this.getRank(false);
			}
		});

		labelStatsBackground.setFitWidth(150);
		labelStatsBackground.setFitHeight(100);

		// vbox 2
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
		zoek.setOnAction(e -> {
			this.controller.search(naam.getText());
		});

		// vbox3
		playerNameLabel = new Label("Gebruikersnaam:", labelStatsBackground);
		playerNameLabel.setFont(new Font("Arial", 20));
		playerNameLabel.setStyle("-fx-font-weight: bold");
		playerNameLabel.setContentDisplay(ContentDisplay.CENTER);

		winsLabel = new Label("Gewonnen:", labelStatsBackground);
		winsLabel.setFont(new Font("Arial", 20));
		winsLabel.setStyle("-fx-font-weight: bold");
		winsLabel.setContentDisplay(ContentDisplay.CENTER);

		lossesLabel = new Label("Verloren:", labelStatsBackground);
		lossesLabel.setFont(new Font("Arial", 20));
		lossesLabel.setStyle("-fx-font-weight: bold");
		lossesLabel.setContentDisplay(ContentDisplay.CENTER);

		highestScoreLabel = new Label("Hoogste behaalde score:", labelStatsBackground);
		highestScoreLabel.setFont(new Font("Arial", 20));
		highestScoreLabel.setStyle("-fx-font-weight: bold");
		highestScoreLabel.setContentDisplay(ContentDisplay.CENTER);

		mostPlacedColorLabel = new Label("Meest geplaatste kleur:", labelStatsBackground);
		mostPlacedColorLabel.setFont(new Font("Arial", 20));
		mostPlacedColorLabel.setStyle("-fx-font-weight: bold");
		mostPlacedColorLabel.setContentDisplay(ContentDisplay.CENTER);

		mostPlacedValue = new Label("Meest geplaatste waarde:", labelStatsBackground);
		mostPlacedValue.setFont(new Font("Arial", 20));
		mostPlacedValue.setStyle("-fx-font-weight: bold");
		mostPlacedValue.setContentDisplay(ContentDisplay.CENTER);

		numOpponents = new Label("Aantal tegenstanders:", labelStatsBackground);
		numOpponents.setFont(new Font("Arial", 20));
		numOpponents.setStyle("-fx-font-weight: bold");
		numOpponents.setContentDisplay(ContentDisplay.CENTER);

		vbox = new VBox(titel1, rankedList, OrderRank);
		vbox2 = new VBox(titel2, naam, zoek);
		vbox3 = new VBox(this.playerNameLabel, this.winsLabel, this.lossesLabel, this.highestScoreLabel,
				this.mostPlacedColorLabel, this.mostPlacedValue, this.numOpponents);
		vbox3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		this.getChildren().addAll(vbox, vbox2, vbox3);
	}

	public void update() {
		this.playerNameLabel.setText("Gebruikersnaam: " + controller.getUsername());
		this.winsLabel.setText("Gewonnen: " + controller.getWins());
		this.lossesLabel.setText("Verloren: " + controller.getLosses());
		this.highestScoreLabel.setText("Hoogste behaalde score: " + controller.getHighestScore());
		this.numOpponents.setText("Aantal tegenstanders: " + controller.getNumOpponents());
		this.mostPlacedColorLabel.setText("Meest geplaatste kleur: " + controller.getMostPlacedColor());
		this.mostPlacedValue.setText("Meest geplaatste waarde:" + controller.getMostPlacedValue());
	}
	
	public void makebox() {
		for(int i = 0 ; i < controller.getNames().size(); i++) {
		}
	}
}
