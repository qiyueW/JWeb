package system.base.timework;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class TimeWorkInitService {

	private final system.base.log.SysLog log = new system.base.log.SysLog();
	private final ExecutorPool ep = new ExecutorPool();

	public void ini(List<Class> cs) {
		log.setLogTitle("执行时间周期工作");
		TimeWork obj;
		try {
			for (Class c : cs) {
				if (TimeWork.class.isAssignableFrom(c) && !c.equals(TimeWork.class)) {
					TimeWorkConfiguration tc = new TimeWorkConfiguration();
					obj = (TimeWork) c.newInstance();
					obj.configuration(tc);
					if (tc.onlyDoOne) {
						tc.doIni();
						ep.get().schedule(obj, tc.initialDelay, tc.timeUnit);
						log.putLog(1,"执行 1次性操作  " + c.getName());
						continue;
					}
					//以后多样化扩展时，从这里开始
					else{
						//目前仅支持按周期 循环
						tc.doIni();
						ep.get().scheduleAtFixedRate(obj,tc.initialDelay,tc.period, tc.timeUnit);
						log.putLog(1, "执行周期循环" + c.getName());
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(TimeWorkInitService.class.getName()).log(Level.SEVERE, null, ex);
		}
		log.println();
	}
}
