package javastory.budget.service;

import java.util.List;

import javastory.budget.service.dto.YearlyDueDto;

public interface YearlyDueService {

	public void add(YearlyDueDto yearlyDueDto);

	public YearlyDueDto retrieve(String yearFound);

	public void modify(YearlyDueDto target);

	public void delete(String year);

	public List<YearlyDueDto> retrieveAll();

}
