package org.antwhale.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: 何欢
 * @Date: 2022/4/16 21:13
 * @Description:时间工具类
 */
public class SimpleDateUtils {

    private static DateTimeFormatter FormatFactory(String format) {
        switch (format) {
            case "yyyyMMdd":
                return DateTimeFormatter.ofPattern("yyyyMMdd");
            case "yyyyMM":
                return DateTimeFormatter.ofPattern("yyyyMM");
            case "yyyyMMddHHmmss":
                return DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            case "yyyy_mm_dd":
                return DateTimeFormatter.ofPattern("yyyy-mm-dd");
            case "yyyy-MM-dd HH:mm:ss":
                return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            default:
                return null;
        }
    }

    /**
     * @author 何欢
     * @Description 得到当前的时间
     * @Date 21:51 2022/5/9
     * @Param
     * @Return
     **/
    public static String getNowDate(String format) {
        return LocalDateTime.now().format(FormatFactory(format));
    }

    /**
     * @author 何欢
     * @Description 得到n个月后的日期
     * @Date 18:01 2022/5/15
     * @Param
     * @Return
     **/
    public static Date getDateAfterN(Date date, Integer n) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH, n);//在日历的月份上增加n个月
        return c.getTime();
    }


    /**
     * @author 何欢
     * @Description String转成LocalDateTime
     * @Date 20:35 2022/5/24
     * @Param
     * @Return
     **/
    public static LocalDateTime formatStringToLocalDateTime(String localDateTime) {
        if ("".equals(localDateTime)) {
            return null;
        }
        return LocalDate.parse(localDateTime, FormatFactory("yyyy-MM-dd HH:mm:ss")).atStartOfDay();
    }

    /**
    *@author 何欢
    *@Date 17:02 2022/8/28
    *@Description LocalDateTime转String
    **/
    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        if ("".equals(localDateTime)) {
            return null;
        }
        return localDateTime.format(FormatFactory(format));
    }


}
