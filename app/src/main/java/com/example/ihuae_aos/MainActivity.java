package com.example.ihuae_aos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.Item.MsgItem;
import com.example.ihuae_aos.Util.SharedPreferencesManager;
import com.example.ihuae_aos.databinding.ActivityMainBinding;
import com.example.ihuae_aos.databinding.ToolbarLayoutBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public MainPagerAdapter adapter;
    private ToolbarLayoutBinding toolbar;
    public static ArrayList<MonthVO> monthItems = new ArrayList<>();
    public static ArrayList<MsgItem> msgItems = new ArrayList<>();
    public static int dDay = 0;
    public static Calendar startCal = Calendar.getInstance();
    public static Calendar endCal = Calendar.getInstance();

    private int tab_on_ic_ids[] = {R.drawable.tab_on_ic_home, R.drawable.tab_on_ic_date, R.drawable.tab_on_ic_edit, R.drawable.tab_on_ic_message};
    private int tab_off_ic_ids[] = {R.drawable.tab_off_ic_home, R.drawable.tab_off_ic_date, R.drawable.tab_off_ic_edit, R.drawable.tab_off_ic_message};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        toolbar = binding.toolbar;
        init();
        eventHandler();
    }

    private void init(){
        if (monthItems.size()==0){
            createMonthItem();
        }else{
            calDDay();
        }
        Log.d("##############month", monthItems.toString());

        adapter = new MainPagerAdapter(this.getSupportFragmentManager(), getLifecycle());
        binding.viewPager2.setCurrentItem(0);

        binding.viewPager2.setUserInputEnabled(false);
        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.setOffscreenPageLimit(4);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                if(position == 0) tab.setIcon(tab_on_ic_ids[position]);
                else tab.setIcon(tab_off_ic_ids[position]);
            }
        }).attach();
    }

    private void createMonthItem(){
        initCal(startCal);
        initCal(endCal);
        endCal.add(Calendar.DAY_OF_MONTH, 100);

        dDay = 1;
        monthItems.clear();
        for (int i = 0; i < endCal.get(Calendar.MONTH)-startCal.get(Calendar.MONTH)+1; i++) {
            MonthVO monthItem = new MonthVO();
            monthItem.monthDate.add(Calendar.MONTH, i);
            monthItem.Month = monthItem.monthDate.get(Calendar.MONTH)+1;
            monthItem.getDays();
            monthItems.add(monthItem);
        }
    }

    private void eventHandler(){
        toolbar.folderIcon.setOnClickListener(view -> {

        });

        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(tab_on_ic_ids[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(tab_off_ic_ids[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initCal(Calendar cal){
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    private void calDDay(){
        Calendar todayCal = Calendar.getInstance(); //오늘날자 가져오기
        initCal(todayCal);

        long today = todayCal.getTimeInMillis()/86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
        long startDay = startCal.getTimeInMillis()/86400000;
        long count = today - startDay; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
        dDay = (int) count;
        Log.d("######dDay", dDay+"//");
    }

    private void saveData(){
        Log.d("###########monthITem", monthItems.toString());
        Log.d("############start", startCal.getTime().toString());
        Log.d("##########msg", msgItems.toString());
        SharedPreferencesManager.setMonthItem(this, monthItems);
        SharedPreferencesManager.setCal(this, startCal, "startCal");
        SharedPreferencesManager.setCal(this, endCal, "endCal");
        SharedPreferencesManager.setMsgItem(this, msgItems);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}