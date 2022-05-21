package com.example.ihuae_aos.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.R;
import com.example.ihuae_aos.databinding.FeelingStickerItemBinding;

public class FeelingStickerAdapter extends RecyclerView.Adapter<FeelingStickerAdapter.itemVH> {
    private Context mContext;
    public OnStickerClickListener onStickerClickListener;
    public int sel_position = 0;
    public interface OnStickerClickListener{
        void onClick(int position);
    }
    public void setOnStickerClickListener(OnStickerClickListener onStickerClickListener){
        this.onStickerClickListener = onStickerClickListener;
    }

    private int stickerColors[] = {R.color.status_pleasure, R.color.status_blue, R.color.status_aggro, R.color.status_serenity};

    public FeelingStickerAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(FeelingStickerItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        holder.binding.feelingSticker.setColorFilter(mContext.getColor(stickerColors[position]));
        if(sel_position-1 == position){
            holder.binding.rootContainer.setSelected(true);
        }else{
            holder.binding.rootContainer.setSelected(false);
        }
        holder.binding.getRoot().setOnClickListener(view -> {
            if(onStickerClickListener != null) onStickerClickListener.onClick(holder.getLayoutPosition());
            sel_position = holder.getLayoutPosition()+1;
        });
    }

    @Override
    public int getItemCount() {
        return stickerColors.length;
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public FeelingStickerItemBinding binding;
        public itemVH(FeelingStickerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
