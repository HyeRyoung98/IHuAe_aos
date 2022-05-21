package com.example.ihuae_aos.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.MainActivity;
import com.example.ihuae_aos.Home.WriteDialog;
import com.example.ihuae_aos.databinding.FragmentDateBinding;

import java.util.Calendar;

public class DateFragment extends Fragment {
    private FragmentDateBinding binding;
    private MonthAdapter monthAdapter;
    private WriteDialog dialog;
    private OnEventListener onEventListener;

    private boolean isFirst = true;

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
        onEventListener = new OnEventListener() {
            @Override
            public void onClick(Calendar day, int position, int month, int sta, String content) {
                binding.dailyFeelingContainer.setVisibility(View.VISIBLE);
                binding.day.setText(day.get(Calendar.DAY_OF_MONTH)+"일");
                binding.contents.setText(content);
                dialog = new WriteDialog(getContext(), sta, content);
                /*
                dialog.show();
                dialog.setOnWriteDialogListener(new WriteDialog.OnWriteDialogListener() {
                    @Override
                    public void onResist(int status, String contents) {
                        for (int i = 0 ; i < monthAdapter.months.size(); i++) {
                            MonthVO monthVO = monthAdapter.months.get(i);
                            if(monthVO.monthDate.getTime().getMonth() == month){
                                monthAdapter.months.get(i).days.get(position).status = status;
                                monthAdapter.months.get(i).days.get(position).content = contents;

                                monthAdapter.notifyDataSetChanged();
                                monthVOs = monthAdapter.months;
                                binding.contents.setText(contents);
                            }
                        }
                    }
                });
                 */
            }
        };
        monthAdapter = new MonthAdapter(getContext(), onEventListener);
        binding.monthRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.monthRecycler.setAdapter(monthAdapter);
        monthAdapter.months = ((MainActivity)getActivity()).monthItems;
    }

    @Override
    public void onResume() {
        super.onResume();
        monthAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.dailyFeelingContainer.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
