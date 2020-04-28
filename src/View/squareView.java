package View;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class squareView extends Pane {
	private IntegerProperty value;
	private DiceView dice;
	
	public squareView(StringProperty colorProperty, IntegerProperty valueProperty, DiceView dice) {
		this.value = valueProperty;
		this.dice = dice;
		
		ObjectProperty<Background> background = this.backgroundProperty();
		background.bind(Bindings.createObjectBinding(() -> getBackground(colorProperty), colorProperty));
	}

	public void setSize(double width, double height) {
		this.setMinSize(width, height);
		
		if (dice == null) {
			this.getChildren().add(new DicePattern(value, width, height));
		} else {
			this.getChildren().add(dice);
			dice.drawDice(width, height);
		}
		
	}

	private Background getBackground(StringProperty colorProperty) {
		BackgroundFill fill = new BackgroundFill(colorConverter(colorProperty.getValue()), null, null);
		return new Background(fill);
	}

	//Verplaatsen naar een eigen klasse en laten overerven
	private Color colorConverter(String color) {
		if (color == null) {
			return Color.WHITE;
		}
		
		switch (color) {
		case "blue":
			return Color.DODGERBLUE;
		case "yellow":
			return Color.YELLOW;
		case "green":
			return Color.LAWNGREEN;
		case "purple":
			return Color.MEDIUMPURPLE;
		case "red":
			return Color.RED;
		default:
			return Color.WHITE;
		}
	}
}
