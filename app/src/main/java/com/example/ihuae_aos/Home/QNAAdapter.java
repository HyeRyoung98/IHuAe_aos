package com.example.ihuae_aos.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.QNAItemBinding;

import java.util.ArrayList;

public class QNAAdapter extends RecyclerView.Adapter<QNAAdapter.itemVH> {

    public ArrayList<String> ques = new ArrayList<>();

    private Context mContext;
    public QNAAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(QNAItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        holder.binding.qTxt.setText(ques.get(position));

    }

    @Override
    public int getItemCount() {
        return ques.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public QNAItemBinding binding;
        public itemVH(QNAItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
