package javastory.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//
	public static String today(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		return dateFormat.format((Calendar.getInstance()).getTime());
	}
	
	public static String today() {
		return today("yyyy.MM.dd");
	}
	
	public static String CurrentTime() {
		//
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss");
		long time = System.currentTimeMillis();
		return dateFormat.format(new Date(time));
	}
	
	public static LocalDate convertFrom(String targetDateStr) {
		String[] dateArr = targetDateStr.split("\\.");
		int[] days = new int[dateArr.length];
		for (int i=0; i<dateArr.length; i++) {
			days[i] = Integer.parseInt(dateArr[i]);
		}
		LocalDate localDate = LocalDate.of(days[0], days[1], days[2]);
		
		return localDate;
	}
}