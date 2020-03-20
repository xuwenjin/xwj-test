package util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

	/**
	 * 获取精确到秒的时间戳(10位)
	 */
	public static long getSecondTimestamp(Date date) {
		if (null == date) {
			return 0;
		}
		String timestamp = String.valueOf(date.getTime() / 1000);
		return Long.valueOf(timestamp);
	}

	/**
	 * 获取精确到秒的时间戳(13位)
	 */
	public static long getMinSecondTimestamp(Date date) {
		if (null == date) {
			return 0;
		}
		String timestamp = String.valueOf(date.getTime());
		return Long.valueOf(timestamp);
	}

	/**
	 * 获取当前时间精确到秒的时间戳(10位)
	 */
	public static long getCurrSecondTimestamp() {
		return getSecondTimestamp(new Date());
	}

	/**
	 * 获取当前时间精确到毫秒的时间戳(13位)
	 */
	public static long getCurrMinSecondTimestamp() {
		return getMinSecondTimestamp(new Date());
	}

	/**
	 * 将精确到秒的时间戳转为日期
	 * 
	 * @param secondTimestamp
	 *            到秒的时间戳
	 */
	public static Date getDateBySecondTimestamp(long secondTimestamp) {
		return new Date(secondTimestamp * 1000);
	}

	/**
	 * LocalDateTime转换为Date
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zoneId).toInstant();
		return Date.from(instant);
	}

	/**
	 * LocalDate转换为Date
	 */
	public static Date localDate2Date(LocalDate localDate) {
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
		return Date.from(instant);
	}

	/**
	 * Date转换为LocalDate
	 */
	public static LocalDate date2LocalDate(Date date) {
		return TimeUtil.date2LocalDateTime(date).toLocalDate();
	}

	/**
	 * Date转换为LocalDateTime
	 */
	public static LocalDateTime date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		return instant.atZone(zoneId).toLocalDateTime();
	}

	/**
	 * 获取当前日期(线程安全)
	 */
	public static Date now() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime2Date(localDateTime);
	}

	/**
	 * 比较两个日期之间的时间间隔
	 * 
	 * @param unit
	 *            时间间隔类型
	 */
	public static long between(Date startTime, Date endTime, TimeUnit unit) {
		LocalDateTime startLocalDateTime = date2LocalDateTime(startTime);
		LocalDateTime endLocalDateTime = date2LocalDateTime(endTime);
		Duration duration = Duration.between(startLocalDateTime, endLocalDateTime);

		long betweenCount = 0;
		if (TimeUnit.DAYS.equals(unit)) { // 天数
			betweenCount = duration.toDays();
		}
		if (TimeUnit.HOURS.equals(unit)) { // 小时
			betweenCount = duration.toHours();
		}
		if (TimeUnit.MINUTES.equals(unit)) { // 分钟
			betweenCount = duration.toMinutes();
		}
		if (TimeUnit.MILLISECONDS.equals(unit)) { // 毫秒
			betweenCount = duration.toMillis();
		}
		return betweenCount;
	}

	/**
	 * 比较两个日期之间的分钟数
	 */
	public static long betweenToMinutes(Date startTime, Date endTime) {
		return between(startTime, endTime, TimeUnit.MINUTES);
	}

	/**
	 * 比较两个日期之间的天数
	 */
	public static long betweenToDays(Date startTime, Date endTime) {
		return between(startTime, endTime, TimeUnit.DAYS);
	}

	/**
	 * 将日期为空的，设置为当前日期
	 */
	public static Date setNullToNow(Date date) {
		if (date == null) {
			return now();
		}
		return date;
	}

}
