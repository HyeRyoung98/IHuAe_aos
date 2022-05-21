package com.example.ihuae_aos.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.MsgItemBinding;

import java.util.ArrayList;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.itemVH> {

    public ArrayList<String> msgItems = new ArrayList<>();

    private Context mContext;
    public MsgAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public MsgAdapter.itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(MsgItemBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.itemVH holder, int position) {
        holder.binding.msgTxt.setText(msgItems.get(position));
    }

    @Override
    public int getItemCount() {
        return msgItems.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public MsgItemBinding binding;
        public itemVH(MsgItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
