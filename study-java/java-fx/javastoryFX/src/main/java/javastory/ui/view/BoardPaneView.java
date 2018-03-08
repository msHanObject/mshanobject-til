package javastory.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javastory.club.service.dto.BoardDto;
import javastory.club.service.dto.PostingDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.PostingController;
import javastory.ui.window.WindowLycler;
import javastory.util.TableManager;

public class BoardPaneView {
	//
	private GridPane gridPane;
	private TextField clubField, nameField, adminEmailField, searchField;
	private Button modifyBoardBtn, searchBtn, newPostingBtn;
	private TableView<PostingDto> fullPostingTable;
	private PostingController postingController;
	
	public BoardPaneView() {
		//
		clubField = new TextField();
		clubField.setDisable(true);
		nameField = new TextField();
		adminEmailField = new TextField();
		searchField = new TextField();
		modifyBoardBtn = new Button("modify");
		searchBtn = new Button("search");
		newPostingBtn = new Button("new posting");
		postingController = ClubControllerLycler.shareInstance().requestPostingController();
	}
	
	public GridPane createBoardPane() {
		// Creating and Setting a GirdPane
		gridPane = new GridPane();
		gridPane.setStyle("-fx-background-color: #FFFFFF;");
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);

		setButtonsAction();

		// Creating Labels
		Label clubLabel = new Label("• current club name:");
		Label nameLabel = new Label("• current board name:");
		Label adminEmailLabel = new Label("• board admin-email:");

		// Creating a VBox for labels
		VBox labelBox = new VBox();
		labelBox.getChildren().addAll(clubLabel, nameLabel, adminEmailLabel);
		labelBox.setSpacing(20);
		labelBox.setPadding(new Insets(5, 0, 0, 0));
		labelBox.setAlignment(Pos.TOP_RIGHT);	

		// Creating a HBox for first line
		HBox firstLineBox = new HBox();
		firstLineBox.getChildren().addAll(clubField, modifyBoardBtn);
		HBox.setHgrow(clubField, Priority.ALWAYS);
		firstLineBox.setSpacing(10);
		
		// Creating a VBox for fields
		VBox fieldBox = new VBox();
		fieldBox.getChildren().addAll(firstLineBox, nameField, adminEmailField);
		fieldBox.setSpacing(10);
		fieldBox.setMaxWidth(355);

		// Creating a HBox for search line
		HBox searchLineBox = new HBox();
		searchLineBox.getChildren().addAll(searchField, searchBtn, newPostingBtn);
		HBox.setHgrow(searchField, Priority.ALWAYS);
		searchLineBox.setSpacing(10);
		
		gridPane.add(labelBox, 0, 0);
		gridPane.add(fieldBox, 1, 0);
		HBox.setHgrow(fieldBox, Priority.ALWAYS);
		gridPane.add(searchLineBox, 0, 1, 2, 1);
		createPostingTalbes();
		gridPane.add(fullPostingTable, 0, 2, 2, 1);
		
		return gridPane;
	}

	private void setButtonsAction() {
		//
		newPostingBtn.setOnAction(e -> {
			WindowLycler.shareInstance().requestPostingWindow().display();
		});
		
		modifyBoardBtn.setOnAction(e -> {
//			nameField.getText();
		});
	}

	private void createPostingTalbes() {
		// Creating posting tableView
		fullPostingTable = TableManager.shareInstance().requestFullPostingTable();
	}

	public void loadBoardData(TravelClubDto selectedClub) {
		//Load data
		BoardDto currentBoard = postingController.getCurrentBoard();
		TableManager.shareInstance().refreshFullPostingTable();
		String clubId =  currentBoard.getClubId();
		TravelClubDto currentClub = ClubControllerLycler.shareInstance().requestClubController().serachById(clubId);

		//Set data
		clubField.setText(currentClub.getName());
		nameField.setText(currentBoard.getName());
		adminEmailField.setText(currentBoard.getAdminEmail());
	}
}
