package javastory.budget.store;

import javastory.budget.entity.budget.CashBook;

public interface CashBookStore {
	//
	public boolean isExist(String cashBookId);
	public void register(CashBook cashBook);
	public CashBook retrieve(String cashBookId);
	public CashBook retrieveByname(String cashBookName);
	public void update(CashBook cashBook);
	public void remove(String cashBookId);
}
