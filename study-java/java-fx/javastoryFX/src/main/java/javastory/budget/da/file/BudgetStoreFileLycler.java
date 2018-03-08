package javastory.budget.da.file;

import javastory.budget.store.CashBookStore;
import javastory.budget.store.TravelStore;
import javastory.budget.store.YearlyDueStore;
import javastory.club.store.StoreLycler;

public class BudgetStoreFileLycler implements StoreLycler{

	private static StoreLycler shareInstance;

	public BudgetStoreFileLycler() {
		//
	}
	
	public synchronized static StoreLycler shareInstance() {
		//
		if (shareInstance == null) {
			shareInstance = new BudgetStoreFileLycler();
		}
		return shareInstance;
	}
	
	@Override
	public TravelStore requestTravelStore() {
		// 
		return new TravelFileStore();
	}

	@Override
	public CashBookStore requestCashBookStore() {
		// 
		return new CashBookFileStore();
	}

	@Override
	public YearlyDueStore requestYearlyDueStore() {
		// 
		return new YearlyDueFileStore();
	}

}