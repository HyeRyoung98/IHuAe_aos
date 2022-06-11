package com.example.ihuae_aos.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.Item.MsgItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SharedPreferencesManager {

    private static final String PREFERENCES_NAME = "ihuae_preferences";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static SharedPreferences getPreferences(Context mContext){
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void clearPreferences(Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static void setMonthItem(Context context, ArrayList<MonthVO> monthItems){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < monthItems.size(); i++) {
            MonthVO monthVO = monthItems.get(i);
            Gson gson = new Gson();
            String json = gson.toJson(monthVO);
            sb.append(json);
            if(i!=monthItems.size()-1) sb.append("JFin");
        }
        editor.putString("monthItems", sb.toString());

        editor.apply();
    }

    public static ArrayList<MonthVO> getMonthItem(Context context){
        SharedPreferences prefs = getPreferences(context);
        ArrayList<MonthVO> monthItems = new ArrayList<>();
        monthItems.clear();

        String sb = prefs.getString("monthItems", "");
        if(!sb.isEmpty()){
            String[] lis = sb.split("JFin");

            for (int i = 0; i < lis.length; i++) {
                String json = lis[i];
                Gson gson = new Gson();
                MonthVO monthVO = gson.fromJson(json, MonthVO.class);
                monthItems.add(monthVO);
            }
        }
        return monthItems;
    }

    public static void setCal(Context context, Calendar calendar, String key){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        String cal = sdf.format(calendar.getTime());
        editor.putString(key, cal);
        editor.apply();
    }

    public static Calendar getCal(Context context, String key){
        SharedPreferences prefs = getPreferences(context);
        Calendar calendar = Calendar.getInstance();
        String cal = prefs.getString(key, "");
        if(!cal.isEmpty()){
            Date date = null;
            try {
                date = sdf.parse(cal);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
            Log.d("############cal", calendar.getTime().toString());
            return calendar;
        }else{
            return null;
        }

    }

    public static void setMsgItem(Context context, ArrayList<MsgItem> msgItems){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msgItems.size(); i++) {
            MsgItem msg = msgItems.get(i);
            Gson gson = new Gson();
            String json = gson.toJson(msg);
            sb.append(json);
            if(i!=msgItems.size()-1) sb.append("JFin");
        }
        editor.putString("msgItems", sb.toString());
        editor.apply();
    }

    public static ArrayList<MsgItem> getMsgItem(Context context){
        SharedPreferences prefs = getPreferences(context);
        ArrayList<MsgItem> msgItems = new ArrayList<>();
        msgItems.clear();

        String sb = prefs.getString("msgItems", "");
        if(!sb.isEmpty()){
            String[] lis = sb.split("JFin");
            for (int i = 0; i < lis.length; i++) {
                String json = lis[i];
                Gson gson = new Gson();
                MsgItem msg = gson.fromJson(json, MsgItem.class);
                msgItems.add(msg);
            }
        }

        return msgItems;
    }
}
