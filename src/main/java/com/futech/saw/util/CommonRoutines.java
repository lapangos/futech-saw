package com.futech.saw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonRoutines {

	private static final Logger LOG = LoggerFactory.getLogger(CommonRoutines.class);

	private CommonRoutines() {
		super();
	}

	public static boolean isNullOrBlank(Object input) {
		return (input == null || "".equals(input.toString().trim())) ? true : false;
	}

	public static boolean isNullOrEmpty(List<?> input) {
		return (input == null || input.isEmpty()) ? true : false;
	}

	public static boolean isNullOrEmpty(Set<?> input) {
		return (input == null || input.isEmpty()) ? true : false;
	}

	public static Date getToday() {
		return new Date();
	}

	public static boolean isNullOrEmptyAttrb(String input) {
		return (input == null || input.isEmpty()) ? true : false;
	}

	public static Date stringToDate(String strDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			LOG.error("ParseException in CommonRoutines.stringToDate : {}", e);
		}
		return date;

	}

	public static Date stringToDateFormatWOTimeStamp(String date) {

		String expectedPattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		Date ordSubDate = null;

		try {
			ordSubDate = formatter.parse(date);
		} catch (ParseException e) {
			LOG.error("ParseException in CommonRoutines.stringToDateFormat2 : {}", e);
		}

		return ordSubDate;
	}

	public static String dateToStringFormat(Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return formatter.format(date);
	}

	public static boolean compareStringDates(String dateOne, String dateTwo) {

		boolean result = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			if (formatter.parse(dateOne).compareTo(formatter.parse(dateTwo)) != 0) {
				result = true;
			}
		} catch (ParseException e) {
			LOG.error("ParseException in CommonRoutines.compareStringDates : {}", e);
		}

		return result;

	}

	public static Integer compareStringDatesReturnInt(String dateOne, String dateTwo) {

		Integer result = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			result = formatter.parse(dateOne).compareTo(formatter.parse(dateTwo));

		} catch (ParseException e) {
			LOG.error("ParseException in CommonRoutines.compareStringDatesReturnInt : {}", e);
		}

		return result;

	}

	public static String objToJsonStr(Object obj) {
		String toJson = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			toJson = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException jpe) {
			LOG.error("ParseException in CommonRoutines.objToJsonStr : {}", jpe);
		} catch (Exception e) {
			LOG.error("ParseException in CommonRoutines.objToJsonStr : {}", e);
		}

		return toJson;
	}

	public static boolean isDate1GreaterThanEqualToDate2(String dateOne, String dateTwo) {
		boolean dateOneIsGreater = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {

			if (formatter.parse(dateOne).after(formatter.parse(dateTwo))
					|| formatter.parse(dateOne).equals(formatter.parse(dateTwo))) {
				dateOneIsGreater = true;
			}

		} catch (ParseException e) {
			LOG.error("ParseException in CommonRoutines.isDate1GreaterThanEqualToDate2 : {}", e);
		}

		return dateOneIsGreater;

	}

	public static String dateToStringdateWOTime(Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = null;
		if (!CommonRoutines.isNullOrBlank(date)) {
			strDate = formatter.format(date);
		}

		return strDate;
	}

	public static Calendar makeCalendarTimeStampZero(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	public static String getStringValue(String value) {
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}

	public static boolean isNull(Object input) {
		if (input == null) {
			return true;
		} else {
			return false;

		}
	}

	public static String getISO8601StringForDate(Date date) {
		if (!isNullOrBlank(date)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
			// dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			return dateFormat.format(date);
		} else {
			return null;
		}
	}

}
