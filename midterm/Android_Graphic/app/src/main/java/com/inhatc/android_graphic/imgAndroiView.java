package com.inhatc.android_graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class imgAndroiView extends View {
    Drawable imgAndroi;
    int ix, iy;
    int imgWidth, imgHeight;

    public imgAndroiView(Context context, AttributeSet attrs) {
        super(context, attrs);

        imgAndroi = this.getResources().getDrawable(R.drawable.img_androi);
        imgWidth = imgAndroi.getIntrinsicWidth();
        imgHeight = imgAndroi.getIntrinsicHeight();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        imgAndroi.setBounds(ix, iy, ix+imgWidth, iy+imgHeight);
        imgAndroi.draw(canvas);

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ix = (this.getWidth() - imgWidth) / 2;
        iy = (this.getHeight() - imgHeight) / 2;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                ix -= 15;
                if(ix <= 0)
                    ix = 0;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ix += 15;
                if(ix >= this.getWidth() - imgWidth)
                    ix = this.getWidth() - imgWidth;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                iy -= 15;
                if(iy <= 0)
                    iy = 0;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                iy += 15;
                if(iy >= this.getHeight() - imgHeight)
                    iy = this.getHeight() - imgHeight;
                break;
        }
        this.invalidate();

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ix = (int)event.getX();
        iy = (int)event.getY();
        this.invalidate();

        return super.onTouchEvent(event);
    }
}
