package basic.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * Created by sunny-chen on 17/2/15.
 */
public class App {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Calendar 类是一个抽象类，它为特定瞬间与一组诸如 YEAR、MONTH、DAY_OF_MONTH、HOUR 等
     * 日历字段之间的转换提供了一些方法，并为操作日历字段（例如获得下星期的日期）提供了一些方法。
     * 瞬间可用毫秒值来表示，它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00.000，格里高利历）的偏移量。
     * */
    private void test() {
        Calendar cal = Calendar.getInstance();

        // 取得当前日期头一天
        cal.add(Calendar.DAY_OF_YEAR, -1);

        // 取得当前日期后一天
//        cal.add(Calendar.DAY_OF_MONTH, +1);

        System.out.println("Today is " + format.format(Calendar.getInstance().getTime()));
        System.out.println("the day is " + format.format(cal.getTime()));
    }

    /**
     * 获取固定月份日期
     * */
    private void test1() {
        // 2012-12-25，Java中月份是0-11
        Calendar cal = new GregorianCalendar(2012,11,25,0,0,0);
        Date date = cal.getTime();
        System.out.println("2012 christmas is:" + format.format(date));
    }

    /**
     * 取日期的部分
     * */
    private void test2() {
        Calendar cal   = Calendar.getInstance();
        int      year  = cal.get(Calendar.YEAR);
        int      month = cal.get(Calendar.MONDAY);
        int      day   = cal.get(Calendar.DAY_OF_MONTH);
        int      hour  = cal.get(Calendar.HOUR_OF_DAY);
        int      min   = cal.get(Calendar.MINUTE);
        int      sec   = cal.get(Calendar.SECOND);

        System.out.println(year);
        System.out.println(month + 1);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(min);
        System.out.println(sec);
    }

    /**
     * 取得当前月份的最大天数，和最小的天数
     * */
    private void test3() {
        Calendar cal  = Calendar.getInstance();
        int      day  = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int      day1 = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        System.out.println(day);
        System.out.println(day1);
    }

    /**
     * 获取当前月份的第一天
     * */
    private void test4() {
        format = new SimpleDateFormat("yyyy-MM-01");
        Date firstDay = new Date();
        System.out.println("the month first day is " + format.format(firstDay));
    }

    /**
     * 获取当前月份的最后一天
     * */
    private void test5() {
        Calendar cal = Calendar.getInstance();
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        format = new SimpleDateFormat("yyyy-MM-" + maxDay);
        System.out.println("the month the last day is " + format.format(cal.getTime()));
    }

    /**
     * 求两个日期之间的相隔的天数
     * */
    private void test6() throws ParseException {
        format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = format.parse("2015-7-12");
        Date endDate   = format.parse("2015-11-21");
        long day       = (endDate.getTime() - beginDate.getTime())/(24 * 60 * 60 * 1000);
        System.out.println("相隔的天数=" + day);
    }

    /**
     * 获取一年前的日期
     * */
    private void test7() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        long beforeTime = (today.getTime()/1000)-(24 * 60 * 60 * 365);  //一年后日期就是+
        today.setTime(beforeTime * 1000);
        String beforeDate = format.format(today);
        System.out.println(beforeDate);
    }

    /**
     * 求10个小时之后的日期
     * */
    private void test8() {
        format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, -10); //10个小时之后是正10
        System.out.println("date : " + format.format(cal.getTime()));
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            app.test8();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
