package javastory.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javastory.club.service.dto.MemberDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.ui.controller.ClubController;
import javastory.ui.window.NewClubWindow;
import javastory.ui.window.WindowLycler;
import javastory.util.Session;
import javastory.util.SizeHandler;
import javastory.util.TableManager;

public class DetailMainView {
	//
	private ClubController clubController;
	private TableView<TravelClubDto> clubTable;
	private ClubPaneView clubPaneView;
	private BoardPaneView boardPaneView;
	private TravelPaneView travelPaneView;
	private TextField searchField;
	private Tab boardTab, travelTab;

	public DetailMainView() {
		//
		clubController = new ClubController();
		clubPaneView = WindowLycler.shareInstance().requestClubPaneView();
		boardPaneView = WindowLycler.shareInstance().requestBoardPaneView();
		travelPaneView = WindowLycler.shareInstance().requestTravelPaneView();
	}

	public VBox setBorderLeft() {
		// Creating Nodes
		String notice = "If you want to create a new club ...";
		Text navText = new Text(notice);
		Button newButton = new Button("click! here");
		searchField = new TextField();
		Button searchButton = new Button("search");
		Image searchImg = new Image(getClass().getClassLoader().getResourceAsStream("images/search_icon.png"));
		ImageView imageView = new ImageView(searchImg);
		VBox borderLeftBox = new VBox();
		HBox searchBox = new HBox();
		HBox navBox = new HBox();

		// Setting onAction at button
		newButton.setOnAction(e -> openNewClubWindow());
		searchButton.setOnAction(e -> searchClub());

		// Creating table
		clubTable = TableManager.shareInstance().requestClubTable();
		TableManager.shareInstance().refreshClubTable();
		
		// Setting action on table
		clubTable.setOnMouseClicked(e -> {
			TravelClubDto selectedClub = clubTable.getSelectionModel().getSelectedItem();
			if (selectedClub == null) {
				return;
			}
			clubPaneView.loadClubData(selectedClub);
			boardPaneView.loadBoardData(selectedClub);
			travelPaneView.loadTravelData(selectedClub);
			boardTab.setDisable(false);
			travelTab.setDisable(false);
		});

		// Setting And Styling Nodes
		searchField.setPromptText("Search clubs here~!");
		imageView.setFitWidth(SizeHandler.SEARCH_ICON_WIDTH);
		imageView.setFitHeight(SizeHandler.SEARCH_ICON_HEIGHT);
		searchButton.setGraphic(imageView);
		newButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		searchButton.setStyle("-fx-background-color: white; -fx-border-style: solid inside;");
		HBox.setHgrow(searchField, Priority.ALWAYS);
		VBox.setVgrow(searchField, Priority.ALWAYS);
		searchField.setMaxHeight(Double.MAX_VALUE);
		VBox.setVgrow(clubTable, Priority.ALWAYS);
		navBox.setAlignment(Pos.CENTER);
		navBox.setSpacing(5);
		searchBox.setSpacing(5);
		borderLeftBox.setSpacing(10);
		borderLeftBox.setPadding(new Insets(10, 10, 10, 10));
		borderLeftBox.setStyle("-fx-border-style: solid inside; -fx-background-color: white;");

		// Get children
		searchBox.getChildren().addAll(searchField, searchButton);
		navBox.getChildren().addAll(navText, newButton);
		borderLeftBox.getChildren().addAll(navBox, searchBox, clubTable);

		return borderLeftBox;
	}

	private void searchClub() {
		// 
		String clubName = searchField.getText();
		if (clubName.isEmpty()) {
			TableManager.shareInstance().refreshClubTable();
		}else {
			findOne(clubName);
		}
	}
	
	private void findOne(String clubName) {
		//
		TravelClubDto foundClub = clubController.searchByName(clubName);
		if (foundClub != null) {
			clubTable.getItems().clear();
			clubTable.getItems().add(foundClub);
		}
	}

	private void openNewClubWindow() {
		// Before window display checking session, Check if the session has user information
		if (checkingSessionUser()) {
			NewClubWindow newClubWindow = WindowLycler.shareInstance().requestNewClubWindow();
			newClubWindow.display();
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

	public TabPane setBorderCenter() {
		//
		TabPane tabPane = new TabPane();

		Tab clubTab = new Tab("Club");
		clubTab.setContent(clubPaneView.createClubPane());

		boardTab = new Tab("Board");
		boardTab.setContent(boardPaneView.createBoardPane());
		boardTab.setDisable(true);

		travelTab = new Tab("Travel");
		travelTab.setContent(travelPaneView.createTravelPane());
		travelTab.setDisable(true);

		tabPane.getTabs().addAll(clubTab, boardTab, travelTab);
		tabPane.setStyle("-fx-background-color: white;");

		return tabPane;
	}
}