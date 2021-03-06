package com.example.ihuae_aos.Item;

import java.util.ArrayList;
import java.util.Calendar;
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

    public int year = 0;
    public int month = 0;
    public int day = 0;
    public Calendar today = new GregorianCalendar();

    public int status = 0;
    public String content = "";
    public boolean isEnable = false;

    public String ques = "";
    public String ans = "";
    public ArrayList<String> diaries = new ArrayList<>();

    @Override
    public String toString() {
        return "{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", today=" + today +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", isEnable=" + isEnable +
                ", ques='" + ques + '\'' +
                ", ans='" + ans + '\'' +
                ", diaries=" + diaries +
                '}';
    }


}
