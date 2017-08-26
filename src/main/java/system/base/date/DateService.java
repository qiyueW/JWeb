package system.base.date;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

final public class DateService {
	public static final NextMonth NT;
	public static final ToXDate TO;
	public static final RunDate RUN;
	
	
	static {
		NT = new NextMonth();
		TO=new ToXDate();
		RUN=new RunDate();	
	}
	
        final public static String getNowTime(){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        final public static String getNowTime(String timeFormat){
            return new SimpleDateFormat(timeFormat).format(new Date());
        }
        
	public static void main(String args[]){
		
		System.out.println(DateService.NT.nextMonth(DateService.TO.toDate("2017-3-31")).toString());
	}
}
