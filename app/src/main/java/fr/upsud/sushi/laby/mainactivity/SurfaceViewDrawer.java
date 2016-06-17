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


    public  void draw(int x, int y, Bitmap b, int id, Canvas canvas, int kx, int ky) {
        int gap = 15;
        Bitmap bm = getResizedBitmap(b);
        int sizex = bm.getWidth() ;
        int sizey=bm.getHeight();
        RectF whereToDraw = new RectF(
                x*sizex-kx*gap, y*sizey-ky*gap,
                (x+1)*sizex-kx*gap ,
                (1+y) *sizey-ky*gap);
        if (id!= 0) canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bm, null, whereToDraw, paint);
    }


    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int newWidth =(int) (width * this.scale);
        int newHeight =(int) (height * this.scale);
        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
        return resizedBitmap;
    }

}

