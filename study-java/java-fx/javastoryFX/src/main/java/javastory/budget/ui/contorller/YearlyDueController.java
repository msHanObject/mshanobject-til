package javastory.budget.ui.contorller;

import java.util.List;

import javastory.budget.service.YearlyDueService;
import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.util.exception.NoSuchYearlyDueException;
import javastory.budget.util.exception.YearlyDueDuplicationException;
import javastory.club.logic.ServiceLogicLycler;

public class YearlyDueController {
	//
	
	private YearlyDueService yearlyDueService;
	private YearlyDueDto currentYearlyDue;
	
	public YearlyDueController() {
		//
		if (currentYearlyDue == null) {			
			currentYearlyDue = new YearlyDueDto();
		}
		yearlyDueService = ServiceLogicLycler.shareInstance().createYearlyDueService();
	}
	
	public List<YearlyDueDto> findAll() {
		// 
		List<YearlyDueDto> years = yearlyDueService.retrieveAll();
		return years;
	}

	public YearlyDueDto find(String year) {
		//
		YearlyDueDto yearlyDueDto = null;
		if (year.isEmpty()) {
			System.out.println("Year can't be a left blank.");
		}
		
		try {
			yearlyDueDto = yearlyDueService.retrieve(year);
			currentYearlyDue = yearlyDueDto;
		} catch (NoSuchYearlyDueException e ) {
			System.out.println(e.getMessage());
		}
		return yearlyDueDto;
	}

	public YearlyDueDto requestcurrentYearlyDuelyDue() {
		// 
		return currentYearlyDue;
	}

	public void setCurrentYearlyDue(YearlyDueDto currentYearlyDue) {
		// 
		this.currentYearlyDue = currentYearlyDue;
	}

	public void register(YearlyDueDto newYearlyDue) {
		// 
		try {
			yearlyDueService.add(newYearlyDue);
		} catch (YearlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void modify() {
		// 
		try {
			yearlyDueService.modify(currentYearlyDue);
		} catch (YearlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(YearlyDueDto targetYearlyDue) {
		// 
		try {			
			yearlyDueService.delete(targetYearlyDue.getYear());
		} catch (NoSuchYearlyDueException e) {
			System.out.println(e.getMessage());
		}
	}
}