package system.base.date;

import java.text.SimpleDateFormat;
import java.util.Date;

final public class DateService {

    /**
     * 集中处理前后个月有关的，
     */
    public static final NextMonth NT;
    /**
     * 处理日期转换有相关的方法
     */
    public static final ToXDate TO;
    /**
     * 关于多时间差的计算
     */
    public static final RunDate RUN;
    /**
     * 时间范围有关的计算
     */
    public static final ScopeDate SCOPE;

    static {
        NT = new NextMonth();
        TO = new ToXDate();
        RUN = new RunDate();
        SCOPE = new ScopeDate();
    }

    /**
     * 即得当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    final public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 即得当前时间|日期
     *
     * @param timeFormat 自定义格式
     * @return String
     */
    final public static String getNowTime(String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(new Date());
    }

    /**
     * 格式化Date类型的时间成字符串
     *
     * @param date Date
     * @param format 例 yyyy-MM-dd HH:mm:ss
     * @return String
     */
    final public static String getDate(Date date, String format) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
