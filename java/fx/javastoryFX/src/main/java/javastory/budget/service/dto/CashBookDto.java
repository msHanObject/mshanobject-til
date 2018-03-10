package javastory.budget.service.dto;

import java.util.List;

import javastory.budget.entity.CashBook;
import javastory.budget.share.Socialian;

public class CashBookDto {
	//
	private static final String DEFAULT_DELIMITER = "||";
	
	private String cashBookId;//travelId
	private Socialian manager;
	private String bankAccount;
	private double budgetGoal;
	private double monthlyDue;
	private double balance;
	private double totalIncome;
	private double totalExpense;

	private List<TranDto> transactions; 
	
	public CashBookDto() {
		//
	}
	
	public CashBookDto(String bankAccount, double budgetGoal, double monthlyDue) {
		// 
		this.bankAccount = bankAccount;
		this.budgetGoal = budgetGoal;
		this.monthlyDue = monthlyDue;
	}
	
	public CashBookDto(CashBook budget) {
		// 
		this.cashBookId = budget.getCashBookId();
		this.manager = budget.getManager();
		this.bankAccount = budget.getBankAccount();
		this.budgetGoal = budget.getBudgetGoal();
		this.monthlyDue = budget.getMonthlyDue();
		this.balance = budget.getBalance();
		this.transactions = budget.getTransactions();
		this.totalIncome = budget.getTotalIncome();
		this.totalExpense = budget.getTotalExpense();
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("cashBookId").append(DEFAULT_DELIMITER).append(cashBookId).append(DEFAULT_DELIMITER);
		builder.append("manager").append(DEFAULT_DELIMITER).append(manager).append(DEFAULT_DELIMITER);
		builder.append("bankAccount").append(DEFAULT_DELIMITER).append(bankAccount).append(DEFAULT_DELIMITER);
		builder.append("budgetGoal").append(DEFAULT_DELIMITER).append(budgetGoal).append(DEFAULT_DELIMITER);
		builder.append("monthlyDue").append(DEFAULT_DELIMITER).append(monthlyDue).append(DEFAULT_DELIMITER);
		builder.append("balance").append(DEFAULT_DELIMITER).append(balance).append(DEFAULT_DELIMITER);
		builder.append("totalIncome").append(DEFAULT_DELIMITER).append(totalIncome).append(DEFAULT_DELIMITER);
		builder.append("totalExpense").append(DEFAULT_DELIMITER).append(totalExpense).append(DEFAULT_DELIMITER);
		builder.append("transactions").append(DEFAULT_DELIMITER).append(transactions).append(DEFAULT_DELIMITER);
		
		return builder.toString();
	}

	public CashBook toCashBook() {
		// 
		CashBook budget = new CashBook();
		budget.setCashBookId(cashBookId);
		budget.setManager(manager);
		budget.setBankAccount(bankAccount);
		budget.setBudgetGoal(budgetGoal);
		budget.setMonthlyDue(monthlyDue);
		budget.setBalance(balance);
		budget.setTotalIncome(totalIncome);
		budget.setTotalExpense(totalExpense);
		budget.setTransactions(transactions);
		return budget;
	}

	public String getCashBookId() {
		return cashBookId;
	}

	public void setCashBookId(String cashBookId) {
		this.cashBookId = cashBookId;
	}

	public Socialian getManager() {
		return manager;
	}

	public void setManager(Socialian manager) {
		this.manager = manager;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public double getBudgetGoal() {
		return budgetGoal;
	}

	public void setBudgetGoal(double budgetGoal) {
		this.budgetGoal = budgetGoal;
	}

	public double getMonthlyDue() {
		return monthlyDue;
	}

	public void setMonthlyDue(double monthlyDue) {
		this.monthlyDue = monthlyDue;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public List<TranDto> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TranDto> transactions) {
		this.transactions = transactions;
	}
}