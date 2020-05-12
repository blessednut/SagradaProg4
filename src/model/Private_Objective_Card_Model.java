package model;

import DataBase.Private_Objective_CardDB;

public class Private_Objective_Card_Model {
	private Private_Objective_CardDB POCDB;

	public Private_Objective_Card_Model(int GameId, String username) {
		POCDB = new Private_Objective_CardDB(this);
	}

//	TODO er moet een kleur uit de database komen.
//	TODO er moet een speler meegegeven worden
	public String getColor(int GameId, String username) {
		String color = "";
		try {
		color = POCDB.getColor(GameId, username);
		}catch (Exception e) {
			
		}
		

		return color;
	}

}
