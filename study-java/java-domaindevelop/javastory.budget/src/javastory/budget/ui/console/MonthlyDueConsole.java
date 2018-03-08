package javastory.budget.ui.console;

import java.util.Iterator;
import java.util.List;

import javastory.budget.entity.account.MonthlyDue;
import javastory.budget.logic.ServiceLogicLycler;
import javastory.budget.service.YearlyDueService;
import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.util.ConsoleUtil;
import javastory.budget.util.MonthlyDueDuplicationException;
import javastory.budget.util.YearlyDueDuplicationException;

public class MonthlyDueConsole {
	//
	private static final int JANUARY = 1;
	private static final int DECEMBER = 12;
	
	private YearlyDueDto currentYear;
	private List<MonthlyDue> currentMonthlyDues; 
	private ConsoleUtil consoleUtil;
	private YearlyDueService yearlyDueService;
	
	public MonthlyDueConsole(YearlyDueDto currentYear) {
		// 
		this.currentYear = currentYear;
		currentMonthlyDues = currentYear.getMonthlyDues();
		yearlyDueService = ServiceLogicLycler.shareInstance().createYearlyDueService();
		consoleUtil = new ConsoleUtil();
	}

	public void register() {
		//
		MonthlyDue monthlyDue;
		int month = consoleUtil.getIntegerOf("Month to register(0.MonthlyDue menu)");
		if (month == 0) {
			return;
		}
		if (month >= JANUARY && month <= DECEMBER) {
		} else {
			System.out.println("You can only enter between January and December.");
			return;
		}
		
		double amount = consoleUtil.getDoubleOf("Amount to register(0.MonthlyDue menu)");
		if (amount == 0) {
			return;
		}
		
		try {
			monthlyDue = new MonthlyDue(month, amount);
			currentMonthlyDues.add(monthlyDue);
			System.out.println("Registered monthlydue: " + monthlyDue.toString());
		} catch (MonthlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
		
		try {			
			currentYear.setMonthlyDues(currentMonthlyDues);
			yearlyDueService.modify(currentYear);
		} catch (YearlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void find() {
		//
		while (true) {
			//
			int monthFound = consoleUtil.getIntegerOf("Month to find(0.MonthlyDue menu)");
			if (monthFound == 0) {
				return;
			}
			
			Iterator<MonthlyDue> monthIter = currentMonthlyDues.iterator();
			MonthlyDue monthlyDue;
			
			boolean foundResult = false;
			
			while (monthIter.hasNext()) {
				monthlyDue = monthIter.next();
				if (monthlyDue.getMonth() == monthFound) {
					System.out.println("Found monthlydue: " + monthlyDue.toString());
					foundResult = true;
				}
			}
			if (foundResult == false) {
				System.out.println("There is no exist monthlydue with month --> " + monthFound);
			}
		}
	}
	
	public MonthlyDue findOne() {
		//
		MonthlyDue targetMonth = null;
		while(true) {
			//
			int monthFound = consoleUtil.getIntegerOf("Month to find(0.MonthlyDue menu)");
			if (monthFound == 0) {
				break;
			}
			
			double amountFound = consoleUtil.getDoubleOf("Amount to find");
			
			Iterator<MonthlyDue> monthIter = currentMonthlyDues.iterator();
			
			while (monthIter.hasNext()) {
				targetMonth = monthIter.next();
				if (targetMonth.getMonth() == monthFound && targetMonth.getAmount() == amountFound) {
					System.out.println("Found monthlyDue: " + targetMonth.toString());
					return targetMonth;
				}
			}
			targetMonth = null;
			break;
		}
		return targetMonth;
	}
	
	public void modify() {
		// 
		MonthlyDue target = findOne();
		int index = currentMonthlyDues.indexOf(target);
		if (target == null) {
			System.out.println("There is no exist target.");
			return;
		}
		
		try {
			double newAmount = consoleUtil.getDoubleOf("New amount to modify");
			target.setAmount(newAmount);
			System.out.println("Modified monthlydue to --> " + target.toString());
		} catch (MonthlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}

		try {			
			System.out.println("target index: " + index);
			currentMonthlyDues.set(index, target);
			currentYear.setMonthlyDues(currentMonthlyDues);
			yearlyDueService.modify(currentYear);
		} catch (YearlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void delete() {
		// 
		MonthlyDue target = findOne();
		if (target == null) {
			System.out.println("There is no exist target.");
			return;
		}
		
		String confirmStr = consoleUtil.getValueOf("Do you want delete the target?(Yes: y, No: n)");
		if (confirmStr.toLowerCase().equals("yes") || confirmStr.toLowerCase().equals("y")) {
			currentMonthlyDues.remove(currentMonthlyDues.indexOf(target));
			System.out.println("Deleted target: " + target.toString());
			
			currentYear.setMonthlyDues(currentMonthlyDues);
			yearlyDueService.modify(currentYear);
		}
		if (confirmStr.toLowerCase().equals("no") || confirmStr.toLowerCase().equals("n")) {
			System.out.println("Cancled deleting target.");
		}
	}
}