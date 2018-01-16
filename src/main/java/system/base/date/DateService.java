package system.base.date;

import java.text.SimpleDateFormat;
import java.util.Date;

final public class DateService {

    public static final NextMonth NT;
    public static final ToXDate TO;
    public static final RunDate RUN;
    public static final ScopeDate SCOPE;

    static {
        NT = new NextMonth();
        TO = new ToXDate();
        RUN = new RunDate();
        SCOPE = new ScopeDate();
    }

    final public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    final public static String getNowTime(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(new Date());
    }

    /**
     *  
     * @param date
     * @param format 例 yyyy-MM-dd HH:mm:ss 
     * @return 
     */
    final public static String getDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static void main(String args[]) {

        System.out.println(DateService.NT.nextMonth(DateService.TO.toDate("2017-3-31")).toString());
    }
}
