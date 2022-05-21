package com.example.ihuae_aos.Message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.databinding.FragmentEditBinding;
import com.example.ihuae_aos.databinding.FragmentMassageBinding;

public class MessageFragment extends Fragment {
    private FragmentMassageBinding binding;
    private MsgAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMassageBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        eventHandler();
    }

    private void init(){
        adapter = new MsgAdapter(getContext());
        binding.msgRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.msgRecycler.setAdapter(adapter);
        adapter.msgItems.clear();
    }

    private void eventHandler(){
        binding.sendMsg.setOnClickListener(view -> {
            String msg = binding.editMsg.getText().toString().trim();
            if(!msg.isEmpty()){
                adapter.msgItems.add(msg);
                adapter.notifyDataSetChanged();
                binding.editMsg.setText(null);
            }else{
                Toast.makeText(getContext(), "메세지를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
