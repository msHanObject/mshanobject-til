package javastory.fx.window;

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
import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.step4.service.dto.ClubMembershipDto;
import javastory.club.stage3.step4.ui.console.ClubMembershipConsole;
import javastory.fx.util.ConfirmBox;

public class ClubMembershipWindow {
	//
	private String title;
	private TextField q1Input;
	private ComboBox<String> q2Box;
	private Button register, modify, delete, cancel;
	private ClubMembershipConsole membershipConsole;
	private ClubMembershipDto currentMemberShip;
	
	public ClubMembershipWindow(String title, ClubMembershipConsole membershipConsole) {
		//
		this.title = title;
		this.membershipConsole = membershipConsole;
		this.currentMemberShip = new ClubMembershipDto();
		q1Input = new TextField();
		q2Box = new ComboBox<>();
		register = new Button("register");
		modify = new Button("modify");
		delete = new Button("delete");
		cancel = new Button("cancel");
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
		Text headLine = new Text("ClubMembership");
		headLine.setFont(Font.font("Arial", 20));
	    grid.add(headLine, 1, 0, 2, 2); 
		
		//Question 1 label
		Label q1 = new Label();
		q1.setText("• email: ");
		q1.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q1, 0, 3);
		
		//Question 1 input
		q1Input.setMaxWidth(140);
		grid.add(q1Input, 1, 3, 2, 1);
		
		//Question 2 label
		Label q2 = new Label();
		q2.setText("• memberRole: ");
		q2.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q2, 0, 5);
		
		//Question 2 comboBox
		this.getRoleItems(q2Box);
		this.checkBoxValue(q2Box);
		grid.add(q2Box, 1, 5, 2, 1);
	
		//register button
		grid.add(register, 0, 7);
		register.setOnAction(e -> {
			String email = q1Input.getText();
			membershipConsole.register(email, currentMemberShip.getRole());
			modify.setDisable(false);
			delete.setDisable(false);
			window.close();
		});
		
		//modify button
		grid.add(modify, 1, 7);
		modify.setOnAction(e -> {
			membershipConsole.update(currentMemberShip);
			register.setDisable(false);
			window.close();
		});
		
		//delete button
		grid.add(delete, 2, 7);
		delete.setOnAction(e -> {
			boolean result = ConfirmBox.display("Remove ClubMemberShip", "Are you sure you want to remove this membership?");
			if (result) {
				membershipConsole.delete(currentMemberShip);
				register.setDisable(false);
				window.close();
			}
		});
		
		//cancel button
		grid.add(cancel, 3, 7);
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
	
	private void checkBoxValue(ComboBox<String> roleBox) {
		// 
		roleBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->{
			currentMemberShip.setRole(RoleInClub.valueOf(newValue));
		});
	}

	private void getRoleItems(ComboBox<String> roleBox) {
		// 
		if (!roleBox.getItems().isEmpty()) {
			return;
		}
		
		for (RoleInClub role : RoleInClub.values()) {
			roleBox.getItems().add(role.name());
		}
	}

	public void registerMode() {
		//
		this.fieldClear();
		modify.setDisable(true);
		delete.setDisable(true);
		init();
	}
	
	public void modifyMode(ClubMembershipDto targetMembership) {
		//
		this.fieldClear();
		register.setDisable(true);
		q1Input.setText(targetMembership.getMemberEmail());
		q2Box.setValue(targetMembership.getRole().name());
		currentMemberShip = membershipConsole.searchByEmail(targetMembership.getMemberEmail());
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		q1Input.setText(null);
		q2Box.setValue(null);
	}
}