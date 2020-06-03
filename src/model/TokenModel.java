package model;

import DataBase.TokenDB;

public class TokenModel {
	private TokenDB tokenDB;

	public TokenModel() {
		tokenDB = new TokenDB();
	}

	public void insertTokenInDB(int gameID, int playerID) {
		tokenDB.insertTokenInDB(gameID, playerID);
	}

	public void updateToken(int toolCardID, int roundID, int gameID, int playerID, int cost) {
		tokenDB.updateToken(toolCardID, roundID, gameID, playerID, cost);
	}

	public boolean tokensAdded(int tokenAmount, int playerID) {
		return tokenDB.tokensAdded(tokenAmount, playerID);
	}

	public int getToolCardCost(int toolCardID, int gameID) {
		return tokenDB.getToolCardCost(toolCardID, gameID);
	}

	public int getToolCardID(int gameID, String cardName) {
		return tokenDB.getToolCardID(gameID, cardName);
	}

	public int getTokenAmount(int playerID) {
		return tokenDB.getTokenAmount(playerID);
	}
}
