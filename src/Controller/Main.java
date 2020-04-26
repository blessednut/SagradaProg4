package Controller;

import DataBase.DBCon;
import View.DiceView;
import View.WindowPatternView;
import View.WindowPatternView2;
import View.squareView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
//		MySceneController myscene = new MySceneController();
//		GameController gameCon = new GameController();
//		stage.setTitle("Sagrada 2020");
////		stage.initStyle(StageStyle.UNDECORATED);
////		stage.setAlwaysOnTop(true);
//		stage.setScene(myscene.getMyscene());
//		stage.show();
//
//		myscene.getMyscene().addEventHandler(KeyEvent.KEY_PRESSED, new MyKeyHandler());

		// Hierna volgt testcode
		DBCon con = new DBCon();
		PatternCardModel card = con.getPatternCard(3);
		PatternCardFieldModel[][] field = con.getField(3);

		// Test inladen field
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				System.out.println("Id= " + field[x][y].getIdPatternCard() + " x= " + field[x][y].getX() + " y= "
						+ field[x][y].getY() + " value= " + field[x][y].getValue() + " color= "
						+ field[x][y].getColor());
			}
		}

		// Test inladen card
//		System.out.println("Card id = " + card.getIdPatternCard());
//		System.out.println("Card name = " + card.nameProperty());
//		System.out.println("Card difficulty = " + card.getDifficulty());
//		System.out.println("Card standard = " + card.getStandard());

		// Maak squareView
		squareView[][] fieldView = new squareView[5][4];
		for (int x = 0; x < fieldView.length; x++) {
			for (int y = 0; y < fieldView[x].length; y++) {
				// Nep gegevens om te testen totdat de database is gevuld
				if (getRandomIntBetweenRange(1, 3) == 3) {
					if (field[x][y].colorProperty().getValue() != null) {
						System.out.println(field[x][y].colorProperty().getValue());
						fieldView[x][y] = new squareView(field[x][y].colorProperty(), field[x][y].valueProperty(),
								new DiceView(colorConverter(field[x][y].colorProperty().getValue()),
										getRandomIntBetweenRange(1, 6)));
					} else if (field[x][y].valueProperty().getValue() != 0) {
						fieldView[x][y] = new squareView(field[x][y].colorProperty(), field[x][y].valueProperty(),
								new DiceView(randomColor(getRandomIntBetweenRange(1, 5)),
										field[x][y].valueProperty().getValue()));
					} else {
						fieldView[x][y] = new squareView(field[x][y].colorProperty(), field[x][y].valueProperty(),
								new DiceView(randomColor(getRandomIntBetweenRange(1, 5)),
										getRandomIntBetweenRange(1, 6)));
					}

				} else {
					fieldView[x][y] = new squareView(field[x][y].colorProperty(), field[x][y].valueProperty(), null);
				}

			}
		}

//		DiceView[][] dieView = new DiceView[5][4];
//		for (int x = 0; x < field.length; x++) {
//			for (int y = 0; y < field[x].length; y++) {
//				if (getRandomIntBetweenRange(1, 5) == 5) {
//					dieView[x][y] = new DiceView(Color.RED, getRandomIntBetweenRange(1, 6));
//				}
//			}
//		}

		stage.setTitle("test");
		stage.setScene(new Scene(new StackPane(new WindowPatternView2(600, 450, card.nameProperty(), fieldView))));
		stage.show();
	}

	private Color randomColor(int i) {
		switch (i) {
		case 1:
			return Color.DODGERBLUE;
		case 2:
			return Color.YELLOW;
		case 3:
			return Color.LAWNGREEN;
		case 4:
			return Color.MEDIUMPURPLE;
		case 5:
			return Color.RED;
		default:
			return Color.WHITE;
		}
	}

	private Color colorConverter(String color) {
		if (color == null) {
			return Color.WHITE;
		}

		switch (color) {
		case "blauw":
			return Color.DODGERBLUE;
		case "geel":
			return Color.YELLOW;
		case "groen":
			return Color.LAWNGREEN;
		case "paars":
			return Color.MEDIUMPURPLE;
		case "rood":
			return Color.RED;
		default:
			return Color.WHITE;
		}
	}

	public static int getRandomIntBetweenRange(int min, int max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	private class MyKeyHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		}

	}

}
