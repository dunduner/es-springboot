package com.zn.juc.timeutil;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeTest {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println("当前年月日："+now);

        System.out.println(now.getMonth());//FEBRUARY 二月
        System.out.println(now.getDayOfWeek());//WEDNESDAY
        System.out.println(now.getYear());//2020
        System.out.println(now.getMonthValue());//2
        System.out.println(now.getDayOfMonth());//26


        LocalDate date = LocalDate.of(2020,2,26);
        System.out.println("自定义日期:"+date);
        System.out.println(now.equals(date));

        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println("当前时分秒毫秒："+time);
        LocalTime newTime = time.plusHours(3);
        System.out.println("三个小时后的时间为:"+newTime);

        System.out.println("====================================");
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018,2,6);
        MonthDay birthday = MonthDay.of(date2.getMonth(),date2.getDayOfMonth());
        System.out.println(birthday);
        MonthDay currentMonthDay = MonthDay.from(date1);
        System.out.println(currentMonthDay);
        if(currentMonthDay.equals(birthday)){
            System.out.println("是你的生日");
        }else{
            System.out.println("你的生日还没有到");
        }

        System.out.println("====================================");
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());


        System.out.println("====================================");
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);


        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);
    }

}
