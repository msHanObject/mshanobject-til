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
import javastory.club.stage3.step4.service.dto.BoardDto;
import javastory.club.stage3.step4.ui.console.BoardConsole;
import javastory.fx.window.BoardWindow;

public class BoardView {
	//
	private BoardConsole boardConsole;
	private BoardWindow boardWindow;
	private TextField inputText;
	private Button searchButton;
	private Button newButton;
	private ListView<BoardDto> listView;
	
	public BoardView() {
		//
		boardConsole = new BoardConsole();
		boardWindow = new BoardWindow("Board window.", boardConsole);
	}
	
	public GridPane addGridPane() {
		GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 0, 0, 40));

	    // 
	    Text title = new Text("SocialBoard");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 4, 1); 

	    // 
	    inputText = new TextField();
	    inputText.setFont(Font.font("Arial", 10));
	    inputText.setMinWidth(300);
	    grid.add(inputText, 1, 2, 4, 1);

	    // 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchBoard());
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
	
	private void searchBoard() {
		//
		String boardName = inputText.getText();
		List<BoardDto> boardFounds = null;
		if (!boardName.isEmpty()) {
			boardFounds = boardConsole.searchByName(boardName);
		}
		// Get list
		getList(boardFounds);
		inputText.setText("");
	}
	
	private void getList(List<BoardDto> boardDtos) {
		//
		if (boardDtos != null) {
			listView.getItems().clear();
			Iterator<BoardDto> boardIter = boardDtos.iterator();
			while (boardIter.hasNext()) {
				listView.getItems().add(boardIter.next());
			}
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<BoardDto> listIter = boardConsole.findAll().iterator();
			while(listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		boardWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(BoardDto targetBoard) {
		//
		boardWindow.modifyMode(targetBoard);
		getList(null);
	}
}