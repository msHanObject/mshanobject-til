package javastory.budget.service.dto;

import java.util.List;

import javastory.budget.entity.budget.CashBook;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.share.DatePair;
import javastory.budget.share.IdName;
import javastory.budget.share.Socialian;

public class CashBookDto {
	//
	private static final String DEFAULT_DELIMITER = "||";
	
	private IdName travel;
	private IdName cashBook;
	
	private Socialian manager;
	private String bankAccount;// 
	private String currencyCode; 
	private double budgetGoal; //
	private double monthlyDue;//
	private double balance;
	private DatePair preparationTerm; 
	private String memo;
	private long time; 

	private List<Transaction> transactions; 
	
	public CashBookDto(IdName travel, IdName budget) {
		// 
		this.travel = travel;
		this.cashBook = budget;
	}
	
	public CashBookDto(CashBook budget) {
		// 
		this.travel = budget.getTravel();
		this.cashBook = budget.getCashBook();
		this.manager = budget.getManager();
		this.bankAccount = budget.getBankAccount();
		this.currencyCode = budget.getCurrencyCode();
		this.budgetGoal = budget.getBudgetGoal();
		this.monthlyDue = budget.getMonthlyDue();
		this.balance = budget.getBalance();
		this.preparationTerm = budget.getPreparationTerm();
		this.memo = budget.getMemo();
		this.time = budget.getTime();
		this.transactions = budget.getTransactions();
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("CashBook").append(DEFAULT_DELIMITER);
		builder.append("travelId").append(DEFAULT_DELIMITER);
		builder.append(getTravelId()).append(DEFAULT_DELIMITER);
		builder.append("travelName").append(DEFAULT_DELIMITER);
		builder.append(getTravelName()).append(DEFAULT_DELIMITER);
		builder.append("cashBookId").append(DEFAULT_DELIMITER);
		builder.append(getId()).append(DEFAULT_DELIMITER);
		builder.append("cashBookName").append(DEFAULT_DELIMITER);
		builder.append(getName()).append(DEFAULT_DELIMITER);
		builder.append("manager").append(DEFAULT_DELIMITER);
		builder.append(manager).append(DEFAULT_DELIMITER);
		builder.append("bankAccount").append(DEFAULT_DELIMITER);
		builder.append(bankAccount).append(DEFAULT_DELIMITER);
		builder.append("currencyCode").append(DEFAULT_DELIMITER);
		builder.append(currencyCode).append(DEFAULT_DELIMITER);
		builder.append("budgetGoal").append(DEFAULT_DELIMITER);
		builder.append(budgetGoal).append(DEFAULT_DELIMITER);
		builder.append("monthlyDue").append(DEFAULT_DELIMITER);
		builder.append(monthlyDue).append(DEFAULT_DELIMITER);
		builder.append("balance").append(DEFAULT_DELIMITER);
		builder.append(balance).append(DEFAULT_DELIMITER);
		builder.append("preparationTerm").append(DEFAULT_DELIMITER);
		builder.append(preparationTerm).append(DEFAULT_DELIMITER);
		builder.append("memo").append(DEFAULT_DELIMITER);
		builder.append(memo).append(DEFAULT_DELIMITER);
		builder.append("time").append(DEFAULT_DELIMITER);
		builder.append(time).append(DEFAULT_DELIMITER);
		builder.append("transactions").append(DEFAULT_DELIMITER);
		builder.append(transactions).append(DEFAULT_DELIMITER);
		
		return builder.toString();
	}

	public CashBook toCashBook() {
		// 
		CashBook budget = new CashBook();
		budget.setCashBook(cashBook);
		budget.setTravel(travel);
		budget.setManager(manager);
		budget.setBankAccount(bankAccount);
		budget.setCurrencyCode(currencyCode);
		budget.setBudgetGoal(budgetGoal);
		budget.setMonthlyDue(monthlyDue);
		budget.setBalance(balance);
		budget.setPreparationTerm(preparationTerm);
		budget.setMemo(memo);
		budget.setTime(time);
		budget.setTransactions(transactions);
		return budget;
	}

	public String getTravelName() {
		//
		return travel.getName();
	}
	
	public String getTravelId() {
		//
		return travel.getId();
	}
	
	public void setTravelName(String name) {
		//
		travel.setName(name);
	}
	
	public void setTravelId(String id) {
		//
		travel.setId(id);
	}

	public void setCashBoookId(String id) {
		//
		cashBook.setId(id);
	}
	
	public String getId() {
		//
		return cashBook.getId();
	}
	
	public String getName() {
		// 
		return cashBook.getName();
	}
	
	public void setCashBookName(String cashBookName) {
		// 
		cashBook.setName(cashBookName);
	}
	
	public IdName getTravel() {
		return travel;
	}


	public void setTravel(IdName travel) {
		this.travel = travel;
	}


	public IdName getCashBook() {
		return cashBook;
	}


	public void setCashBook(IdName cashBook) {
		this.cashBook = cashBook;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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

	public DatePair getPreparationTerm() {
		return preparationTerm;
	}

	public void setPreparationTerm(DatePair preparationTerm) {
		this.preparationTerm = preparationTerm;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}