package javastory.fx.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.stage3.step4.service.dto.PostingDto;
import javastory.club.stage3.step4.ui.console.PostingConsole;
import javastory.fx.util.AlertBox;
import javastory.fx.util.ConfirmBox;

public class PostingWindow {
	//
	private String wTitle;
	private TextField titleInput, idInput, contentInput;
	private PasswordField pwInput;
	private Button register, modify, delete, cancel;
	private PostingConsole postingConsole;
	private PostingDto currentPosting;
	
	public PostingWindow(String wTitle, PostingConsole postingConsole) {
		//
		this.wTitle = wTitle;
		this.postingConsole = postingConsole;
		this.currentPosting = new PostingDto();
		titleInput = new TextField();
		idInput = new TextField();
		pwInput = new PasswordField();
		contentInput = new TextField();
		register = new Button("register");
		modify = new Button("modify");
		delete = new Button("delete");
		cancel = new Button("cancel");
	}
	
	private void init() {
		//
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(wTitle);
		window.setMinWidth(300);
		
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setAlignment(Pos.CENTER);
		
		//HeadLine
		Text headLine = new Text("Posting");
		headLine.setFont(Font.font("Arial", 20));
	    grid.add(headLine, 1, 0, 2, 2); 
		
		//Question 1 label
		Label q1 = new Label();
		q1.setText("• title: ");
		q1.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q1, 0, 3);
		
		//Question 1 input
		titleInput.setMaxWidth(140);
		grid.add(titleInput, 1, 3, 2, 1);
		
		//Question 2 label
		Label q2 = new Label();
		q2.setText("• writer-email: ");
		q2.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q2, 0, 5);
		
		//Question 2 input
		grid.add(idInput, 1, 5);
		
		//Question 3 label
		Label q3 = new Label();
		q3.setText("• password: ");
		q3.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q3, 2, 5);
		
		//Question 3 input
		grid.add(pwInput, 3, 5);
		
		//Question 4 label
		Label q4 = new Label();
		q4.setText("• content: ");
		q4.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q4, 0, 7);
		
		//Question 4 input
		contentInput.setMinHeight(200);
		grid.add(contentInput, 1, 7, 3, 3);
	
		//register button
		grid.add(register, 0, 12);
		register.setOnAction(e -> {
			String title = titleInput.getText();
			String writerEmail = idInput.getText();
			String password = pwInput.getText();
			if (password == null) {
				password = "`?blank?";
			}
			String content = contentInput.getText();
			postingConsole.register(title, writerEmail, password, content);
			window.close();
		});
		
		//modify button
		grid.add(modify, 1, 12);
		modify.setOnAction(e -> {
			// Check if it has changed.
			if (!titleInput.getText().equals(currentPosting.getTitle())) {
				currentPosting.setTitle(titleInput.getText());
			}
			if (!contentInput.getText().equals(currentPosting.getContents())) {
				currentPosting.setContents(contentInput.getText());
			}
			// Check password.
			String pw = pwInput.getText(); 
			if (pw == null) {
				pw = "`?blank?";
			}
			if (pw.equals(currentPosting.getPassword())) {				
				postingConsole.update(currentPosting);
			} else {
				AlertBox.display("Password Alert!", "You have entered the wrong password!");
				System.out.println(currentPosting.getPassword());
			}
			window.close();
		});
		
		//delete button
		grid.add(delete, 2, 12);
		delete.setOnAction(e -> {
			boolean result = ConfirmBox.display("Remove Posting", "Are you sure you want to remove this membership?");
			if (result) {
				postingConsole.delete(currentPosting);
				window.close();
			}
		});
		
		//cancel button
		grid.add(cancel, 3, 12);
		cancel.setOnAction(e -> {
			window.close();
		});
		
		Scene scene = new Scene(grid, 600, 600);
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

	public void modifyMode(PostingDto targetPosting) {
		// 
		this.fieldClear();
		register.setDisable(true);
		titleInput.setText(targetPosting.getTitle());
		idInput.setText(targetPosting.getWriterEmail());
		idInput.setDisable(true);
		pwInput.setText(null);
		contentInput.setText(targetPosting.getContents());
		currentPosting = targetPosting;
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		titleInput.setText(null);
		idInput.setText(null);
		contentInput.setText(null);
		idInput.setDisable(false);
	}
}