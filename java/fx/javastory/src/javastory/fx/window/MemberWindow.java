package javastory.fx.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.ui.console.MemberConsole;
import javastory.fx.util.ConfirmBox;

public class MemberWindow {
	//
	private String title;
	private TextField q1Input, q2Input, q3Input, q4Input, q5Input;
	private Button register, modify, delete, cancel;
	private MemberConsole memberConsole;
	private MemberDto currentMember;
	
	public MemberWindow(String title, MemberConsole memberConsole) {
		//
		this.title = title;
		this.memberConsole = memberConsole;
		q1Input = new TextField();
		q2Input = new TextField();
		q3Input = new TextField();
		q4Input = new TextField();
		q5Input = new TextField();
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
		Text headLine = new Text("CommunityMember");
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
		q2.setText("• name: ");
		q2.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q2, 0, 5);
		
		//Question 2 input
		q2Input.setMaxWidth(140);
		grid.add(q2Input, 1, 5, 2, 1);
		
		//Question 3 label
		Label q3 = new Label();
		q3.setText("• phone number: ");
		q3.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q3, 0, 7);
		
		//Question 3 input
		q3Input.setMaxWidth(140);
		grid.add(q3Input, 1, 7, 2, 1);
		
		//Question 4 label
		Label q4 = new Label();
		q4.setText("• nick name: ");
		q4.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q4, 0, 9);
		
		//Question 4 input
		q4Input.setMaxWidth(140);
		grid.add(q4Input, 1, 9, 2, 1);
		
		//Question 5 label
		Label q5 = new Label();
		q5.setText("• birthday(yyyy.mm.dd)");
		q5.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q5, 0, 11);
		
		//Question 5 input
		q5Input.setMaxWidth(140);
		grid.add(q5Input, 1, 11, 2, 1);
	
		//register button
		grid.add(register, 0, 13);
		register.setOnAction(e -> {
			memberConsole.register(q1Input.getText(),
					q2Input.getText(), q3Input.getText(),
					q4Input.getText(), q5Input.getText());
			modify.setDisable(false);
			delete.setDisable(false);
			window.close();
		});
		
		//modify button
		grid.add(modify, 1, 13);
		modify.setOnAction(e -> {
			currentMember.setEmail(q1Input.getText());
			currentMember.setName(q2Input.getText());
			currentMember.setPhoneNumber(q3Input.getText());
			currentMember.setNickName(q4Input.getText());
			currentMember.setBirthDay(q5Input.getText());
			memberConsole.modify(currentMember);
			register.setDisable(false);
			window.close();
		});
		
		//delete button
		grid.add(delete, 2, 13);
		delete.setOnAction(e -> {
			boolean result = ConfirmBox.display("Remove Member", "Are you sure you want to remove this member?");
			if (result) {
				memberConsole.remove(currentMember);
				register.setDisable(false);
				window.close();
			}
		});
		
		//cancel button
		grid.add(cancel, 3, 13);
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
	
	public void registerMode() {
		//
		this.fieldClear();
		modify.setDisable(true);
		delete.setDisable(true);
		init();
	}
	
	public void modifyMode(MemberDto targetMember) {
		//
		this.fieldClear();
		register.setDisable(true);
		q1Input.setText(targetMember.getEmail());
		q2Input.setText(targetMember.getName());
		q3Input.setText(targetMember.getPhoneNumber());
		q4Input.setText(targetMember.getNickName());
		q5Input.setText(targetMember.getBirthDay());
		currentMember = memberConsole.searchByEmail(targetMember.getEmail());
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		q1Input.setText(null);
		q2Input.setText(null);
		q3Input.setText(null);
		q4Input.setText(null);
		q5Input.setText(null);
	}
}