package system.base.timework;


public abstract class TimeWork implements Runnable{
	/**
	 * 设置任务工作的相关参数
	 * 
	 * @param cf TimeWorkConfiguration
	 */
	public abstract void configuration(TimeWorkConfiguration cf);
	
}
