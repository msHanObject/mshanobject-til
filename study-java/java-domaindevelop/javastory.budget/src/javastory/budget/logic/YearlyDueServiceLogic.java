package javastory.budget.logic;

import javastory.budget.da.file.StoreFileLycler;
import javastory.budget.entity.account.YearlyDue;
import javastory.budget.service.YearlyDueService;
import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.store.YearlyDueStore;
import javastory.budget.util.NoSuchYearlyDueException;
import javastory.budget.util.YearlyDueDuplicationException;

public class YearlyDueServiceLogic implements YearlyDueService {
	//
	private YearlyDueStore yearlyDueStore;
	
	public YearlyDueServiceLogic() {
		//
//		yearlyDueStore = StoreMapLycler.getInstance().requestYearlyDueStore();
		yearlyDueStore = StoreFileLycler.shareInstance().requestYearlyDueStore();
	}
	
	@Override
	public void add(YearlyDueDto yearlyDueDto) {
		//
		String year = yearlyDueDto.getYear();
		if (yearlyDueStore.isExist(year)) {
			throw new YearlyDueDuplicationException("There is already exists with --> " + year);
		}
		
		YearlyDue yearlyDue = yearlyDueDto.toYearlyDue(); 
		yearlyDueStore.add(yearlyDue);
	}

	@Override
	public YearlyDueDto retrieve(String yearFound) {
		// 
		YearlyDue yearlyDue = yearlyDueStore.retrieve(yearFound); 
		if (yearlyDue == null) {
			throw new NoSuchYearlyDueException("There is no yearlydue with year --> " + yearFound);
		}
		
		return new YearlyDueDto(yearlyDue);
	}

	@Override
	public void modify(YearlyDueDto target) {
		// 
		yearlyDueStore.modify(target.toYearlyDue());
	}

	@Override
	public void delete(String year) {
		// 
		if(!yearlyDueStore.isExist(year)) {
			throw new NoSuchYearlyDueException("There is no yearlydue with year --> " + year);
		}
		yearlyDueStore.delete(year);
	}
}