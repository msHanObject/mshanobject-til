package javastory.budget.logic;

import javastory.budget.da.file.StoreFileLycler;
import javastory.budget.entity.budget.CashBook;
import javastory.budget.service.CashBookService;
import javastory.budget.service.dto.CashBookDto;
import javastory.budget.store.CashBookStore;
import javastory.budget.util.CashBookDuplicationException;
import javastory.budget.util.NoSuchCashBookException;

public class CashBookServiceLogic implements CashBookService {
	//
	private CashBookStore cashBookStore;
	
	public CashBookServiceLogic() {
		//
//		cashBookStore = StoreMapLycler.getInstance().requestCashBookStore();
		cashBookStore = StoreFileLycler.shareInstance().requestCashBookStore();
	}

	@Override
	public void register(CashBookDto cashBookDto) {
		// 
		String cashBookId = cashBookDto.getId();
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
}