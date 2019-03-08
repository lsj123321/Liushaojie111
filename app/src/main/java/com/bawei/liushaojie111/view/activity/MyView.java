package com.bawei.liushaojie111.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.bawei.liushaojie111.R;

public class MyView extends AppCompatImageView {
    private int radius;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //测量自定义View的宽高

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        radius = Math.min(measuredWidth, measuredHeight)/2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint=new Paint();
        float mScale=1;
        paint.setColor(getResources().getColor(R.color.colorAccent));
        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        if (drawable!=null){
            Bitmap bitmap=drawable.getBitmap();
            BitmapShader bitmapShader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
            mScale=(radius * 2.0f) / Math.min(bitmap.getHeight(),bitmap.getWidth());
            Matrix matrix=new Matrix();
            matrix.setScale(mScale,mScale);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            canvas.drawCircle(radius,radius,radius,paint);
        }else {
            super.onDraw(canvas);
        }

    }
}
