package com.example.ihuae_aos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.MonthlyItemBinding;

import java.util.ArrayList;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.itemVH> {

    private Context mContext;
    public ArrayList<MonthVO> months = new ArrayList<>();

    private OnEventListener onEventListener;

    public MonthAdapter(Context context, OnEventListener onEventListener){
        this.mContext = context;
        this.onEventListener = onEventListener;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(MonthlyItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        MonthVO month = months.get(holder.getLayoutPosition());
        holder.dayAdapter.days.clear();
        holder.dayAdapter.days.addAll(month.days);
        holder.dayAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public MonthlyItemBinding binding;
        public DayAdapter dayAdapter;

        public itemVH(MonthlyItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            dayAdapter = new DayAdapter(mContext, onEventListener);
            binding.dayRecycler.setLayoutManager(new GridLayoutManager(mContext, 7));
            binding.dayRecycler.setAdapter(dayAdapter);
            binding.dayRecycler.setScrollContainer(false);

        }
    }
}
