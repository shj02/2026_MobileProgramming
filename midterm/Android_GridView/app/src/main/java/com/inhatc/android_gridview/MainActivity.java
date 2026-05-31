package com.inhatc.android_gridview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    GridView objGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        objGridView = (GridView)findViewById(R.id.gridView1);
        objGridView.setAdapter(new ImageAdapter(this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] mThumbIds = {
                R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
                R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
                R.drawable.img_7, R.drawable.img_8, R.drawable.img_9
        };
        public ImageAdapter(Context objContext) { mContext = objContext; }
        public int getCount() { return mThumbIds.length; }
        public Object getItem(int position) { return null; }
        public long getItemId(int position) { return 0; }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView objImageView;
            if (convertView == null) {
                objImageView = new ImageView(mContext);
                objImageView.setLayoutParams(new GridView.LayoutParams(300,200));
                objImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                objImageView.setPadding(8, 8, 8, 8);
            } else {
                objImageView = (ImageView) convertView;
            }
            objImageView.setImageResource(mThumbIds[position]);
            return objImageView;
        }
    }
}