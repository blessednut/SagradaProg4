package View;

import javafx.scene.paint.Color;

public class ColorConverter {
	public Color colorConverter(String color) {
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
