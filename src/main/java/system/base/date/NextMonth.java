package system.base.date;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class NextMonth {
	/**
	 * 取当前月份的 下个月 第nextMonthDay天。 如果nextMonthDay天大于下个月的最后一天，则以下个月最后一天为准。
	 * 
	 * @param nextMonthDay
	 */
	final public LocalDate nextMonth(int nextMonthDay) {
		LocalDate ld1 = LocalDate.now();
		int nextMonth;
		int nextyear;
		if (ld1.getMonthValue() == 12) {
			nextMonth = 1;
			nextyear = ld1.getYear() + 1;
		} else {
			nextMonth = ld1.getMonthValue() + 1;
			nextyear = ld1.getYear();
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, nextyear);
		cal.set(Calendar.MONTH, ld1.getMonthValue());
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return lastDay < nextMonthDay ? LocalDate.of(nextyear, nextMonth, lastDay)
				: LocalDate.of(nextyear, nextMonth, nextMonthDay);
	}

	/**
	 * 取当前date月份的 下个月 第nextMonthDay天。 如果nextMonthDay天大于下个月的最后一天，则以下个月最后一天为准。
	 * 
	 * @param nextMonthDay
	 */
	final public LocalDate nextMonth(Date date, int nextMonthDay) {

		LocalDate ld1 = DateService.TO.toLocalDate(date);

		nextMonthDay = nextMonthDay < 1 ? ld1.getDayOfMonth() : nextMonthDay;

		int nextMonth;
		int nextyear;
		if (ld1.getMonthValue() == 12) {
			nextMonth = 1;
			nextyear = ld1.getYear() + 1;
		} else {
			nextMonth = ld1.getMonthValue() + 1;
			nextyear = ld1.getYear();
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, nextyear);
		cal.set(Calendar.MONTH, ld1.getMonthValue());
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return lastDay < nextMonthDay ? LocalDate.of(nextyear, nextMonth, lastDay)
				: LocalDate.of(nextyear, nextMonth, nextMonthDay);
	}

	/**
	 * 取当前date月份的 下个月 第nextMonthDay天。 如果nextMonthDay天大于下个月的最后一天，则以下个月最后一天为准。
	 * 
	 * @param nextMonthDay
	 */
	final public LocalDate nextMonth(Date date) {

		LocalDate ld1 = DateService.TO.toLocalDate(date);
		int nextMonth;
		int nextyear;
		if (ld1.getMonthValue() == 12) {
			nextMonth = 1;
			nextyear = ld1.getYear() + 1;
		} else {
			nextMonth = ld1.getMonthValue() + 1;
			nextyear = ld1.getYear();
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, nextyear);
		cal.set(Calendar.MONTH, ld1.getMonthValue());
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return ld1.getDayOfMonth() > lastDay ? LocalDate.of(nextyear, nextMonth, lastDay)
				: LocalDate.of(nextyear, nextMonth, ld1.getDayOfMonth());
	}

	/**
	 * 保证date月份的天数。跳到指定年月(如果date的天数在于新日期最后一天，则限定于新日期的最后一天)。
	 * 
	 * @param nextMonthDay
	 */
	final public LocalDate nextMonth_jump(Date date,int year,int month) {
		
		LocalDate ld1 = DateService.TO.toLocalDate(date);
		LocalDate now = LocalDate.of(year, month,1);
		int  i=ld1.getDayOfMonth()>now.lengthOfMonth()
				?now.lengthOfMonth()
				:ld1.getDayOfMonth();
		return now.plusDays(--i);
	}
}
