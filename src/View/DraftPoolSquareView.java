package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.GameDiceModel;

public class DraftPoolSquareView extends StackPane {
	private GameDiceModel testDice;
	
	//Square met een dice
	public DraftPoolSquareView (double width, double height, GameDiceModel dice) {
		this.setPrefSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		this.setOnMouseEntered(event -> fieldHover());
		this.setOnMouseExited(event -> fieldUnhover());
		
		//Test Kan weghalen
		//this.testDice = dice;
		
		
		dice.colorProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				//Test 2
				//System.out.println("Test 2");
				Color color = new ColorConverter().colorConverter((dice.colorProperty().getValue()));
				setDiceView(color, dice.valueProperty().getValue());
			}
			
		});
		
		dice.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				//Test 2
				//System.out.println("Test 1");
				Color color = new ColorConverter().colorConverter((dice.colorProperty().getValue()));
				setDiceView(color, dice.valueProperty().getValue());
			}
		});
		
		setDiceView(new ColorConverter().colorConverter((dice.colorProperty().getValue())), dice.valueProperty().getValue());
		
		//Test voor binding
//		dice.colorProperty().setValue("red");
//		dice.valueProperty().setValue(6);
	}
	
	//Square zonder een dice
	public DraftPoolSquareView (double width, double height) {
		this.setPrefSize(width, height);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}
	
	private void setDiceView (Color color, int eyes) {
		this.getChildren().clear();
		DiceView dieView = new DiceView(color, eyes);
		dieView.drawDice(80, 80);
		this.getChildren().add(dieView);
	}
	
	private void fieldHover() {
		DropShadow shadow1 = new DropShadow();
		shadow1.setColor(Color.GREEN);
		shadow1.setOffsetX(2);
		shadow1.setOffsetY(2);

		DropShadow shadow2 = new DropShadow();
		shadow2.setColor(Color.GREEN);
		shadow2.setOffsetX(-2);
		shadow2.setOffsetY(-2);

		shadow2.setInput(shadow1);
		this.setEffect(shadow2);
	}
	
	private void fieldUnhover() {
		this.setEffect(null);
	}
}
