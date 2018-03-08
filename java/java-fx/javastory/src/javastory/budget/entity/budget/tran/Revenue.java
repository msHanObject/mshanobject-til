package javastory.budget.entity.budget.tran;

public class Revenue implements TranItem{
	//
	private double amount;
	private double vat;
	
	public Revenue() {
		//
	}
	
	public Revenue(double amount, double vat) {
		//
		this.amount = amount;
		this.vat = vat;
	}
	
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("[Rvenue] ");
		builder.append("amount: ").append(amount);
		builder.append(", vat: ").append(vat);
		builder.append(", type: ").append(getType().toString());
		
		return builder.toString();
	}
	
	public static Revenue getSample() {
		//
		Revenue revenue = new Revenue(440000,4400);
		return revenue;
	}
	
	@Override
	public TranType getType() {
		//
		return TranType.Revenue;
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
