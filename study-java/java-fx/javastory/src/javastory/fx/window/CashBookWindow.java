package javastory.fx.window;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.share.IdName;
import javastory.fx.controller.CashBookController;
import javastory.fx.controller.TravelController;
import javastory.fx.detail.CashBookDetailPart;
import javastory.fx.detail.TransactionTable;
import javastory.fx.util.AlertBox;
import javastory.fx.util.ConfirmBox;

public class CashBookWindow {
	//
	private Scene scene;
	private String title;
	private CashBookController cashBookController;
	private TravelController travelController;
	private TextField travelIdInput, travelConsole, //travel object info 
						nameInput, infoInput; //cashBook object info
	private Button search, register, modify, delete, cancel;
	private CashBookDto currentCashBook;
	private CashBookDetailPart cashBookDetailPart;
	private TransactionTable transactionTable;
	
	public CashBookWindow(String title, CashBookController cashBookController) {
		//
		this.title = title;
		this.cashBookController = cashBookController;
		travelController = new TravelController();
		this.currentCashBook = cashBookController.getCurrentCashBook();
		travelIdInput = new TextField();
		travelConsole = new TextField();
		travelConsole.setEditable(false);
		nameInput = new TextField();
		infoInput = new TextField();
		search = new Button("search");
		register = new Button("register");
		modify = new Button("modify");
		delete = new Button("delte");
		cancel = new Button("cancel");
		cashBookDetailPart = new CashBookDetailPart(cashBookController);
	}
	
	public void init() {
		//
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		
		//Border create
		BorderPane border = new BorderPane();
		
		//Grid create
		GridPane grid =new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(0,0,0,40));
		
		//HeadLine
		Text headLine = new Text("CashBook");
		headLine.setFont(Font.font("Arial", 20));
		grid.add(headLine, 3, 1, 3, 2);
		
		//Travel connect
		travelIdInput.setMaxWidth(140);
		grid.add(travelIdInput, 1, 4, 2, 1);
		
		//Travel console
		travelConsole.setMaxWidth(400);
		grid.add(travelConsole, 1, 5, 10, 1);
		
		grid.add(search, 4, 4);
		search.setOnAction(e ->  {
			if (travelIdInput.getText() != null) {
				this.findAndSetTravel();
				cashBookDetailPart.setManagerBox();
			}
		});
		
		//cashBook name label
		Label cName = new Label();
		cName.setText("• name: ");
		grid.add(cName, 1, 7);
		//cashBook name input
		nameInput.setMaxWidth(140);
		grid.add(nameInput, 2, 7);
		
		//cashBook info label
		Label cInfo = new Label();
		cInfo.setText("• info: ");
		grid.add(cInfo, 3, 7);
		//cashBook info input
		infoInput.setMaxWidth(140);
		grid.add(infoInput, 4, 7);
		
		//Border set top
		border.setTop(grid);
		//Border set center
		border.setCenter(cashBookDetailPart.display());
		
		//create Transaction table
		transactionTable = new TransactionTable(currentCashBook);

		// RMDC box
		HBox rmdcBox = new HBox();
		rmdcBox.setPadding(new Insets(10,10,10,10));
		rmdcBox.setSpacing(10);
		rmdcBox.setAlignment(Pos.CENTER);

		//register button
		rmdcBox.getChildren().add(register);
		register.setOnAction( e -> {
			this.setCurrentCashBook();
			if (currentCashBook != null) {
				currentCashBook = cashBookController.getCurrentCashBook();
				cashBookController.register(currentCashBook);
			}
			window.close();
		});
		
		//modify button
		rmdcBox.getChildren().add(modify);
		modify.setOnAction( e -> {
			//Check if it has changed.
			boolean isModified = this.checkModified();
			if (isModified) {
				this.setCurrentCashBook();
				cashBookController.modify();
			} else {
				AlertBox.display("Modify Error", "Nothing has been modified");
				return;
			}
			window.close();
		});
		
		//delete button
		rmdcBox.getChildren().add(delete);
		delete.setOnAction( e -> {
			boolean result = ConfirmBox.display("Remove CashBook", "Are you sure you want to remove this cashBook?");
			if (result) {
				cashBookController.delete(currentCashBook);
				window.close();
			}
		});
		
		//cancel button
		rmdcBox.getChildren().add(cancel);
		cancel.setOnAction(e -> {
			window.close();
		});
		
		// Border bottom set
		VBox bottomBox = new VBox();
		bottomBox.getChildren().add(transactionTable.addTable());
		bottomBox.getChildren().add(rmdcBox);
		border.setBottom(bottomBox);
		
		scene = new Scene(border, 1100, 1000);
		window.setScene(scene);
		window.showAndWait();
	}

	private boolean checkModified() {
		// 
		if (currentCashBook.getName().equals(nameInput.getText())
			&& currentCashBook.getMemo().equals(infoInput.getText())
			&& !cashBookDetailPart.checkModified()
			&& currentCashBook.getTransactions().equals((List)transactionTable.getTranTable().getItems())) {
			return false;
		}
		return true;
	}

	private void setCurrentCashBook() {
		//
		IdName cashBook = new IdName(currentCashBook.getTravelId(), nameInput.getText());
		currentCashBook.setCashBook(cashBook);
		currentCashBook.setMemo(infoInput.getText());
		cashBookController.setCurrentCashBook(currentCashBook);
		//
		cashBookDetailPart.setCurrentCashBookDetail();
		//
		currentCashBook.setTransactions((List)transactionTable.getTranTable().getItems());
	}

	private void findAndSetTravel() {
		// 
		TravelDto travelFound = null;
		String travelId = travelIdInput.getText();
		if (travelId == null) {
			System.out.println("No such travel exception");
		}
		travelFound = travelController.findOne(travelId);
		if (travelFound == null) {
			travelConsole.setText("No such travel with " + travelId);
			return;
		}
		IdName travel = new IdName(travelId, travelFound.getName());
		travelConsole.setText(travel.toString());
		currentCashBook.setTravel(travel);
		cashBookController.setCurrentCashBook(currentCashBook);
	}

	public void reigsterMode() {
		// 
		this.fieldClear();
		modify.setDisable(true);
		delete.setDisable(true);
		init();
	}

	public void modifyMode(CashBookDto targetCashBook) {
		// 
		this.fieldClear();
		register.setDisable(true);
		currentCashBook = targetCashBook;
		cashBookController.setCurrentCashBook(targetCashBook);
		travelIdInput.setDisable(true);
		search.setDisable(true);
		travelConsole.setText(targetCashBook.getTravel().toString());
		nameInput.setText(targetCashBook.getName());
		infoInput.setText(targetCashBook.getMemo());
		//set detailPart
		cashBookDetailPart.setDetailInput();
		
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		travelIdInput.setDisable(false);
		search.setDisable(false);
		travelConsole.setText(null);
		nameInput.setText(null);
		infoInput.setText(null);
		cashBookDetailPart.setDetailClear();
		currentCashBook = new CashBookDto();
	}
}