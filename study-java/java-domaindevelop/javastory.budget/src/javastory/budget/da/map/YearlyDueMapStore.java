package javastory.budget.da.map;

import java.util.Map;

import javastory.budget.da.map.io.MemoryMap;
import javastory.budget.entity.account.YearlyDue;
import javastory.budget.store.YearlyDueStore;
import javastory.budget.util.YearlyDueDuplicationException;

public class YearlyDueMapStore implements YearlyDueStore {
	//
	private Map<String,YearlyDue> yearlyDueMap;
	
	public YearlyDueMapStore() {
		//
		yearlyDueMap = MemoryMap.getInstance().getYearlyDueMap();
	}
	
	@Override
	public YearlyDue retrieve(String year) {
		// 
		return yearlyDueMap.get(year);
	}

	@Override
	public void add(YearlyDue yearlyDue) {
		// 
		String year = yearlyDue.getYear();
		if(yearlyDueMap.get(year) != null) {
			throw new YearlyDueDuplicationException("The yearlydue is already exists with year --> " + year);
		}
		yearlyDueMap.put(year, yearlyDue);
	}

	@Override
	public void modify(YearlyDue yearlyDue) {
		// 
		yearlyDueMap.put(yearlyDue.getYear(), yearlyDue);
	}

	@Override
	public boolean isExist(String year) {
		// 
		if (yearlyDueMap.get(year) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void delete(String year) {
		// 
		yearlyDueMap.remove(year);
	}
}