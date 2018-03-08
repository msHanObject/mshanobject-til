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
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.console.ClubConsole;
import javastory.fx.window.ClubWindow;

public class TravelClubView {
	//
	private ClubConsole clubConsole;
	private ClubWindow clubWindow;
	private TextField nameInput;
	private Button searchButton;
	private Button newButton;
	private ListView<TravelClubDto> listView;
	
	public TravelClubView() {
		//
		clubConsole = new ClubConsole();
		clubWindow = new ClubWindow("Club window.",clubConsole);
	}
	
	public GridPane addGridPane() {
	    GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 0, 0, 40));

	    // 
	    Text title = new Text("TravelClub");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 4, 1); 

	    // 
	    nameInput = new TextField();
	    nameInput.setFont(Font.font("Arial", 10));
	    nameInput.setMinWidth(300);
	    grid.add(nameInput, 1, 2, 4, 1);

	    // 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchClub());
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
	
	private void searchClub() {
		//
		String clubName = nameInput.getText();
		TravelClubDto clubFound = null;
		if (!clubName.isEmpty()) {
			clubFound = clubConsole.searchByName(clubName);
		}
		//Get List
		getList(clubFound);
		nameInput.setText("");
	}
	
	private void getList(TravelClubDto clubDto) {
		//
		if (clubDto != null) {
			listView.getItems().clear();
			listView.getItems().add(clubDto);
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<TravelClubDto> listIter = clubConsole.findAll().iterator();
			while (listIter.hasNext()) {			
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		clubWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(TravelClubDto targetClub) {
		//
		clubWindow.modifyMode(targetClub);
		getList(null);
	}	
}