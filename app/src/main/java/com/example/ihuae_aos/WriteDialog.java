package com.example.ihuae_aos;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WriteDialog extends Dialog {
    private ImageView cancelBtn;
    private RecyclerView stickerRecycler;
    private FeelingStickerAdapter adapter;
    private EditText editText;
    private Button registBtn;

    private int status = 0;

    private Context mContext;

    public OnWriteDialogListener onWriteDialogListener;
    public interface OnWriteDialogListener{
        void onResist(int status, String contents);
    }
    public void setOnWriteDialogListener(OnWriteDialogListener onWriteDialogListener){
        this.onWriteDialogListener = onWriteDialogListener;
    }

    public WriteDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        setContentView(R.layout.dialog_write);

        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.getWindow().setGravity(Gravity.CENTER); // 가운데 위치하도록
        //this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT/* DpToPxConverter.convert(mContext,700)*/);

        init();
        eventHandler();
    }

    private void init(){
        cancelBtn = findViewById(R.id.write_dialog_cancel_button);
        stickerRecycler = findViewById(R.id.write_dialog_feeling_sticker_recycler);
        editText = findViewById(R.id.write_dialog_feeling_edit_text);
        registBtn = findViewById(R.id.write_dialog_regist_btn);
    }

    private void eventHandler(){
        cancelBtn.setOnClickListener(view -> {
            dismiss();
        });
        adapter = new FeelingStickerAdapter(mContext);
        stickerRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        stickerRecycler.setAdapter(adapter);
        adapter.setOnStickerClickListener(new FeelingStickerAdapter.OnStickerClickListener() {
            @Override
            public void onClick(int position) {
                status = position+1;
                adapter.notifyDataSetChanged();
            }
        });


        registBtn.setOnClickListener(view -> {
            String text = editText.getText().toString().trim();
            if(onWriteDialogListener!=null) onWriteDialogListener.onResist(status, text);
            dismiss();
        });
    }
}
