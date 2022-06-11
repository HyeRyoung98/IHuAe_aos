package com.example.ihuae_aos.Home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae_aos.R;

public class GuideCardDialog extends Dialog {
    private ImageView cancelBtn;
    private RecyclerView cardRecycler;
    private CardAdapter cardAdapter;

    private Context mContext;
    public GuideCardDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        setContentView(R.layout.dialog_guide_card);

        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        //this.getWindow().setGravity(Gravity.CENTER); // 가운데 위치하도록

        init();
        eventHandler();
    }

    private void init(){
        cancelBtn = findViewById(R.id.guide_card_dialog_cancel_button);
        cardRecycler = findViewById(R.id.guide_card_dialog_card_recycler);

        cardAdapter = new CardAdapter(mContext);
        cardRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        cardRecycler.setAdapter(cardAdapter);
    }

    private void eventHandler(){
        cancelBtn.setOnClickListener(view -> {
            dismiss();
        });
    }
}
