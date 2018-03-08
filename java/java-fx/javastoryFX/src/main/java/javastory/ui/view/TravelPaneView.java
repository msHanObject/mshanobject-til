package javastory.ui.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.ui.contorller.BudgetControllerLycler;
import javastory.budget.ui.contorller.CashBookController;
import javastory.budget.ui.contorller.TravelController;
import javastory.club.service.dto.TravelClubDto;
import javastory.ui.window.CashBookWindow;
import javastory.ui.window.WindowLycler;
import javastory.util.Session;
import javastory.util.SizeHandler;
import javastory.util.TableManager;

public class TravelPaneView {
	//
	private TextField searchField;
	private Button openCashBookBtn, searchBtn, addTravelBtn, delTravelBtn;
	private TableView<TravelDto> fullTravelTable;
	private TextField travelTitleField, leaderField, startDateField, endDateField, participantsField, memoField;
	private TravelController travelController;
	private CashBookController cashBookController;
	
	public TravelPaneView() {
		//
		searchField = new TextField();
		openCashBookBtn = new Button("open cashBook");
		searchBtn = new Button("search");
		addTravelBtn = new Button("add");
		delTravelBtn = new Button("delete");
		travelTitleField = new TextField();
		leaderField = new TextField();
		startDateField = new TextField();
		endDateField = new TextField();
		participantsField = new TextField();
		memoField = new TextField();
		travelController = BudgetControllerLycler.shareInstance().requestTravelController();
		cashBookController = BudgetControllerLycler.shareInstance().requestCashBookController();
	}
	
	public GridPane createTravelPane() {
		// Creating and Setting a GirdPane
		GridPane gridPane = new GridPane();
		gridPane = new GridPane();
		gridPane.setStyle("-fx-background-color: #FFFFFF;");
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		setButtonsAction();
		
		createPostingTalbes();
		settingFields();
		
		//Creating a HBox for addDeleteBox of travelTable
		HBox addDeleteBox = new HBox();
		addDeleteBox.getChildren().addAll(travelTitleField, leaderField, startDateField,
				endDateField, participantsField, memoField, addTravelBtn, delTravelBtn);
		addDeleteBox.setSpacing(10);
		
		VBox tableBox = new VBox();
		tableBox.getChildren().addAll(fullTravelTable, addDeleteBox);
		
		searchField.setMinWidth(SizeHandler.SEARCH_FIELD_WIDTH);
		
		gridPane.add(searchField, 0, 0, 2, 1);
		gridPane.add(searchBtn, 3, 0);
		gridPane.add(openCashBookBtn, 4, 0);
		gridPane.add(tableBox, 0, 1, 5, 3);
		
		return gridPane;
	}

	private void createPostingTalbes() {
		//
		fullTravelTable = TableManager.shareInstance().requestFullTravelTable();
		fullTravelTable.setOnMouseClicked(e-> {
			TravelDto currentTravel = fullTravelTable.getSelectionModel().getSelectedItem();
			Session.shareInstance().setCurrentTravel(currentTravel);
		});
	}

	private void setButtonsAction() {
		// Search Travel
		searchBtn.setOnAction(e -> {
			String travelName = searchField.getText();
			List<TravelDto> travelFounds = null;
			if (!travelName.isEmpty()) {
				travelFounds = travelController.searchByName(travelName);
			}
			getList(travelFounds);
			searchField.setText("");
		});
		
		// Open CashBook Window
		openCashBookBtn.setOnAction(e -> {
			String travelId = Session.shareInstance().getCurrentTravel().getTravelId();
			CashBookDto cashBookFound = null;
			if ( travelId != null) {
				cashBookFound = cashBookController.findByTravelId(travelId);
				Session.shareInstance().setCurrentCashBook(cashBookFound);
			}
			CashBookWindow cashBookWindow = WindowLycler.shareInstance().requestCashBookWindow();
			cashBookWindow.display();
			
		});
		
		// Add Travel
		addTravelBtn.setOnAction(e -> {
			String title = travelTitleField.getText();
			String leader = leaderField.getText();
			String startDate = startDateField.getText();
			String endDate = endDateField.getText();
			String participants = participantsField.getText();
			String memo = memoField.getText();
			
			TravelDto newTravel = TableManager.shareInstance().addTravelItem(title, leader, startDate, endDate, participants, memo);
			travelController.register(newTravel);
			initializeField();
			refreshTable();
		});
		
		// Delete Travel
		delTravelBtn.setOnAction(e -> {
			TravelDto targetTravel = fullTravelTable.getSelectionModel().getSelectedItem();
			travelController.delete(targetTravel);
            
            refreshTable();
		});
	}

	private void getList(List<TravelDto> travelFounds) {
		//
		TableManager.shareInstance().getTravelFounds(travelFounds);		
	}

	private void refreshTable() {
		TravelClubDto currentClub = Session.shareInstance().getCurrentClub();
		TableManager.shareInstance().refreshFullTravelTable(currentClub.getUsid());
	}
	
	private void initializeField() {
		// 
		travelTitleField.clear();
		leaderField.clear();
		startDateField.clear();
		endDateField.clear();
		participantsField.clear();
		memoField.clear();
	}
	
	private void settingFields() {
		travelTitleField.setPromptText("ooo's ooo travel");
		leaderField.setPromptText("example@mail.com");
		startDateField.setPromptText("2018-01-01");
		endDateField.setPromptText("2018-01-31");
		participantsField.setPromptText("3");
		memoField.setPromptText("memo");
	}

	public void loadTravelData(TravelClubDto selectedClub) {
		// Refresh table
		TableManager.shareInstance().refreshFullTravelTable(selectedClub.getUsid());
	}
}
