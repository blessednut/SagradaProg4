package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.GameDiceModel;

public class RoundtrackDB {

    private ResultSet rs;
    private Statement st;
    private PreparedStatement ps;


    public RoundtrackDB() {
        this.st = DBCon.getInstance().getSt();
    }

    public ArrayList<GameDiceModel> getDice(int gameid) {
        try {
            ArrayList<GameDiceModel> remainingDice = new ArrayList<GameDiceModel>();
            String query = "select dienumber, diecolor, eyes, roundtrack from gamedie where roundtrack is not null and idgame = "+gameid+" order by roundtrack;";
            rs = st.executeQuery(query);
            while (rs.next()) {
                remainingDice.add(new GameDiceModel(gameid, rs.getInt("dienumber"), rs.getString("diecolor"), rs.getInt("eyes"), rs.getInt("roundtrack"), 0));
            }
            return remainingDice;
        } catch (Exception e) {
            System.out.println("RoundtrackDB:");
            System.out.println(e);
        }
        return null;
    }

}
