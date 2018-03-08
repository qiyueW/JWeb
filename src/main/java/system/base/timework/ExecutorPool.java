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

    /**
     * 定时器容器
     */
    private static final Map<Class<?>, ScheduledFuture<?>> MAP = new HashMap<>();
    /**
     * ScheduledExecutorService 单例
     */
    private static final ScheduledExecutorService SES = Executors.newSingleThreadScheduledExecutor();

    /**
     * 取出 ScheduledExecutorService 实例
     *
     * @return ScheduledExecutorService
     */
    ScheduledExecutorService get() {
        return SES;
    }

    /**
     * 停止任务
     *
     * @param c Class
     * @return boolean
     */
    public static boolean timeStop(Class<?> c) {
        ScheduledFuture<?> s = MAP.get(c);
        if (null != s) {
            boolean x = s.cancel(true);
            if (x) {
                MAP.remove(c);
            }
            return x;
        }
        return false;
    }

    /**
     * ScheduledFuture 实例
     *
     * @param c Class
     * @return ScheduledFuture
     */
    public static ScheduledFuture<?> getScheduledFuture(Class c) {
        return MAP.get(c);
    }

//    public static void timeDo(Class c) {
//
//    }

}
