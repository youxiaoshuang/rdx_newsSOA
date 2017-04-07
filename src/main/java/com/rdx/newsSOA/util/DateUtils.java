package com.rdx.newsSOA.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String chDATE_FORMAT = "yyyy年MM月dd日";
	public static final String defalut_date_format = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat ymdFormat = new SimpleDateFormat(DATE_FORMAT);
	public static SimpleDateFormat chYmdFormat = new SimpleDateFormat(chDATE_FORMAT);
	public static SimpleDateFormat defalutDateFormat = new SimpleDateFormat(defalut_date_format);
	private static final SimpleDateFormat longsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat longsdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat longsdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat longsdf4 = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
	private static final SimpleDateFormat shortsdf = new SimpleDateFormat("HH:mm");
	/**
	 * 文章列表
	 * @param publishTime
	 * @return
	 */
	public static String formatDateStrForPage(Date publishTime) {
		if(publishTime==null){
			return "--";
		}
		Date now=new Date();
		long diff = (now.getTime()/1000 - publishTime.getTime()/1000);
		String startDay = longsdf2.format(now);
		startDay = startDay+" 00:00:00";
		Date dateStartDay = null;
		try {
			dateStartDay = longsdf3.parse(startDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String strYear = longsdf4.format(now);
		strYear = strYear +"-01-01 00:00:00";
		Date dateYear= null;
		try {
			dateYear = longsdf3.parse(strYear);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long minutes = diff / (60);
		long hours = diff /(60*60);
		if(diff<60){
			return "刚才";
		}else if(diff>=60 && diff<60*60 ){
			return minutes+"分钟前";
		}else if(diff>=60*60 && diff<4*60*60){  
			return hours+"小时前";
		}
		else if(diff>=4*60*60 && diff <24*60*60 && publishTime.after(dateStartDay)){
			return shortsdf.format(publishTime);
		}
		else if(diff>4*60*60 && diff <24*60*60 && publishTime.before(dateStartDay)){
			return sdf.format(publishTime);
		}
		else if(diff >=24*60*60 && diff <365*24*60*60 && publishTime.after(dateYear)){
			return sdf.format(publishTime);
		}else {
			return longsdf.format(publishTime);
		}
		
	}
	/**
	 * 
	 * 功能描述:在baseTime上增加天数，如6月30号增加一天就自动变为7月1号
	 * 返回结果从日历逻辑上是正确的
	 * <pre>
	 * Modify Reason:(修改原因,不需覆盖，直接追加.)
	 *     bianjie:   2015年6月15日      新建
	 * </pre>
	 *
	 * @param baseTime
	 * @param addDay
	 * @return
	 */
	public static Date addDays(Date baseTime, int addDay){
		baseTime = ridTimeFromDay(baseTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseTime);
		calendar.add(Calendar.DAY_OF_MONTH, addDay);
		return calendar.getTime();
	}
	
	public static Date ridTimeFromDay(Date date){
		try {
			return ymdFormat.parse(ymdFormat.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDateByYMD(int year,int month,int day){
		try {
			return ymdFormat.parse(year+"-"+month+"-"+day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
     * 获取当前月的最后一天 
     * @return
     * @throws ParseException 
     */  
    public static Date getMaxMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return ridTimeFromDay(calendar.getTime());
    }
	
    public static int compareYmd(Date date1,Date date2){
    	Calendar c1 = Calendar.getInstance();
    	c1.setTime(date1);
    	Calendar c2 = Calendar.getInstance();
    	c2.setTime(date2);
    	return c1.compareTo(c2);
    }
    
    public static String formatDateByPattern(Date date,SimpleDateFormat pattern){
    	return pattern.format(date);
    }
    
    /**
     * 通过指定年月日时分秒获得Date
     */
    public static Date getDateBySetNo(int year,int month,int day,int hour,int minute,int second){
    	Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
    }
    
    public static String addMonth(Date date,Integer num) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, num);
		String preMonth = defalutDateFormat.format(c.getTime());
		return preMonth;
	}
    
	public static String formatDateToString(Date date) {
		String format = defalutDateFormat.format(date);
		return format;
		
	}
	
	public static void main(String[] args) {
		String str = "2016-05-24 13:04:40";
		Date before = null;
		try {
			before = longsdf3.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date now = new Date();
		System.out.println("now:"+longsdf3.format(now));
		System.out.println("publishTime:"+str);
		str = formatDateStrForPage(before);
		System.out.println(str);
	}
}
