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
import javastory.budget.service.dto.TravelDto;
import javastory.fx.controller.TravelController;
import javastory.fx.window.TravelWindow;

public class TravelView {
	//
	private TravelController travelController;
	private TravelWindow travelWindow;
	private TextField inputText;
	private Button searchButton, newButton;
	private ListView<TravelDto> listView;
	
	public TravelView() {
		//
		travelController = new TravelController();
		travelWindow = new TravelWindow("TravelWindow.", travelController);
	}
	
	public GridPane addGridPane() {
		GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 0, 0, 40));

	    // 
	    Text title = new Text("Travel");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 4, 1); 

	    // 
	    inputText = new TextField();
	    inputText.setFont(Font.font("Arial", 10));
	    inputText.setMinWidth(300);
	    grid.add(inputText, 1, 2, 4, 1);

	    // 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchTravel());
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

	private void searchTravel() {
		// 
		String travelName = inputText.getText();
		List<TravelDto> travelFounds = null;
		if (!travelName.isEmpty()) {
			travelFounds = travelController.searchByName(travelName);
		}
		// Get list
		getList(travelFounds);
		inputText.setText("");
	}
	
	private void getList(List<TravelDto> travelDtos) {
		//
		if (travelDtos != null) {
			listView.getItems().clear();
			Iterator<TravelDto> travelIter = travelDtos.iterator();
			while (travelIter.hasNext()) {
				listView.getItems().add(travelIter.next());
			}
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<TravelDto> listIter = travelController.findAll().iterator();
			while (listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		travelWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(TravelDto targetTravel) {
		//
		travelWindow.modifyMode(targetTravel);
		getList(null);
	}
}