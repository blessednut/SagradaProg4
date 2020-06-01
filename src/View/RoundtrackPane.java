package View;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RoundtrackPane extends BorderPane{

    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;
    private final static CornerRadii RADIUS = new CornerRadii(10.00);
    private ArrayList<VBox> rounds;

    public RoundtrackPane() {
        createRoundtrack();
    }

    private void createRoundtrack() {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, RADIUS, null)));
        HBox numbers = new HBox();
        HBox dice = new HBox();
        rounds = new ArrayList<>();
        for(int i=0; i<10; i++) {
            rounds.add(new VBox());
            dice.getChildren().add(rounds.get(i));
        }
        this.setTop(numbers);
        this.setBottom(dice);
        for(int i = 1; i<11; i++) {
            String letter = String.valueOf(i);
            Label label = new Label(letter);
            label.setMaxSize(WIDTH, HEIGHT);
            label.setMinSize(WIDTH, HEIGHT);
            label.setPrefSize(WIDTH, HEIGHT);
            label.setFont(new Font("Arial", 18));
            label.setTextFill(Color.WHITE);
            numbers.getChildren().addAll(label);
        }
    }

    public void addDice(int roundtrack, Color color, int eyes) { 
        DiceView dv = new DiceView(color, eyes);
        dv.drawDice(WIDTH, HEIGHT);
        //System.out.println("ROUNDTRACK = " + (roundtrack - 1));
        rounds.get(roundtrack-1).getChildren().add(dv);
    }
    
    public void clearRoundtrack () {
    	for (VBox vbox : rounds) {
    		 vbox.getChildren().clear();
    	}
    }
}