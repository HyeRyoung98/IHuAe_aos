package com.example.ihuae_aos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.FragmentDateBinding;

import java.util.Calendar;

public class DateFragment extends Fragment {
    private FragmentDateBinding binding;
    private MonthAdapter monthAdapter;
    private WriteDialog dialog;
    private OnEventListener onEventListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        dialog = new WriteDialog(getContext());
        onEventListener = new OnEventListener() {
            @Override
            public void onClick(Calendar day, int position, int month) {
                dialog.show();
                dialog.setOnWriteDialogListener(new WriteDialog.OnWriteDialogListener() {
                    @Override
                    public void onResist(int status, String contents) {
                        for (int i = 0 ; i < monthAdapter.months.size(); i++) {
                            MonthVO monthVO = monthAdapter.months.get(i);
                            Log.d("#######month1", monthVO.monthDate.getTime().getMonth()+"");
                            Log.d("#######month2", month+"");
                            Log.d("#######month1", monthVO.monthDate.get(Calendar.MONTH)+"");
                            if(monthVO.monthDate.getTime().getMonth() == month){
                                monthAdapter.months.get(i).days.get(position).status = status;
                                monthAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        };
        monthAdapter = new MonthAdapter(getContext(), onEventListener);


        binding.monthRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.monthRecycler.setAdapter(monthAdapter);
        MonthVO monthItem = new MonthVO();
        monthItem.getDays();
        monthAdapter.months.add(monthItem);







    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
