//package system.base.timework;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Test {
//	
//	public static void main(String args[]) {
//		List<Class> c=new ArrayList<>();
//		c.add(a.class);
//		new TimeWorkInitService().ini(c);
//	}
//	
//}
//
//class a extends TimeWork{
//
//	@Override
//	public void run() {
//		LocalTime lt=LocalTime.now();
//		System.out.println("时 分 秒:"+lt.getHour()+"//"+lt.getMinute()+"//"+lt.getSecond());
//	}
//
//	@Override
//	public void configuration(TimeWorkConfiguration cf) {
////		cf.doEveryMinute(30);
//		//每小时的53分31秒，执行一次
//		cf.doEveryHour(57, 55);
////		cf.doEveryDay(18,33,10);
////		cf.initialDelay=5;
////		cf.period=2;
////		cf.timeUnit=TimeUnit.SECONDS;
////		cf.onlyDoOne=true;
//	}
//	
//}
