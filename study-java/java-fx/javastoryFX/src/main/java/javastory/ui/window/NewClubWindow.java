package javastory.ui.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.entity.RoleInClub;
import javastory.club.ui.controller.BoardController;
import javastory.club.ui.controller.ClubController;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.ClubMembershipController;
import javastory.util.Session;
import javastory.util.SizeHandler;
import javastory.util.TableManager;

public class NewClubWindow {

	private Stage window;
	private TextField cNameField, cInfoField;
	private Button createButton, cancelButton;
	private VBox vBox;
	private HBox buttonBox;
	private Text announceLabel; 
	private ClubController clubController;
	private ClubMembershipController membershipController;
	private BoardController boardController;
	
	public NewClubWindow() {
		//
		vBox = new VBox();
		buttonBox = new HBox();
		clubController = ClubControllerLycler.shareInstance().requestClubController();
		membershipController = ClubControllerLycler.shareInstance().requestMembershipController();
		boardController = ClubControllerLycler.shareInstance().requestBoardController();
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
		Label newClubHeader = new Label("New Club");
		
		// Creating text fields
		creatingTextFields();

		// Creating buttons
		creatingButtons();
		
		// Creating announce words
		creatingLabel();
		
		//Setting onAction for Buttons
		settingActions();
		
		//Creating Boxes
		buttonBox.getChildren().addAll(createButton, cancelButton);
		
		vBox.getChildren().addAll(newClubHeader, cNameField, cInfoField, announceLabel, buttonBox);
		
		//Styling nodes
		stylingNodes();
		
		//Creating a scene object
		Scene scene = new Scene(vBox);
		
		window.setScene(scene);
		window.show();
	}

	private void creatingLabel() {
		// 
		announceLabel = new Text();
		String words = "Input new club";
		announceLabel.setText(words);
	}

	private void stylingNodes() {
		// 
		createButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		cancelButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		buttonBox.setSpacing(10);
		vBox.setSpacing(10);
		vBox.setMinSize(SizeHandler.POPUP_QUARTER_WIDTH, SizeHandler.POPUP_QUARTER_HEIGHT);
		vBox.setPadding(new Insets(10,10,10,10));
		vBox.setAlignment(Pos.CENTER);
	}

	private void settingActions() {
		// 
		createButton.setOnAction(e -> {
			String clubName = cNameField.getText();
			String clubInfo = cInfoField.getText();
			
			createClub(clubName, clubInfo);
			createDefaultBoard(clubName);
			
			//Refresh club table
			TableManager.shareInstance().refreshClubTable();
			window.close();
		});
		
		cancelButton.setOnAction(e -> {
			window.close();
		});
	}

	private void createDefaultBoard(String clubName) {
		// 
		String boardName = clubName + "'s Board";
		String adminEmail = Session.shareInstance().getLoginUser().getEmail();
		boardController.register(clubName, boardName, adminEmail);
	}

	private void createClub(String name, String info) {
		//
		if (name != null && info != null) {
			membershipController.setCurrentClub(clubController.register(name, info));
		}
		String currentUserEmail = Session.shareInstance().getLoginUser().getEmail();
		membershipController.register(currentUserEmail, RoleInClub.President);
	}

	private void creatingTextFields() {
		// Creating Text Field for club name
		cNameField = new TextField();
		cNameField.setPromptText("Input new club name");

		// Creating Text Field for club Info
		cInfoField = new TextField();
		cInfoField.setPromptText("Input club's information");

	}

	private void creatingButtons() {
		// Creating Buttons
		createButton = new Button("Create");
		cancelButton = new Button("Cancel");
	}
}