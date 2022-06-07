package com.example.ihuae_aos.Edit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.Item.DayVO;
import com.example.ihuae_aos.R;
import com.example.ihuae_aos.databinding.WeekItemBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.itemVH> {

    public ArrayList<DayVO> weekDays = new ArrayList<>();

    private Context mContext;
    public WeekAdapter(Context context){
        this.mContext = context;
    }

    public OnWeekListener onWeekListener;
    public interface OnWeekListener{
        void onClick(int position);
    }
    public void setOnWeekListener(OnWeekListener onWeekListener){
        this.onWeekListener = onWeekListener;
    }

    @NonNull
    @Override
    public WeekAdapter.itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(WeekItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeekAdapter.itemVH holder, int position) {
        DayVO day = weekDays.get(position);
        holder.binding.weekText.setText(day.day+"");
        holder.itemView.setEnabled(day.isEnable);
        int textColor = day.isEnable? R.color.black : R.color.enable_font_gray;
        holder.binding.weekText.setTextColor(mContext.getColor(textColor));
        holder.binding.getRoot().setOnClickListener(view -> {
            if(onWeekListener!=null) onWeekListener.onClick(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return weekDays.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public WeekItemBinding binding;
        public itemVH(WeekItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
