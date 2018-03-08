package javastory.budget.logic;

import java.util.ArrayList;
import java.util.List;

import javastory.budget.da.file.BudgetStoreFileLycler;
import javastory.budget.entity.CashBook;
import javastory.budget.service.CashBookService;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.store.CashBookStore;
import javastory.budget.util.exception.CashBookDuplicationException;
import javastory.budget.util.exception.NoSuchCashBookException;

public class CashBookServiceLogic implements CashBookService {
	//
	private CashBookStore cashBookStore;
	
	public CashBookServiceLogic() {
		//
//		cashBookStore = StoreMapLycler.getInstance().requestCashBookStore();
		cashBookStore = BudgetStoreFileLycler.shareInstance().requestCashBookStore();
	}

	@Override
	public void register(CashBookDto cashBookDto) {
		// 
		String cashBookId = cashBookDto.getCashBookId();
		if (cashBookStore.isExist(cashBookId)) {
			throw new CashBookDuplicationException("The cashbook id already exists with --> " + cashBookId);
		}
		CashBook cashBook = cashBookDto.toCashBook();
		cashBookStore.register(cashBook);
	}

	@Override
	public CashBookDto findOne(String cashBookId) {
		// 
		CashBook cashBook = cashBookStore.retrieve(cashBookId);
		if (cashBook == null) {
			throw new NoSuchCashBookException("There is no cashbook with id --> " + cashBookId);
		}
		return new CashBookDto(cashBook);
	}

	@Override
	public void update(CashBookDto targetCashBook) {
		// 
		cashBookStore.update(targetCashBook.toCashBook());
	}
	
	@Override
	public void remove(String cashBookId) {
		// 
		if (!cashBookStore.isExist(cashBookId)) {
			throw new NoSuchCashBookException("There is no cashbook with id --> " + cashBookId);
		}
		cashBookStore.remove(cashBookId);		
	}

//	@Override
//	public List<CashBookDto> findCashBookByName(String cashBookName) {
//		// 
//		List<CashBook> cashBooks = cashBookStore.retrieveByname(cashBookName);
//		if (cashBooks == null) {
//			throw new NoSuchCashBookException("There is no cashBook with name --> " + cashBookName);
//		}
//		
//		List<CashBookDto> cashBookDtos = new ArrayList<>();
//		for (CashBook cashBook : cashBooks) {
//			cashBookDtos.add(new CashBookDto(cashBook));
//		}
//		return cashBookDtos;
//	}

	@Override
	public List<CashBookDto> findAll() {
		// 
		List<CashBook> cashBooks = cashBookStore.retrieveAll();
		if (cashBooks== null	) {
			throw new NoSuchCashBookException("There is no cashBook at all");
		}
		
		List<CashBookDto> cashBookDtos = new ArrayList<>();
		for (CashBook cashBook : cashBooks) {
			cashBookDtos.add(new CashBookDto(cashBook));
		}
		return cashBookDtos;
	}
}