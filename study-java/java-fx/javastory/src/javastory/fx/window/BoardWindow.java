package javastory.fx.window;

import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.stage3.step4.service.dto.BoardDto;
import javastory.club.stage3.step4.service.dto.ClubMembershipDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.console.BoardConsole;
import javastory.club.stage3.step4.ui.console.ClubConsole;
import javastory.club.stage3.step4.ui.console.ClubMembershipConsole;
import javastory.fx.util.ConfirmBox;

public class BoardWindow {
	//
	private String title;
	private TextField q2Input;
	private ComboBox<String> q1Box, q3Box;
	private Button register, modify, delete, cancel;
	private BoardConsole boardConsole;
	private ClubConsole clubConsole;
	private ClubMembershipConsole membershipConsole;
	private BoardDto currentBoard;

	public BoardWindow(String title, BoardConsole boardConsole) {
		// 
		this.title = title;
		this.boardConsole = boardConsole;
		this.clubConsole = new ClubConsole();
		this.membershipConsole = new ClubMembershipConsole();
		this.q2Input = new TextField();
		this.q1Box = new ComboBox<>();
		this.q3Box = new ComboBox<>();
		this.register = new Button("register");
		this.modify = new Button("modify");
		this.delete = new Button("delete");
		this.cancel = new Button("cancel");
	}
	
	private void init() {
		//
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(0,0,0,0));
		grid.setAlignment(Pos.CENTER);
		
		//HeadLine
		Text headLine = new Text("SocialBoard");
		headLine.setFont(Font.font("Arial", 20));
	    grid.add(headLine, 1, 0, 2, 2); 
		
		//Question 1 label
		Label q1 = new Label();
		q1.setText("• club: ");
		q1.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q1, 0, 3);
		
		//Question 1 input
		q1Box.setPromptText("Select club for connect!");
		this.getClubItems(q1Box);
		this.checkClubBoxValue(q1Box);
		grid.add(q1Box, 1, 3, 2, 1);
		
		//Question 2 label
		Label q2 = new Label();
		q2.setText("• name: ");
		q2.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q2, 0, 5);
		
		//Question 2 input
		q2Input.setMaxWidth(140);
		grid.add(q2Input, 1, 5, 2, 1);
		
		//Question 3 label
		Label q3 = new Label();
		q3.setText("• admin-email: ");
		q3.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q3, 0, 7);
		
		//Question 3 input
		q3Box.setMaxWidth(140);
		q3Box.setPromptText("Select admin-email of membership!");
		this.checkMembershipBoxValue(q3Box);
		grid.add(q3Box, 1, 7, 2, 1);
	
		//register button
		grid.add(register, 0, 9);
		register.setOnAction(e -> {
			boardConsole.register(q1Box.getValue(), q2Input.getText(), q3Box.getValue());
			modify.setDisable(false);
			delete.setDisable(false);
			window.close();
		});
		
		//modify button
		grid.add(modify, 1, 9);
		modify.setOnAction(e -> {
			currentBoard.setName(q2Input.getText());
			currentBoard.setAdminEmail(q3Box.getValue());//
			boardConsole.modify(currentBoard);
			register.setDisable(false);
			window.close();
		});
		
		//delete button
		grid.add(delete, 2, 9);
		delete.setOnAction(e -> {
			boolean result = ConfirmBox.display("Remove Board", "Are you sure you want to remove this board?");
			if (result) {
				boardConsole.remove(currentBoard);
				register.setDisable(false);
				window.close();
			}
		});
		
		//cancel button
		grid.add(cancel, 3, 9);
		cancel.setOnAction(e -> {
			register.setDisable(false);
			modify.setDisable(false);
			delete.setDisable(false);
			window.close();
		});
		
		Scene scene = new Scene(grid, 500, 500);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private void checkMembershipBoxValue(ComboBox<String> membershipBox) {
		// 
		membershipBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue != null && currentBoard != null) {
				currentBoard.setAdminEmail(newValue);				
			}
		});
	}

	private void getMembershipItems(ComboBox<String> membershipBox, String clubId) {
		//
		q3Box.getItems().clear();
		if (!clubId.isEmpty()) {
			List<ClubMembershipDto> membershipList = membershipConsole.findAllMembershipsIn(clubId);
			Iterator<ClubMembershipDto> membershipIter = membershipList.iterator();
			while (membershipIter.hasNext()) {
				membershipBox.getItems().add(membershipIter.next().getMemberEmail());
			}
		}
	}

	private void checkClubBoxValue(ComboBox<String> clubBox) {
		// 
		clubBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue == null) {
				currentBoard = null;
				return;
			}
			String clubId = clubConsole.searchByName(newValue).getUsid();
			
			this.getMembershipItems(q3Box, clubId);
		});
	}
	
	public void getClubItems(ComboBox<String> box) {
		//
		if (box == null) {
			
			System.out.println(box);
		}
		List<TravelClubDto> clubList = clubConsole.findAll();
		for (TravelClubDto club : clubList) {			
			box.getItems().add(club.getName());
		}
	}
	
	public void registerMode() {
		//
		this.fieldClear();
		modify.setDisable(true);
		delete.setDisable(true);
		init();
	}

	public void modifyMode(BoardDto targetBoard) {
		// 
		this.fieldClear();
		currentBoard = targetBoard;
		register.setDisable(true);
		TravelClubDto clubFound = clubConsole.serachById(targetBoard.getClubId());
		q1Box.setValue(clubFound.getName());
		q2Input.setText(targetBoard.getName());
		q3Box.setValue(targetBoard.getAdminEmail());;
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		q1Box.getItems().clear();
		q1Box.setValue(null);
		q1Box.setPromptText("Select club for connect!");
		q2Input.setText(null);
		q3Box.getItems().clear();
		q3Box.setValue(null);
		q3Box.setPromptText("Select admin-email of membership!");
	}
}