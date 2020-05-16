package View;


import javafx.scene.layout.Pane;

public abstract class CardPane extends Pane {
	private int cardHeight;
	private int cardWidth;

	//isToolCard geeft het verschil aan tussen de doelkaarten en de gereedschapskaarten
//	Timer animTimer = new Timer();
	
	public CardPane() {
		cardHeight = 350;
		cardWidth = 250;
		this.setMinSize(cardWidth, cardHeight);
		this.setMaxSize(cardWidth, cardHeight);
		this.setPrefSize(cardWidth, cardHeight);
	}
	      


	}
