package View;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class FieldView extends Pane {
	private Color color;
	private int value;
	private DiceView dice;

	public FieldView() {
		this.color = null;
		this.value = -1;
	}

	public FieldView(DiceView dice) {
		this.color = null;
		this.value = -1;
		this.dice = dice;
	}

	public FieldView(Color color) {
		this.color = color;
		this.value = -1;
	}

	public FieldView(Color color, DiceView dice) {
		this.color = color;
		this.value = -1;
		this.dice = dice;
	}

	public FieldView(int value) {
		this.color = null;
		this.value = value;
	}

	public FieldView(int value, DiceView dice) {
		this.color = null;
		this.value = value;
		this.dice = dice;
	}

	public void drawField(double width, double height) {
		this.setMinSize(width, height);
		this.setOnMouseEntered(event -> fieldHover());
		this.setOnMouseExited(event -> fieldUnhover());

		if (dice == null) {
			if (color == null && value == -1) {
				this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			} else if (color == null) {
				this.getChildren().add(new DicePattern(value, width, height));
				this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			} else {
				this.setBackground(new Background(new BackgroundFill(color, null, null)));
			}
		} else {
			dice.drawDice(width, height);

			if (this.color != null) {
				this.setBackground(new Background(new BackgroundFill(color, null, null)));
			}

			this.getChildren().add(dice);
		}
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
