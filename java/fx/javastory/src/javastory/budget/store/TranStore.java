package javastory.budget.store;

import javastory.budget.entity.budget.Transaction;

public interface TranStore {

	public boolean isExist(String tranId);

	public void register(Transaction tran);

	public Transaction retrieve(String tranId);

	public void modify(Transaction tran);

	public void delete(String tranId);

}
