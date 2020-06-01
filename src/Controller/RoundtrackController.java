package Controller;

import View.ColorConverter;
import View.RoundtrackPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.RoundtrackModel;

public class RoundtrackController {
    private RoundtrackPane roundtrackPane;
    private RoundtrackModel roundtrackModel;

    public RoundtrackController(int gameid){
        roundtrackPane = new RoundtrackPane();
        roundtrackModel = new RoundtrackModel(gameid);
    }

    public RoundtrackPane getRoundtrackPane() {
        return roundtrackPane;
    }

    public void fillRoundtrack() {
        ColorConverter cc = new ColorConverter();
        roundtrackPane.clearRoundtrack();
        int i = 0;
        while(i<roundtrackModel.getDice().size()) {
            int roundtrack = roundtrackModel.getDice().get(i).getRoundtrack();
//            Color color = cc.colorConverter(roundtrackModel.getDice().get(i).getDieColorString());
//            int eyes = roundtrackModel.getDice().get(i).getEyes().intValue();
            
            Color color = cc.colorConverter(roundtrackModel.getDice().get(i).colorProperty().getValue());
            int eyes = roundtrackModel.getDice().get(i).valueProperty().getValue();
//            System.out.println("FILL ROUND TRACK: ");
//            System.out.println("Color: " + color + " Eyes = " + eyes);
            roundtrackPane.addDice(roundtrack, color, eyes);
            i++;
        }
    }
    
    public void bla() {
    	for(VBox round : roundtrackPane.getRounds()) {
    		for(int i=0; round.getChildren().get(i)!=null; i++) {
    			round.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent> () {
    				@Override
    				public void handle(MouseEvent event) {
    					onClick();
    				}

					
    			});
    		}
    	}
    }
    
    private void onClick() {
		
	}
}