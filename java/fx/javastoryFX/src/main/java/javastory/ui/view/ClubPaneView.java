package javastory.ui.view;

import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javastory.budget.service.dto.TravelDto;
import javastory.club.entity.RoleInClub;
import javastory.club.service.dto.ClubMembershipDto;
import javastory.club.service.dto.MemberDto;
import javastory.club.service.dto.PostingDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.ui.controller.ClubController;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.ClubMembershipController;
import javastory.club.ui.controller.PostingController;
import javastory.util.DatePickerUtil;
import javastory.util.DateUtil;
import javastory.util.Session;
import javastory.util.TableManager;

public class ClubPaneView {
	//
	private GridPane gridPane;
	private TextField nameField;
	private DatePicker foundationPicker;
	private Button joinClubBtn, modifyClubBtn, deleteClubBtn;
	private TextArea infoArea;
	private TableView<ClubMembershipDto> membershipTable;
	private TableView<PostingDto> postingTable;
	private TableView<TravelDto> travelTable;
	private ClubController clubController;
	private ClubMembershipController membershipController;
	private PostingController postingController;
	private Session session;

	public ClubPaneView() {
		// Creating a club name Text
		nameField = new TextField("no name.");

		// Creating buttons
		joinClubBtn = new Button("join");
		modifyClubBtn = new Button("modify");
		deleteClubBtn = new Button("delete");

		// Creating a date picker for club foundation day
		foundationPicker = DatePickerUtil.shareInstance().requestDatePicker();

		// Creating a club information TextArea
		infoArea = new TextArea("no information.");
		infoArea.setPrefRowCount(10);
		infoArea.setPrefColumnCount(50);
		infoArea.setWrapText(true);

		clubController = ClubControllerLycler.shareInstance().requestClubController();
		membershipController = ClubControllerLycler.shareInstance().requestMembershipController();
		postingController = ClubControllerLycler.shareInstance().requestPostingController();
		session = Session.shareInstance();
	}

	public GridPane createClubPane() {
		// Creating and Setting a GirdPane
		gridPane = new GridPane();
		gridPane.setStyle("-fx-background-color: #FFFFFF;");
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		setButtonsAction();
		
		// Creating Labels
		Label nameLabel = new Label("• club name:");
		Label foundationLabel = new Label("• club foundation day:");
		Label infoLabel = new Label("• club information:");
		
		// Creating a VBox for labels
		VBox labelBox = new VBox();
		labelBox.getChildren().addAll(nameLabel, foundationLabel, infoLabel);
		labelBox.setSpacing(20);
		labelBox.setPadding(new Insets(5,0,0,0));
		labelBox.setAlignment(Pos.TOP_RIGHT);

		// Creating a HBox for first line
		HBox firstLineBox = new HBox();
		firstLineBox.getChildren().addAll(nameField, joinClubBtn, modifyClubBtn, deleteClubBtn);
		firstLineBox.setSpacing(10);
		
		// Creating a VBox for fields
		VBox fieldBox = new VBox();
		fieldBox.getChildren().addAll(firstLineBox, foundationPicker, infoArea);
		fieldBox.setSpacing(10);
		fieldBox.setMaxWidth(355);

		gridPane.add(labelBox, 0, 0);
		gridPane.add(fieldBox, 1, 0);
		createTalbes();
		gridPane.add(membershipTable, 0, 1);
		gridPane.add(postingTable, 1, 1);
		gridPane.add(travelTable, 2, 1);
		
		return gridPane;
	}

	private void setButtonsAction() {
		// 
		joinClubBtn.setOnAction(e -> {
			String currentUserEmail = session.getLoginUser().getEmail();
			membershipController.register(currentUserEmail, RoleInClub.Member);
			String currentClubId = membershipController.getCurrentClub().getUsid();
			TableManager.shareInstance().refreshMembershipTable(currentClubId);
		});
		
		modifyClubBtn.setOnAction(e -> {
			
			//Get modified values of club
			String newClubName = nameField.getText();
			String pattern = "yyyy.MM.dd";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			String newClubFoundationDay = foundationPicker.getValue().format(dateFormatter);
			String newClubInformation = infoArea.getText();

			//Set modified values on current club
			TravelClubDto targetClub = session.getCurrentClub();
			targetClub.setName(newClubName);
			targetClub.setFoundationDay(newClubFoundationDay);
			targetClub.setIntro(newClubInformation);
			clubController.modify(targetClub);
			//TODO:클럽 수정시 해당 클럽에 멤버쉽이 있는 멤버들의 멤버쉽정보 변경 필요 
			//Refresh club table
			TableManager.shareInstance().refreshClubTable();
		});
		
		deleteClubBtn.setOnAction(e -> {
			TravelClubDto targetClub = session.getCurrentClub();
			clubController.remove(targetClub);
			/*TODO:클럽 삭제시 해당 클럽에 멤버쉽이 있는 멤버들의 멤버쉽정보 변경 필요
			 * 		해당 클럽과 연관되어 있는 border posting travel cashbook transaction 모두 연쇄 삭제  
			 */
			//Refresh club table
			TableManager.shareInstance().refreshClubTable();
			//TODO: Refresh clubPane
		});
	}

	private void checkSessionUser() {
		// If the user of the session is not president
		MemberDto currentUser = session.getLoginUser();
		ClubMembershipDto membershipDto = membershipController.searchByEmail(currentUser.getEmail());
		if (membershipDto == null) {
			settingGuestMode();
		} else if (membershipDto.getRole() == RoleInClub.Member) {
			settingMemberMode();
		} else {
			// If the user of the session is president
			settingPresidentMode();
		}
	}

	private void settingPresidentMode() {
		// Set visible
		modifyClubBtn.setVisible(true);
		deleteClubBtn.setVisible(true);
	}

	private void settingGuestMode() {
		// TODO: invisible membership , post, travel table
		settingMemberMode();
	}

	private void createTalbes() {
		//
		createSimplicityMembershipTB();
		createRecentPostTB();
		createRecentTravelTB();
	}

	private void createRecentTravelTB() {
		// Creating recent travel tableView
		travelTable = TableManager.shareInstance().requestTravelTable();
		travelTable.setMinWidth(250);
		travelTable.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				//TODO:
			}
		});
	}

	private void createRecentPostTB() {
		// Creating recent posting tableView
		postingTable = TableManager.shareInstance().requestPostingTable();
		postingTable.setMinWidth(250);
		postingTable.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				//TODO:
			}
		});
	}

	private void createSimplicityMembershipTB() {
		// Creating simplicity membership talbeView
		membershipTable = TableManager.shareInstance().requestMembershipTable();
		membershipTable.setMinWidth(250);
		membershipTable.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				//TODO: call Detail membership window
			}
		});
	}

	private void settingMemberMode() {
		// Set invisible
		modifyClubBtn.setVisible(false);
		deleteClubBtn.setVisible(false);
	}

	public void loadClubData(TravelClubDto targetClub) {
		session.setCurrentClub(targetClub);
		postingController.setCurrentBoard(session.getCurrentBoard());
		membershipController.setCurrentClub(targetClub);
		TableManager.shareInstance().refreshMembershipTable(targetClub.getUsid());
		TableManager.shareInstance().refreshPostingTable();
		TableManager.shareInstance().refreshTravelTable(targetClub.getUsid());
		
		nameField.setText(targetClub.getName());		
		foundationPicker.setValue(DateUtil.convertFrom(targetClub.getFoundationDay()));
		infoArea.setText(targetClub.getIntro());

		checkSessionUser();
	}
}
