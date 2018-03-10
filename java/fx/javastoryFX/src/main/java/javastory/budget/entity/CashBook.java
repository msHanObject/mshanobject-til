package javastory.budget.entity;

import java.util.ArrayList;
import java.util.List;

import javastory.budget.service.dto.TranDto;
import javastory.budget.share.Socialian;

public class CashBook {
	//
	private static final String DEFAULT_DELIMITER = "||";
	
	private String cashBookId;
	private Socialian manager;
	private String bankAccount;
	private double budgetGoal;
	private double monthlyDue;
	private double balance; 
	private double totalIncome;
	private double totalExpense;

	private List<TranDto> transactions; 
	
	
	public CashBook() {
		//
		this.manager = Socialian.getSample();
		this.balance = 0;
		this.totalIncome = 0;
		this.totalExpense = 0;
		this.transactions = new ArrayList<TranDto>();
	}
	
	public CashBook(String bankAccount, double budgetGoal, double monthlyDue) {
		this();
		this.bankAccount = bankAccount;
		this.budgetGoal = budgetGoal;
		this.monthlyDue = monthlyDue;
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
