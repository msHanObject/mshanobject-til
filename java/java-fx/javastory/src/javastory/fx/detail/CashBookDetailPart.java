package javastory.fx.detail;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javastory.budget.entity.CertifiedCurrency;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.share.DatePair;
import javastory.budget.share.Socialian;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.ui.console.MemberConsole;
import javastory.fx.controller.CashBookController;

public class CashBookDetailPart {
	//
	private static final String DEFAULT_CURRENCY ="₩";
	private ComboBox<String> managerBox;
	private TextField bankAccountInput, currencyCodeInput, balanceInput,
						budgetGoalInput, monthlyDueInput, startDateInput, endDateInput;
	private Button designation;
	private Text designatedCode;
	private CashBookController cashBookController;
	private CashBookDto currentCashBook;
	private Socialian manager;
	
	public CashBookDetailPart(CashBookController cashBookController) {
		//
		this.cashBookController = cashBookController;
		this.currentCashBook = cashBookController.getCurrentCashBook();
		managerBox = new ComboBox<>();
		bankAccountInput = new TextField();
		currencyCodeInput = new TextField();
		balanceInput = new TextField();
		budgetGoalInput = new TextField();
		monthlyDueInput = new TextField();
		startDateInput = new TextField();
		endDateInput = new TextField();
		designation = new Button("√");
		designatedCode = new Text("DEFAULT_CURRENCY");
	}
	
	public GridPane display() {
		//
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(0,0,0,40));
		
		//manager label
		Label manager = new Label();
		manager.setText("• manager: ");
		grid.add(manager, 1, 1);
		//manager input
		managerBox.setMinWidth(140);
		managerBox.setMaxWidth(400);
		this.checkManagerValue(managerBox);
		grid.add(managerBox, 2, 1, 2, 1);
		
		//bankAccount label
		Label bankAccount = new Label();
		bankAccount.setText("• bacnkAccount: ");
		grid.add(bankAccount, 1, 3);
		//accountNumber input
		bankAccountInput.setMaxWidth(400);
		grid.add(bankAccountInput, 2, 3, 2, 1);
		
		//currency code label
		Label codeLabel = new Label();
		codeLabel.setText("• currencyCode: ");
		grid.add(codeLabel, 1, 5);
		//currency code input
		currencyCodeInput.setMaxWidth(100);
		grid.add(currencyCodeInput, 2, 5);
		//currency code designate
		grid.add(designation, 3, 5);
		designation.setOnAction(e -> {
			String currencyCode = currencyCodeInput.getText();
			boolean checkResult = false;
			if (currencyCode != null) {
				checkResult = this.checkCode();				
			}
			if (checkResult) {				
				designatedCode.setText(currencyCode);
			}
		});
		
		//balance label
		Label balance = new Label();
		balance.setText("• balance: ");
		grid.add(balance, 4, 5);
		//balance input
		balanceInput.setMaxWidth(100);
		grid.add(balanceInput, 5, 5);
		
		//budgetGoal label
		Label budgetGoal = new Label();
		budgetGoal.setText("• budgetGoal: ");
		grid.add(budgetGoal, 1, 7);
		//budgetGoal input
		budgetGoalInput.setMaxWidth(100);
		grid.add(budgetGoalInput, 2, 7);
		//add designatedCode
		grid.add(designatedCode, 3, 7);
		
		//monthlyDue label
		Label monthlyDue = new Label();
		monthlyDue.setText("• monthlyDue: ");
		grid.add(monthlyDue, 4, 7);
		//monthlyDue input
		monthlyDueInput.setMaxWidth(100);
		grid.add(monthlyDueInput, 5, 7);
		//add designatedCode
//		grid.add(designatedCode, 6, 7);
		
		//prepareTerm label
		Label prepareTerm = new Label();
		prepareTerm.setText("• prepareTerm: ");
		grid.add(prepareTerm, 1, 9);
		//prepareTerm start date
		startDateInput.setMaxWidth(100);
		grid.add(startDateInput, 2, 9);
		//add wave mark
		Text waveMark = new Text("~");
		grid.add(waveMark, 3, 9);
		//prepareTerm end date
		endDateInput.setMaxWidth(100);
		grid.add(endDateInput, 4, 9);
		
		return grid;
	}

	private boolean checkCode() {
		// 
		for (CertifiedCurrency certifiedCurrency : CertifiedCurrency.values()) {
			String currency = certifiedCurrency.toString();
			if (currency.equals(currencyCodeInput.getText())) {
				return true;
			}
		}
		return false;
	}

	public void setManagerBox() {
		//
		if (currentCashBook.getTravel() == null) {
			return;
		}
		List<String> candidateList = null;
		candidateList = cashBookController.getMemberList();
		for (String candidate : candidateList) {
			managerBox.getItems().add(candidate);
		}
	}
	
	private void checkManagerValue(ComboBox<String> managerBox) {
		//
		managerBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue != null) {
				MemberConsole memberConsole = new MemberConsole();
				MemberDto member = memberConsole.searchByEmail(newValue);
				manager = new Socialian(member.getBirthDay(), member.getNickName(),
												 member.getName(), member.getEmail());
			}
		});
	}
	
	public void setCurrentCashBookDetail() {
		//
		currentCashBook.setManager(manager);
		currentCashBook.setBankAccount(bankAccountInput.getText());
		currentCashBook.setCurrencyCode(designatedCode.getText());
		double balance = Double.valueOf(balanceInput.getText());
		currentCashBook.setBalance(balance);
		double budgetGoal = Double.valueOf(budgetGoalInput.getText());
		currentCashBook.setBudgetGoal(budgetGoal);
		double monthlyDue = Double.valueOf(monthlyDueInput.getText());
		currentCashBook.setMonthlyDue(monthlyDue);
		DatePair preparationTerm = new DatePair(startDateInput.getText(), endDateInput.getText());
		currentCashBook.setPreparationTerm(preparationTerm);
		cashBookController.setCurrentCashBook(currentCashBook);
	}
	
	public void setDetailInput() {
		//
		currentCashBook = cashBookController.getCurrentCashBook();
		managerBox.setValue(currentCashBook.getManager().getEmail());
		this.setManagerBox();
		bankAccountInput.setText(currentCashBook.getBankAccount());
		currencyCodeInput.setText(currentCashBook.getCurrencyCode());
		designatedCode.setText(currentCashBook.getCurrencyCode());
		balanceInput.setText(String.valueOf(currentCashBook.getBalance()));
		budgetGoalInput.setText(String.valueOf(currentCashBook.getBudgetGoal()));
		monthlyDueInput.setText(String.valueOf(currentCashBook.getMonthlyDue()));
		startDateInput.setText(currentCashBook.getPreparationTerm().getStartDate());
		endDateInput.setText(currentCashBook.getPreparationTerm().getEndDate());
	}

	public void setDetailClear() {
		// 
		managerBox.getItems().clear();
		bankAccountInput.setText(null);
		currencyCodeInput.setText(null);
		designatedCode.setText(DEFAULT_CURRENCY);
		balanceInput.setText(null);
		budgetGoalInput.setText(null);
		monthlyDueInput.setText(null);
		startDateInput.setText(null);
		endDateInput.setText(null);
	}

	public boolean checkModified() {
		// 
		if (currentCashBook.getManager().getEmail().equals(managerBox.getValue())
			&& currentCashBook.getBankAccount().equals(bankAccountInput.getText())
			&& currentCashBook.getCurrencyCode().equals(currencyCodeInput.getText())
			&& (currentCashBook.getBalance() == Double.valueOf(balanceInput.getText()))
			&& (currentCashBook.getBudgetGoal() == Double.valueOf(budgetGoalInput.getText()))
			&& (currentCashBook.getMonthlyDue() == Double.valueOf(monthlyDueInput.getText()))
			&& currentCashBook.getPreparationTerm().getStartDate().equals(startDateInput.getText())
			&& currentCashBook.getPreparationTerm().getEndDate().equals(endDateInput.getText())) {
				return false;
			}
		return true;
	}
}