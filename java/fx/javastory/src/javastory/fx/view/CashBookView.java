package javastory.fx.view;

import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javastory.budget.service.dto.CashBookDto;
import javastory.fx.controller.CashBookController;
import javastory.fx.window.CashBookWindow;

public class CashBookView {
	//
	private CashBookController cashBookController;
	private CashBookWindow cashBookWindow;
	private TextField inputText;
	private Button searchButton, newButton;
	private ListView<CashBookDto> listView;
	
	public CashBookView() {
		//
		cashBookController = new CashBookController();
		cashBookWindow = new CashBookWindow("CashBookWindow", cashBookController);
	}
	
	public GridPane addGridPane() {
	    GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0,0,0,40));
	    
	    //
	    Text title = new Text("CashBook");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 4, 1);
	    
	    //
	    inputText = new TextField();
	    inputText.setFont(Font.font("Arial", 10));
	    inputText.setMinWidth(300);
	    grid.add(inputText, 1, 2, 4, 1);
	    
	    //
	    searchButton  = new Button("Search");
	    searchButton.setOnAction(e -> searchCashBook());
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
	
	private void searchCashBook() {
		//
		String cashBookName = inputText.getText();
		List<CashBookDto> cashBookFounds = null;
		if (!cashBookName.isEmpty()) {
			cashBookFounds = cashBookController.searchByName(cashBookName);
		}
		// Get list
		getList(cashBookFounds);
		inputText.setText("");
	}
	
	private void getList(List<CashBookDto> cashBookDtos) {
		//
		if (cashBookDtos != null) {
			listView.getItems().clear();
			Iterator<CashBookDto> cashBookIter = cashBookDtos.iterator();
			while (cashBookIter.hasNext()) {
				listView.getItems().add(cashBookIter.next());
			}
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<CashBookDto> listIter = cashBookController.findAll().iterator();
			while (listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		cashBookWindow.reigsterMode();
		getList(null);
	}
	
	private void callModifyMode(CashBookDto targetCashBook) {
		//
		cashBookWindow.modifyMode(targetCashBook);
		getList(null);
	}
}