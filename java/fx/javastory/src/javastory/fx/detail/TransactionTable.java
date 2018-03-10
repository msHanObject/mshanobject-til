package javastory.fx.detail;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TranDto;
import javastory.budget.share.IdName;

public class TransactionTable {
	//
	private TableView<TranDto> tranTable;
	private List<Transaction> tranList;
	private CashBookDto currentCashBook;
	private TextField cashBookInput, tranInput, tradingAccountInput,
						dateInput, memoInput;
	
	public TransactionTable(CashBookDto currentCashBook) {
		//
		this.currentCashBook = currentCashBook;
		this.tranList = currentCashBook.getTransactions();
		tranTable = new TableView<>();
		tranTable.setItems(getTran());
		this.getTranColumns();
	}
	
	private ObservableList<TranDto> getTran() {
		// 
		ObservableList<TranDto> trans = FXCollections.observableArrayList();
		if (tranList != null) {
			for (Transaction tran : tranList) {
				trans.add(new TranDto(tran));
			}
		}
		return trans;
	}
	
	public VBox addTable() {
		//
		cashBookInput = new TextField();
		cashBookInput.setPromptText("CashBook");
		cashBookInput.setMinWidth(100);
		
		tranInput = new TextField();
		tranInput.setPromptText("Tran");
		tranInput.setMinWidth(50);
		
		tradingAccountInput = new TextField();
		tradingAccountInput.setPromptText("TradingAccount");
		tradingAccountInput.setMinWidth(100);

		dateInput = new TextField();
		dateInput.setPromptText("Date");
		dateInput.setMinWidth(50);
		
		memoInput = new TextField();
		memoInput.setPromptText("Memo");
		memoInput.setMinWidth(100);
		
		Button addButton = new Button("Add");
		addButton.setOnAction( e -> addButtonClicked());
		
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction( e -> deleteButtonClicked());
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(cashBookInput, tranInput,
				tradingAccountInput, dateInput, memoInput, addButton, deleteButton);
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(0,0,10,0));
		vBox.setSpacing(10);
		vBox.getChildren().addAll(tranTable, hBox);
		
		return vBox;
	}
	
	private void deleteButtonClicked() {
		// 
		ObservableList<TranDto> tranSelected, allTrans;
		allTrans = tranTable.getItems();
		tranSelected = tranTable.getSelectionModel().getSelectedItems();
		
		tranSelected.forEach(allTrans::remove);
	}

	private void addButtonClicked() {
		// 
		IdName tran = new IdName(tranInput.getText(), "");
		IdName cashBook = currentCashBook.getCashBook();
		if (cashBook == null) {
			cashBook = new IdName(cashBookInput.getText(),"");
		}
		TranDto tranDto = new TranDto(cashBook, tran);
		tranDto.setDate(dateInput.getText());
		IdName tradingAccount = new IdName(tradingAccountInput.getText(), "");
		tranDto.setTradingAccount(tradingAccount);
		tranDto.setMemo(memoInput.getText());
		tranDto.setTime(System.currentTimeMillis());
		
		tranTable.getItems().add(tranDto);
		cashBookInput.clear(); tranInput.clear();
		tradingAccountInput.clear(); dateInput.clear(); memoInput.clear();
	}

	private void getTranColumns() {
		//
		TableColumn<TranDto, IdName> cashBookColumn = new TableColumn<>("CashBook");
		cashBookColumn.setMinWidth(200);
		cashBookColumn.setCellValueFactory(new PropertyValueFactory<>("cashBook"));
		tranTable.getColumns().add(cashBookColumn);
		
		TableColumn<TranDto, String> tranColumn = new TableColumn<>("Tran");
		tranColumn.setMinWidth(100);
		tranColumn.setCellValueFactory(new PropertyValueFactory<>("tran"));
		tranTable.getColumns().add(tranColumn);
		
		TableColumn<TranDto, IdName> tradingColumn = new TableColumn<>("TradingAccount");
		tradingColumn.setMinWidth(200);
		tradingColumn.setCellValueFactory(new PropertyValueFactory<>("tradingAccount"));
		tranTable.getColumns().add(tradingColumn);
		
		TableColumn<TranDto, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		tranTable.getColumns().add(dateColumn);
		
		TableColumn<TranDto, String> memoColumn = new TableColumn<>("Memo");
		memoColumn.setMinWidth(100);
		memoColumn.setCellValueFactory(new PropertyValueFactory<>("memo"));
		tranTable.getColumns().add(memoColumn);
		
		TableColumn<TranDto, Long> timeColumn = new TableColumn<>("Time");
		timeColumn.setMinWidth(100);
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		tranTable.getColumns().add(timeColumn);
	}

	public TableView<TranDto> getTranTable() {
		return tranTable;
	}
}
