package javastory.budget.da.file;

import java.util.List;
import java.util.NoSuchElementException;

import javastory.budget.da.file.io.CashBookFile;
import javastory.budget.entity.budget.CashBook;
import javastory.budget.store.CashBookStore;
import javastory.budget.util.CashBookDuplicationException;
import javastory.budget.util.NoSuchCashBookException;

public class CashBookFileStore implements CashBookStore {
	//
	private CashBookFile cashBookFile;
	
	public CashBookFileStore() {
		//
		cashBookFile = new CashBookFile();
	}
	
	@Override
	public boolean isExist(String cashBookId) {
		// 
		return cashBookFile.exists(cashBookId);
	}

	@Override
	public void register(CashBook cashBook) {
		// 
		if (cashBookFile.exists(cashBook.getId())) {
			throw new CashBookDuplicationException("The cashbook already exists: " + cashBook.toString());
		}
		cashBookFile.write(cashBook);
	}

	@Override
	public CashBook retrieve(String cashBookId) {
		// 
		return cashBookFile.read(cashBookId);
	}

	@Override
	public List<CashBook> retrieveByname(String cashBookName) {
		// 
		return cashBookFile.readyByName(cashBookName);
	}

	@Override
	public void update(CashBook cashBook) {
		// 
		if (!cashBookFile.exists(cashBook.getId())) {
			throw new NoSuchElementException("No such element: " + cashBook.toString());
		}
		cashBookFile.update(cashBook);
	}

	@Override
	public void remove(String cashBookId) {
		// 
		if (!cashBookFile.exists(cashBookId)) {
			throw new NoSuchCashBookException("No such cashbook with id: " + cashBookId);
		}
		cashBookFile.delete(cashBookId);
	}

	@Override
	public List<CashBook> retrieveAll() {
		// 
		return cashBookFile.readAll();
	}
}