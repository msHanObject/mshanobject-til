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
import javastory.club.stage3.step4.service.dto.BoardDto;
import javastory.club.stage3.step4.service.dto.PostingDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.console.ClubConsole;
import javastory.club.stage3.step4.ui.console.PostingConsole;
import javastory.fx.window.PostingWindow;

public class PostingView {
	//
	private ClubConsole clubConsole;
	private PostingConsole postingConsole;
	private PostingWindow postingWindow;
	private TextField inputText;
	private Button searchButton, newButton;
	private ComboBox<String> clubBox;
	private ListView<PostingDto> listView;
	
	public PostingView() {
		//
		clubConsole = new ClubConsole();
		postingConsole = new	PostingConsole();
		postingWindow = new PostingWindow("Posting window", postingConsole);
		clubBox = new ComboBox<>();
	}
	
	public GridPane addGridPane() {
		GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 0, 0, 40));

	    //
	    clubBox.setPromptText("Select club to find board");
	    this.getClubItems(clubBox);
	    this.checkBoxValue(clubBox);
	    grid.add(clubBox, 1, 1);
	    
	    // 
	    Text title = new Text("Posting");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 4, 1); 
	    
	    // 
	    inputText = new TextField();
	    inputText.setFont(Font.font("Arial", 10));
	    inputText.setMinWidth(300);
	    grid.add(inputText, 1, 2, 4, 1);

	    // 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchPosting());
	    grid.add(searchButton, 5, 2);

	    // 
	    newButton = new Button("New");
	    newButton.setOnAction(e -> callRegisterMode());
	    grid.add(newButton, 6, 2); 
	    
	    // 
	    listView = new ListView<>();
	    listView.setMinWidth(300);
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
			BoardDto boardFound = postingConsole.findBoardByClubName(newValue);
			postingConsole.setCurrentBoard(boardFound);
		});
	}
	
	private void getClubItems(ComboBox<String> clubBox) {
		// 
		List<TravelClubDto> clubs = clubConsole.findAll();
		for (TravelClubDto club: clubs) {
			clubBox.getItems().add(club.getName());
		}
	}

	private void searchPosting() {
		//
		String postingId = inputText.getText();
		PostingDto postingFound = null;
		
		if (!postingId.isEmpty()) {
			postingFound = postingConsole.searchOne(postingId);
		}
		// Get list
		getList(postingFound);
		inputText.setText("");
	}
	
	private void getList(PostingDto postingDto) {
		//
		if (postingDto != null) {
			listView.getItems().clear();
			listView.getItems().add(postingDto);
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<PostingDto> listIter = postingConsole.getPostings().iterator();
			while(listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		postingWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(PostingDto targetPosting) {
		//
		postingWindow.modifyMode(targetPosting);
		getList(null);
	}
}