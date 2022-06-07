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
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < monthItems.size(); i++) {
            jsonArray.put(monthItems.get(i));
        }
        Log.d("#####shared json", jsonArray.toString());
        editor.putString("monthItems", jsonArray.toString());
        editor.apply();
    }

    public static ArrayList<MonthVO> getMonthItem(Context context){
        SharedPreferences prefs = getPreferences(context);
        String json = prefs.getString("monthItems", "");
        Log.d("#####Json", json);
        ArrayList<MonthVO> monthItems = new ArrayList<>();
        monthItems.clear();
        if(!json.isEmpty()){
            try {
                //JSONObject obj = new JSONObject(json);
                JSONArray jsonArray = new JSONArray(json);
                Log.d("###########ja", jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    MonthVO monthVO = GsonUtil.getCustomGson().fromJson(jsonArray.getJSONObject(i).toString(), MonthVO.class);
                    Log.d("################monthVO", monthVO.toString());
                    monthItems.add(monthVO);
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
            return calendar;
        }else{
            return null;
        }

    }

    public static void setMsgItem(Context context, ArrayList<MsgItem> msgItems){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < msgItems.size(); i++) {
            jsonArray.put(msgItems.get(i));
        }
        editor.putString("msgItems", jsonArray.toString());
        editor.apply();
    }

    public static ArrayList<MsgItem> getMsgItem(Context context){
        SharedPreferences prefs = getPreferences(context);
        String json = prefs.getString("msgItems", "");
        ArrayList<MsgItem> msgItems = new ArrayList<>();
        msgItems.clear();
        if(!json.isEmpty()){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    MsgItem msgItem = GsonUtil.getCustomGson().fromJson(jsonArray.getJSONObject(i).toString(), MsgItem.class);
                    msgItems.add(msgItem);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return msgItems;
    }


    /*
        public static void setEndCal(Context context, Calendar endCal){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        String cal = sdf.format(endCal.getTime());
        editor.putString("endCal", cal);
        editor.apply();
    }

    public static Calendar getEndCal(Context context){
        SharedPreferences prefs = getPreferences(context);
        Calendar endCal = Calendar.getInstance();
        String cal = prefs.getString("endCal", "");
        if(!cal.isEmpty()){
            Date date = null;
            try {
                date = sdf.parse(cal);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            endCal.setTime(date);
        }else{
            endCal.set(0,0,0,0,0,0);
        }
        return endCal;
    }
     */
}
