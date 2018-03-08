package javastory.budget.da.map;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import javastory.budget.da.map.io.MemoryMap;
import javastory.budget.entity.budget.CashBook;
import javastory.budget.store.CashBookStore;
import javastory.budget.util.CashBookDuplicationException;

public class CashBookMapStore implements CashBookStore {
	//
	private Map<String,CashBook> cashBookMap;
	
	public CashBookMapStore() {
		//
		cashBookMap = MemoryMap.getInstance().getCashBookMap();
	}
	
	@Override
	public boolean isExist(String cashBookId) {
		// 
		if (cashBookMap.get(cashBookId) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void register(CashBook cashBook) {
		// 
		String cashBookId = cashBook.getId();
		if (cashBookMap.get(cashBookId) != null) {
			throw new CashBookDuplicationException("The cashbook is already exists with id --> " + cashBookId);
		}
		
		cashBookMap.put(cashBookId,cashBook);
	}

	@Override
	public CashBook retrieve(String cashBookId) {
		// 
		return cashBookMap.get(cashBookId);
	}

	@Override
	public CashBook retrieveByname(String cashBookName) {
		// 
		Iterator<CashBook> cashBookIter = cashBookMap.values().iterator();
		CashBook cashBookFound = null;
		while (cashBookIter.hasNext()) {
			if (cashBookIter.next().getName().equals(cashBookName)) {
				cashBookFound = cashBookIter.next();
				break;
			}
		}
		return cashBookFound;
	}

	@Override
	public void update(CashBook cashBook) {
		//
		if (cashBookMap.get(cashBook.getId()) == null) {
			throw new NoSuchElementException("No such a  element with --> " + cashBook.toString());
		}
		
		cashBookMap.put(cashBook.getId(), cashBook);
	}

	@Override
	public void remove(String cashBookId) {
		// 
		cashBookMap.remove(cashBookId);
	}
}