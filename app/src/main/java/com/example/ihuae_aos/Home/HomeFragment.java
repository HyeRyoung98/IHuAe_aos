package com.example.ihuae_aos.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ihuae_aos.Item.DayVO;
import com.example.ihuae_aos.MainActivity;
import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.databinding.FragmentHomeBinding;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WriteDialog dialog;
    private QNAAdapter qnaAdapter;
    private ArrayList<MonthVO> monthVOs = new ArrayList<>();
    private DayVO dayVO = new DayVO();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        binding.dDay.setText("D+"+MainActivity.dDay);

        monthVOs = MainActivity.monthItems;
        Calendar date = new GregorianCalendar();
        for (int i = 0; i < monthVOs.size(); i++) {
            MonthVO monthVO = monthVOs.get(i);
            if(monthVO.monthDate.get(Calendar.MONTH)==date.get(Calendar.MONTH)){
                for (int j = 0; j < monthVOs.get(i).days.size(); j++) {
                    if(monthVOs.get(i).days.get(j).day==date.get(Calendar.DAY_OF_MONTH)){
                        dayVO = monthVOs.get(i).days.get(j);

                    }
                }
            }

        }
        dialog = new WriteDialog(getContext(), dayVO.status, dayVO.content);
        dialog.setOnWriteDialogListener(new WriteDialog.OnWriteDialogListener() {
            @Override
            public void onResist(int status, String contents) {
                dayVO.status = status;
                dayVO.content = contents;
            }
        });
        binding.writeBtn.setOnClickListener(v -> {
            dialog.show();
        });

        qnaAdapter = new QNAAdapter(getContext());
        binding.todayQNARecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.todayQNARecycler.setAdapter(qnaAdapter);
        qnaAdapter.ques.clear();
        for (int i = 0; i < MainActivity.monthItems.size(); i++) {
            MonthVO month = MainActivity.monthItems.get(i);
            for (int j = 0; j < month.days.size(); j++) {
                DayVO day = month.days.get(j);
                if(day.isEnable){
                    qnaAdapter.ques.add(day.ques);
                }
            }
        }
        binding.todayQNARecycler.scrollToPosition(MainActivity.dDay-1);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
