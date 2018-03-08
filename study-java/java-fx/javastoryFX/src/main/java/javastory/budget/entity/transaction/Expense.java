package javastory.budget.entity.transaction;


public class Expense implements TranItem {
	//
	private double amount;
	private double vat;
	
	public Expense() {
		//
	}
	
	public Expense(double amount, double vat) {
		this.amount = amount;
		this.vat = vat;
	}
	
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("[Expense] ");
		builder.append("amount : ").append(amount);
		builder.append(", vat : ").append(vat);
		builder.append(", type : ").append(getType().toString());
		
		return builder.toString();
	}
	
	public static Expense getSample() {
		//
		Expense expense = new Expense(220000,2200);
		return expense;
	}
	
	@Override
	public TranType getType() {
		//
		return TranType.Expense;
	}
	
	@Override
	public double getAmount() {
		//
		return amount;
	}
	
	public void setAmount(double amount) {
		//
		this.amount = amount;
	}
	
	@Override
	public double getVat() {
		//
		return vat;
	}
	
	public void setVat(double vat) {
		//
		this.vat = vat;
	}
	
	public static void main(String[] args) {
		//
		System.out.println(getSample());
	}
}