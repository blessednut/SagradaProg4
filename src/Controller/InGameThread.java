package Controller;

import javafx.application.Platform;

public class InGameThread extends Thread {

	private GameController gameController;

	private boolean running;

	public InGameThread(GameController gameController) {
		this.gameController = gameController;
	}

	public void run() {
		running = true;

		while (running) {
			//TODO: isTurn bepaald of je aan de beurt bent
			//TODO: aan de beurt alle tijd krijgen om je dingen te doen.
			//TODO: als je niet aan de beurt bent refresh elke 5 sec.
			if(gameController.getIsTurn()) {
				try {
					Thread.sleep(2000);
					System.out.println("nu slaap ik");
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				try {
					Platform.runLater(new Runnable() {
						
						public void run() {
							gameController.refresh();
							System.out.println("refresh");
							try {
								Thread.sleep(5000);
							} 
							catch (Exception ex) {
								ex.printStackTrace();
							}

						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}			
		}
	}
	

	public void terminateThread() {
		this.running = false;
	}

}
