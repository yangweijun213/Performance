package com.lrm.biz;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jeff yang
 *
 */
/**
 * @author jeff yang
 *
 */
public class DateTimeUtil {

	/**
	 * Calculate the number of days between two dates
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int dateInterval(long date1, long date2) {
		if (date2 > date1) {
			date2 = date2 + date1;
			date1 = date2 - date1;
			date2 = date2 - date1;
		}

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		// check whether the same year or not
		int y1 = calendar1.get(Calendar.YEAR);
		int y2 = calendar2.get(Calendar.YEAR);

		int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
		int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
		int maxDays = 0;
		int day = 0;
		if (y1 - y2 > 0) {
			day = numerical(maxDays, d1, d2, y1, y2, calendar2);
		} else {
			day = d1 - d2;
		}
		return day;
	}

	/**
	 * Date interval computation eg:20121201- 20121212
	 *
	 * @param maxDays
	 * @param d1
	 * @param d2
	 * @param y1
	 * @param y2
	 * @param calendar
	 * @return
	 */
	public static int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar) {
		int day = d1 - d2;
		int betweenYears = y1 - y2;
		List<Integer> d366 = new ArrayList<Integer>();

		if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366) {
			System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
			day += 1;
		}

		for (int i = 0; i < betweenYears; i++) {
			calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1);
			maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
			if (maxDays != 366) {
				day += maxDays;
			} else {
				d366.add(maxDays);
			}
			if (i == betweenYears - 1 && betweenYears > 1 && maxDays == 366) {
				day -= 1;
			}
		}

		for (int i = 0; i < d366.size(); i++) {
			if (d366.size() >= 1) {
				day += d366.get(i);
			}
		}
		return day;
	}

	/**
	 * judge if this year is a leap year
	 *
	 * @param year
	 * @return
	 */
	public static boolean judgeyear(int year) {
		boolean leadyear;
		if (year % 4 == 0 && year % 100 != 0) {
			leadyear = true;
		} else if (year % 400 == 0) {
			leadyear = true;
		} else
			leadyear = false;
		return leadyear;
	}

	/**
	 * get current year 、 month 、day
	 */
	public static ArrayList<String> getYearMonthDay() {
		DateFormat formatYear = new SimpleDateFormat("yyyy");
		DateFormat formatMonth = new SimpleDateFormat("MM");
		DateFormat formatDay = new SimpleDateFormat("dd");
		Date now = new Date(System.currentTimeMillis());
		String[] compareArray = { "01", "02", "03", "04", "05", "06", "07", "08", "09" };
		String year = formatYear.format(now);
		String month = formatMonth.format(now);
		String day = formatDay.format(now);
		for (int i = 0; i < compareArray.length; i++) {
			if (month.equals(compareArray[i])) {
				month = month.substring(1);
			}
			if (day.equals(compareArray[i])) {
				day = day.substring(1);
			}
		}
		int intMonth = Integer.parseInt(month);
		month = intMonth + "";
		ArrayList<String> list = new ArrayList<String>();
		list.add(year);
		list.add(month);
		list.add(day);
		return list;
	}

	/**
	 * get the year 、 month 、day of the arg
	 *
	 * @param date
	 * @return
	 */
	public static ArrayList<String> getYearMonthDay(String date) {
		String[] arg = date.split("-");
		String[] compareArray = { "01", "02", "03", "04", "05", "06", "07", "08", "09" };
		for (int i = 0; i < compareArray.length; i++) {
			if (arg[1].equals(compareArray[i])) {
				arg[1] = arg[1].substring(1);
			}
			if (arg[2].equals(compareArray[i])) {
				arg[2] = arg[2].substring(1);
			}
		}
		int intMonth = Integer.parseInt(arg[1]);
		arg[1] = intMonth + "";
		ArrayList<String> list = new ArrayList<String>();
		list.add(arg[0]);
		list.add(arg[1]);
		list.add(arg[2]);
		System.out.println(list);
		return list;
	}

	/**
	 * get current system time
	 *
	 * @return
	 */
	public static String showDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date(System.currentTimeMillis());
		return format.format(now);

	}

	/**
	 * convert date string to date
	 *
	 * @param strDate
	 *            eg:yyyyMMdd
	 * @return
	 */
	public static long getDateTime(String strDate) {
		return getDateByFormat(strDate, "yyyyMMdd").getTime();
	}

	/**
	 * @param strDate
	 * @param format
	 * @return Date
	 */
	public static Date getDateByFormat(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return (sdf.parse(strDate));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * convert long to timestamp
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Timestamp convertLongToTimestamp(long timestamp) {
		Timestamp ts = new Timestamp(timestamp);
		return ts;
	}


}
