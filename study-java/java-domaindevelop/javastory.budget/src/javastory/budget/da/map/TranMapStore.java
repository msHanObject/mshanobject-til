package javastory.budget.da.map;

import java.util.Map;
import java.util.NoSuchElementException;

import javastory.budget.da.map.io.MemoryMap;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.store.TranStore;
import javastory.budget.util.TranDuplicationException;

public class TranMapStore implements TranStore{
	//
	private Map<String,Transaction> tranMap;
	
	public TranMapStore() {
		//
		tranMap = MemoryMap.getInstance().getTransactionMap();
	}
	
	@Override
	public boolean isExist(String tranId) {
		// 
		if (tranMap.get(tranId) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void register(Transaction tran) {
		// 
		String tranId = tran.getId();
		if (tranMap.get(tranId) != null) {
			throw new TranDuplicationException("The transaction is already exists with id --> " + tranId);
		}
		
		tranMap.put(tranId, tran);
	}

	@Override
	public Transaction retrieve(String tranId) {
		//
		return tranMap.get(tranId);
	}

	@Override
	public void modify(Transaction tran) {
		// 
		if (tranMap.get(tran.getId()) == null) {
			throw new NoSuchElementException("No such a element with --> " + tran.toString());
		}
		
		tranMap.put(tran.getId(), tran);
	}

	@Override
	public void delete(String tranId) {
		// 
		tranMap.remove(tranId);
	}
}
