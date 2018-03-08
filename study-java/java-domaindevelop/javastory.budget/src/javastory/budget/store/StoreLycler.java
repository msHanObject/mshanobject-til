package javastory.budget.store;

public interface StoreLycler {
	//
	public TravelStore requestTravelStore();
	public CashBookStore requestCashBookStore();
	public TranStore requestTranStore();
	public YearlyDueStore requestYearlyDueStore();
}
