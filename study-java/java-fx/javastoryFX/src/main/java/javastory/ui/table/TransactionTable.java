package javastory.ui.table;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TranDto;
import javastory.util.Session;

public class TransactionTable {
	//
	private TableView<TranDto> table;
	private List<TranDto> tranList;
	private TextField dateField, descriptionField, incomeField, expenseField;
	private Button addTranBtn, delTranBtn;
	private CashBookDto currentCashBook;

	public TransactionTable() {
		//
		table = new TableView<>();
		dateField = new TextField();
		descriptionField = new TextField();
		incomeField = new TextField();
		expenseField = new TextField();
		addTranBtn = new Button("add");
		delTranBtn = new Button("delete");
	}

	public TableView<TranDto> getTable() {
		this.currentCashBook = Session.shareInstance().getCurrentCashBook();
		// Get transaction list from currentCashBook
		if (currentCashBook == null) {
			currentCashBook = new CashBookDto();
		}
		tranList = currentCashBook.getTransactions();
		// Initialize transaction table items
		this.initializeTable();

		getColumns();
		table.setItems(getTransactionItems());
		return table;
	}

	private ObservableList<TranDto> getTransactionItems() {
		//
		ObservableList<TranDto> trans = FXCollections.observableArrayList();
		if (tranList != null) {
			for (TranDto tran : tranList) {
				trans.add(tran);
			}
		}
		return trans;
	}

	public VBox display() {
		// Create VBox for tranAddDelBox and transactionTable
		VBox borderCenterBox = new VBox();

		setButtonActions();
		// Create HBox for transaction add and delete
		HBox tranAddDelBox = new HBox();
		tranAddDelBox.setSpacing(10);
		tranAddDelBox.setAlignment(Pos.CENTER);
		tranAddDelBox.getChildren().addAll(dateField, descriptionField, incomeField, expenseField, addTranBtn,
				delTranBtn);

		borderCenterBox.setAlignment(Pos.CENTER);
		borderCenterBox.getChildren().addAll(table, tranAddDelBox);

		return borderCenterBox;
	}

	private void setButtonActions() {
		//
		addTranBtn.setOnAction(e -> {
			//
			LocalDate date = LocalDate.parse(dateField.getText());
			String description = descriptionField.getText();
			Double income = Double.parseDouble(incomeField.getText());
			Double expense = Double.parseDouble(expenseField.getText());

			this.addItems(date, description, income, expense);
		});

		delTranBtn.setOnAction(e -> {
			//
			this.deleteItems();
		});
	}

	private void getColumns() {
		//
		TableColumn<TranDto, LocalDate> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.prefWidthProperty().bind(table.widthProperty().divide(7));

		TableColumn<TranDto, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		descriptionColumn.prefWidthProperty().bind(table.widthProperty().divide(1.75));

		TableColumn<TranDto, Double> incomeColumn = new TableColumn<>("Income");
		incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
		incomeColumn.prefWidthProperty().bind(table.widthProperty().divide(7));

		TableColumn<TranDto, Double> expenseColumn = new TableColumn<>("Expense");
		expenseColumn.setCellValueFactory(new PropertyValueFactory<>("expense"));
		// expenseColumn.setMinWidth(80);
		expenseColumn.prefWidthProperty().bind(table.widthProperty().divide(7));

		table.getColumns().addAll(dateColumn, descriptionColumn, incomeColumn, expenseColumn);
	}

	public void addItems(LocalDate date, String description, Double income, Double expense) {
		//
		TranDto newTran = new TranDto(income, expense);
		newTran.setDate(date);
		newTran.setDescription(description);

		table.getItems().add(newTran);
	}

	public void deleteItems() {
		//
		ObservableList<TranDto> tranSelected, allTrans;
		allTrans = table.getItems();
		tranSelected = table.getSelectionModel().getSelectedItems();
		tranSelected.forEach(allTrans::remove);
	}

	private void initializeTable() {
		//
		dateField.setText(null);
		descriptionField.setText(null);
		incomeField.setText(null);
		expenseField.setText(null);
	}
}
