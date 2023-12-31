package com.team15.lower.wellnessbaby;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by AyeNyeinThu on 25. 10. 2016.
 */
public class Rounded_View extends ImageView
{
    public Rounded_View(Context ctx, AttributeSet attrs)
    {
        super(ctx,attrs);
    }
    public void onDraw(Canvas convas)
    {
        Drawable drawable=getDrawable();
        if(drawable != null && getWidth() != 0 && getHeight()!=0)
        {
            Bitmap bitmap=((BitmapDrawable)drawable).getBitmap().copy(Bitmap.Config.ARGB_8888,true);
            int w=getWidth();
            int h=getHeight();
            convas.drawBitmap(getRoundedCroppedBitmap(bitmap,w),0.0f,0.0f,null);
        }
    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius)
    {
        Bitmap finalBitmap;
        if(bitmap.getWidth()== radius && bitmap.getWidth()==radius)
        {
            finalBitmap=bitmap;
        }
        else {
            finalBitmap= Bitmap.createScaledBitmap(bitmap,radius,radius,false);
        }
        Bitmap output= Bitmap.createBitmap(finalBitmap.getWidth(),finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(output);
        Paint paint=new Paint();
        Rect rect=new Rect(0,0,finalBitmap.getWidth(),finalBitmap.getWidth());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0,0,0,0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(((float)(finalBitmap.getWidth()/2))+0.7f,((float)(finalBitmap.getHeight()/2))+0.7f,((float) (finalBitmap.getWidth()/2))+0.f,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap,rect,rect,paint);

        return  output;
    }

}