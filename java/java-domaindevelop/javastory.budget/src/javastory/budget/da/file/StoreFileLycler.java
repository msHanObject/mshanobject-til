package javastory.budget.da.file;

import javastory.budget.store.CashBookStore;
import javastory.budget.store.StoreLycler;
import javastory.budget.store.TranStore;
import javastory.budget.store.TravelStore;
import javastory.budget.store.YearlyDueStore;

public class StoreFileLycler implements StoreLycler{

	private static StoreLycler shareInstance;

	public StoreFileLycler() {
		//
	}
	
	public synchronized static StoreLycler shareInstance() {
		//
		if (shareInstance == null) {
			shareInstance = new StoreFileLycler();
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
	public TranStore requestTranStore() {
		// 
		return new TranFileStore();
	}

	@Override
	public YearlyDueStore requestYearlyDueStore() {
		// 
		return new YearlyDueFileStore();
	}

}