package com.inhatc.android_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCow;
    private Button btnDog;
    private Toast objToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCow = (Button)findViewById(R.id.btnCow);
        btnDog = (Button)findViewById(R.id.btnDog);
        btnCow.setOnClickListener(this);
        btnDog.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btnCow) {
            Intent cowIntent = new Intent(MainActivity.this, CowActivity.class);
//            startActivity(cowIntent);
            startActivityForResult(cowIntent, 1);
        } else if(view == btnDog) {
            Intent dogIntent = new Intent(MainActivity.this, DogActivity.class);
//            startActivity(dogIntent);
            startActivityForResult(dogIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            String strData = data.getStringExtra("Animal_Sound");
            objToast = Toast.makeText(this, strData, Toast.LENGTH_LONG);
            objToast.show();
        }
    }
}