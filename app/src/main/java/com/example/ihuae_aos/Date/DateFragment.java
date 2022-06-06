package com.example.ihuae_aos.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.Item.DayVO;
import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.MainActivity;
import com.example.ihuae_aos.Home.WriteDialog;
import com.example.ihuae_aos.databinding.FragmentDateBinding;

import java.util.Calendar;

public class DateFragment extends Fragment {
    private FragmentDateBinding binding;
    private MonthAdapter monthAdapter;
    private OnEventListener onEventListener;
    private int prePos = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        eventHandler();
    }

    private void init(){
        onEventListener = new OnEventListener() {
            @Override
            public void onClick(DayVO dayVO, int position) {
                binding.dailyFeelingContainer.setVisibility(View.VISIBLE);
                binding.day.setText(dayVO.day+"일");
                binding.contents.setText(dayVO.content);
            }
        };
        monthAdapter = new MonthAdapter(getContext(), onEventListener);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
                //return super.canScrollHorizontally();
            }

            @Override
            public boolean canScrollVertically() {
                return false;
                //return super.canScrollVertically();
            }
        };
        binding.monthRecycler.setLayoutManager(lm);
        binding.monthRecycler.setAdapter(monthAdapter);

        monthAdapter.months = ((MainActivity)getActivity()).monthItems;
        prePos = getThisMonthPosition();
        setMonthCal();
    }

    private void eventHandler(){
        binding.leftArr.setOnClickListener(view -> {
            if(prePos>0){
                prePos = prePos-1;
                setMonthCal();
                binding.dailyFeelingContainer.setVisibility(View.GONE);
            }else{
                Toast.makeText(getContext(), "첫번째 페이지입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.rightArr.setOnClickListener(view -> {
            if(prePos<2){
                prePos = prePos+1;
                setMonthCal();
                binding.dailyFeelingContainer.setVisibility(View.GONE);

            }else{
                Toast.makeText(getContext(), "마지막 페이지입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getThisMonthPosition(){
        int i = 0;
        Calendar cal = Calendar.getInstance();
        for (MonthVO month: monthAdapter.months) {
            if(month.Month==cal.get(Calendar.MONTH)+1) return i;
            i++;
        }
        return 2;
    }

    private void setMonthCal(){
        binding.monthRecycler.scrollToPosition(prePos);
        binding.monthText.setText(monthAdapter.months.get(prePos).Month+"월");
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


                /*
                dialog = new WriteDialog(getContext(), sta, content);
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






                PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.monthRecycler);
        SnapPagerScrollListener listener = new SnapPagerScrollListener(
                snapHelper,
                SnapPagerScrollListener.ON_SCROLL,
                true,
                new SnapPagerScrollListener.OnChangeListener() {
                    @Override
                    public void onSnapped(int position) {
                        //position 받아서 이벤트 처리
                        binding.monthText.setText(monthAdapter.months.get(position).Month+"월");
                    }
                }
        );
        binding.monthRecycler.addOnScrollListener(listener);
                 */