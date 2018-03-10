package javastory.budget.da.map.io;

import java.util.LinkedHashMap;
import java.util.Map;

import javastory.budget.entity.account.YearlyDue;
import javastory.budget.entity.budget.CashBook;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.entity.travel.Travel;

public class MemoryMap {
	private static MemoryMap singletonMap;

    private Map<String,Travel>  travelMap;
    private Map<String,CashBook>  cashBookMap;
    private Map<String,Transaction> transactionMap;
    private Map<String,YearlyDue> yearlyDueMap;

    private MemoryMap() {
        //
        this.travelMap = new LinkedHashMap<>();
        this.cashBookMap = new LinkedHashMap<>();
        this.transactionMap = new LinkedHashMap<>();
        this.yearlyDueMap = new LinkedHashMap<>();
    }

    public static MemoryMap getInstance() {
    		//
	    	if (singletonMap == null) {
	    		singletonMap = new MemoryMap();
	    	}

	    	return singletonMap;
    }

	public static MemoryMap getSingletonMap() {
		return singletonMap;
	}

	public static void setSingletonMap(MemoryMap singletonMap) {
		MemoryMap.singletonMap = singletonMap;
	}

	public Map<String, Travel> getTravelMap() {
		return travelMap;
	}

	public void setTravelMap(Map<String, Travel> travelMap) {
		this.travelMap = travelMap;
	}

	public Map<String, CashBook> getCashBookMap() {
		return cashBookMap;
	}

	public void setCashBookMap(Map<String, CashBook> cashBookMap) {
		this.cashBookMap = cashBookMap;
	}

	public Map<String, YearlyDue> getYearlyDueMap() {
		return yearlyDueMap;
	}

	public void setYearlyDueMap(Map<String, YearlyDue> yearlyDueMap) {
		this.yearlyDueMap = yearlyDueMap;
	}

	public Map<String, Transaction> getTransactionMap() {
		return transactionMap;
	}

	public void setTransactionMap(Map<String, Transaction> transactionMap) {
		this.transactionMap = transactionMap;
	}
}
