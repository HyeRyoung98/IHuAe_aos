package com.example.ihuae_aos;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.DayItemBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.itemVH> {

    private Context mContext;
    public ArrayList<DayVO> days = new ArrayList<>();
    private OnEventListener onEventListener;

    public DayAdapter(Context context, OnEventListener onEventListener){
        this.mContext = context;
        this.onEventListener = onEventListener;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(DayItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        DayVO day = days.get(holder.getLayoutPosition());
        holder.binding.dayText.setText(day.today.get(Calendar.DAY_OF_MONTH)+"");
        day.month = day.today.getTime().getMonth();
        int textColor = day.status == 0 ? R.color.black : R.color.white;
        int textStyle = day.status == 0 ? Typeface.NORMAL : Typeface.BOLD;
        holder.binding.dayText.setTextColor(mContext.getColor(textColor));
        holder.binding.dayText.setTypeface(null, textStyle);

        int visible = day.status != 0 ? ViewGroup.VISIBLE : ViewGroup.INVISIBLE;
        holder.binding.feelingSticker.setVisibility(visible);
        holder.binding.feelingSticker.setColorFilter(mContext.getColor(setSticker(day.status)));

        holder.binding.getRoot().setOnClickListener(view -> {
            if(onEventListener != null) {
                onEventListener.onClick(day.today, holder.getLayoutPosition(), day.month);
            }
        });
    }

    private int setSticker(int status){
        switch (status){
            case 1: return R.color.status_pleasure;
            case 2: return R.color.status_blue;
            case 3: return R.color.status_aggro;
            case 4: return R.color.status_serenity;
            default: return R.color.status_none;
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public DayItemBinding binding;
        public itemVH(DayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}