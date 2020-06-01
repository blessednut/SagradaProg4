package Controller;

import java.util.ArrayList;

import View.StatisticsPane;
import model.StatisticModel;

public class StatisticController {
	private StatisticsPane STView;
	private StatisticModel STModel;

	
	public StatisticController () {
		STModel = new StatisticModel();
		STView = new StatisticsPane(this);
	}
	
	public StatisticsPane getView () {
		return STView;
	}
	
	public void search (String username) {
		if (STModel.usernameExists(username)) {
			STView.update();
		}
	}
	
	public int getWins () {
		return STModel.getWins();
	}
	
	public int getLosses () {
		return STModel.getLosses();
	}

	public int getHighestScore() {
		return STModel.getHighestScore();
	}

	public String getUsername() {
		return STModel.getPlayerName();
	}
	
	public int getNumOpponents () {
		return STModel.getNumOpponents();
	}

	public int getMostPlacedValue() {
		return STModel.getMostPlacedValue();
	}
	
	public String getMostPlacedColor () {
		return STModel.getMostPlacedColor();
	}

//	public List<String> getRank() {
//		return STModel.getRankList(isASC);
//	}
	
	public void setOrder (boolean isASC) {
		STModel.setASC(isASC);
	}
	
	public ArrayList<String> getNames(){
		return STModel.getNames();
	}
	
	public ArrayList<String> searchNamesWithWins(){
		return STModel.searchNamesWithWins();
	}
	
	public ArrayList<Integer> searchAmountOfWins(){
		return STModel.searchAmountOfWins();
	}
	
	
}
