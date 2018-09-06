package com.mengb.mymvpapplication.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mengb.mymvpapplication.R;

import java.util.jar.Attributes;

public class DelEdittext extends LinearLayout  {
    private EditText et;
    private ImageButton ib;
    public DelEdittext(Context context){
        super(context);
    }
    public DelEdittext(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.del_edit,this,true);
        et=(EditText)findViewById(R.id.et);
        ib = (ImageButton)findViewById(R.id.ib);
        ib.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()!=0){
                    showBtn();
                }else {
                    hideBtn();
                }

            }
        };
        et.addTextChangedListener(tw);
    }


    public void hideBtn() {
       ib.setVisibility(GONE);
    }


    public void showBtn() {
        ib.setVisibility(VISIBLE);
    }



}
