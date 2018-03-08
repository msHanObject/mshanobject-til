package javastory.ui.window;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TranDto;
import javastory.budget.ui.contorller.BudgetControllerLycler;
import javastory.budget.ui.contorller.CashBookController;
import javastory.ui.table.TransactionTable;
import javastory.util.Session;
import javastory.util.SizeHandler;
import javastory.util.TableManager;

public class CashBookWindow {
	//
	private Stage window;
	private Button updateButton, cancelButton;
	private TextField bankAccountField, budgetGoalField, monthlyDueField;
	private Text balanceText, totalIncomeText, totalExpenseText;

	private CashBookController cashBookController;
	private CashBookDto currentCashBook;
	private TransactionTable tranInstance;
	private TableView<TranDto> tranTable;

	public CashBookWindow() {
		this.currentCashBook = Session.shareInstance().getCurrentCashBook();
		cashBookController = BudgetControllerLycler.shareInstance().requestCashBookController();
		// Create textfields
		bankAccountField = new TextField();
		budgetGoalField = new TextField();
		monthlyDueField = new TextField();

		// Create texts
		balanceText = new Text("0");
		totalIncomeText = new Text("0");
		totalExpenseText = new Text("0");
		
		setButton();
		getCashBookData();
		// Request create transaction table
		tranInstance = TableManager.shareInstance().requestTransactionTable();
		tranTable = tranInstance.getTable();
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

		BorderPane border = new BorderPane();

		// Create Labels
		Label bankAccountLabel = new Label("Bank Account");
		Label budgetGoalLabel = new Label("Budget Goal");
		Label monthlyDueLabel = new Label("Monthly Due");
		Label totalIncomeLabel = new Label("Total Income");
		Label totalExpenseLabel = new Label("Total Expense");
		Label balanceLabel = new Label("Bank Balance");

		// Create HBox for cashbook information
		HBox borderTopBox = new HBox();
		borderTopBox.setSpacing(10);
		borderTopBox.setAlignment(Pos.CENTER);
		borderTopBox.getChildren().addAll(bankAccountLabel, bankAccountField, budgetGoalLabel, budgetGoalField,
				monthlyDueLabel, monthlyDueField);

		// Create HBox for total result information
		HBox totalBox = new HBox();
		totalBox.setSpacing(10);
		totalBox.setAlignment(Pos.CENTER);
		totalBox.getChildren().addAll(totalIncomeLabel, totalIncomeText, totalExpenseLabel, totalExpenseText,
				balanceLabel, balanceText);

		// Create HBox for buttons
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(updateButton, cancelButton);

		// Create VBox for totalBox and buttonBox
		VBox borderBottomBox = new VBox();
		borderBottomBox.getChildren().addAll(totalBox, buttonBox);
		borderBottomBox.setSpacing(10);

		// Setting border
		border.setTop(borderTopBox);
		border.setCenter(tranInstance.display());
		border.setBottom(borderBottomBox);
		border.setPadding(new Insets(20, 20, 20, 20));

		// Set scene
		Scene scene = new Scene(border, SizeHandler.POPUP_SCREEN_WIDTH, SizeHandler.POPUP_SCREEN_HEIGHT);
		window.setScene(scene);
		window.showAndWait();
	}

	private void getCashBookData() {
		//
		initializeContents();
		if (currentCashBook != null) {
			setFiledText();
		} else {
			currentCashBook = new CashBookDto();
		}
	}

	private void initializeContents() {
		// Initialize cashBook contents
		bankAccountField.setText(null);
		budgetGoalField.setText(null);
		monthlyDueField.setText(null);
		balanceText.setText("0");
		totalIncomeText.setText("0");
		totalExpenseText.setText("0");
	}

	private void setFiledText() {
		//
		bankAccountField.setText(currentCashBook.getBankAccount());
		budgetGoalField.setText(String.valueOf(currentCashBook.getBudgetGoal()));
		monthlyDueField.setText(String.valueOf(currentCashBook.getMonthlyDue()));
		balanceText.setText(String.valueOf(currentCashBook.getBalance()));
		totalIncomeText.setText(String.valueOf(currentCashBook.getTotalIncome()));
		totalExpenseText.setText(String.valueOf(currentCashBook.getTotalExpense()));
	}

	private void setButton() {
		//
		updateButton = new Button("register");
		updateButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		updateButton.setOnAction(e -> {
			String bankAccount = bankAccountField.getText();
			Double budgetGoal = Double.parseDouble(budgetGoalField.getText());
			Double monthlyDue = Double.parseDouble(monthlyDueField.getText());

			CashBookDto cashBookDto = new CashBookDto(bankAccount, budgetGoal, monthlyDue);
			cashBookDto.setCashBookId(Session.shareInstance().getCurrentTravel().getTravelId());
			cashBookDto.setBalance(Double.parseDouble(balanceText.getText()));
			cashBookDto.setTotalIncome(Double.parseDouble(balanceText.getText()));
			cashBookDto.setTotalExpense(Double.parseDouble(totalExpenseText.getText()));
			cashBookDto.setTransactions((List) tranTable.getItems());

			cashBookController.register(cashBookDto);
			window.close();
		});

		cancelButton = new Button("cancel");
		cancelButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		cancelButton.setOnAction(e -> {
			window.close();
		});
	}
}