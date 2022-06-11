package com.example.ihuae_aos.Item;

import java.util.Calendar;

public class MsgItem {
    public MsgItem(){}
    public String msg = "";
    public Calendar created_at;

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
