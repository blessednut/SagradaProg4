package View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarView extends MenuBar {
	
	private Menu menu;
	
	public MenuBarView() {
		createMenu();
	}
	
	public void createMenu() {
		menu = new Menu("Option");
		MenuItem home = new MenuItem("Home");
		MenuItem games = new MenuItem("Games");
		MenuItem friends = new MenuItem("Vrienden");
		MenuItem stats = new MenuItem("Statistieken");
		MenuItem credits = new MenuItem("Credits");
		
		menu.getItems().addAll(home,games,friends,stats,credits);
		this.getMenus().add(menu);
		
		
	}


}
