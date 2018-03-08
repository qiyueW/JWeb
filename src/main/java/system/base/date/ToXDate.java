package system.base.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ToXDate {

    /**
     * 日期格式
     */
    private final String dateFormat = system.web.WebContext.getWebContext().webConfig.DATE_FORMAT;
    /**
     * 时间格式
     */
    private final String timeFormat = system.web.WebContext.getWebContext().webConfig.TIME_FORMAT;

    //
    // private String dateFormat="yyyy-MM-dd";
    // private String timeFormat="yyyy-MM-dd";
    //
    /**
     * 将date转换成LocalDate
     *
     * @param date Date
     * @return LocalDate
     */
    public LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将String转换成LocalDate
     *
     * @param date String
     * @return LocalDate
     */
    public LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 将String转换成LocalDate
     *
     * @param date String
     * @param dateFormat String
     * @return LocalDate
     */
    public LocalDate toLocalDate(String date, String dateFormat) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 将String转换成toLocalTime
     *
     * @param time String
     * @return LocalTime
     */
    public LocalTime toLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(timeFormat));
    }

    /**
     * 将String转换成toLocalTime
     *
     * @param time String
     * @param timeFormat String
     * @return LocalTime
     */
    public LocalTime toLocalTime(String time, String timeFormat) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(timeFormat));
    }

    /**
     * 将字符串date转换成日期（采用系统全局配置里的日期格式)
     *
     * @param date String
     * @return Date
     */
    public Date toDate(String date) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 将字符串date转换成日期+时间 （采用系统全局配置里的日期+时间格式)
     *
     * @param date 日期+时间 String
     * @return Date Date
     */
    public Date toTime(String date) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 将字符串date转换成日期|时间（采用自定义的日期格式)
     *
     * @param date 日期
     * @param dateFormat 日期格式
     * @return Date
     */
    public Date toDateOrTime(String date, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 将LocalDate转换成Date
     *
     * @param ld LocalDate
     * @return Date 
     */
    public Date toDate(final LocalDate ld) {
        return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
