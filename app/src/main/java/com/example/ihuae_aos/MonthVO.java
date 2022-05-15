package com.example.ihuae_aos;

import android.util.Log;

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
        int year = monthDate.getTime().getYear();
        int month = monthDate.getTime().getMonth();
        days.clear();
        for (int i = 1; i < lastDay+1; i++) {
            DayVO day = new DayVO();
            day.today.set(year, month, i, 0, 0,0);
            days.add(day);
        }
    }


}
