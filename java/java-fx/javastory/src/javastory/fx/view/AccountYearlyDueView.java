package javastory.fx.view;

import java.util.Iterator;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javastory.budget.service.dto.YearlyDueDto;
import javastory.fx.controller.YearlyDueController;
import javastory.fx.window.YearlyDueWindow;

public class AccountYearlyDueView {
	//
	private YearlyDueController yearlyDueConsole;
	private YearlyDueWindow yearlyDueWindow;
	private TextField inputText;
	private Button searchButton;
	private Button newButton;
	private ListView<YearlyDueDto> listView;
	
	public AccountYearlyDueView() {
		//
		yearlyDueConsole = new YearlyDueController();
		yearlyDueWindow = new YearlyDueWindow("YearlyDue window", yearlyDueConsole);
	}
	
	public GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,0,0,40));
		
		//Title
		Text title = new Text("AccountYearlyDue");
		title.setFont(Font.font("Arial", 20));
		grid.add(title,  2, 1, 4, 1);
		
		//
		inputText = new TextField();
		inputText.setFont(Font.font("Arial", 10));
		grid.add(inputText, 1, 2, 4, 1);
		
		// 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchYearlyDue());
	    grid.add(searchButton, 5, 2);

	    // 
	    newButton = new Button("New");
	    newButton.setOnAction(e -> callRegisterMode());
	    grid.add(newButton, 6, 2); 
	    
	    // 
	    listView = new ListView<>();
	    listView.setOnMouseClicked(e -> {
	    		if (e.getClickCount() == 2) {
	    			callModifyMode(listView.getSelectionModel().getSelectedItem());
	    		}
	    });
	    grid.add(listView, 1, 3, 4, 1);
	    
	    //empty
	    Text empty = new Text("");
	    grid.add(empty, 7, 0, 1, 6);

	    return grid;
	}

	private void searchYearlyDue() {
		// 
		String year = inputText.getText();
		YearlyDueDto yearlyDueFound = null;
		if (!year.isEmpty()) {
			yearlyDueFound = yearlyDueConsole.find(year);
		}
		//Get list
		getList(yearlyDueFound);
		inputText.setText("");
	}

	private void getList(YearlyDueDto yearlyDueFound) {
		// 
		if (yearlyDueFound != null) {
			listView.getItems().clear();
			listView.getItems().add(yearlyDueFound);
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<YearlyDueDto> listIter = yearlyDueConsole.findAll().iterator();
			while (listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		yearlyDueWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(YearlyDueDto targetYaerlyDue) {
		//
		yearlyDueWindow.modifyMode(targetYaerlyDue);
		getList(null);
	}
}