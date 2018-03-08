package javastory.ui.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.service.dto.MemberDto;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.MemberController;
import javastory.club.util.exception.NoSuchMemberException;
import javastory.ui.view.FxStoryMainView;
import javastory.util.Session;
import javastory.util.SizeHandler;

public class LoginWindow {

	private Stage window;
	private MemberController memberController;

	public LoginWindow() {
		//
		memberController = ClubControllerLycler.shareInstance().requestMemberController();
	}

	public void display() {
		//
		this.setWindow();
	}

	private void setWindow() {
		//
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		String title = new String(this.getClass().getSimpleName());
		window.setTitle(title);

		// Creating Label for subtitle
		Label logInLabel = new Label("Log In...");

		// Creating Text Filed for email
		TextField emailField = new TextField();
		emailField.setPromptText("anonymous@gmail.com");

		// Creating Text Filed for password
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("••••••••••••");

		// Creating Buttons
		Button signInButton = new Button("Sign in");
		Button signUpButton = new Button("Sign up");

		// Setting onAction for Buttons
		signInButton.setOnAction(e -> {
			// login and window.close and set ParentWindow's center
			String targetId = emailField.getText();
			String targetPw = passwordField.getText();
			boolean loginCheck = false;
			MemberDto foundMember = memberController.searchByEmail(targetId);
			if (foundMember == null) {
				throw new NoSuchMemberException("There is no such member with -> " + targetId);
			}
			loginCheck = targetPw.equals(foundMember.getPhoneNumber()); // Use phone number as password
			if (loginCheck) {
				System.out.println("Login success!");
				Session session = Session.shareInstance();
				session.setLoginUser(foundMember);
				window.close();
				// Set mainView center
				FxStoryMainView mainView = WindowLycler.shareInstance().requestMainView();
				mainView.setInnerView();
			} else {
				System.out.println("Login failed!\nUser input: " + targetPw + "\nOriginal pw: " + foundMember.getPhoneNumber());
			}

		});

		signUpButton.setOnAction(e -> {
			// popup signup window
			WindowLycler.shareInstance().requestSignupWindow().display();
			window.close();
		});

		// Creating Label for signup button
		Label labelForSignUp = new Label("Need an account ?");
		labelForSignUp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		// Createing HBox
		HBox hBox = new HBox();
		hBox.getChildren().addAll(labelForSignUp, signUpButton);
		hBox.setSpacing(5);
		HBox.setHgrow(labelForSignUp, Priority.ALWAYS);

		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(SizeHandler.POPUP_SCREEN_WIDTH, SizeHandler.POPUP_SCREEN_HEIGHT);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(10);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid
		gridPane.add(logInLabel, 0, 0);
		gridPane.add(emailField, 0, 1);
		gridPane.add(passwordField, 0, 2);
		gridPane.add(signInButton, 0, 3);
		gridPane.add(hBox, 0, 4);

		// Styling nodes
		signInButton.setStyle("-fx-background-color: black; -fx-text-fill: white;"); // "-fx-font: normal bold 20px
																						// 'serif' "
		signUpButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

		gridPane.setStyle("-fx-background-color: white;");

		// Creating a scene object
		Scene scene = new Scene(gridPane);

		// Adding scene to the stage
		window.setScene(scene);

		// Displaying the contents of the stage
		window.show();
	}
}