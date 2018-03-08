package javastory.budget.ui.console;

import javastory.budget.logic.ServiceLogicLycler;
import javastory.budget.service.CashBookService;
import javastory.budget.service.TravelService;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.share.DatePair;
import javastory.budget.share.IdName;
import javastory.budget.share.Socialian;
import javastory.budget.util.CashBookDuplicationException;
import javastory.budget.util.ConsoleUtil;
import javastory.budget.util.NoSuchCashBookException;
import javastory.budget.util.NoSuchTravelException;

public class CashBookConsole {
	//
	private ConsoleUtil consoleUtil;
	private CashBookService cashBookService;
	private TravelService travelService;
	
	public CashBookConsole() {
		//
		consoleUtil = new ConsoleUtil();
		cashBookService = ServiceLogicLycler.shareInstance().createCashBookService();
		travelService = ServiceLogicLycler.shareInstance().createTravelService();
	}
	
	public TravelDto findTravel() {
		//
		TravelDto travelFound = null;
		while (true) {
			//
			String targetId = consoleUtil.getValueOf("Target travel Id(0.CashBook menu)");
			if (targetId.equals("0")) {
				break;
			}
			if (targetId.isEmpty()) {
				System.out.println("Travel id can't be left blank.");
				break;
			}
			
			try {
				travelFound = travelService.findTravel(targetId);
				System.out.println("Found travel: " + travelFound.toString());
				break;
			} catch (NoSuchTravelException e) {
				System.out.println(e.getMessage());
			}
			travelFound = null;
		}
		return travelFound;
	}

	public void register() {
		// 
		while (true) {
			//
			TravelDto targetTravel = findTravel();
			if (targetTravel == null) {
				return;
			}
			
			String cashBookName = consoleUtil.getValueOf("CashBook name to register(0.CashBookMenu)");
			if (cashBookName.equals("0")) {
				return;
			}
			if (cashBookName.isEmpty()) {
				System.out.println("CashBook name can't be left blank.");
				return;
			}
			
			IdName travel = targetTravel.getTravel();
			IdName budget = new IdName(targetTravel.getId(), cashBookName);
			try {
				CashBookDto cashBookDto = new CashBookDto(travel, budget);
				cashBookService.register(cashBookDto);
				System.out.println("Registered cashbook: " + cashBookDto.toString());
			} catch (CashBookDuplicationException| NoSuchTravelException | NoSuchCashBookException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public void find() {
		// 
		CashBookDto cashBookFound= null;
		while (true) {
			//
			String cashBookId = consoleUtil.getValueOf("CashBook id to search(0.CashBookMenu)");
			if (cashBookId.equals("0")) {
				break;
			}
			if (cashBookId.isEmpty()) {
				System.out.println("CashBook id can't be a null");
				break;
			}
			
			try {
				cashBookFound = cashBookService.findOne(cashBookId);
				System.out.println("Found cashbook: " + cashBookFound.toString());
			} catch (NoSuchCashBookException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public CashBookDto findOne() {
		// 
		CashBookDto cashBookFound= null;
		while (true) {
			//
			String cashBookId = consoleUtil.getValueOf("CashBook id to search(0.CashBookMenu)");
			if (cashBookId.equals("0")) {
				break;
			}
			if (cashBookId.isEmpty()) {
				System.out.println("CashBook id can't be a null");
				break;
			}
			
			try {
				cashBookFound = cashBookService.findOne(cashBookId);
				System.out.println("Found cashbook: " + cashBookFound.toString());
				break;
			} catch (NoSuchCashBookException e) {
				System.out.println(e.getMessage());
			}
		}
		return cashBookFound;
	}

	public void update() {
		// 
		CashBookDto targetCashBook = findOne();
		if (targetCashBook == null) {
			return;
		}
		
		String cashBookName = consoleUtil.getValueOf(" new cashbook name to update(0.CashBookMenu)");
		if (cashBookName.equals("0")) {
			return;
		}
		if (!cashBookName.isEmpty()) {
			targetCashBook.setCashBookName(cashBookName);
		}
		
		String confirmStr = consoleUtil.getValueOf("Do you want modify manager? (Yes: y, No: n)");
		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			String socialId = consoleUtil.getValueOf("SocialId");
			String firstName = consoleUtil.getValueOf("FirstName");
			String familyName = consoleUtil.getValueOf("FamilyName");
			String email = consoleUtil.getValueOf("Email");
			
			Socialian manager = new Socialian(socialId, firstName, familyName, email);
			targetCashBook.setManager(manager);
		}
		if (confirmStr.toLowerCase().equals("n") || confirmStr.toLowerCase().equals("no")) {
			System.out.println("The manager is maintained.");
		}
		
		String bankAccount = consoleUtil.getValueOf("BankAccount [Enter to maintain]");
		if (bankAccount.isEmpty()) {
			bankAccount = targetCashBook.getBankAccount();
		}
		
		String currencyCode = consoleUtil.getValueOf("CurrencyCode [Enter to maintain]");
		if (currencyCode.isEmpty()) {
			currencyCode = targetCashBook.getCurrencyCode();
		}
		
		double budgetGoal = consoleUtil.getDoubleOf("BudgetGoal [Enter -1 to maintain]");
		if (budgetGoal == -1) {
			budgetGoal = targetCashBook.getBudgetGoal();
		}
		
		double monthlyDue = consoleUtil.getDoubleOf("MonthlyDue [Enter -1 to maintain]");
		if (monthlyDue == -1) {
			monthlyDue = targetCashBook.getMonthlyDue();
		}
		
		double balance = consoleUtil.getDoubleOf("Balance [Enter -1 to maintain]");
		if (balance == -1) {
			balance = targetCashBook.getBalance();
		}
		
		String startDate = consoleUtil.getValueOf("StartDate [Enter to maintain]");
		if (startDate.isEmpty()) {
			startDate = targetCashBook.getPreparationTerm().getStartDate();
		}
		
		String endDate = consoleUtil.getValueOf("EndDate [Enter to maintain]");
		if (endDate.isEmpty()) {
			endDate = targetCashBook.getPreparationTerm().getEndDate();
		}
		DatePair preparationTerm = new DatePair(startDate, endDate);
		
		String memo = consoleUtil.getValueOf("Memo [Enter to maintain]");
		if (memo.isEmpty()) {
			memo = targetCashBook.getMemo();
		}
		
		targetCashBook.setBankAccount(bankAccount);
		targetCashBook.setCurrencyCode(currencyCode);
		targetCashBook.setBudgetGoal(budgetGoal);
		targetCashBook.setMonthlyDue(monthlyDue);
		targetCashBook.setBalance(balance);
		targetCashBook.setPreparationTerm(preparationTerm);
		targetCashBook.setMemo(memo);
		targetCashBook.setTime(System.currentTimeMillis());
		
		try {
			cashBookService.update(targetCashBook);
			System.out.println("Updated cashbook --> " + targetCashBook.toString());
		} catch (CashBookDuplicationException | NoSuchCashBookException e) {
			System.out.println(e.getMessage());
		}
	}

	public void remove() {
		// 
		CashBookDto targetCashBook = findOne();
		if (targetCashBook == null) {
			return;
		}
		
		System.out.println("Removing a cashbook --> " + targetCashBook.getName());
		cashBookService.remove(targetCashBook.getId());
	}
}
