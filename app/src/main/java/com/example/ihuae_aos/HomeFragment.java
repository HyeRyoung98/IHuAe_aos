package com.example.ihuae_aos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ihuae_aos.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WriteDialog dialog;
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
        monthVOs = ((MainActivity)getActivity()).monthItems;
        Calendar date = new GregorianCalendar();
        for (int i = 0; i < monthVOs.size(); i++) {
            MonthVO monthVO = monthVOs.get(i);
            if(monthVO.monthDate.get(Calendar.MONTH)==date.get(Calendar.MONTH)){
                for (int j = 0; j < monthVOs.get(i).days.size(); j++) {
                    if(monthVOs.get(i).days.get(j).today.get(Calendar.DAY_OF_MONTH)==date.get(Calendar.DAY_OF_MONTH)){
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
                //((MainActivity)getActivity()).adapter.notifyDataSetChanged();
            }
        });
        binding.writeBtn.setOnClickListener(v -> {
            dialog.show();
        });
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
