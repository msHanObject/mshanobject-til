package javastory.ui.window;

import java.util.Locale;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.MemberController;
import javastory.util.DatePickerUtil;
import javastory.util.SizeHandler;

public class SignupWindow {
	//
	private Stage window;
	private MemberController memberController;

	public SignupWindow() {
		Locale.setDefault(Locale.KOREA);
		memberController = ClubControllerLycler.shareInstance().requestMemberController();
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

		// Creating label for header
		Label header = new Label("Join");

		// Creating label for email
		Label emailLabel = new Label("• email: ");

		// Creating email textField
		TextField emailField = new TextField();
		emailField.setPromptText("E-mail to be used as an Id");

		// Creating label for name
		Label nameLabel = new Label("• name: ");

		// Creating name textField
		TextField nameField = new TextField();
		nameField.setPromptText("User name");

		// Creating label for nickname
		Label nicknameLabel = new Label("• nickname: ");

		// Creating nickname textField
		TextField nickNameField = new TextField();
		nickNameField.setPromptText("Nickname");

		// Creating label for phone number
		Label phoneLabel = new Label("• phone: ");

		// Creating phone number textField
		TextField phoneField = new TextField();
		phoneField.setPromptText("01012341234");

		// Creating label for birthDay
		Label birthDayLabel = new Label("• birthDay: ");
		
		// Creating Tooltip for datepicker
		Tooltip tooltip = new Tooltip("Select your Birthday");

		// Creating Datepicker for birthDay
		DatePicker datePicker = DatePickerUtil.shareInstance().requestDatePicker();
		datePicker.setTooltip(tooltip);

		// Creating buttons
		Button cancelButton = new Button("cancel");
		Button joinButton = new Button("join");
		
		//Setting onAction for buttons
		cancelButton.setOnAction(e -> {
			window.close();
			WindowLycler.shareInstance().requestLoginWindow().display();
		});
		
		joinButton.setOnAction(e -> {
			//Register member information
			memberController.register(
					emailField.getText(), nameField.getText(),
					nickNameField.getText(), phoneField.getText(), datePicker.getValue().toString());
			//Back to the login window
			window.close();
			WindowLycler.shareInstance().requestLoginWindow().display();
		});

		// Creating headerBox
		HBox headerBox = new HBox();
		headerBox.getChildren().addAll(new Region(),header, new Region());
		
		// Creating labelBox
		VBox labelBox = new VBox();
		labelBox.getChildren().addAll(emailLabel, nameLabel, nicknameLabel, phoneLabel, birthDayLabel);
		
		// Creating buttonBox
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(cancelButton, joinButton);

		// Creating fieldBox
		VBox fieldBox = new VBox();
		fieldBox.getChildren().addAll(emailField, nameField, nickNameField, phoneField, datePicker, buttonBox);
		fieldBox.setAlignment(Pos.CENTER);
		
		// Creating GridPane
		GridPane gridPane = new GridPane();
		
		// Add to GridPane
		gridPane.add(headerBox, 1, 0, 3, 1);
		gridPane.add(labelBox, 0, 1, 1, 5);
		gridPane.add(fieldBox, 3, 1, 2, 5);
		gridPane.add(buttonBox, 3, 6, 2, 1);

		// Styling nodes
		joinButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		cancelButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		gridPane.setStyle("-fx-background-color: white;");
		
		// Aligning nodes
		headerBox.setAlignment(Pos.CENTER);
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		fieldBox.setAlignment(Pos.CENTER_LEFT);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		gridPane.setAlignment(Pos.CENTER);
		
		// Set a gap and space
		gridPane.setVgap(5);
		labelBox.setSpacing(15);
		fieldBox.setSpacing(5);
		buttonBox.setSpacing(10);
		
		//Setting size for the pane 
	    gridPane.setMinSize(SizeHandler.POPUP_SCREEN_WIDTH, SizeHandler.POPUP_SCREEN_HEIGHT);

		// Creating a scene object
		Scene scene = new Scene(gridPane);

		// Adding scene to the stage
		window.setScene(scene);

		// Displaying the contents of the stage
		window.show();
	}
}
