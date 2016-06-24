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

//import fr.upsud.sushi.laby.R;
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


    SurfaceViewDrawer(SurfaceView bg, SurfaceView player, LinearLayout l, Level lvl) {
        surfaceViews = new ArrayList<SurfaceView>();
        surfaceViews.add(bg);
        surfaceViews.add(player);
        this.scale = Constants.IMAGE_SIZE;
    }

    SurfaceViewDrawer(SurfaceView bg, SurfaceView player, SurfaceView chili, SurfaceView wall, LinearLayout l, Level lvl) {
        surfaceViews = new ArrayList<SurfaceView>();
        surfaceViews.add(bg);
        surfaceViews.add(player);
        surfaceViews.add(chili);
        surfaceViews.add(wall);
        this.scale = Constants.IMAGE_SIZE;
    }

    public ArrayList<SurfaceView> getSurfaceViews() {
        return this.surfaceViews;
    }


    public  void draw(int x, int y, Bitmap b, int id, Canvas canvas, int kx, int ky) {

        Bitmap bm = getResizedBitmap(b);
        int sizex = bm.getWidth() ;
        int gap = (int) sizex/4;
        int sizey=bm.getHeight();
        RectF whereToDraw = new RectF(
                x*sizex-kx*gap, y*sizey-ky*gap,
                (x+1)*sizex-kx*gap ,
                (1+y) *sizey-ky*gap);
        if (id== 1)
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bm, null, whereToDraw, paint);
    }


    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float newWidth = Constants.CELLSIZE/width;
        float newHeight = Constants.CELLSIZE/ height;
        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, (int)(newWidth*width), (int)(newHeight*height), false);
        return resizedBitmap;
    }

}

