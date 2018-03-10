package javastory.fx.story;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javastory.fx.view.AccountYearlyDueView;
import javastory.fx.view.BoardView;
import javastory.fx.view.CashBookView;
import javastory.fx.view.ClubMembershipView;
import javastory.fx.view.MemberView;
import javastory.fx.view.PostingView;
import javastory.fx.view.TravelClubView;
import javastory.fx.view.TravelView;

public class MainView extends Application{
	//
	private Stage window;
	private Scene scene;
	private GridPane centerGrid;
	
	private TravelClubView travelClubView;
	private MemberView memberView;
	private ClubMembershipView membershipView;
	private BoardView boardView;
	private PostingView postingView;
	private TravelView travelView;
	private CashBookView cashBookView;
	private AccountYearlyDueView accountYearlyDueView;
	
	public MainView() {
		//
		travelClubView = new TravelClubView();
		memberView = new MemberView();
		membershipView = new ClubMembershipView();
		boardView = new BoardView();
		postingView = new PostingView();
		travelView = new TravelView();
		cashBookView = new CashBookView();
		accountYearlyDueView = new AccountYearlyDueView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 
		window = primaryStage;
		window.setTitle("Java Story System");
		
		//Window Size
		int width, height;
		width = 800; height = 400;
		
		//Global Title
		HBox globalTitle = new HBox();
		Text text = new Text("Java Story System");
		text.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		globalTitle.getChildren().add(text);
		globalTitle.setPadding(new Insets(20, 20, 20, width/2.9));
		globalTitle.setStyle("-fx-background-color: #FFFFFF;");

		//TreeItem
		TreeItem<String> root, club, budget;
		
		//Root
		root = new TreeItem<>();
		root.setExpanded(false);
		
		//Club
		club = makeBranch("Club", root); 
		List<String> clubBranch = new ArrayList<>();
		clubBranch.add("TravelClubView");
		clubBranch.add("MemberView");
		clubBranch.add("ClubMembershipView");
		clubBranch.add("BoardView");
		clubBranch.add("PostingView");
		makeBranch(clubBranch, club);
				
		//Buget
		budget = makeBranch("Budget", root);
		List<String> budgetBranch = new ArrayList<>();
		budgetBranch.add("TravelView");
		budgetBranch.add("CashBookView");
		budgetBranch.add("AccountYearlyDueView");
		makeBranch(budgetBranch, budget);
		
		//Create pane
		BorderPane border = new BorderPane();
		
		//Create tree
		TreeView<String> globalView = new TreeView<>(root);
		globalView.setShowRoot(false);
		globalView.getSelectionModel().selectedItemProperty().addListener( (v, oldVAlue, newValue) -> {
			//
			if (newValue != null) {
				centerGrid = switchPane(newValue.getValue());
				border.setCenter(centerGrid);
			}
		});
		
		//Global View
		VBox vbox = new VBox();
		vbox.getChildren().add(globalView);
		
		//Layout
		border.setTop(globalTitle);
		border.setLeft(vbox);
				
		scene = new Scene(border, width, height);
		window.setScene(scene);
		window.show();
	}

	private TreeItem<String> makeBranch(String title, TreeItem<String> parent){
		//
		TreeItem<String> item = new TreeItem<>(title);

		item.setExpanded(true);
		parent.getChildren().add(item);
		
		return item;
	}
	
	private void makeBranch(List<String> titles, TreeItem<String> parent){
		//
		Iterator<String> tIter = titles.iterator();
		TreeItem<String> item;
		while (tIter.hasNext()) {
			item = new TreeItem<>(tIter.next());
			item.setExpanded(true);
			parent.getChildren().add(item);
		}
	}

	private GridPane switchPane(String value) {
		//
		String view = value.trim();
		if (view.equals(travelClubView.getClass().getSimpleName())) {
			return travelClubView.addGridPane();
		}
		if (view.equals(memberView.getClass().getSimpleName())) {
			return memberView.addGridPane();
		}
		if (view.equals(membershipView.getClass().getSimpleName())) {
			return membershipView.addGridPane();
		}
		if (view.equals(boardView.getClass().getSimpleName())) {
			return boardView.addGridPane();
		}
		if (view.equals(postingView.getClass().getSimpleName())) {
			return postingView.addGridPane();
		}
		if (view.equals(travelView.getClass().getSimpleName())) {
			return travelView.addGridPane();
		}
		if (view.equals(cashBookView.getClass().getSimpleName())) {
			return cashBookView.addGridPane();
		}
		if (view.equals(accountYearlyDueView.getClass().getSimpleName())) {
			return accountYearlyDueView.addGridPane();
		}
		GridPane emptyGrid = new GridPane();
		emptyGrid.setStyle("-fx-background-color: #FFFFFF;");
		return emptyGrid;
	}
}