package javastory.budget.service.dto;

import java.time.LocalDate;

import javastory.budget.entity.Transaction;

public class TranDto {
	//
	private static final String DEFAULT_DELIMITER = "||";

	private LocalDate date;
	private String description;
	private double income;
	private double expense;

	
	public TranDto(double income, double expense) {
		//
		this.income = income;
		this.expense = expense;
	}
	
	public TranDto(Transaction transaction) {
		// 
		this.date = transaction.getDate();
		this.description = transaction.getDescription();
		this.income = transaction.getIncome();
		this.expense = transaction.getExpense();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("Date").append(DEFAULT_DELIMITER);
		builder.append(date).append(DEFAULT_DELIMITER);
		builder.append("Description").append(DEFAULT_DELIMITER);
		builder.append(description).append(DEFAULT_DELIMITER);
		builder.append("Income").append(DEFAULT_DELIMITER);
		builder.append(income).append(DEFAULT_DELIMITER);
		builder.append("Expense").append(DEFAULT_DELIMITER);
		builder.append(expense).append(DEFAULT_DELIMITER);
		
		return builder.toString();
	}

	public Transaction toTransaction() {
		// 
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setDescription(description);
		transaction.setIncome(income);
		transaction.setExpense(expense);
		
		return transaction;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}
}
