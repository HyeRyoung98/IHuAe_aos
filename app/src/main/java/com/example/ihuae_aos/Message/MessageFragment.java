package com.example.ihuae_aos.Message;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.Item.MsgItem;
import com.example.ihuae_aos.MainActivity;
import com.example.ihuae_aos.databinding.FragmentMassageBinding;

import java.util.Calendar;

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
        adapter.msgItems.addAll(MainActivity.msgItems);
        binding.msgRecycler.scrollToPosition(adapter.msgItems.size()-1);
    }

    private void eventHandler(){
        binding.sendMsg.setOnClickListener(view -> {
            String msg = binding.editMsg.getText().toString().trim();
            if(!msg.isEmpty()){
                MsgItem msgItem = new MsgItem();
                msgItem.msg = msg;
                msgItem.created_at = Calendar.getInstance();
                adapter.msgItems.add(msgItem);
                adapter.notifyDataSetChanged();
                binding.editMsg.setText(null);
                binding.msgRecycler.scrollToPosition(adapter.msgItems.size()-1);
            }else{
                Toast.makeText(getContext(), "???????????? ??????????????????.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.editMsg.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.msgItems.addAll(adapter.msgItems);
        binding = null;
    }
}
