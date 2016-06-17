package fr.upsud.sushi.laby.mainactivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import java.security.spec.ECField;
import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Constants;

/**
 * Created by proval on 6/14/16.
 */
public class SurfaceViewDrawer {
    public static final int BG_LAYER = 0;
    public static final int PLAYER_LAYER = 1;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
             | Paint.ANTI_ALIAS_FLAG);

    private ArrayList<SurfaceView> surfaceViews;
    float scale;
    private int frameWidth = 133;
    private int frameHeight = 133;

    SurfaceViewDrawer(SurfaceView bg, SurfaceView player, LinearLayout l, Level lvl) {
        surfaceViews = new ArrayList<SurfaceView>();
        surfaceViews.add(bg);
        surfaceViews.add(player);
        this.scale = lvl.getCellSize()/Constants.IMAGE_SIZE;
    }

    public ArrayList<SurfaceView> getSurfaceViews() {
        return this.surfaceViews;
    }


    /*
    public  void draw(int x, int y, Bitmap b, int id) {

        Bitmap bm = getResizedBitmap(b);
        int sizex = bm.getWidth() ;
        int sizey=bm.getHeight();


        try {
            SurfaceView v = surfaceViews.get(id);
            SurfaceHolder ourHolder = v.getHolder();


                if (ourHolder.getSurface().isValid()) {
                    if(id==1) v.getHolder().setFormat(PixelFormat.TRANSPARENT);

                    Canvas canvas =ourHolder.lockCanvas();

                    if (id == 1) canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    canvas.drawBitmap(bm, x *sizex, y * sizey, paint);
                    ourHolder.unlockCanvasAndPost(canvas);
                } else {
                    System.out.println("surfaceholder pas valide");
                }

        } catch (Exception e) {
        }

    }
*/

    public  void draw(int x, int y, Bitmap b, int id) {

        Bitmap bm = getResizedBitmap(b);
        int sizex = bm.getWidth() ;
        int sizey=bm.getHeight();
        RectF whereToDraw = new RectF(
                x*sizex, y*sizex,
                (x+1)*sizex ,
                (1+y) *sizey);

        try {
            SurfaceView v = surfaceViews.get(id);
            SurfaceHolder ourHolder = v.getHolder();


            if (ourHolder.getSurface().isValid()) {
                if(id==1) v.getHolder().setFormat(PixelFormat.TRANSPARENT);

                Canvas canvas =ourHolder.lockCanvas();

                if (id == 1) canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                canvas.drawBitmap(bm,null, whereToDraw, paint);

                ourHolder.unlockCanvasAndPost(canvas);
            } else {
                System.out.println("surfaceholder pas valide");
            }

        } catch (Exception e) {
        }

    }
    public  void draw(int x, int y, Bitmap b, int id, int kx, int ky) {
        int gap = 15;
        Bitmap bm = getResizedBitmap(b);
        int sizex = bm.getWidth() ;
        int sizey=bm.getHeight();
        RectF whereToDraw = new RectF(
                x*sizex-kx*gap, y*sizey-ky*gap,
                (x+1)*sizex-kx*gap ,
                (1+y) *sizey-ky*gap);

        try {
            SurfaceView v = surfaceViews.get(id);
            SurfaceHolder ourHolder = v.getHolder();


            if (ourHolder.getSurface().isValid()) {
                if(id==1) v.getHolder().setFormat(PixelFormat.TRANSPARENT);

                Canvas canvas =ourHolder.lockCanvas();

                if (id == 1) canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                canvas.drawBitmap(bm, null, whereToDraw, paint);

                ourHolder.unlockCanvasAndPost(canvas);
            } else {
                System.out.println("surfaceholder pas valide");
            }

        } catch (Exception e) {
        }

    }



    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        int newWidth =(int) (width * this.scale);
        int newHeight =(int) (height * this.scale);

        /*float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
*/
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
        //bm.recycle();
        return resizedBitmap;
    }

}

