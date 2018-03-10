package javastory.club.store;

import javastory.budget.store.CashBookStore;
import javastory.budget.store.TravelStore;
import javastory.budget.store.YearlyDueStore;

public interface StoreLycler {
	//
	public TravelStore requestTravelStore();
	public CashBookStore requestCashBookStore();
	public YearlyDueStore requestYearlyDueStore();
}
