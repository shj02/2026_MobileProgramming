package com.inhatc.android_checkboxradiobutton;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView objTV;
    private RadioButton rdoScreenSet;
    private RadioButton rdoScreenReset;
    private CheckBox chkColor_R;
    private CheckBox chkColor_G;
    private CheckBox chkColor_B;
    private int bkColor = 0xFFFFFFFF;
    private String strData;
    private View objLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        objTV = (TextView)findViewById(R.id.textView3);
        objLayout = findViewById(R.id.main);

        rdoScreenSet = (RadioButton)findViewById(R.id.radioButton);
        rdoScreenReset = (RadioButton)findViewById(R.id.radioButton2);
        rdoScreenSet.setOnClickListener(this);
        rdoScreenReset.setOnClickListener(this);

        chkColor_R = (CheckBox)findViewById(R.id.checkBox);
        chkColor_G = (CheckBox)findViewById(R.id.checkBox2);
        chkColor_B = (CheckBox)findViewById(R.id.checkBox3);
        chkColor_R.setOnClickListener(this);
        chkColor_G.setOnClickListener(this);
        chkColor_B.setOnClickListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        bkColor = 0xFF000000;
        if(rdoScreenSet.isChecked()) {
            strData = "Color Setting Mode : " + rdoScreenSet.getText().toString();
            if(chkColor_R.isChecked()) bkColor |= 0xFFFF0000;
            if(chkColor_G.isChecked()) bkColor |= 0xFF00FF00;
            if(chkColor_B.isChecked()) bkColor |= 0xFF0000FF;
        } else {
            strData = "Color Setting Mode : " + rdoScreenReset.getText().toString();
            chkColor_R.setChecked(false);
            chkColor_G.setChecked(false);
            chkColor_B.setChecked(false);
            if(chkColor_R.isPressed()||chkColor_G.isPressed()||chkColor_B.isPressed())
                strData = "Setting the color setting mode to Set.";
        }
        objTV.setTextColor(Color.GRAY);
        objTV.setBackgroundColor(bkColor);
        objLayout.setBackgroundColor(bkColor);
        objTV.setText(strData);
    }
}