package com.inhatc.android_menuxml;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView objTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater objInflater = getMenuInflater();
        objInflater.inflate(R.menu.optionmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        int itemID = item.getItemId();

        if (itemID == R.id.menu_Text_Color_Red) {
            objTxtView.setTextColor(Color.RED);
            return true;
        } else if (itemID == R.id.menu_Text_Color_Green) {
            objTxtView.setTextColor(Color.GREEN);
            return true;
        } else if (itemID == R.id.menu_Text_Color_Blue) {
            objTxtView.setTextColor(Color.BLUE);
            return true;
        }

        if (itemID == R.id.menu_Text_Style_Normal) {
            objTxtView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            item.setChecked(true);
            return true;
        } else if (itemID == R.id.menu_Text_Style_Bold) {
            objTxtView.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD);
            item.setChecked(true);
            return true;
        } else if (itemID == R.id.menu_Text_Style_Italic) {
            objTxtView.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
            item.setChecked(true);
            return true;
        }

        if (itemID == R.id.menu_Text_Size_20sp) {
            objTxtView.setTextSize(20);
            return true;
        } else if (itemID == R.id.menu_Text_Size_24sp) {
            objTxtView.setTextSize(24);
            return true;
        } else if (itemID == R.id.menu_Text_Size_32sp) {
            objTxtView.setTextSize(32);
            return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater objInflater = getMenuInflater();
        objInflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);

        int itemID = item.getItemId();

        if (itemID == R.id.menu_Text_Background_Color_Red) {
            objTxtView.setBackgroundColor(Color.RED);
            return true;
        } else if (itemID == R.id.menu_Text_Background_Color_Green) {
            objTxtView.setBackgroundColor(Color.GREEN);
            return true;
        } else if (itemID == R.id.menu_Text_Background_Color_Blue) {
            objTxtView.setBackgroundColor(Color.BLUE);
            return true;
        }
        return false;
    }
}