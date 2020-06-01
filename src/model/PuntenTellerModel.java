package model;

import DataBase.PuntenTellerDB;

public class PuntenTellerModel {
	private PuntenTellerDB ptDB;
	
	public PuntenTellerModel() {
		this.ptDB = new PuntenTellerDB();
	}
	
	public void updateScore(int score, int idplayer) {
		ptDB.updateScore(score, idplayer);
	}
}

