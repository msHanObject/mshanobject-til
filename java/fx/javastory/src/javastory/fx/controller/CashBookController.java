package javastory.fx.controller;

import java.util.ArrayList;
import java.util.List;

import javastory.budget.logic.ServiceLogicLycler;
import javastory.budget.service.CashBookService;
import javastory.budget.service.TravelService;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.service.dto.TravelDto;
import javastory.budget.util.CashBookDuplicationException;
import javastory.budget.util.NoSuchCashBookException;
import javastory.budget.util.NoSuchTravelException;
import javastory.club.stage3.step4.service.dto.ClubMembershipDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.console.ClubConsole;

public class CashBookController {
	//
	private CashBookService cashBookService;
	private CashBookDto currentCashBook;
	private TravelService travelService;
	
	public CashBookController() {
		//
		cashBookService = ServiceLogicLycler.shareInstance().createCashBookService();
		travelService = ServiceLogicLycler.shareInstance().createTravelService();
		currentCashBook = new CashBookDto();
	}
	
	private TravelDto findTravel() {
		//
		TravelDto travelFound = null;
		try {
			travelFound = travelService.findTravel(currentCashBook.getTravelId()); 
		} catch (NoSuchTravelException e) {
			System.out.println(e.getMessage());
		}
		return travelFound;
	}
	
	public List<String> getMemberList() {
		//
		String clubId = this.findTravel().getClubId();
		ClubConsole clubConsole = new ClubConsole();
		TravelClubDto clubFound = clubConsole.serachById(clubId);
		
		List<String> candidateList = new ArrayList<>();
		for (ClubMembershipDto membership : clubFound.getMembershipList()) {
			candidateList.add(membership.getMemberEmail());
		}
		
		return candidateList;
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
	
	public List<CashBookDto> searchByName(String cashBookName) {
		// 
		List<CashBookDto> cashBookDtos = null;
		try {
			cashBookDtos = cashBookService.findCashBookByName(cashBookName);
		} catch (NoSuchCashBookException e) {
			System.out.println(e.getMessage());
		}
		return cashBookDtos;
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

	public void delete(CashBookDto currentCashBook) {
		// 
		try {
			cashBookService.remove(currentCashBook.getId());
		} catch (NoSuchCashBookException e) {
			System.out.println(e.getMessage());
		}
	}
}