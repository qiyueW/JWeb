package system.base.date;

final public class DT {
	public static final NextMonth NT;
	public static final ToXDate TO;
	
	static {
		NT = new NextMonth();
		TO=new ToXDate();
	}
	
	public static void main(String args[]){
		
		System.out.println(DT.NT.nextMonth(DT.TO.toDate("2017-3-31")).toString());
	}
}
