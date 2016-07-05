package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import java.util.ArrayList;

//import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 6/14/16.
 */
public class SurfaceViewDrawer {
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
             | Paint.ANTI_ALIAS_FLAG);

    private ItemDrawer itemDrawer;
    private SurfaceView bg;
    float scale;

    public SurfaceViewDrawer(SurfaceView bg, SurfaceView player, LinearLayout l, Level lvl) {
        ArrayList<MovableElement> lEl = new ArrayList<MovableElement>();
        lEl.add(lvl.getPlayer());
        lEl.add(lvl.getbWall());
        lEl.add(lvl.getItem());
        this.bg=bg;
        itemDrawer= new ItemDrawer(player,lEl,lvl, this );
        this.scale = Values.IMAGE_SIZE;
    }
    public ItemDrawer getItemDrawer() {
        return this.itemDrawer;
    }
    public SurfaceView getBg(){return this.bg;}

    public  void draw(int x, int y, Bitmap b, boolean erase, Canvas canvas, int kx, int ky) {

        Bitmap bm = getResizedBitmap(b);
        int gap = (int) Values.CELLSIZE/4;
        float topx = x* Values.CELLSIZE-kx*gap;
        float topy = y* Values.CELLSIZE+ky;
        RectF whereToDraw = new RectF(
                topx + Values.LSHIFT, topy+ Values.HSHIFT,
                topx + Values.CELLSIZE + Values.LSHIFT,
                topy + Values.CELLSIZE+ Values.HSHIFT);
        /*if (erase) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
        */
        canvas.drawBitmap(bm, null, whereToDraw, paint);
    }


    public Bitmap getResizedBitmap(Bitmap bm) {
        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, Values.CELLSIZE, Values.CELLSIZE, false);
        return resizedBitmap;
    }

}

