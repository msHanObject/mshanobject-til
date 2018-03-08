package javastory.budget.logic;

import javastory.budget.da.file.StoreFileLycler;
import javastory.budget.entity.budget.Transaction;
import javastory.budget.service.TranService;
import javastory.budget.service.dto.TranDto;
import javastory.budget.store.TranStore;
import javastory.budget.util.NoSuchTranException;
import javastory.budget.util.TranDuplicationException;

public class TranServiceLogic implements TranService{
	//
	private TranStore tranStore;
	
	public TranServiceLogic() {
		//
//		tranStore = StoreMapLycler.getInstance().requestTranStore();
		tranStore = StoreFileLycler.shareInstance().requestTranStore();
	}

	@Override
	public void register(TranDto tranDto) {
		// 
		String tranId = tranDto.getId();
		if (tranStore.isExist(tranId)) {
			throw new TranDuplicationException("The transaction id already eixsts with id --> " + tranId);
		}
		Transaction tran = tranDto.toTran();
		tranStore.register(tran);		
	}

	@Override
	public TranDto retreive(String tranId) {
		// 
		Transaction transaction = tranStore.retrieve(tranId);
		if (transaction == null) {
			throw new NoSuchTranException("There is no transaction with id --> " + tranId);
		}
		return new TranDto(transaction);
	}

	@Override
	public void modify(TranDto targetTran) {
		// 
		tranStore.modify(targetTran.toTran());		
	}

	@Override
	public void delete(String tranId) {
		// 
		if (!tranStore.isExist(tranId)) {
			throw new NoSuchTranException("There is no transaction with id --> " + tranId);
		}
		
		tranStore.delete(tranId);
	}
}
