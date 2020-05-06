package model;

public class PlayerModel {

	private String username = null;
	private int currentGameID;

	public PlayerModel() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCurrentGameID() {
		return currentGameID;
	}

	public void setCurrentGameID(int currentGameID) {
		this.currentGameID = currentGameID;
	}
}
