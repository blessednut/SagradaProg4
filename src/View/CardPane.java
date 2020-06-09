package View;

import javafx.scene.layout.Pane;

public abstract class CardPane extends Pane {
	private int cardHeight;
	private int cardWidth;

	public CardPane() {
		cardHeight = 220;
		cardWidth = 160;
		this.setMinSize(cardWidth, cardHeight);
		this.setMaxSize(cardWidth, cardHeight);
		this.setPrefSize(cardWidth, cardHeight);
	}

}
