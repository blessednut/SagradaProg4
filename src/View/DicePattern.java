package View;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DicePattern extends GridPane {
	private final static int RADIUS = 5;

	public DicePattern(int value, double width, double height) {
		this.setMaxSize(width, height);
		fillGridPane(value, width, height);

//		final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				fillGridPane(value, width, height);
//			}
//
//		};
//		value.addListener(changeListener);
		//fillGridPane(value, width, height);
	}

	public DicePattern(Color white, int value) {
		// TODO Auto-generated constructor stub
	}

	private void fillGridPane(int value, double width, double height) {
		// Make Grid
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				StackPane pane = new StackPane();
				pane.setPrefSize(width / 3, height / 3);
				this.add(pane, x, y);
			}
		}

		// Make Dots
		switch (value) {
		case 1:
			getStackPaneFromGrid(this, 1, 1).getChildren().add(makeDot(RADIUS));
			break;
		case 2:
			getStackPaneFromGrid(this, 0, 2).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 0).getChildren().add(makeDot(RADIUS));
			break;
		case 3:
			getStackPaneFromGrid(this, 0, 2).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 1, 1).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 0).getChildren().add(makeDot(RADIUS));
			break;
		case 4:
			getStackPaneFromGrid(this, 0, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 0, 2).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 2).getChildren().add(makeDot(RADIUS));
			break;
		case 5:
			getStackPaneFromGrid(this, 0, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 0, 2).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 1, 1).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 2).getChildren().add(makeDot(RADIUS));
			break;
		case 6:
			getStackPaneFromGrid(this, 0, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 1, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 0).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 0, 2).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 1, 2).getChildren().add(makeDot(RADIUS));
			getStackPaneFromGrid(this, 2, 2).getChildren().add(makeDot(RADIUS));
			break;
		default:
			break;
		}
	}

	private StackPane getStackPaneFromGrid(GridPane grid, int row, int column) {
		StackPane pane = null;

		for (Node node : grid.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				if (node instanceof StackPane) {
					return (StackPane) node;
				}
			}
		}
		return pane;
	}

	private Circle makeDot(int radius) {
		Circle circle = new Circle();
		circle.setRadius(radius);
		circle.setFill(Color.BLACK);
		return circle;
	}
}
