package javastory.budget.entity.transaction;

public class Forward implements TranItem{
	//
	private double amount;
	private double vat;
	
	public Forward() {
		//
	}
	
	public Forward(double amount, double vat) {
		this.amount = amount;
		this.vat = vat;
	}
	
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("[Forward] ");
		builder.append("amount: ").append(amount);
		builder.append(", vat: ").append(vat);
		builder.append(", type: ").append(getType().toString());
		
		return builder.toString();
	}
	
	public static Forward getSample() {
		//
		Forward forward = new Forward(33000,3300);
		return forward;
	}
	
	@Override
	public TranType getType() {
		//
		return TranType.Forward;
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