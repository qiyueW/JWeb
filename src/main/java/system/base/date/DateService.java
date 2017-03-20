package system.base.date;

final public class DateService {
	public static final NextMonth NT;
	public static final ToXDate TO;
	public static final RunDate RUN;
	
	
	static {
		NT = new NextMonth();
		TO=new ToXDate();
		RUN=new RunDate();	
	}
	
	public static void main(String args[]){
		
		System.out.println(DateService.NT.nextMonth(DateService.TO.toDate("2017-3-31")).toString());
	}
}
