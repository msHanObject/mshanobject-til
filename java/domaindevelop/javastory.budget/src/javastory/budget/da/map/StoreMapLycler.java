package javastory.budget.da.map;

import javastory.budget.store.CashBookStore;
import javastory.budget.store.StoreLycler;
import javastory.budget.store.TranStore;
import javastory.budget.store.TravelStore;
import javastory.budget.store.YearlyDueStore;

public class StoreMapLycler implements StoreLycler {
    //
    private static StoreMapLycler lycler;

    private StoreMapLycler() {
    };

    public static StoreMapLycler getInstance() {
            //
            if (lycler == null) {
                    lycler = new StoreMapLycler();
            }

            return lycler;
    }

	@Override
	public TravelStore requestTravelStore() {
		// 
		return new TravelMapStore();
	}

	public CashBookStore requestCashBookStore() {
		// 
		return new CashBookMapStore();
	}

	public TranStore requestTranStore() {
		// 
		return new TranMapStore();
	}

	public YearlyDueStore requestYearlyDueStore() {
		// 
		return new YearlyDueMapStore();
	}
}