package system.base.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class ScopeDate {

    private final static DateTimeFormatter DF = DateTimeFormatter.ofPattern(system.web.WebContext.getWebContext().webConfig.DATE_FORMAT);
    private final static DateTimeFormatter TF = DateTimeFormatter.ofPattern(system.web.WebContext.getWebContext().webConfig.TIME_FORMAT);

    public String[] currentMonth1_N(String format) {
        DateTimeFormatter df = null == format || format.isEmpty() ? DF : DateTimeFormatter.ofPattern(format);
        String[] x = new String[2];
        LocalDate now = LocalDate.now();
        x[0] = now.withDayOfMonth(1).format(df);
        x[1] = now.format(df);
        return x;
    }

    public String[] currentMonth1_End(String format) {
        DateTimeFormatter df = null == format || format.isEmpty() ? DF : DateTimeFormatter.ofPattern(format);
        String[] x = new String[2];
        LocalDate now = LocalDate.now();
        x[0] = now.withDayOfMonth(1).format(df);
        x[1] = now.with(TemporalAdjusters.lastDayOfMonth()).format(df);
        return x;
    }
}
