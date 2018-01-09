package tool;

import java.util.List;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//用于统计数据时获取连续的日期
public class TraverseDate {

    private static transient int gregorianCutoverYear = 1582;

    private static final int[] DAYS_P_MONTH_LY= {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final int[] DAYS_P_MONTH_CY= {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final int Y = 0, M = 1, D = 2;

    public static int[] splitYMD(String date){
        date = date.replace("-", "");
        int[] ymd = {0, 0, 0};
        ymd[Y] = Integer.parseInt(date.substring(0, 4));
        ymd[M] = Integer.parseInt(date.substring(4, 6));
        ymd[D] = Integer.parseInt(date.substring(6, 8));
        return ymd;
    }

    public static boolean isLeapYear(int year) {
        return year >= gregorianCutoverYear ?
                ((year%4 == 0) && ((year%100 != 0) || (year%400 == 0))) : (year%4 == 0);
    }


    private static int[] addOneDay(int year, int month, int day){
        if(isLeapYear(year)){
            day++;
            if( day > DAYS_P_MONTH_LY[month -1]){
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }else{
            day++;
            if( day > DAYS_P_MONTH_CY[month -1]){
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }
        int[] ymd = {year, month, day};
        return ymd;
    }

    public static String formatMonthDay(int decimal){
        DecimalFormat df = new DecimalFormat("00");
        return df.format( decimal );
    }

    public static String formatYear(int decimal){
        DecimalFormat df = new DecimalFormat("0000");
        return df.format( decimal );
    }

    public static long countDay(String begin,String end){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate , endDate;
        long day = 0;
        try {
            beginDate= format.parse(begin);
            endDate=  format.parse(end);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static List<String> getEveryday(String beginDate , String endDate){
        long days = countDay(beginDate, endDate);
        int[] ymd = splitYMD( beginDate );
        List<String> everyDays = new ArrayList<String>();
        everyDays.add(beginDate);
        for(int i = 0; i < days; i++){
            ymd = addOneDay(ymd[Y], ymd[M], ymd[D]);
            everyDays.add(formatYear(ymd[Y])+"-"+formatMonthDay(ymd[M])+"-"+formatMonthDay(ymd[D]));
        }
        return everyDays;
    }

    public static void main(String[] args) {
        List<String> list = TraverseDate.getEveryday("2017-12-20", "2018-01-02");
        for (String result : list) {
            System.out.println(result);
        }
    }
}
