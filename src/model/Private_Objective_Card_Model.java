package model;

import DataBase.Private_Objective_CardDB;

public class Private_Objective_Card_Model {
	private Private_Objective_CardDB POCDB;

	public Private_Objective_Card_Model(int GameId, String username) {
		POCDB = new Private_Objective_CardDB();
	}

	public String getColor(int GameId, String username) {
		String color = "";
		try {
		color = POCDB.getColor(GameId, username);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return color;
	}

}
