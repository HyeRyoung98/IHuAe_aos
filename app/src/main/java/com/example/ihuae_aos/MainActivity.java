package com.example.ihuae_aos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.databinding.ActivityMainBinding;
import com.example.ihuae_aos.databinding.ToolbarLayoutBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public MainPagerAdapter adapter;
    private ToolbarLayoutBinding toolbar;
    public static ArrayList<MonthVO> monthItems = new ArrayList<>();

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
        createMonthItem();
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
        monthItems.clear();
        for (int i = 0; i < 3; i++) {
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
}