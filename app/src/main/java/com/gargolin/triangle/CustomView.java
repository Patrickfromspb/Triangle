package com.gargolin.triangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.08.2016.
 */
public class CustomView extends View {
    private Paint paint;
    private static ArrayList<Integer> listX=new ArrayList<Integer>();
    private static ArrayList<Integer> listY=new ArrayList<Integer>();
    public CustomView(Context context) {
        super(context);
        
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (listX.size()==0 || ((listX.get(listX.size()-1)!=((int) motionEvent.getX())||(listY.get(listY.size()-1)!=(int)motionEvent.getY())))){

                listX.add((int) motionEvent.getX());
                    listY.add((int)motionEvent.getY());
                    invalidate();
                }
             return true;
            }
        });
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int i=listX.size();
        switch(i){
            case 0: break;
            case 1:
            { canvas.drawCircle(listX.get(i-1),listY.get(i-1),40,paint);break;}
            case 2:
            {canvas.drawLine(listX.get(i-1),listY.get(i-1),listX.get(i-2),listY.get(i-2),paint);break;}
            default: {
                canvas.drawLine(listX.get(i - 1), listY.get(i - 1), listX.get(i - 2), listY.get(i - 2), paint);
                canvas.drawLine(listX.get(i - 1), listY.get(i - 1), listX.get(i - 3), listY.get(i - 3), paint);
                canvas.drawLine(listX.get(i - 2), listY.get(i - 2), listX.get(i - 3), listY.get(i - 3), paint);
                ArrayList<Float> list = triangle(listX.get(i - 1), listX.get(i - 2), listX.get(i - 3), listY.get(i - 1), listY.get(i - 2), listY.get(i - 3));
                canvas.drawLine(listX.get(i - 1), listY.get(i - 1), list.get(0), list.get(1), paint);
                canvas.drawLine(listX.get(i - 2), listY.get(i - 2), list.get(2), list.get(3), paint);
                canvas.drawLine(listX.get(i - 3), listY.get(i - 3), list.get(4), list.get(5), paint);
                canvas.drawLine(listX.get(i - 1), listY.get(i - 1), list.get(6), list.get(7), paint);
                canvas.drawLine(listX.get(i - 2), listY.get(i - 2), list.get(8), list.get(9), paint);
                canvas.drawLine(listX.get(i - 3), listY.get(i - 3), list.get(10), list.get(11), paint);
            }

        }
    }
        private ArrayList<Float> triangle(int  ax, int bx, int cx, int ay, int by,int cy){
            ArrayList<Float> list=new ArrayList<Float>();
            float y= (float) 0.1;
            list.add((float) ((bx*1.0+cx)*0.5));
            list.add((float) ((by*1.0+cy)*0.5));
            list.add((float) ((ax*1.0+cx)*0.5));
            list.add((float) ((ay*1.0+cy)*0.5));
            list.add((float) ((bx*1.0+ax)*0.5));
            list.add((float) ((by*1.0+ay)*0.5));
            float a= (float) Math.sqrt((bx-cx)*(bx-cx)+(by-cy)*(by-cy));
            float b= (float) Math.sqrt((ax-cx)*(ax-cx)+(ay-cy)*(ay-cy));
            float c= (float) Math.sqrt((bx-ax)*(bx-ax)+(by-ay)*(by-ay));
            y=c/b;
            list.add((float) ((bx*1.0+y*cx)/(1+y)));
            list.add((float) ((by*1.0+y*cy)/(1+y)));
            y=c/a;
            list.add((float) ((ax*1.0+y*cx)/(1+y)));
            list.add((float) ((ay*1.0+y*cy)/(1+y)));
            y=a/b;
            list.add((float) ((bx*1.0+y*ax)/(1+y)));
            list.add((float) ((by*1.0+y*ay)/(1+y)));
            return list;



        }
}
