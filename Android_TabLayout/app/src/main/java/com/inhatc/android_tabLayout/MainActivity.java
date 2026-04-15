package com.inhatc.android_tabLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TabHost myTabHost = null; //TabHost Object
    TabHost.TabSpec myTabSpec; //TabSpec Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        myTabHost = (TabHost)findViewById(R.id.tabhost);
        myTabHost.setup();

        // Add Tab
        myTabSpec = myTabHost.newTabSpec("Artists")
                .setIndicator("Artists")
                .setContent(R.id.tab1);
        myTabHost.addTab(myTabSpec);


        myTabSpec = myTabHost.newTabSpec("Albums")
                .setIndicator("Albums")
                .setContent(R.id.tab2);
        myTabHost.addTab(myTabSpec);


        myTabSpec = myTabHost.newTabSpec("Songs")
                .setIndicator("Songs")
                .setContent(R.id.tab3);
        myTabHost.addTab(myTabSpec);


        myTabHost.setCurrentTab(0);

        myTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.RED);
        myTabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.GREEN);
        myTabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.BLUE);

        for(int idx=0; idx<myTabHost.getTabWidget().getChildCount(); ++idx) {
            myTabHost.getTabWidget().getChildAt(idx).getLayoutParams().height=150;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}