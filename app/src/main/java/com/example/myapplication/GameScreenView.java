package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameScreenView extends View {

    private Paint simplePaint;
    private static boolean loaded = false;

    public GameScreenView(Context context) {
        super(context);

        if (!loaded) {
            init();
            loaded = true;
        }
    }

    public GameScreenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    @Override
    protected void onDraw(Canvas c) {
        Rect r = new Rect(50, 50, 200, 200);
        c.drawRect(r, simplePaint);
    }

    private void init() {
        simplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        simplePaint.setColor(0x0000ff);
    }

}
