package fr.upsud.sushi.laby.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.SurfaceView;

import java.util.ArrayList;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;

public class GameLoopThread extends Thread {
    private ItemDrawer view;
    private boolean running = true;
    public GameLoopThread(ItemDrawer view) {
        this.view = view;

    }
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {

        while (running) {
            Canvas c = null;
            try {

                c = view.getsV().getHolder().lockCanvas();

                synchronized (view.getsV().getHolder()) {
                    //erase
                    //c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    for (MovableElement el : view.getElements()) {
                        if(el!=null) {
                            el.getSprite().update();

                            el.getSprite().draw(c);
                            if(Values.DEBUG_MODE) System.out.println("try to draw " + el.getSprite().toString());
                        }
                    }
                }
            } finally {
                if (c != null) {
                    view.getsV().getHolder().unlockCanvasAndPost(c);
                }
            }
            try {
                sleep(300);
            } catch (Exception e) {e.printStackTrace();}

        }


    }
}
