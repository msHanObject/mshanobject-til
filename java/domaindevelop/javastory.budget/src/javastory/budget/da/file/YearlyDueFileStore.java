package javastory.budget.da.file;

import javastory.budget.da.file.io.YearlyDueFile;
import javastory.budget.entity.account.YearlyDue;
import javastory.budget.store.YearlyDueStore;
import javastory.budget.util.NoSuchYearlyDueException;
import javastory.budget.util.YearlyDueDuplicationException;

public class YearlyDueFileStore implements YearlyDueStore {
	//
	private YearlyDueFile yearlyDueFile;
	
	public YearlyDueFileStore() {
		//
		yearlyDueFile = new YearlyDueFile();
	}
	
	@Override
	public boolean isExist(String year) {
		// 
		return yearlyDueFile.exists(year);
	}
	
	@Override
	public void add(YearlyDue yearlyDue) {
		// 
		if (yearlyDueFile.exists(yearlyDue.getYear())) {
			throw new YearlyDueDuplicationException("The yearlydue already exists: " + yearlyDue.toString());
		}
		yearlyDueFile.write(yearlyDue);
	}
	
	@Override
	public YearlyDue retrieve(String year) {
		// 
		return yearlyDueFile.read(year);
	}

	@Override
	public void modify(YearlyDue yearlyDue) {
		// 
		yearlyDueFile.update(yearlyDue);
	}

	@Override
	public void delete(String year) {
		// 
		if (!yearlyDueFile.exists(year)) {
			throw new NoSuchYearlyDueException("No such yearlydue with year: " + year);
		}
		yearlyDueFile.delete(year);
	}
}