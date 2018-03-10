package javastory.ui.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.PostingController;
import javastory.util.SizeHandler;

public class PostingWindow {
	//
	private Stage window;
	private PostingController postingController;
	private TextField titleField, writerField;
	private PasswordField passwordField;
	private TextArea contentArea;
	private Label header, titleLabel, writerLabel, passwordLabel, contentLabel;
	private Button registerBtn, modifyBtn, deleteBtn, cancelBtn;

	public PostingWindow() {
		//
		postingController = ClubControllerLycler.shareInstance().requestPostingController();
		// Creating textFields
		titleField = new TextField();
		writerField = new TextField();
		passwordField = new PasswordField();
		contentArea = new TextArea();
		// Setting textArea
		contentArea.setPrefRowCount(10);
		contentArea.setPrefColumnCount(50);
		contentArea.setWrapText(true);
		
		// Creating labels
		header = new Label("New Posting");
		titleLabel = new Label("• title:");
		writerLabel = new Label("• writer-email:");
		passwordLabel = new Label("• password:");
		contentLabel = new Label("• content:");

		// Creating buttons
		registerBtn = new Button("register");
		modifyBtn = new Button("modify");
		deleteBtn = new Button("delete");
		cancelBtn = new Button("cancel");
	}

	public void display() {
		this.setWindow();
	}

	private void setWindow() {
		// Setting window properties
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		String title = new String(this.getClass().getSimpleName());
		window.setTitle(title);

		// Creating VBox for label
		VBox labelBox = new VBox();
		labelBox.getChildren().addAll(titleLabel, writerLabel, passwordLabel, contentLabel);
		labelBox.setSpacing(20);
		labelBox.setPadding(new Insets(30,0,0,0));
		labelBox.setAlignment(Pos.TOP_RIGHT);

		// Creating VBox for field
		VBox fieldBox = new VBox();
		fieldBox.getChildren().addAll(header, titleField, writerField, passwordField, contentArea);
		fieldBox.setSpacing(10);

		// Creating HBox
		HBox hBox = new HBox();
		hBox.getChildren().addAll(labelBox, fieldBox);
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.setStyle("-fx-background-color: white;");
		
		// Creating HBox for button
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(registerBtn, modifyBtn, deleteBtn, cancelBtn);
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setStyle("-fx-background-color: white;");
		
		//Creating VBox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hBox, buttonBox);
		vBox.setSpacing(10);
		vBox.setStyle("-fx-background-color: white;");
		vBox.setPadding(new Insets(10,10,10,10));
		vBox.setAlignment(Pos.CENTER);
		vBox.setMinSize(SizeHandler.POPUP_SCREEN_WIDTH, SizeHandler.POPUP_SCREEN_HEIGHT);
		
		setButtonsAction();
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}

	private void setButtonsAction() {
		// 
		registerBtn.setOnAction(e -> {
			String title = titleField.getText();
			String writerEmail = writerField.getText();
			String password = passwordField.getText();
			String content = contentArea.getText();
			
			postingController.register(title, writerEmail, password, content);
			window.close();
		});
		
		modifyBtn.setOnAction(e -> {
			
		});
		
		deleteBtn.setOnAction(e -> {
			
		});
		
		cancelBtn.setOnAction(e -> {
			window.close();
		});
	}
}
