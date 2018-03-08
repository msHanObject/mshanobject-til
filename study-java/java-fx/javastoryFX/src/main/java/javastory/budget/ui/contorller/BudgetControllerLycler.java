package javastory.budget.ui.contorller;

public class BudgetControllerLycler {
	//
	private static BudgetControllerLycler instance;
	
	private TravelController travelController;
	private CashBookController cashBookController;
	
	private BudgetControllerLycler() {
		//
	}
	
	public synchronized static BudgetControllerLycler shareInstance() {
		//
		if (instance == null) {
			instance = new BudgetControllerLycler();
		}
		return instance;
	}
	
	public TravelController requestTravelController() {
		//
		if (travelController == null) {
			travelController = new TravelController();
		}
		return travelController;
	}
	
	public CashBookController requestCashBookController() {
		//
		if (cashBookController == null) {
			cashBookController = new CashBookController();
		}
		return cashBookController;
	}
}
