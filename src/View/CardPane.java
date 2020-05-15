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
	      
//		TODO: Code voor een hover functie
//	        animTimer.scheduleAtFixedRate(new TimerTask() {
//	            int i = 0;
//
//	            @Override
//	            public void run() {
//	                if(i < 100) {
//	                    cardWidth += 5;
//	                    cardHeight += 5;
//	                } else {
//	                    this.cancel();
//	                }
//	                i++;
//	            }
//
//	        }, 1000, 25);


	}
