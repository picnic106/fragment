package java.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yaolan on 2015/9/17.
 */
public class DateUtils  extends org.apache.commons.lang.time.DateUtils{
    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String format(Date date,String pattern){
        if (date==null){
            date=new Date();
        }
        if (pattern==null){
            pattern="yyyy-MM-dd";
        }
       return DateFormatUtils.format(date,pattern);
    }

}
