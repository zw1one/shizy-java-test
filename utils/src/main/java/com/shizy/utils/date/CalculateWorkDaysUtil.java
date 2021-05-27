package com.shizy.utils.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * zz
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/5/19 11:50
 */
public class CalculateWorkDaysUtil {

    /*
      特殊的工作日(星期六、日工作)
              */
    private static final List<String> SPECIAL_WORK_DAYS = new ArrayList<>();


    /*   特殊的休息日(星期一到五休息)
     */
    private static final List<String> SPECIAL_REST_DAYS = new ArrayList<>();

    static {
        initSpecialDays();
    }

    private static void initSpecialDays() {
        //手动维护特殊放假或工作的日子
        SPECIAL_WORK_DAYS.add("2021-09-18");
        SPECIAL_WORK_DAYS.add("2021-09-26");
        SPECIAL_WORK_DAYS.add("2021-10-09");

        SPECIAL_REST_DAYS.add("2021-06-14");
        SPECIAL_REST_DAYS.add("2021-09-20");
        SPECIAL_REST_DAYS.add("2021-09-21");
        SPECIAL_REST_DAYS.add("2021-10-01");
        SPECIAL_REST_DAYS.add("2021-10-04");
        SPECIAL_REST_DAYS.add("2021-10-05");
        SPECIAL_REST_DAYS.add("2021-10-06");
        SPECIAL_REST_DAYS.add("2021-10-07");
        SPECIAL_REST_DAYS.add("2022-01-28");
        SPECIAL_REST_DAYS.add("2022-01-29");
        SPECIAL_REST_DAYS.add("2022-01-30");
        SPECIAL_REST_DAYS.add("2022-01-31");
        SPECIAL_REST_DAYS.add("2021-02-01");
        SPECIAL_REST_DAYS.add("2021-02-02");
        SPECIAL_REST_DAYS.add("2021-02-03");
        SPECIAL_REST_DAYS.add("2021-02-04");
        SPECIAL_REST_DAYS.add("2021-02-05");
        SPECIAL_REST_DAYS.add("2021-02-06");
        SPECIAL_REST_DAYS.add("2021-02-07");
    }

    public static int getworkDays(String strStartDate, String strEndDate) {
        return getworkDays(strStartDate, strEndDate, "yyyy-MM-dd");
    }

    public static int getworkDays(String strStartDate, String strEndDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);

        Calendar cl1 = Calendar.getInstance();
        Calendar cl2 = Calendar.getInstance();

        try {
            cl1.setTime(df.parse(strStartDate));
            cl2.setTime(df.parse(strEndDate));

        } catch (ParseException e) {
            System.out.println("日期格式非法");
            e.printStackTrace();
        }

        int count = 0;
        while (cl1.compareTo(cl2) <= 0) {
            //如果不是周六或者周日则工作日+1
            if (cl1.get(Calendar.DAY_OF_WEEK) != 7 && cl1.get(Calendar.DAY_OF_WEEK) != 1) {
                count++;
                //如果不是周六或者周日，但是该日属于国家法定节假日或者特殊放假日则-1
                if (SPECIAL_REST_DAYS.contains(DateFormatUtils.format(cl1.getTime(), pattern))) {
                    count--;
                }
            }
            //如果是周六或者周日，但是该日属于需要工作的日子则 +1
            if (SPECIAL_WORK_DAYS.contains(DateFormatUtils.format(cl1.getTime(), pattern))) {
                count++;
            }
            cl1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                getworkDays("2020-09-27", "2020-10-11")
        );
    }
}
