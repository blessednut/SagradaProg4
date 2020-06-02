package View;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TokenPane extends Pane {
	private final static int WIDTH = 10;
	private final static int HEIGHT = 10;

	
	public TokenPane(Color playerColor)  {
		this.setMinSize(WIDTH, HEIGHT);
		this.setPrefSize(WIDTH, HEIGHT);
		this.setMaxSize(WIDTH, HEIGHT);
		this.setPadding(new Insets(0,0,0,5));
		
		Circle payToken = new Circle();
		payToken.setCenterX(WIDTH/2);
		payToken.setCenterY(HEIGHT/2);
		payToken.setRadius(8);
		payToken.setStroke(Color.BLACK);
		payToken.setStrokeWidth(2);
		
		payToken.setFill(playerColor);
		this.getChildren().add(payToken);
	}
}
