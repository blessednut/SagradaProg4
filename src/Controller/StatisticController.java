package Controller;

import java.util.ArrayList;
import java.util.List;

import View.StatisticsPane;
import model.StatisticModel;

public class StatisticController {
	private StatisticsPane view;
	private StatisticModel model;
	private boolean isASC;
	
	public StatisticController () {
		this.isASC = false;
		model = new StatisticModel();
		view = new StatisticsPane(this);
	}
	
	public StatisticsPane getView () {
		return view;
	}
	
	public void search (String username) {
		if (model.usernameExists(username)) {
			view.update();
		}
	}
	
	public int getWins () {
		return model.getWins();
	}
	
	public int getLosses () {
		return model.getLosses();
	}

	public int getHighestScore() {
		return model.getHighestScore();
	}

	public String getUsername() {
		return model.getPlayerName();
	}
	
	public int getNumOpponents () {
		return model.getNumOpponents();
	}

	public int getMostPlacedValue() {
		return model.getMostPlacedValue();
	}
	
	public String getMostPlacedColor () {
		return model.getMostPlacedColor();
	}

	public List<String> getRank() {
		return model.getRankList(isASC);
	}
	
	public void setOrder (boolean isASC) {
		this.isASC = isASC;
	}
}
