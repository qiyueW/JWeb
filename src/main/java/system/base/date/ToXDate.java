package system.base.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ToXDate {
	private String dateFormat = system.web.WebContext.getWebContext().webConfig.DATE_FORMAT;
	private String timeFormat = system.web.WebContext.getWebContext().webConfig.DATE_FORMAT;

	//
	// private String dateFormat="yyyy-MM-dd";
	// private String timeFormat="yyyy-MM-dd";
	//
	/**
	 * 将date转换成LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 将String转换成LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public LocalDate toLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
	}

	/**
	 * 将String转换成LocalDate
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public LocalDate toLocalDate(String date, String dateFormat) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
	}

	/**
	 * 将String转换成toLocalTime
	 * 
	 * @param date
	 * @return
	 */
	public LocalTime toLocalTime(String time) {
		return LocalTime.parse(time, DateTimeFormatter.ofPattern(timeFormat));
	}

	/**
	 * 将String转换成toLocalTime
	 * 
	 * @param date
	 * @return
	 */
	public LocalTime toLocalTime(String time, String timeFormat) {
		return LocalTime.parse(time, DateTimeFormatter.ofPattern(timeFormat));
	}

	/**
	 * 将字符串date转换成日期（采用系统全局配置里的日期格式)
	 * 
	 * @param date
	 * @return
	 */
	public Date toDate(String date) {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		try {
			return sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串date转换成日期+时间 （采用系统全局配置里的日期+时间格式)
	 * 
	 * @param date
	 *            日期+时间
	 * @return Date
	 */
	public Date toTime(String date) {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		try {
			return sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串date转换成日期|时间（采用自定义的日期格式)
	 * 
	 * @param date
	 *            日期
	 * @param dateFormat
	 *            日期格式
	 * @return Date
	 */
	public Date toDateOrTime(String date, String dateFormat) {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		try {
			return sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将LocalDate转换成Date
	 * 
	 * @param ld
	 *            LocalDate
	 * @return Date
	 */
	public Date toDate(final LocalDate ld) {
		return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static void main(String args[]) {
		ToXDate to = new ToXDate();
		Date date = to.toDate(LocalDate.now());
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
	}
}
