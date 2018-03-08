package javastory.budget.da.file;

import java.util.NoSuchElementException;

import javastory.budget.da.file.io.TranFile;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.store.TranStore;
import javastory.budget.util.NoSuchTranException;
import javastory.budget.util.TranDuplicationException;

public class TranFileStore implements TranStore {
	//
	private TranFile tranFile;
	
	public TranFileStore() {
		//
		tranFile = new TranFile();
	}
	@Override
	public boolean isExist(String tranId) {
		// 
		return tranFile.exists(tranId);
	}

	@Override
	public void register(Transaction tran) {
		//
		if (tranFile.exists(tran.getId())) {
			throw new TranDuplicationException("The transaction already exists: " + tran.toString());
		}
		tranFile.write(tran);
	}

	@Override
	public Transaction retrieve(String tranId) {
		// 
		return tranFile.read(tranId);
	}

	@Override
	public void modify(Transaction tran) {
		// 
		if (!tranFile.exists(tran.getId())) {
			throw new NoSuchElementException("No such element: " + tran.toString());
		}
		tranFile.update(tran);
	}

	@Override
	public void delete(String tranId) {
		// 
		if (!tranFile.exists(tranId)) {
			throw new NoSuchTranException("No such transaction with id: " + tranId);
		}
		tranFile.delete(tranId);
	}
}