package javastory.budget.service;

import java.util.List;

import javastory.budget.service.dto.CashBookDto;

public interface CashBookService {

	public void register(CashBookDto cashBookDto);

	public CashBookDto findOne(String cashBookId);

	public void remove(String cashBookName);

	public void update(CashBookDto targetCashBook);

	public List<CashBookDto> findCashBookByName(String cashBookName);

	public List<CashBookDto> findAll();
}
