package com.example.ihuae_aos.Item;

import com.example.ihuae_aos.Item.DayVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MonthVO {
    public MonthVO(){}


    public Calendar monthDate = new GregorianCalendar();
    public int Month = 0;

    public ArrayList<DayVO> days = new ArrayList<>();
    public void getDays(){
        int lastDay = monthDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        int year = monthDate.get(Calendar.YEAR);
        int month = monthDate.get(Calendar.MONTH);
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int firstWeek = cal.get(Calendar.DAY_OF_WEEK);
        days.clear();
        int i = 1;
        while(i != firstWeek){
            DayVO day = new DayVO();
            day.year = year;
            day.month = month;
            days.add(day);
            i++;
        }
        for (int j = 1; j < lastDay+1; j++) {
            DayVO day = new DayVO();
            day.year = year;
            day.month = month;
            day.day = j;
            day.today.set(year, month, j, 0, 0,0);
            day.week = day.today.get(Calendar.DAY_OF_WEEK);
            days.add(day);
        }
    }


}
