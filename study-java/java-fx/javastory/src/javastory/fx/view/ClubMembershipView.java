package javastory.fx.view;

import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javastory.club.stage3.step4.service.dto.ClubMembershipDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.console.ClubMembershipConsole;
import javastory.fx.window.ClubMembershipWindow;

public class ClubMembershipView {
	//
	private ClubMembershipConsole membershipConsole;
	private ClubMembershipWindow membershipWindow;
	private TextField inputText;
	private Button searchButton, newButton;
	private ComboBox<String> clubBox;
	private ListView<ClubMembershipDto> listView;
	
	public ClubMembershipView() {
		//
		membershipConsole = new	ClubMembershipConsole();
		membershipWindow = new ClubMembershipWindow("ClubMembership window", membershipConsole);
		clubBox = new ComboBox<>();
	}
	
	public GridPane addGridPane() {
		GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 0, 0, 40));

	    //
	    clubBox.setPromptText("Select club to find membership");
	    this.getClubItems(clubBox);
	    this.checkBoxValue(clubBox);
	    grid.add(clubBox, 1, 1);
	    
	    // 
	    Text title = new Text("ClubMembership");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 3, 1); 
	    
	    // 
	    inputText = new TextField();
	    inputText.setFont(Font.font("Arial", 10));
	    inputText.setMinWidth(200);
	    grid.add(inputText, 1, 2, 4, 1);

	    // 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchMembership());
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
	
	private void checkBoxValue(ComboBox<String> clubBox) {
		//
		clubBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->{
			TravelClubDto targetClub = membershipConsole.findAndGetClub(newValue);
			membershipConsole.setCurrentClub(targetClub);
		});
	}
	
	private void getClubItems(ComboBox<String> clubBox) {
		// 
		List<TravelClubDto> clubs = membershipConsole.findAllClub();
		for (TravelClubDto club: clubs) {
			clubBox.getItems().add(club.getName());
		}
	}

	private void searchMembership() {
		//
		String memberEmail = inputText.getText();
		ClubMembershipDto membershipFound = null;
		
		if (!memberEmail.isEmpty()) {
			membershipFound = membershipConsole.searchByEmail(memberEmail);
		}
		// Get list
		getList(membershipFound);
		inputText.setText("");
	}
	
	private void getList(ClubMembershipDto membershipDto) {
		//
		if (membershipDto != null) {
			listView.getItems().clear();
			listView.getItems().add(membershipDto);
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			String clubId = membershipConsole.getCurrentClub().getUsid();
			Iterator<ClubMembershipDto> listIter = membershipConsole.findAllMembershipsIn(clubId).iterator();
			while(listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		membershipWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(ClubMembershipDto targetMembership) {
		//
		membershipWindow.modifyMode(targetMembership);
		getList(null);
	}
}