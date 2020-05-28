package model;

import java.util.ArrayList;
import DataBase.RoundtrackDB;

public class RoundtrackModel {

    private RoundtrackDB roundtrackDB;
    private int gameid;

    public RoundtrackModel(int gameid) {
        this.roundtrackDB = new RoundtrackDB();
        this.gameid = gameid;
    }

    public ArrayList<GameDiceModel> getDice(){
        return roundtrackDB.getDice(gameid);
    }
}
