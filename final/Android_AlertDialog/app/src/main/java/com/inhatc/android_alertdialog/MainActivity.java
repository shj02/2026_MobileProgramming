package com.inhatc.android_alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton objTGButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        objTGButton = (ToggleButton)findViewById(R.id.tgbtnSwitch);
        objTGButton.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        showDialog(0);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        super.onCreateDialog(id, args);
        AlertDialog dlgAlert;
        final String items[] = {"통화 계속", "통화 종료"};

        dlgAlert = new AlertDialog.Builder(this)
                .setIcon(R.drawable.question)
                .setTitle("알림!")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strData = null;
                        if(i == 0) strData = "통화중....";
                        else strData = "통화 종료";
                        Toast.makeText(MainActivity.this, strData, Toast.LENGTH_SHORT).show();
                    }
                })
                .setView(null)
                .setPositiveButton("확인(OK)", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); }
                }).create();

        return dlgAlert;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }
}