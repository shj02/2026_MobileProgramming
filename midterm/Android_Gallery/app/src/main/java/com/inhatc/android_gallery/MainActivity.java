package com.inhatc.android_gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Gallery objGallery;
    Toast objToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        objGallery = (Gallery)findViewById(R.id.gallery1);
        objGallery.setAdapter(new ImageAdapter(this));
        objGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                objToast = Toast.makeText(MainActivity.this, "Index: " + position, Toast.LENGTH_LONG);
                objToast.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;
        private Integer[] mThumbIds = {
                R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
                R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
                R.drawable.img_7, R.drawable.img_8, R.drawable.img_9
        };

        public ImageAdapter(Context objContxt) {
            mContext = objContxt;
            TypedArray objArray = obtainStyledAttributes(R.styleable.gallery1);
            mGalleryItemBackground = objArray.getResourceId(
                    R.styleable.gallery1_android_galleryItemBackground, 0);
            objArray.recycle();
        }
        public int getCount() { return mThumbIds.length; }
        public Object getItem(int position) { return position; }
        public long getItemId(int position) { return position; }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView objImgView;

            objImgView = new ImageView(mContext);
            objImgView.setImageResource(mThumbIds[position]);
            objImgView.setLayoutParams(new Gallery.LayoutParams(300, 300));
            objImgView.setScaleType(ImageView.ScaleType.FIT_XY);
            objImgView.setPadding(10, 10, 10, 10);
            objImgView.setBackgroundResource(mGalleryItemBackground);
            return objImgView;
        }
    }
}