package Controller;

import View.ColorConverter;
import View.RoundtrackPane;
import javafx.beans.property.StringProperty;
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
            System.out.println("FILL ROUND TRACK: ");
            System.out.println("Color: " + color + " Eyes = " + eyes);
            roundtrackPane.addDice(roundtrack, color, eyes);
            i++;
        }
    }
}