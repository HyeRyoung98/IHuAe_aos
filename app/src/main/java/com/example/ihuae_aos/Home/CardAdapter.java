package com.example.ihuae_aos.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.GuideCardItemBinding;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.itemVH> {

    private Context mContext;
    public CardAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public CardAdapter.itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(GuideCardItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.itemVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public GuideCardItemBinding binding;
        public itemVH(GuideCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
