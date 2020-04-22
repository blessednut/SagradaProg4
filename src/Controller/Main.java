package Controller;

import View.DiceView;
import View.FieldView;
import View.WindowPatternView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

//		MySceneController myscene = new MySceneController();
//		GameController gameCon = new GameController();
//		stage.setTitle("Sagrada 2020");
//		stage.setScene(myscene.getMyscene());
//		stage.show();

		stage.setTitle("Test");
		stage.setScene(new Scene(new StackPane(new WindowPatternView(350, 300, "Fractal Drops", makeField(), 6, 2))));
		stage.show();
	}

	private FieldView[][] makeField() {
		FieldView[][] fields = new FieldView[5][4];
		for (int x = 0; x < fields.length; x++) {
			for (int y = 0; y < fields[y].length; y++) {
				boolean hasDice;
				DiceView dice = new DiceView(Color.WHITE, 1);
				
				if (numberGen(1, 4) == 4) {
					hasDice = true;
					Color color = Color.WHITE;
					switch (numberGen(1, 4)) {
					case 1:
						color = Color.RED;
						break;
					case 2:
						color = Color.YELLOW;
						break;
					case 3:
						color = Color.DEEPSKYBLUE;
						break;
					case 4:
						color = Color.MEDIUMPURPLE;
						break;
					}

					dice = new DiceView(color, numberGen(1, 6));
				} else {
					hasDice = false;
				}

				switch (numberGen(1, 3)) {
				case 1:
					fields[x][y] = (hasDice) ? new FieldView(dice): new FieldView();
					break;
				case 2:
					fields[x][y] = (hasDice) ? new FieldView(numberGen(1, 6), dice) : new FieldView(numberGen(1, 6));
					break;
				case 3:
					switch (numberGen(1, 4)) {
					case 1:
						fields[x][y] = (hasDice) ? new FieldView(Color.RED, new DiceView(Color.RED, numberGen(1, 6))) : new FieldView(Color.RED);
						break;
					case 2:
						fields[x][y] = (hasDice) ? new FieldView(Color.DEEPSKYBLUE, new DiceView(Color.DEEPSKYBLUE, numberGen(1, 6))) : new FieldView(Color.DEEPSKYBLUE);
						break;
					case 3:
						fields[x][y] = (hasDice) ? new FieldView(Color.YELLOW, new DiceView(Color.YELLOW, numberGen(1, 6))) : new FieldView(Color.YELLOW);
						break;
					case 4:
						fields[x][y] = (hasDice) ? new FieldView(Color.MEDIUMPURPLE, new DiceView(Color.MEDIUMPURPLE, numberGen(1, 6))) : new FieldView(Color.MEDIUMPURPLE);
						break;
					}

					break;
				}
				// fields[x][y] = new FieldView(new DiceView(Color.YELLOW, 5));
			}
		}
		return fields;
	}

	private int numberGen(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}
}
