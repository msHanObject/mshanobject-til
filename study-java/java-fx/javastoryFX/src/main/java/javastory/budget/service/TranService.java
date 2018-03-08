package javastory.budget.service;

import javastory.budget.service.dto.TranDto;

public interface TranService {

	public void register(TranDto tranDto);

	public TranDto retreive(String tranId);

	public void modify(TranDto targetTran);

	public void delete(String tranId);

}
