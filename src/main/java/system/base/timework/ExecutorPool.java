package system.base.timework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 *
 * @author IK
 */
public class ExecutorPool {

	private static final Map<Class<?>, ScheduledFuture<?>> map = new HashMap<>();
	private static final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
	
	ScheduledExecutorService get() {
		return ses;
	}

	public static boolean timeStop(Class<?> c) {
		ScheduledFuture<?> s = map.get(c);
		if (null != s) {
			boolean x = s.cancel(true);
			if (x) {
				map.remove(c);
			}
			return x;
		}
		return false;
	}

	public static ScheduledFuture<?> getScheduledFuture(Class c) {
		return map.get(c);
	}

	public static void timeDo(Class c) {

	}

	public static void main(String args[]) {
		// ExecutorPool.timeDo(Test.class);
		// System.out.println(ExecutorPool.timeStop(Test.class));
	}
}
