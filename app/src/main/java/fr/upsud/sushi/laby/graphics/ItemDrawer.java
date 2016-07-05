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

import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.maze.Player;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Values;


public class ItemDrawer {
    private Bitmap[] staticBmp;
    private Bitmap[][] mvingBmp;
    private Bitmap[] actionBmp;
    private SurfaceHolder holder;

    private Sprite sprite;
    private SurfaceViewDrawer drawer;
    private ArrayList<MovableElement> listEl;
    private SurfaceView sV;
    private GameLoopThread gm;

    Level l;


    public ItemDrawer(SurfaceView sV, final ArrayList<MovableElement> listEl,final Level l, SurfaceViewDrawer drawer) {
        this.listEl=listEl;
        this.sV=sV;
        this.l=l;
        this.drawer=drawer;
        this.gm = null;
        final ItemDrawer that = this;
        holder = sV.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
           public void surfaceCreated(SurfaceHolder holder) {
               l.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());
               holder.setFormat(PixelFormat.TRANSPARENT);
               Canvas c = holder.lockCanvas();
               c.drawRect(new Rect(300, 300, 350, 350), Values.paint);
               holder.unlockCanvasAndPost(c);
               if (gm != null && gm.isAlive())
                   try {
                       gm.setRunning(false);
                       gm.join(0);
                   } catch (InterruptedException exn) {};
               gm = new GameLoopThread(that);
               gm.setRunning(true);
               gm.start();


           }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
                holder.setFormat(PixelFormat.TRANSPARENT);
                if (gm != null && gm.isAlive())
                    try {
                        gm.setRunning(false);
                        gm.join(0);
                    } catch (InterruptedException exn) {};
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
    public SurfaceView getsV(){
        return this.sV;
    }
    public ArrayList<MovableElement> getElements(){
        return this.listEl;
    }




}