package javastory.util;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.share.DatePair;
import javastory.budget.share.Socialian;
import javastory.budget.ui.contorller.TravelController;
import javastory.club.entity.RoleInClub;
import javastory.club.service.dto.ClubMembershipDto;
import javastory.club.service.dto.PostingDto;
import javastory.club.service.dto.TravelClubDto;
import javastory.club.ui.controller.ClubController;
import javastory.club.ui.controller.ClubControllerLycler;
import javastory.club.ui.controller.ClubMembershipController;
import javastory.club.ui.controller.PostingController;
import javastory.ui.table.TransactionTable;

public class TableManager {
	//
	private static TableManager instance;

	private TableView<TravelClubDto> clubTable;
	private List<TravelClubDto> clubList;
	private TableView<ClubMembershipDto> membershipTable;
	private List<ClubMembershipDto> membershipList;
	private TableView<PostingDto> postingTable;
	private List<PostingDto> postingList;
	private TableView<TravelDto> travelTable;
	private List<TravelDto> travelList;
	private TableView<PostingDto> fullPostingTable;
	private TableView<TravelDto> fullTravelTable;
	private TransactionTable transactionTable;

	private ClubController clubController;
	private ClubMembershipController membershipController;
	private PostingController postingController;
	private TravelController travelController;

	private TableManager() {
		//
		clubController = ClubControllerLycler.shareInstance().requestClubController();
		membershipController = ClubControllerLycler.shareInstance().requestMembershipController();
		postingController = ClubControllerLycler.shareInstance().requestPostingController();
		travelController = ClubControllerLycler.shareInstance().requestTravelController();
	}

	public synchronized static TableManager shareInstance() {
		//
		if (instance == null) {
			instance = new TableManager();
		}
		return instance;
	}

	public TableView<TravelClubDto> requestClubTable() {
		//
		if (clubTable == null) {
			clubTable = new TableView<>();
		}
		getClubColumns();
		return clubTable;
	}

	public void refreshClubTable() {
		//
		clubList = clubController.findAll();
		clubTable.getItems().clear();
		clubTable.setItems(getClubObservableList());
	}

	private ObservableList<TravelClubDto> getClubObservableList() {
		//
		ObservableList<TravelClubDto> clubs = FXCollections.observableArrayList();
		if (clubList != null) {
			for (TravelClubDto club : clubList) {
				clubs.add(club);
			}
		}
		return clubs;
	}

	private void getClubColumns() {
		//
		TableColumn<TravelClubDto, String> nameColumn = new TableColumn<>("Club name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		clubTable.getColumns().add(nameColumn);

		TableColumn<TravelClubDto, String> dateColumn = new TableColumn<>("Foundation Date");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("foundationDay"));
		dateColumn.prefWidthProperty().bind(clubTable.widthProperty().divide(1.5));
		clubTable.getColumns().add(dateColumn);
	}

	public TableView<ClubMembershipDto> requestMembershipTable() {
		//
		if (membershipTable == null) {
			membershipTable = new TableView<>();
		}
		getMembershipColumns();
		return membershipTable;
	}

	public void refreshMembershipTable(String clubId) {
		//
		membershipList = membershipController.findAllMembershipsIn(clubId);
		membershipTable.getItems().clear();
		membershipTable.setItems(getMembershipObservableList());
	}

	private ObservableList<ClubMembershipDto> getMembershipObservableList() {
		//
		ObservableList<ClubMembershipDto> memberships = FXCollections.observableArrayList();
		if (membershipList != null) {
			for (ClubMembershipDto membership : membershipList) {
				memberships.add(membership);
			}
		}
		return memberships;
	}

	private void getMembershipColumns() {
		//
		TableColumn<ClubMembershipDto, String> emailColumn = new TableColumn<>("Member email");
		emailColumn.setMinWidth(150);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));
		membershipTable.getColumns().add(emailColumn);

		TableColumn<ClubMembershipDto, RoleInClub> roleColumn = new TableColumn<>("Member Role");
		roleColumn.setMinWidth(100);
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		membershipTable.getColumns().add(roleColumn);
	}

	public TableView<PostingDto> requestPostingTable() {
		//
		if (postingTable == null) {
			postingTable = new TableView<>();
		}
		getPostingColumns();
		return postingTable;
	}

	public void refreshPostingTable() {
		//
		postingList = postingController.getPostings();
		postingTable.getItems().clear();
		postingTable.setItems(getPostingObservableList());
	}

	private ObservableList<PostingDto> getPostingObservableList() {
		//
		ObservableList<PostingDto> postings = FXCollections.observableArrayList();
		if (postingList != null) {
			for (PostingDto posting : postingList) {
				postings.add(posting);
			}
		}
		return postings;
	}

	private void getPostingColumns() {
		//
		TableColumn<PostingDto, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setMinWidth(250);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		postingTable.getColumns().add(titleColumn);

		TableColumn<PostingDto, Integer> viewsColumn = new TableColumn<>("Views");
		viewsColumn.setMinWidth(50);
		viewsColumn.setCellValueFactory(new PropertyValueFactory<>("readCount"));
		postingTable.getColumns().add(viewsColumn);
	}

	public TableView<TravelDto> requestTravelTable() {
		//
		if (travelTable == null) {
			travelTable = new TableView<>();
		}
		getTravelColumns();
		return travelTable;
	}

	public void refreshTravelTable(String clubId) {
		//
		travelList = travelController.findTravelByClubId(clubId);
		travelTable.getItems().clear();
		travelTable.setItems(getTravelObservableList());
	}

	private ObservableList<TravelDto> getTravelObservableList() {
		//
		ObservableList<TravelDto> travels = FXCollections.observableArrayList();
		if (travelList != null) {
			for (TravelDto travel : travelList) {
				travels.add(travel);
			}
		}
		return travels;
	}

	private void getTravelColumns() {
		//
		TableColumn<TravelDto, String> nameColumn = new TableColumn<>("Travel Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("travelName"));
		travelTable.getColumns().add(nameColumn);

		TableColumn<TravelDto, Socialian> leaderColumn = new TableColumn<>("Leader");
		leaderColumn.setMinWidth(100);
		leaderColumn.setCellValueFactory(new PropertyValueFactory<>("leader"));
		travelTable.getColumns().add(leaderColumn);

		TableColumn<TravelDto, DatePair> termColumn = new TableColumn<>("Travel Term");
		termColumn.setMinWidth(250);
		termColumn.setCellValueFactory(new PropertyValueFactory<>("travelTerm"));
		travelTable.getColumns().add(termColumn);
	}

	public TableView<PostingDto> requestFullPostingTable() {
		//
		if (fullPostingTable == null) {
			fullPostingTable = new TableView<>();
		}
		getFullPostingColumns();
		return fullPostingTable;
	}

	public void refreshFullPostingTable() {
		//
		postingList = postingController.getPostings();
		fullPostingTable.getItems().clear();
		fullPostingTable.setItems(getPostingObservableList());
	}

	private void getFullPostingColumns() {
		//
		TableColumn<PostingDto, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setMinWidth(200);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		fullPostingTable.getColumns().add(titleColumn);

		TableColumn<PostingDto, String> writerColumn = new TableColumn<>("Writer Email");
		writerColumn.setMinWidth(100);
		writerColumn.setCellValueFactory(new PropertyValueFactory<>("writerEmail"));
		fullPostingTable.getColumns().add(writerColumn);

		TableColumn<PostingDto, String> dateColumn = new TableColumn<>("Written Date");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("writtenDate"));
		fullPostingTable.getColumns().add(dateColumn);

		TableColumn<PostingDto, Integer> viewsColumn = new TableColumn<>("Views");
		viewsColumn.setMinWidth(50);
		viewsColumn.setCellValueFactory(new PropertyValueFactory<>("readCount"));
		fullPostingTable.getColumns().add(viewsColumn);
	}

	public TableView<TravelDto> requestFullTravelTable() {
		//
		if (fullTravelTable == null) {
			fullTravelTable = new TableView<>();
		}
		getFullTravelColumns();
		return fullTravelTable;
	}

	public void refreshFullTravelTable(String clubId) {
		//
		travelList = travelController.findTravelByClubId(clubId);
		fullTravelTable.getItems().clear();
		fullTravelTable.setItems(getTravelObservableList());
	}

	private void getFullTravelColumns() {
		//
		TableColumn<TravelDto, String> titleColumn = new TableColumn<>("Travel_Name");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("travelName"));
		titleColumn.prefWidthProperty().bind(fullTravelTable.widthProperty().divide(7.5));

		TableColumn<TravelDto, Socialian> leaderColumn = new TableColumn<>("Leader");
		leaderColumn.setCellValueFactory(new PropertyValueFactory<>("leader"));
		leaderColumn.prefWidthProperty().bind(fullTravelTable.widthProperty().divide(2.6));

		TableColumn<TravelDto, DatePair> termColumn = new TableColumn<>("Travel_Term");
		termColumn.setCellValueFactory(new PropertyValueFactory<>("travelTerm"));
		termColumn.prefWidthProperty().bind(fullTravelTable.widthProperty().divide(3.75));

		TableColumn<TravelDto, Integer> participantsColumn = new TableColumn<>("Participants");
		participantsColumn.setMinWidth(80);
		participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participantsCount"));
		participantsColumn.prefWidthProperty().bind(fullTravelTable.widthProperty().divide(15));

		TableColumn<TravelDto, String> memoColumn = new TableColumn<>("Memo");
		memoColumn.setCellValueFactory(new PropertyValueFactory<>("memo"));
		memoColumn.prefWidthProperty().bind(fullTravelTable.widthProperty().divide(7));

		fullTravelTable.getColumns().addAll(titleColumn, leaderColumn, termColumn, participantsColumn, memoColumn);
	}

	public TravelDto addTravelItem(String title, String leader, String startDate, String endDate, String participants,
			String memo) {
		//
		String clubId = Session.shareInstance().getCurrentClub().getUsid();
		String travelId = clubId + "T" + System.currentTimeMillis();

		TravelDto newTravel = new TravelDto(clubId, travelId, title);
		newTravel.setLeader(Socialian.toSocialian(leader));
		DatePair travelTerm = new DatePair(LocalDate.parse(startDate), LocalDate.parse(endDate));
		newTravel.setTravelTerm(travelTerm);
		newTravel.setParticipantsCount(Integer.parseInt(participants));
		newTravel.setMemo(memo);

		fullTravelTable.getItems().add(newTravel);

		return newTravel;
	}

	public void getTravelFounds(List<TravelDto> travelFounds) {
		//
		if (travelFounds != null) {
			fullTravelTable.getItems().clear();
			Iterator<TravelDto> travelIter = travelFounds.iterator();
			while (travelIter.hasNext()) {
				fullTravelTable.getItems().add(travelIter.next());
			}
			fullTravelTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} else {
			// show every travel in current club
			fullTravelTable.getItems().clear();
			String currentClubId = Session.shareInstance().getCurrentClub().getUsid();
			Iterator<TravelDto> listIter = travelController.findTravelByClubId(currentClubId).iterator();
			while (listIter.hasNext()) {
				fullTravelTable.getItems().add(listIter.next());
			}
		}
	}

	public TransactionTable requestTransactionTable() {
		//
		if (transactionTable == null) {
			transactionTable = new TransactionTable();
		}
		return transactionTable;
	}
}