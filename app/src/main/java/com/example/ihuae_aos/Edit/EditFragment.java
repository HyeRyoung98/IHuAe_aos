package com.example.ihuae_aos.Edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.Item.DayVO;
import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.MainActivity;
import com.example.ihuae_aos.databinding.FragmentEditBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class EditFragment extends Fragment {
    private FragmentEditBinding binding;
    private WeekAdapter weekAdapter;
    private DairyAdapter dairyAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setUI();
        eventHandler();
    }

    private void init(){
        weekAdapter = new WeekAdapter(getContext());
        binding.weekRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.weekRecycler.setAdapter(weekAdapter);

        dairyAdapter = new DairyAdapter(getContext());
        binding.diaryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.diaryRecycler.setAdapter(dairyAdapter);
        getData();
    }
    private void setUI(){
        Calendar cal = Calendar.getInstance();
        //int thisWeek = cal.get(cal.WEEK_OF_YEAR);
        //binding.thisWeek.setText(thisWeek+"주 차");
    }

    private void getData(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, (1-cal.get(Calendar.DAY_OF_WEEK)));
        for (MonthVO month:((MainActivity)getActivity()).monthItems) {
            if(month.monthDate.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
                for (int i = 0; i < month.days.size(); i++) {
                    if(month.days.get(i).day==cal.get(Calendar.DAY_OF_MONTH)){
                        weekAdapter.weekDays.clear();
                        for (int j = 0; j < 7; j++) {
                            weekAdapter.weekDays.add(month.days.get(i+j));
                            weekAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

        }
    }

    private void eventHandler(){
        weekAdapter.setOnWeekListener(new WeekAdapter.OnWeekListener() {
            @Override
            public void onClick(ArrayList<String> diaries) {
                dairyAdapter.dairyItems.clear();
                dairyAdapter.dairyItems.addAll(diaries);
                dairyAdapter.notifyDataSetChanged();
            }
        });

        binding.addBtn.setOnClickListener(view -> {

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
