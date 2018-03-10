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
import javastory.budget.entity.account.MonthlyDue;
import javastory.budget.service.dto.YearlyDueDto;

public class MonthlyDueTable {
	//
	private TableView<MonthlyDue> monthlyDueTable;
	private List<MonthlyDue> monthlyDues;
	private TextField monthInput, amountInput;
	
	public MonthlyDueTable(YearlyDueDto currentYearlyDue) {
		// 
		this.monthlyDues = currentYearlyDue.getMonthlyDues();
		monthlyDueTable = new TableView<>();
		monthlyDueTable.setItems(this.getMonthlyDue());
		this.getMonthlyDueColumns();
	}

	private void getMonthlyDueColumns() {
		// 
		TableColumn<MonthlyDue, Integer> monthColumn = new TableColumn<>("Month");
		monthColumn.setMinWidth(200);
		monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
		monthlyDueTable.getColumns().add(monthColumn);
		
		TableColumn<MonthlyDue, Double> amountColumn = new TableColumn<>("Amount");
		amountColumn.setMinWidth(200);
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		monthlyDueTable.getColumns().add(amountColumn);
	}

	private ObservableList<MonthlyDue> getMonthlyDue() {
		// 
		ObservableList<MonthlyDue> monthes = FXCollections.observableArrayList();
		if (monthlyDues != null) {
			for (MonthlyDue monthlyDue : monthlyDues) {
				monthes.add(monthlyDue);
			}
		}
		return monthes;
	}

	public VBox addTable() {
		// 
		monthInput = new TextField();
		monthInput.setPromptText("Month");
		monthInput.setMinWidth(100);
		
		amountInput = new TextField();
		amountInput.setPromptText("Amount");
		amountInput.setMinWidth(100);
		
		Button addButton = new Button("Add");
		addButton.setOnAction( e -> addButtonClicked());
		
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction( e -> deleteButtonClicked());
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(monthInput, amountInput, addButton, deleteButton);
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(0,0,10,0));
		vBox.setSpacing(10);
		vBox.getChildren().addAll(monthlyDueTable, hBox);
		vBox.setStyle("-fx-background-color: #FFFFFF;");
		
		return vBox;
	}
	
	private void deleteButtonClicked() {
		// 
		ObservableList<MonthlyDue> monthSelected, allMonths;
		allMonths = monthlyDueTable.getItems();
		monthSelected = monthlyDueTable.getSelectionModel().getSelectedItems();
		
		monthSelected.forEach(allMonths::remove);
	}

	private void addButtonClicked() {
		// 
		int month = Integer.parseInt(monthInput.getText());
		double amount = Double.parseDouble(amountInput.getText());
		
		MonthlyDue monthlyDue = new MonthlyDue(month, amount);
		monthlyDueTable.getItems().add(monthlyDue);
		
		monthInput.clear();
		amountInput.clear();
	}

	public boolean checkModified() {
		// 
		if (monthlyDues.equals((List<MonthlyDue>) monthlyDueTable.getItems())) {
			return false;
		}
		return true;
	}

	public TableView<MonthlyDue> getMonthlyDueTable() {
		return monthlyDueTable;
	}
}