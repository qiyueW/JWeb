package system.base.timework;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TimeWorkConfiguration {

	// public boolean openSingleThreadScheduledExecutor = true;

	/**
	 * 只执行一次(默认 false)
	 */
	public boolean onlyDoOne = false;
	/**
	 * 周期类型：秒、分、时、日... 通过 java.util.concurrent.TimeUnit 进行选择(默认 小时)
	 */
	public TimeUnit timeUnit = java.util.concurrent.TimeUnit.SECONDS;
	/**
	 * 延迟时间 initialDelay 后，开始执行(默认 0)
	 */
	public long initialDelay = 0;
	/**
	 * 执行的周期(默认 1)
	 */
	public long period = 1;
	private int user_seconds = -1;
	private int user_minute = -1;
	private int user_hour = -1;

	void doIni() {
		LocalTime lt;
		if (user_seconds > 0) {
			timeUnit = java.util.concurrent.TimeUnit.SECONDS;
			period = 60;
			int now = LocalTime.now().getSecond();
			initialDelay = now > user_seconds ? 60 - now + user_seconds : now < user_seconds ? user_seconds - now : 0;
			return;
		}
		if (user_minute > 0) {
			timeUnit = java.util.concurrent.TimeUnit.SECONDS;
			period = 1 * 60 * 60;// 360秒为一周
			lt = LocalTime.now();
			int now = lt.getMinute() * 60 + lt.getSecond();// 一周内的x秒
			initialDelay = now > user_minute ? period - now + user_minute : now < user_minute ? user_minute - now : 0;
			return;
		}
		if (user_hour > 0) {
			timeUnit = java.util.concurrent.TimeUnit.SECONDS;
			period = 1 * 24 * 60 * 60;// 360秒为一周
			lt = LocalTime.now();
			int now = lt.getHour() * 360 + lt.getMinute() * 60 + lt.getSecond();// 一周内的x秒
			initialDelay = now > user_hour ? period - now + user_hour : now < user_hour ? user_hour - now : 0;
			return;
		}
	}

	/**
	 * 0-59秒，例如，每分钟的第45秒，执行一次，则输入 45
	 * 
	 * @param x int
	 */
	public void doEveryMinute(int x) {
		if (x < 0 || x > 59)
			return;
		this.user_seconds = x;
	}

	/**
	 * 例如：每小的的30分:31秒，如通过doEveryHour(30,31)使用。
	 * 
	 * @param minute
	 *            0-59
	 * @param secends
	 *            0-59
	 */
	public void doEveryHour(int minute, int secends) {
		if (minute < 0 || minute > 59 || secends < 1 || secends > 59)
			return;
		this.user_minute = minute * 60 + secends;
	}

	/**
	 * 例如：每天哪小时哪分哪秒 执行。
	 * 
	 * @param hour
	 *            哪小时 0-23
	 * @param minute
	 *            哪分 0-59
	 * @param secends
	 *            哪秒 0-59
	 */
	public void doEveryDay(int hour, int minute, int secends) {
		if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || secends < 0 || secends > 59)
			return;
		this.user_hour = hour * 360 + minute * 60 + secends;
	}
}
