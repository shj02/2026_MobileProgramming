package com.inhatc.android_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOK;
    private EditText editSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cow);

        Button btnOK = (Button)findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btnOK) {
            Intent CallIntent = getIntent();
            editSound = (EditText)findViewById(R.id.editInputSound);
            CallIntent.putExtra("Animal_Sound", editSound.getText().toString());
            setResult(RESULT_OK, CallIntent);
            finish();
        }
    }
}