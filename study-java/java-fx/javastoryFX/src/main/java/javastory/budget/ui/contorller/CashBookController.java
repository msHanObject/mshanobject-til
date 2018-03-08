package javastory.budget.ui.contorller;

import java.util.List;

import javastory.budget.service.CashBookService;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.util.exception.CashBookDuplicationException;
import javastory.budget.util.exception.NoSuchCashBookException;
import javastory.club.logic.ServiceLogicLycler;

public class CashBookController {
	//
	private CashBookService cashBookService;
	private CashBookDto currentCashBook;
	
	public CashBookController() {
		//
		cashBookService = ServiceLogicLycler.shareInstance().createCashBookService();
		currentCashBook = new CashBookDto();
	}
	
	public CashBookDto getCurrentCashBook() {
		return this.currentCashBook;
	}
	
	public void setCurrentCashBook(CashBookDto currentCashBook) {
		this.currentCashBook = currentCashBook;
	}
	
	public CashBookDto findOne(String cashBookId) {
		//
		CashBookDto cashBookFound = null;
		try {
			cashBookFound = cashBookService.findOne(cashBookId);
		} catch (NoSuchCashBookException e) {
			System.out.println(e.getMessage());
		}
		
		return cashBookFound;
	}

	public List<CashBookDto> findAll() {
		//
		List<CashBookDto> cashBookDtos = null;
		try {
			cashBookDtos = cashBookService.findAll();
		} catch (NoSuchCashBookException e) {
			System.out.println(e.getMessage());
		}
		return cashBookDtos;
	}

	public void register(CashBookDto targetCashBook) {
		// 
		try {
			cashBookService.register(targetCashBook);
		} catch (CashBookDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void modify() {
		// 
		try {
			cashBookService.update(currentCashBook);
		} catch (CashBookDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public CashBookDto findByTravelId(String travelId) {
		// 
		CashBookDto cashBookFound = null;
		try {
			cashBookFound = cashBookService.findOne(travelId);
		}catch (NoSuchCashBookException e) {
			System.out.println(e.getMessage());
		}
		return cashBookFound;
	}
}