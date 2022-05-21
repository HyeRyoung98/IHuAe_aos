package com.example.ihuae_aos.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DayVO {
    public DayVO(){}

    /** status
     *  0 기록없음
     *  1 기쁨
     *  2 슬픔
     *  3 화남
     *  4 그저그럼
     */
    public int status = 0;
    public int month = 0;
    public String content = "";
    public Calendar today = new GregorianCalendar();
    public ArrayList<String> diaries = new ArrayList<>();

}