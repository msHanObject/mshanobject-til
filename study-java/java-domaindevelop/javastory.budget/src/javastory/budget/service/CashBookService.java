package javastory.budget.service;

import javastory.budget.service.dto.CashBookDto;

public interface CashBookService {

	public void register(CashBookDto cashBookDto);

	public CashBookDto findOne(String cashBookId);

	public void remove(String cashBookName);

	public void update(CashBookDto targetCashBook);
}
