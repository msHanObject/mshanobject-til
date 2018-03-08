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
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.ui.console.MemberConsole;
import javastory.fx.window.MemberWindow;

public class MemberView {
	//
	private MemberConsole memberConsole;
	private MemberWindow memberWindow;
	private TextField inputText;
	private Button searchButton;
	private Button newButton;
	private ListView<MemberDto> listView;
	
	public MemberView() {
		//
		memberConsole = new MemberConsole();
		memberWindow = new MemberWindow("Member window.", memberConsole);
	}
	
	public GridPane addGridPane() {
		GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #FFFFFF;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 0, 0, 40));

	    // 
	    Text title = new Text("CommunityMember");
	    title.setFont(Font.font("Arial", 20));
	    grid.add(title, 2, 1, 4, 1); 

	    // 
	    inputText = new TextField();
	    inputText.setFont(Font.font("Arial", 10));
	    inputText.setMinWidth(300);
	    grid.add(inputText, 1, 2, 4, 1);

	    // 
	    searchButton = new Button("Search");
	    searchButton.setOnAction(e -> searchMember());
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
	
	private void searchMember() {
		//
		String memberEmail = inputText.getText();
		MemberDto memberFound = null;
		if (!memberEmail.isEmpty()) {
			memberFound = memberConsole.searchByEmail(memberEmail);
		}
		// Get list
		getList(memberFound);
		inputText.setText("");
	}
	
	private void getList(MemberDto memberDto) {
		//
		if (memberDto != null) {
			listView.getItems().clear();
			listView.getItems().add(memberDto);
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			listView.getItems().clear();
			Iterator<MemberDto> listIter = memberConsole.findAll().iterator();
			while(listIter.hasNext()) {
				listView.getItems().add(listIter.next());
			}
		}
	}
	
	private void callRegisterMode() {
		//
		memberWindow.registerMode();
		getList(null);
	}
	
	private void callModifyMode(MemberDto targetMember) {
		//
		memberWindow.modifyMode(targetMember);
		getList(null);
	}
}