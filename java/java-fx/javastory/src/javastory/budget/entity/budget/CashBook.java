package javastory.budget.entity.budget;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javastory.budget.share.DatePair;
import javastory.budget.share.IdName;
import javastory.budget.share.Socialian;

public class CashBook {
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
	
	
	public CashBook() {
		//
		this.manager = Socialian.getSample();
		this.currencyCode = Currency.getInstance(Locale.getDefault()).getCurrencyCode();
		this.balance = 0;
		this.preparationTerm = DatePair.getSample();
		this.memo = "";
		this.time = System.currentTimeMillis();
		this.transactions = new ArrayList<Transaction>();
	}
	
	public CashBook(IdName travel, IdName cashBook) {
		this();
		this.travel = travel;
		this.cashBook = cashBook;
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
	
	public String getTravelId() {
		//
		return travel.getId();
	}
	
	public String getTravelName() {
		//
		return travel.getName();
	}
	
	public String getId() {
		//
		return cashBook.getId();
	}
	
	public String getName() {
		//
		return cashBook.getName();
	}
	
	public void setTravelId(String id) {
		//
		travel.setId(id);
	}
	
	public void setTravelName(String name) {
		//
		travel.setName(name);
	}
	
	public void setCashBookId(String id) {
		//
		cashBook.setId(id);
	}
	
	public void setCashBookName(String name) {
		//
		cashBook.setName(name);
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
