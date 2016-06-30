package fr.upsud.sushi.laby.mainactivity;

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
import fr.upsud.sushi.laby.graphics.GameView;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 6/14/16.
 */
public class SurfaceViewDrawer {
    public static final int BG_LAYER = 0;
    public static final int PLAYER_LAYER = 1;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
             | Paint.ANTI_ALIAS_FLAG);

    private ArrayList<GameView> GameViews;
    private SurfaceView bg;
    float scale;



    SurfaceViewDrawer(SurfaceView bg, SurfaceView player, SurfaceView chili, SurfaceView wall, LinearLayout l, Level lvl) {
        GameViews = new ArrayList<GameView>();
        this.bg=bg;
        GameViews.add(new GameView(player,lvl.getPlayer(),lvl, this ));
            GameViews.add(new GameView(chili, lvl.getItem(), lvl, this));
            GameViews.add(new GameView(wall, lvl.getbWall(), lvl, this));

        this.scale = Values.IMAGE_SIZE;
    }

    public ArrayList<GameView> getGameViews() {
        return this.GameViews;
    }
    public SurfaceView getBg(){return this.bg;}


    public  void draw(int x, int y, Bitmap b, boolean erase, Canvas canvas, int kx, int ky) {

        Bitmap bm = getResizedBitmap(b);
        int gap = (int) Values.CELLSIZE/4;
        float topx = x* Values.CELLSIZE-kx*gap;
        float topy = y* Values.CELLSIZE+ky;
        RectF whereToDraw = new RectF(
                topx, topy,
                topx + Values.CELLSIZE ,
                topy + Values.CELLSIZE);
        if (erase) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
        canvas.drawBitmap(bm, null, whereToDraw, paint);
    }


    public Bitmap getResizedBitmap(Bitmap bm) {

        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, Values.CELLSIZE, Values.CELLSIZE, false);
        return resizedBitmap;
    }

}

