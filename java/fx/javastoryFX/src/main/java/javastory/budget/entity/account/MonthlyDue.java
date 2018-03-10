package javastory.budget.entity.account;

public class MonthlyDue {
	private static final String DEFAULT_DELIMITER = "||";
	//
	private int month;
	private double amount;
//	private String currencyCode;
//	private DuesType type;
//	private long time;
	
	public MonthlyDue(int month, double amount) {
		//
		this.month = month;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("MonthlyDue").append(DEFAULT_DELIMITER);
		builder.append("month").append(DEFAULT_DELIMITER);
		builder.append(month).append(DEFAULT_DELIMITER);
		builder.append("amount").append(DEFAULT_DELIMITER);
		builder.append(amount).append(DEFAULT_DELIMITER);
		
		return builder.toString();
	}
	
//	public static MonthlyDue getSample() {
//		//
//		MonthlyDue monthlyDue = new MonthlyDue(10,10000);
//		monthlyDue.setCurrencyCode("MSCoin");
//		monthlyDue.setType(DuesType.Regular);
//		monthlyDue.setTime(Calendar.getInstance().getTimeInMillis());
//		
//		return monthlyDue;
//	}
	
	public int getMonth() {
		//
		return month;
	}
	
	public void setMonth(int month) {
		//
		this.month = month;
	}
	
	public double getAmount() {
		//
		return amount;
	}
	
	public void setAmount(double amount) {
		//
		this.amount  = amount;
	}
	
//	public String getCurrencyCode() {
//		//
//		return currencyCode;
//	}
//	
//	public void setCurrencyCode(String currencyCode) {
//		//
//		this.currencyCode = currencyCode;
//	}
//	
//	public DuesType getType() {
//		//
//		return type;
//	}
//	
//	public void setType(DuesType type) {
//		//
//		this.type = type;
//	}
//	
//	public long getTime() {
//		//
//		return time;
//	}
//	
//	public void setTime(long time) {
//		//
//		this.time = time;
//	}
}