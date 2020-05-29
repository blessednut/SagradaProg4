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
//	private IntegerProperty value;
	//private DiceView diceView;
	private WindowPatternSquareController controller;
	private double width, height;

	public WindowPatternSquareView(WindowPatternSquareController controller) {
		this.controller = controller;

		PatternCardFieldModel square = controller.getSquare();
		Color color = new ColorConverter().colorConverter(square.getColor());
		this.setBackground(new Background(new BackgroundFill(color, null, null)));
		
		this.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
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
		
		//System.out.println("asdfasdfasdfdsasdfadsafsdfdfssdf");
	}

	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
		this.setMinSize(width, height);
		this.getChildren().add(new DicePattern(controller.getSquare().getValue(), width, height));
	}
	
	public void updateView () {
		this.getChildren().clear();
		
		//Dit is mischien nodig voor de toolcards
		//if (controller.getDice() != null) {
			this.getChildren().add(new DicePattern(controller.getSquare().getValue(), width, height));
		//}
	}
	
	private void onClick() {
		this.controller.onClick();
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
