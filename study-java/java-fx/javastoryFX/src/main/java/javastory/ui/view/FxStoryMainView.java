package javastory.ui.view;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javastory.club.service.dto.MemberDto;
import javastory.ui.window.WindowLycler;
import javastory.util.Session;

public class FxStoryMainView {

	private MenuBar menuBar; 
	private BorderPane mainBorderPane;
	
	public Parent getPane() {
		// 
		mainBorderPane = new BorderPane();
		createMenus();
		mainBorderPane.setTop(menuBar);
		
		return mainBorderPane;
	}

	private void showWelcome() {
		// 
		WindowLycler.shareInstance().requestWelcomeWindow().display();
	}

	private void createMenus() {
		//Creating MenuBar
		menuBar = new MenuBar();
		Menu systemMenu = new Menu("FxStory");
		
		//start menuItem
		MenuItem startItem = new MenuItem("start");
		startItem.setOnAction(e-> showWelcome());
		
		//Get items and menus
		systemMenu.getItems().add(startItem);
		menuBar.getMenus().add(systemMenu);
		
		plusClubMenus();
	}
	
	private void plusClubMenus() {
		//Creating MenuBar
		Menu clubMenu = new Menu("Club");
		
		//New menuItem
		MenuItem newItem = new MenuItem("new club");
		newItem.setOnAction(e-> showNewClubWindow());
		
		//Get items and menus
		clubMenu.getItems().add(newItem);
		menuBar.getMenus().add(clubMenu);
	}
	
	private void showNewClubWindow() {
		// Before window display checking session, Check if the session has user information 
		if (checkingSessionUser()) {
			WindowLycler.shareInstance().requestNewClubWindow().display();
		}
	}
	
	private boolean checkingSessionUser() {
		//
		MemberDto sessionUser = Session.shareInstance().getLoginUser();
		
		if (sessionUser != null) {
			return true;
		}
		return false;
	}
	
	public void setInnerView() {
		//
		DetailMainView detailMainView = WindowLycler.shareInstance().requestDetailView();
		mainBorderPane.setLeft(detailMainView.setBorderLeft());
		mainBorderPane.setCenter(detailMainView.setBorderCenter());
	}
}