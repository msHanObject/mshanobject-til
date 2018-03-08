package javastory.budget.ui.console;

import java.util.List;

import javastory.budget.entity.Entity;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.entity.budget.tran.Expense;
import javastory.budget.entity.budget.tran.Forward;
import javastory.budget.entity.budget.tran.Revenue;
import javastory.budget.entity.budget.tran.TranItem;
import javastory.budget.logic.ServiceLogicLycler;
import javastory.budget.service.CashBookService;
import javastory.budget.service.TranService;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TranDto;
import javastory.budget.share.IdName;
import javastory.budget.util.ConsoleUtil;
import javastory.budget.util.NoSuchCashBookException;
import javastory.budget.util.NoSuchTranException;
import javastory.budget.util.TranDuplicationException;
import javastory.budget.util.TranscationDuplicationException;

public class TranConsole {
	//
	private TranService tranService;
	private CashBookService cashBookService;
	
	private ConsoleUtil consoleUtil;
	
	public TranConsole() {
		//
		tranService = ServiceLogicLycler.shareInstance().createTranService();
		cashBookService = ServiceLogicLycler.shareInstance().createCashBookService();
		
		consoleUtil = new ConsoleUtil();
	}
	
	public CashBookDto findCashBook() {
		//
		CashBookDto targetCashBook= null;
		while (true) {
			//
			String cashBookId = consoleUtil.getValueOf("CashBookId to connect(0.Transaction menu)");
			if (cashBookId.equals("0")) {
				break;
			}
			if (cashBookId.isEmpty()) {
				System.out.println("CashBookId can't be left blank.");
				break;
			}
			
			try {
				targetCashBook = cashBookService.findOne(cashBookId);
				System.out.println("Connected CashBook: " + targetCashBook.toString());
				break;
			} catch (NoSuchCashBookException e) {
				System.out.println(e.getMessage());
			}
		}
		return targetCashBook;
	}
	
	public void register() {
		// 
		CashBookDto budgetConnected = findCashBook();
		while (true) {
			//
			if (budgetConnected == null) {
				return;
			}
			
			String tranId = consoleUtil.getValueOf("TranId(0.Transaction menu) [Enter autoId]");
			if (tranId.equals("0")) {
				return;
			}
			if (tranId.isEmpty()) {
				tranId = Entity.getSampleId();
			}
			
			String tranName = consoleUtil.getValueOf("TranName(0.Transcation menu)");
			if (tranName.equals("0")) {
				return;
			}
			if (tranName.isEmpty()) {
				System.out.println("TranName can't be left blank.");
				return;
			}
			
			IdName budget = budgetConnected.getCashBook();
			IdName tran = new IdName(tranId, tranName);
			List<Transaction> transactions = budgetConnected.getTransactions();
			
			try {
				//
				TranDto tranDto = new TranDto(budget, tran);
				System.out.println("Registered tran: " + tranDto.toString());
				tranService.register(tranDto);
				
				transactions.add(tranDto.toTran());
				budgetConnected.setTransactions(transactions);
				cashBookService.update(budgetConnected);
			} catch (TranscationDuplicationException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void find() {
		// 
		while(true) {
			//
			String tranId = consoleUtil.getValueOf("TranId to find(0.Transcation menu)");
			if (tranId.equals("0")) {
				return;
			}
			if (tranId.isEmpty()) {
				System.out.println("TranId can't be left blank.");
				return;
			}
			
			try {
				TranDto tranFound = tranService.retreive(tranId);
				System.out.println("Found transaction: " + tranFound.toString());
			} catch (NoSuchTranException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public TranDto findOne() {
		// 
		TranDto targetTran = null;
		while(true) {
			//
			String tranId = consoleUtil.getValueOf("TranId to find(0.Transcation menu)");
			if (tranId.equals("0")) {
				break;
			}
			if (tranId.isEmpty()) {
				System.out.println("TranId can't be left blank.");
				break;
			}
			
			try {
				targetTran = tranService.retreive(tranId);
				System.out.println("Found transaction: " + targetTran.toString());
				break;
			} catch (NoSuchTranException e) {
				System.out.println(e.getMessage());
			}
		}
		return targetTran;
	}
	
	public CashBookDto getCashBook(TranDto tranDto) {
		//
		String cashBookId = tranDto.getCashBookId();
		return cashBookService.findOne(cashBookId);
	}
	
	public void modify() {
		// 
		TranDto targetTran = findOne();
		if (targetTran == null) {
			return;
		}
		
		CashBookDto targetCashBook = getCashBook(targetTran);
		List<Transaction> transactions = targetCashBook.getTransactions();
		
		int index = 0;
		for (int i=0; i<transactions.size(); i++) {
			if (transactions.get(i).getId().equals(targetTran.toTran().getId())) {
				index = i;
				break;
			}
		}
		
		String newTranName = consoleUtil.getValueOf("New transaction name(0.Transcation menu) [Enter to no change]");
		if (newTranName.equals("0")) {
			return;
		}
		if (newTranName.isEmpty()) {
			newTranName = targetTran.getName();
		}
		
		if (newTranName.equals(targetTran.getName())) {
			System.out.println("There is nothing to change.");
			return;
		}
		IdName newTran = new IdName(targetTran.getId(), newTranName);

		String date = consoleUtil.getValueOf("Date");
		
		String tradingAccountId = consoleUtil.getValueOf("TradingAccountId");
		String tradingAccountName = consoleUtil.getValueOf("TradingAccountName");
		IdName tradingAccount = new IdName(tradingAccountId, tradingAccountName);
		
//		String iType = consoleUtil.getValueOf("Item type");
//		double amount = consoleUtil.getDoubleOf("Item Amount");
//		double vat = consoleUtil.getDoubleOf("Item vat");
//		TranItem item = null;
//		if (iType.toLowerCase().equals("expense")) {
//			item = new Expense(amount,vat); 
//		}
//		if (iType.toLowerCase().equals("revenue")) {
//			item = new Revenue(amount,vat);
//		}
//		if (iType.toLowerCase().equals("forward")) {
//			item = new Forward(amount,vat);
//		}
		
		String memo = consoleUtil.getValueOf("Memo");
		
		try {
			//
			targetTran.setTran(newTran);
			targetTran.setDate(date);
			targetTran.setTradingAccount(tradingAccount);
//			targetTran.setItem(item);
			targetTran.setMemo(memo);
			targetTran.setTime(System.currentTimeMillis());
			
			tranService.modify(targetTran);
			System.out.println("Modified transaction: " + targetTran.toString());
			
			transactions.set(index, targetTran.toTran());
			targetCashBook.setTransactions(transactions);
			cashBookService.update(targetCashBook);
		} catch (TranDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete() {
		// 
		TranDto targetTran = findOne();
		if (targetTran == null) {
			return;
		}
		
		CashBookDto targetCashBook = getCashBook(targetTran);
		List<Transaction> transactions = targetCashBook.getTransactions();
		
		int index = 0;
		for (int i=0; i<transactions.size(); i++) {
			if (transactions.get(i).getId().equals(targetTran.toTran().getId())) {
				index = i;
			}
		}
		
		String tranId = targetTran.getId();
		if (tranId.isEmpty()) {
			return;
		}
		
		try {
			//
			tranService.delete(tranId);
			System.out.println("Deleted transaction: " + targetTran.toString());
			
			transactions.remove(index);
			targetCashBook.setTransactions(transactions);
			cashBookService.update(targetCashBook);
		} catch (NoSuchTranException e) {
			System.out.println(e.getMessage());
		}
	}
}