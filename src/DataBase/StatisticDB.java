package DataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StatisticDB {
	private Statement st;

	
	public StatisticDB() {
		this.st = DBCon.getInstance().getSt();

	}
	
	public ArrayList<String> searchNamesWithWins() {
		//System.out.println("asdfasdfasdf");
		ArrayList<String> namesWithWins = new ArrayList<>();
		try {
			String query = "SELECT player.username FROM player \r\n" + 
					"JOIN (SELECT idgame, max(score) mscore FROM player GROUP BY idgame) T ON player.idgame = T.idgame AND player.score = T.mscore and player.playstatus = 'finished'\r\n" + 
					"group by username \r\n" + 
					"order by count(player.username) desc;";
//			} 
//			else {
//				query = "SELECT \r\n" + 
//						"    player.username,count(player.username) as numberOfWins\r\n" + 
//						"FROM\r\n" + 
//						"    player\r\n" + 
//						"        JOIN\r\n" + 
//						"    (SELECT \r\n" + 
//						"        idgame, max(score) mscore\r\n" + 
//						"    FROM\r\n" + 
//						"        player\r\n" + 
//						"    GROUP BY idgame) T ON player.idgame = T.idgame\r\n" + 
//						"        AND player.score = T.mscore and player.playstatus = 'finished' \r\n" + 
//						"group by username\r\n" + 
//						"order by numberOfWins desc;";
//			}	
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				namesWithWins.add(resultset.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namesWithWins;
	}
	public ArrayList<Integer> searchAmountOfWins() {
			ArrayList<Integer> amountOfWins = new ArrayList<>();
			try {
				String query = "SELECT count(player.username)FROM player \r\n" + 
						"JOIN (SELECT idgame, max(score) mscore FROM player GROUP BY idgame) T ON player.idgame = T.idgame AND player.score = T.mscore and player.playstatus = 'finished'\r\n" + 
						"group by username \r\n" + 
						"order by count(player.username) desc";
//				} 
//				else {
//					query = "SELECT \r\n" + 
//							"    player.username,count(player.username) as numberOfWins\r\n" + 
//							"FROM\r\n" + 
//							"    player\r\n" + 
//							"        JOIN\r\n" + 
//							"    (SELECT \r\n" + 
//							"        idgame, max(score) mscore\r\n" + 
//							"    FROM\r\n" + 
//							"        player\r\n" + 
//							"    GROUP BY idgame) T ON player.idgame = T.idgame\r\n" + 
//							"        AND player.score = T.mscore and player.playstatus = 'finished' \r\n" + 
//							"group by username\r\n" + 
//							"order by numberOfWins desc;";
//				}	
				ResultSet resultset = st.executeQuery(query);
				while (resultset.next()) {
					amountOfWins.add(resultset.getInt("count(player.username)"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return amountOfWins; 
		
		
		
		
		
	}
	public int getWins (String username) {
		int wins = 0;
		try {
			String query = "SELECT \r\n" + 
					"    player.username,count(player.username) as numberOfWins\r\n" + 
					"FROM\r\n" + 
					"    player\r\n" + 
					"        JOIN\r\n" + 
					"    (SELECT \r\n" + 
					"        idgame, max(score) mscore\r\n" + 
					"    FROM\r\n" + 
					"        player\r\n" + 
					"    GROUP BY idgame) T ON player.idgame = T.idgame\r\n" + 
					"        AND player.score = T.mscore and player.playstatus = 'finished' \r\n" + 
					"where username = '" + username + "'\r\n" + 
					"group by username;";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				wins = resultset.getInt("numberOfWins");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wins;
	}
	
	public int getLosses (String username) {
		int wins = 0;
		try {
			String query = "SELECT \r\n" + 
					"    player.username,count(player.username) as numberOfWins\r\n" + 
					"FROM\r\n" + 
					"    player\r\n" + 
					"        JOIN\r\n" + 
					"    (SELECT \r\n" + 
					"        idgame, MIN(score) mscore\r\n" + 
					"    FROM\r\n" + 
					"        player\r\n" + 
					"    GROUP BY idgame) T ON player.idgame = T.idgame\r\n" + 
					"        AND player.score = T.mscore and player.playstatus = 'finished' \r\n" + 
					"where username = '" + username + "'\r\n" + 
					"group by username;";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				wins = resultset.getInt("numberOfWins");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wins;
	}

	public int getHighestScore(String username) {
		int score = 0;
		try {
			String query = "select score\r\n" + "from player\r\n" + "where username like '" + username + "'\r\n"
					+ "order by score desc\r\n" + "limit 1;\r\n" + "";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				score = resultset.getInt("score");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}

	public String getMostPlacedColor (String username) {
		String MostPlacedColor = null;
		try {
			String query = "select diecolor\r\n" + 
					"from playerframefield as pf\r\n" + 
					"join player as p\r\n" + 
					"on pf.idplayer = p.idplayer\r\n" + 
					"where p.username = '" + username + "'\r\n" + 
					"group by diecolor \r\n" + 
					"order by count(diecolor) desc\r\n" + 
					"limit 1;";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				MostPlacedColor = resultset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MostPlacedColor;
	}

	public int getMostPlacedValue(String username) {
		int MostPlacedValue = 0;
		try {
			String query = "select eyes\r\n" + "from playerframefield pf \r\n" + "join gamedie gd\r\n"
					+ "on pf.idgame = gd.idgame and pf.dienumber = gd.dienumber and pf.diecolor = gd.diecolor\r\n"
					+ "join player as p\r\n" + "on pf.idplayer = p.idplayer\r\n" + "where p.username = '" + username + "'\r\n"
					+ "group by eyes \r\n" + "order by count(eyes) desc\r\n" + "limit 1;";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				MostPlacedValue = resultset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MostPlacedValue;
	}

	public int getNumOpponents(String username) {
		int numOpponents = 0;
		try {
			String query = "select count(distinct username) - 1 \r\n" + "from player\r\n"
					+ "where idgame = any (select idgame from player where username like '" + username + "');";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				numOpponents = resultset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (numOpponents >= 1) {
			return numOpponents;
		} else {
			return 0;
		}
	}

	public Boolean usernameExists(String username) {
		Boolean result = false;
		try {
			String query = "select username from account where username = '" + username + "' ;";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getAllUsernames(){
		ArrayList<String> names = new ArrayList<>();
		try {
			String query = "select username from account;";
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				names.add(resultset.getString("username"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return names;
	}
}
