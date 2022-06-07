package com.example.ihuae_aos.Edit;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ihuae_aos.R;

public class DairyDialog extends Dialog {

    private Context mContext;
    private ImageView cancelBtn;
    private EditText dairyEdit;
    private TextView resistBtn;

    public OnDialogListener onDialogListener;
    public interface OnDialogListener{
        void onResist(String dairy);
    }
    public void setOnDialogListener(OnDialogListener onDialogListener){
        this.onDialogListener = onDialogListener;
    }

    public DairyDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;

        setContentView(R.layout.dialog_dairy);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.CENTER); // 가운데 위치하도록

        init();
        eventHandler();
    }

    private void init(){
        cancelBtn = findViewById(R.id.dairy_dialog_cancel_btn);
        dairyEdit = findViewById(R.id.dairy_dialog_edit_text);
        resistBtn = findViewById(R.id.dairy_dialog_resist_btn);
    }



    private void eventHandler(){
        cancelBtn.setOnClickListener(view -> {
            dismiss();
        });

        resistBtn.setOnClickListener(view -> {
            if(onDialogListener!=null) {
                String text = dairyEdit.getText().toString().trim();
                onDialogListener.onResist(text);
                dairyEdit.setText(null);
                dismiss();
            }
        });

    }
}
