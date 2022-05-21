package com.example.ihuae_aos.Edit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.DairyItemBinding;

import java.util.ArrayList;

public class DairyAdapter extends RecyclerView.Adapter<DairyAdapter.itemVH> {

    public ArrayList<String> dairyItems = new ArrayList<>();

    private Context mContext;
    public DairyAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public DairyAdapter.itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(DairyItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DairyAdapter.itemVH holder, int position) {
        holder.binding.dairyText.setText(dairyItems.get(position));
    }

    @Override
    public int getItemCount() {
        return dairyItems.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public DairyItemBinding binding;
        public itemVH(DairyItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
