package com.example.ihuae_aos.Edit;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.Item.DayVO;
import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.MainActivity;
import com.example.ihuae_aos.databinding.FragmentEditBinding;

import java.util.Calendar;

public class EditFragment extends Fragment {
    private FragmentEditBinding binding;
    private WeekAdapter weekAdapter;
    private DairyAdapter dairyAdapter;
    private DairyDialog dairyDialog;

    private Calendar todayCal = Calendar.getInstance();
    private TextWatcher tw;
    private DayVO day;
    private int pos = -1;

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
        dairyDialog = new DairyDialog(getActivity());

        weekAdapter = new WeekAdapter(getContext());
        binding.weekRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.weekRecycler.setAdapter(weekAdapter);
        getData();

        dairyAdapter = new DairyAdapter(getContext());
        binding.diaryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.diaryRecycler.setAdapter(dairyAdapter);

        tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                day.ans = editable.toString().trim();
                weekAdapter.notifyItemChanged(pos);
            }
        };

    }
    private void setUI(){
        int thisWeek = MainActivity.dDay/7+1;
        binding.thisWeek.setText(thisWeek+"주 차");
    }

    private void getData(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, (1-cal.get(Calendar.DAY_OF_WEEK)));
        for (MonthVO month:MainActivity.monthItems) {
            if(month.Month==cal.get(Calendar.MONTH)){
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
            public void onClick(int position) {
                day = weekAdapter.weekDays.get(position);
                binding.todayAns.removeTextChangedListener(tw);

                dairyAdapter.dairyItems.clear();
                dairyAdapter.dairyItems.addAll(day.diaries);
                dairyAdapter.notifyDataSetChanged();

                binding.todayAns.setText(day.ans);
                binding.todayQues.setText(day.ques);

                binding.qNAContainer.setVisibility(View.VISIBLE);
                if(day.day==todayCal.get(Calendar.DAY_OF_MONTH)) {
                    binding.addBtn.setVisibility(View.VISIBLE);
                    binding.todayAns.setEnabled(true);
                    binding.todayAns.addTextChangedListener(tw);
                }
                else {
                    binding.addBtn.setVisibility(View.GONE);
                    binding.todayAns.setEnabled(false);
                }
            }
        });

        binding.addBtn.setOnClickListener(view -> {
            dairyDialog.show();
        });

        dairyDialog.setOnDialogListener(new DairyDialog.OnDialogListener() {
            @Override
            public void onResist(String dairy) {
                day.diaries.add(dairy);
                weekAdapter.notifyItemChanged(pos);
                dairyAdapter.dairyItems.add(dairy);
                dairyAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.todayAns.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
