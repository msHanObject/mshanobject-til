package javastory.budget.service.dto;

import javastory.budget.entity.budget.Transaction;
import javastory.budget.entity.budget.tran.TranItem;
import javastory.budget.share.IdName;

public class TranDto {
	//
	private static final String DEFAULT_DELIMITER = "||";
	
	private IdName cashBook;
	private IdName tran;
	
	private String date;
	private IdName tradingAccount; 			//about transaction
//	private TranItem item; 
	private String memo; 
	private long time; 
	
	public TranDto(IdName cashBook, IdName tran) {
		//
		this.cashBook = cashBook;
		this.tran = tran;
	}
	
	public TranDto(Transaction transaction) {
		// 
		this.cashBook = transaction.getCashBook();
		this.tran = transaction.getTran();
		this.date = transaction.getDate();
		this.tradingAccount = transaction.getTradingAccount();
//		this.item = transaction.getItem();
		this.memo = transaction.getMemo();
		this.time = transaction.getTime();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("Transaction").append(DEFAULT_DELIMITER);
		builder.append("CashBookId").append(DEFAULT_DELIMITER);
		builder.append(getCashBookId()).append(DEFAULT_DELIMITER);
		builder.append("CashBookName").append(DEFAULT_DELIMITER);
		builder.append(getCashBookName()).append(DEFAULT_DELIMITER);
		builder.append("TranId").append(DEFAULT_DELIMITER);
		builder.append(getId()).append(DEFAULT_DELIMITER);
		builder.append("TranName").append(DEFAULT_DELIMITER);
		builder.append(getName()).append(DEFAULT_DELIMITER);
		builder.append("Date").append(DEFAULT_DELIMITER);
		builder.append(date).append(DEFAULT_DELIMITER);
		builder.append("TradingAccount").append(DEFAULT_DELIMITER);
		builder.append(tradingAccount).append(DEFAULT_DELIMITER);
//		builder.append("Item").append(DEFAULT_DELIMITER);
//		builder.append(item).append(DEFAULT_DELIMITER);
		builder.append("memo").append(DEFAULT_DELIMITER);
		builder.append(memo).append(DEFAULT_DELIMITER);
		builder.append("time").append(DEFAULT_DELIMITER);
		builder.append(time).append(DEFAULT_DELIMITER);
		
		return builder.toString();
	}

	public Transaction toTran() {
		// 
		Transaction transaction = new Transaction();
		transaction.setCashBook(cashBook);
		transaction.setTran(tran);
		transaction.setDate(date);
		transaction.setTradingAccount(tradingAccount);
//		transaction.setItem(item);
		transaction.setMemo(memo);
		transaction.setTime(time);
		return transaction;
	}
	
	public String getCashBookName() {
		//
		return cashBook.getName();
	}
	
	public String getCashBookId() {
		// 
		return cashBook.getId();
	}
	
	public String getId() {
		//
		return tran.getId();
	}
	
	public String getName() {
		// 
		return tran.getName();
	}
	
	public void setCashBookName(String name) {
		//
		cashBook.setName(name);
	}
	
	public void setCashBookId(String id) {
		//
		cashBook.setId(id);
	}
	
	public void setTranName(String name) {
		//
		tran.setName(name);
	}
	
	public void setTranId(String id) {
		//
		tran.setId(id);
	}
	
	public IdName getCashBook() {
		return cashBook;
	}

	public void setCashBook(IdName cashBook) {
		this.cashBook = cashBook;
	}

	public IdName getTran() {
		return tran;
	}

	public void setTran(IdName tran) {
		this.tran = tran;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public IdName getTradingAccount() {
		return tradingAccount;
	}

	public void setTradingAccount(IdName tradingAccount) {
		this.tradingAccount = tradingAccount;
	}

//	public TranItem getItem() {
//		return item;
//	}
//
//	public void setItem(TranItem item) {
//		this.item = item;
//	}

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
}
