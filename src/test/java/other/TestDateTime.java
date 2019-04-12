package other;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

/**
 * 日期类工具类(DateTime)
 * 
 * Joda-Time是Java SE 8之前Java日期和时间类的广泛使用的替代品
 */
public class TestDateTime {

	/**
	 * 获取年月日点分秒
	 */
	@Test
	public void test1() {
		DateTime dt = new DateTime();

		// 年
		int year = dt.getYear();
		// 月
		int month = dt.getMonthOfYear();
		// 日
		int day = dt.getDayOfMonth();
		// 星期
		int week = dt.getDayOfWeek();
		// 小时
		int hour = dt.getHourOfDay();
		// 分钟
		int min = dt.getMinuteOfHour();
		// 秒
		int second = dt.getSecondOfMinute();
		// 毫秒
		int msec = dt.getMillisOfSecond();

		System.out.println(year + "" + month + "" + day + " " + hour + ":" + min + ":" + second + " " + msec);
		System.out.println("星期" + week);
	}

	/**
	 * DateTime转为Date和Calendar对象
	 */
	@Test
	public void test2() {
		DateTime dt = new DateTime();

		// 转换为java.util.Date对象
		Date d1 = new Date(dt.getMillis());
		Date d2 = dt.toDate();
		System.out.println(d1);
		System.out.println(d2);

		// 转换成java.util.Calendar对象
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(dt.getMillis());
		Calendar c2 = dt.toCalendar(Locale.getDefault());
		System.out.println(c1.getTime());
		System.out.println(c2.getTime());
	}

	/**
	 * DateTime构造函数
	 */
	@Test
	public void test3() {
		DateTime dt = new DateTime();

		Calendar c1 = Calendar.getInstance();
		dt = new DateTime(c1);

		Date date = new Date();
		dt = new DateTime(date);

		String dateStr = "2019-04-12";
		dt = new DateTime(dateStr);

		// 时间字符串，必须是格式化过的。日期和时间之间得有个'T'
		String timeStr = "2019-04-12T13:14:00";
		dt = new DateTime(timeStr);

		System.out.println(dt);
	}

	/**
	 * 日期前后推算
	 */
	@Test
	public void test4() {
		DateTime dt = new DateTime();

		// 昨天
		DateTime yesterday = dt.minusDays(1);
		System.out.println(yesterday);

		// 明天
		DateTime tomorrow = dt.plusDays(1);
		System.out.println(tomorrow);

		// 1个月前
		DateTime before1month = dt.minusMonths(1);
		System.out.println(before1month);

		// 3个月后
		DateTime after3month = dt.plusMonths(3);
		System.out.println(after3month);

		// 2年前
		DateTime before2year = dt.minusYears(2);
		System.out.println(before2year);

		// 5年后
		DateTime after5year = dt.plusYears(5);
		System.out.println(after5year);
	}

	/**
	 * 取特殊日期
	 */
	@Test
	public void test5() {
		// 当前日期
		DateTime dt = DateTime.now();
		System.out.println(dt);

		// 月末日期
		DateTime lastday = dt.dayOfMonth().withMinimumValue();
		System.out.println(lastday);

		// 90天后那周的周一
		DateTime firstday = dt.plusDays(90).dayOfWeek().withMinimumValue();
		System.out.println(firstday);
	}

	/**
	 * 日期比较
	 */
	@Test
	public void test6() {
		DateTime begin = new DateTime("2019-02-01");
		DateTime end = new DateTime("2019-05-01");

		// 计算区间天数
		Period p = new Period(begin, end, PeriodType.days());
		int days = p.getDays();
		System.out.println(days);

		// 计算区间毫秒数
		Duration d = new Duration(begin, end);
		long time = d.getMillis();
		System.out.println(time);

		// 计算特定日期是否在该区间内
		Interval i = new Interval(begin, end);
		boolean contained = i.contains(new DateTime("2019-03-01"));
		System.out.println(contained);
	}

	/**
	 * 格式化输出
	 */
	@Test
	public void test7() {
		DateTime dateTime = new DateTime();

		String s1 = dateTime.toString("yyyy/MM/dd hh:mm:ss.SSSa");
		String s2 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
		String s3 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
		String s4 = dateTime.toString("yyyy/MM/dd HH:mm ZZZZ");
		String s5 = dateTime.toString("yyyy/MM/dd HH:mm Z");

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
	}

	/**
	 * 其它
	 */
	@Test
	public void test8() {
		Date date = DateTime.parse(DateTime.now().toString("yyyy-MM-dd")).toDate();
		System.out.println(date);
	}

}
