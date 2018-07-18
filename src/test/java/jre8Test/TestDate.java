package jre8Test;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * 测试日期、时间
 * 
 * @author XU.WJ 2018年3月1日
 */
public class TestDate {

	public static void main(String[] args) {

		// Clock类使用时区来返回当前的纳秒时间和日期。
		// Clock可以替代System.currentTimeMillis()和TimeZone.getDefault()。
		Clock clock = Clock.systemUTC();
		System.out.println(clock.instant());
		System.out.println(clock.millis());

		// LocalDate仅仅包含ISO-8601日历系统中的日期部分
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.now(clock);
		System.out.println(date1);
		System.out.println(date2);

		// LocalTime则仅仅包含该日历系统中的时间部分
		LocalTime time1 = LocalTime.now();
		LocalTime time2 = LocalTime.now(clock);
		System.out.println(time1);
		System.out.println(time2);

		//Duration类，它持有的时间精确到秒和纳秒。这使得我们可以很容易得计算两个日期之间的不同
		LocalDateTime from = LocalDateTime.of(2018, Month.APRIL, 16, 0, 0, 0);
		LocalDateTime to = LocalDateTime.of(2018, Month.APRIL, 18, 23, 59, 59);
		//用于计算2014年4月16日和2015年4月16日之间的天数和小时数
		Duration duration = Duration.between(from, to);
		System.out.println("Duration in days: " + duration.toDays()); //相差天數
		System.out.println("Duration in hours: " + duration.toHours()); //相差小时数
		System.out.println("Duration in minutes: " + duration.toMinutes()); //相差分钟
		System.out.println("Duration in millis: " + duration.toMillis()); //相差毫秒
		System.out.println("Duration in nanos: " + duration.toNanos()); //相差纳秒
 
	}

}
