package javastory.msClub3.stage2.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	//
	public static String today(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		return dateFormat.format((Calendar.getInstatnce()).getTime());
	}

	public static String today() {
		return today("yyyy.MM.dd");
	}
}
