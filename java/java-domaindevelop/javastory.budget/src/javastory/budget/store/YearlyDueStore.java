package javastory.budget.store;

import javastory.budget.entity.account.YearlyDue;

public interface YearlyDueStore {

	public YearlyDue retrieve(String year);

	public void add(YearlyDue yearlyDue);

	public void modify(YearlyDue yearlyDue);
	
	public boolean isExist(String year);

	public void delete(String year);

}
