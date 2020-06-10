package Controller;

import javafx.application.Platform;

public class InGameThread extends Thread {

	private GameController gameController;

	private boolean running = true;

	public InGameThread(GameController gameController) {
		this.gameController = gameController;
	}

	public void run() {
		while (running) {
			try {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						gameController.refresh();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	public void terminateThread() {
		this.running = false;
	}

}
