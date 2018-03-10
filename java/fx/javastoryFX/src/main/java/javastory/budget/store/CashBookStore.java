package javastory.budget.store;

import java.util.List;

import javastory.budget.entity.CashBook;

public interface CashBookStore {
	//
	public boolean isExist(String cashBookId);
	public void register(CashBook cashBook);
	public CashBook retrieve(String cashBookId);
	public void update(CashBook cashBook);
	public void remove(String cashBookId);
	public List<CashBook> retrieveAll();
}
