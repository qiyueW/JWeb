package system.base.timework;

public class Defualt extends TimeWork{

	
	@Override
	public void run() {
		
	}

	@Override
	public void configuration(TimeWorkConfiguration cf) {
		cf.timeUnit=java.util.concurrent.TimeUnit.HOURS;
		cf.initialDelay=0;
		cf.period=1;
	}
	
}
