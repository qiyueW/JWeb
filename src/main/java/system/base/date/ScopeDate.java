package system.base.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class ScopeDate {

    /**
     * 实例一个DateTimeFormatter，代表日期
     */
    private final static DateTimeFormatter DF = DateTimeFormatter.ofPattern(system.web.WebContext.getWebContext().webConfig.DATE_FORMAT);
    /**
     * 实例一个DateTimeFormatter，代表时间
     */
    private final static DateTimeFormatter TF = DateTimeFormatter.ofPattern(system.web.WebContext.getWebContext().webConfig.TIME_FORMAT);

    /**
     * 当前月份的开始日到操作日
     * @param format 日期
     * @return  String[] x[0]开始日  x[1]操作日
     */
    public String[] currentMonth1_N(String format) {
        DateTimeFormatter df = null == format || format.isEmpty() ? DF : DateTimeFormatter.ofPattern(format);
        String[] x = new String[2];
        LocalDate now = LocalDate.now();
        x[0] = now.withDayOfMonth(1).format(df);
        x[1] = now.format(df);
        return x;
    }

    /**
     * 日期当月的开始日与结束日
     * @param format 日期
     * @return   String[] x[0]开始日  x[1]结束日
     */
    public String[] currentMonth1_End(String format) {
        DateTimeFormatter df = null == format || format.isEmpty() ? DF : DateTimeFormatter.ofPattern(format);
        String[] x = new String[2];
        LocalDate now = LocalDate.now();
        x[0] = now.withDayOfMonth(1).format(df);
        x[1] = now.with(TemporalAdjusters.lastDayOfMonth()).format(df);
        return x;
    }
}
