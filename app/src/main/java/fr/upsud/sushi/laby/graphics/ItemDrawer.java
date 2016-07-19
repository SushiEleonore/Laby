package fr.upsud.sushi.laby.graphics;

/**
 * Created by proval on 6/27/16.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.maze.Player;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Values;


public class ItemDrawer {
    private SurfaceHolder holder;
    private ArrayList<MovableElement> listEl;
    private SurfaceView sV;
    private GameLoopThread gm;
    private SurfaceView bg;
    Level l;


    public ItemDrawer(SurfaceView bg, SurfaceView surfacePlayer, LinearLayout l, Level lvl) {
       // this.listEl=listEl;
        this.sV=surfacePlayer;
        listEl = new ArrayList<MovableElement>();
        listEl.add(lvl.getPlayer());
        listEl.add(lvl.getbWall());
        listEl.add(lvl.getItem());
        this.bg=bg;
        this.l=lvl;
        final Level le= this.l;
        this.gm = null;
        final ItemDrawer that = this;
        holder = sV.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
           public void surfaceCreated(SurfaceHolder holder) {
               le.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());
               holder.setFormat(PixelFormat.TRANSPARENT);
               Canvas c = holder.lockCanvas();
               c.drawRect(new Rect(300, 300, 350, 350), Values.paint);
               holder.unlockCanvasAndPost(c);
               if (gm != null && gm.isAlive())
                   try {
                       gm.setRunning(false);
                       gm.join(0);
                   } catch (InterruptedException exn) {};

               gm=null;

               gm = new GameLoopThread(that);
               gm.setRunning(true);
               gm.start();


           }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
                if(Values.DEBUG_MODE) System.out.println("Surface changée");
                holder.setFormat(PixelFormat.TRANSPARENT);
                if (gm != null && gm.isAlive())
                    try {
                        gm.setRunning(false);
                        gm.join(0);
                    } catch (InterruptedException exn) {};

                gm=null;
                gm = new GameLoopThread(that);
                gm.setRunning(true);
                gm.start();


            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                holder.removeCallback(this);
                if (gm != null && gm.isAlive())
                    try {
                        gm.setRunning(false);
                        gm.join(0);
                    } catch (InterruptedException exn) {};
                gm = null;
            }
        });

    }
    public Level getL() { return this.l;}
    public GameLoopThread getGameLoop(){return this.gm;}
    public SurfaceView getsV(){
        return this.sV;
    }
    public SurfaceView getBg(){return this.bg;}
    public ArrayList<MovableElement> getElements(){
        return this.listEl;
    }

}