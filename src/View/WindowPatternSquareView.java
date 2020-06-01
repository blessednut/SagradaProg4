package View;

import Controller.WindowPatternSquareController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.GameDiceModel;
import model.PatternCardFieldModel;

public class WindowPatternSquareView extends Pane {
	
	private WindowPatternSquareController controller;
	private double width, height;

	public WindowPatternSquareView(WindowPatternSquareController controller) {
		this.controller = controller;

		PatternCardFieldModel square = controller.getSquare();
		Color color = new ColorConverter().colorConverter(square.getColor());
		this.setBackground(new Background(new BackgroundFill(color, null, null)));
		
		this.setOnMouseClicked(new EventHandler<MouseEvent> () {
			public void handle(MouseEvent event) {
				onClick();
			}
		});
		
		controller.setView(this);
	}
	
	public void setDice() {
		GameDiceModel dice = controller.getDice();
		
		Color color = new ColorConverter().colorConverter(dice.colorProperty().getValue());
		DiceView diceView = new DiceView(color, dice.valueProperty().getValue());
		diceView.drawDice(50, 50);
		this.getChildren().add(diceView);
	}

	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
		this.setMinSize(width, height);
		this.getChildren().add(new DicePattern(controller.getSquare().getValue(), width, height));
	}
	
	public void updateView () {
		this.getChildren().clear();
		this.getChildren().add(new DicePattern(controller.getSquare().getValue(), width, height));
	}
	
	private void onClick() {
		this.controller.onClick();
	}
}
