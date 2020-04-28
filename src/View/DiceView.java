package View;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class DiceView extends StackPane {
	private final static CornerRadii RADIUS = new CornerRadii(7);
	private final static Insets INSETS = new Insets(5);
	private final static BorderWidths BORDERWIDTH = new BorderWidths(2);
	private Color color;
	private int value;

	public DiceView(Color color, int value) {
		this.color = color;
		this.value = value;
	}

	public void drawDice(double width, double height) {
		this.setPrefSize(width, height);
		this.setMaxSize(width, height);

		this.setBackground(new Background(new BackgroundFill(color, RADIUS, INSETS)));

		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, RADIUS, BORDERWIDTH, INSETS)));
		this.getChildren().add(new DicePattern(value, width, height));
	}

	public void setValue(int value) {
		this.value = value;
	}
}