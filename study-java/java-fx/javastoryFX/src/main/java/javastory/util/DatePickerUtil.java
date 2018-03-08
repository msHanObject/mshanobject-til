package javastory.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class DatePickerUtil {
	//
	private final String pattern = "yyyy.MM.dd";

	private static DatePickerUtil instance;
	private DatePicker datePicker;

	private DatePickerUtil() {
		//
	}

	public synchronized static DatePickerUtil shareInstance() {
		//
		if (instance == null) {
			instance = new DatePickerUtil();
		}
		return instance;
	}

	public DatePicker requestDatePicker() {
		//
		if (datePicker == null) {
			datePicker = new DatePicker(LocalDate.now());
		}
		datePicker.setEditable(true);
		setConverter();
		datePicker.setValue(LocalDate.now());//initialize
		return datePicker;
	}

	private void setConverter() {
		// Convert the date with pattern
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		datePicker.setConverter(converter);
		datePicker.setPromptText(pattern.toLowerCase());
	}

}
