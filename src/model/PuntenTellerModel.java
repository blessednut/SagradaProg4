package model;

import Controller.PuntenTeller;
import DataBase.PuntenTellerDB;

public class PuntenTellerModel {
	private PuntenTeller pt;
	private PuntenTellerDB ptDB;
	
	public PuntenTellerModel(PuntenTeller pt) {
		this.pt = pt;
		this.ptDB = new PuntenTellerDB();
	}
	
	public void updateScore(int score, int idplayer) {
		ptDB.updateScore(score, idplayer);
	}
}

