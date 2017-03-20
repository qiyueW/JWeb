package system.base.date;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Date;

public class RunDate {

	/**
	 * d1-d2+1
	 * 
	 * @param d1 java.util.Date
	 * @param d2 java.util.Date
	 * @return int
	 */
	public int minus(Date d1, Date d2) {
		return (int)(d1.getTime() - d2.getTime()) / (1000 * 3600 * 24) + 1;
	}
	
	/**
	 * d1-d2+1
	 * @param d1 java.time.LocalDate
	 * @param d2 java.time.LocalDate
	 * @return int
	 */
	public int minus(LocalDate d1, LocalDate d2) {
		return (int)(d1.getLong(ChronoField.EPOCH_DAY) - d2.getLong(ChronoField.EPOCH_DAY) + 1);
	}

	/**
	 * d1-d2+1
	 * @param d1 java.time.LocalDate
	 * @param d2 java.util.Date
	 * @return int
	 */
	public int minus(LocalDate d1, Date d2) {
		return (int)(d1.getLong(ChronoField.EPOCH_DAY) - DateService.TO.toLocalDate(d2).getLong(ChronoField.EPOCH_DAY) + 1);
	}
	
	/**
	 * d1-d2+1
	 * @param d1 java.util.Date
	 * @param d2 java.time.LocalDate
	 * @return int
	 */
	public int minus(Date d1, LocalDate d2) {
		return (int)(DateService.TO.toLocalDate(d1).getLong(ChronoField.EPOCH_DAY) - d2.getLong(ChronoField.EPOCH_DAY) + 1);
	}
	
	

	public static void main(String args[]) {
		String date1, date2;
		date1 = "2016-02-09";
		date2 = "2017-02-09";

		LocalDate d1 = DateService.TO.toLocalDate(date1);
		LocalDate d2 = DateService.TO.toLocalDate(date2);
		Date dd1 = DateService.TO.toDate(date1);
		Date dd2 = DateService.TO.toDate(date2);

		System.out.println(new RunDate().minus(d2, dd1));
	}
}
