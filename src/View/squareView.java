package View;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.effect.DropShadow;
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
		ColorConverter converter = new ColorConverter();
		BackgroundFill fill = new BackgroundFill(converter.colorConverter(colorProperty.getValue()), null, null);
		return new Background(fill);
	}
	
//	this.setOnMouseEntered(event -> fieldHover());
//	this.setOnMouseExited(event -> fieldUnhover());
//	
//	private void fieldHover() {
//		DropShadow shadow1 = new DropShadow();
//		shadow1.setColor(Color.GREEN);
//		shadow1.setOffsetX(2);
//		shadow1.setOffsetY(2);
//
//		DropShadow shadow2 = new DropShadow();
//		shadow2.setColor(Color.GREEN);
//		shadow2.setOffsetX(-2);
//		shadow2.setOffsetY(-2);
//
//		shadow2.setInput(shadow1);
//		this.setEffect(shadow2);
//	}
//
//	private void fieldUnhover() {
//		this.setEffect(null);
//	}
}
