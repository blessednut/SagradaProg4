package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class LoginDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	public LoginDB () {
		this.st = DBCon.getInstance().getSt();
	}
	
	public void registerLogin(String Username, String Password) {
		try {
			String query = "insert into Account values(?,?)";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ps.execute();
			Alert succes = new Alert(AlertType.INFORMATION, "Uw nieuwe account is aangemaakt.", ButtonType.OK);
			succes.showAndWait();
			if (succes.getResult() == ButtonType.OK) {
				succes.close();
			}
		} catch (Exception ex) {
			Alert exception = new Alert(AlertType.ERROR,
					"De gebruikersnaam die je wilt gebruiken bestaat al.\nKies een andere gebruiksnaam alstublieft.",
					ButtonType.YES, ButtonType.NO);
			exception.showAndWait();
			if (exception.getResult() == ButtonType.YES) {
				exception.close();
			} else {
				Platform.exit();
			}
		}
	}

	public String getPassword(String username) {
		String result = null;
		try {
			String query = "select password from account where username = '" + username + "';";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				result = resultset.getString("password");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
