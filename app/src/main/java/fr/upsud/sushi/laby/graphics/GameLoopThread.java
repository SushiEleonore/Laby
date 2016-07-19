package fr.upsud.sushi.laby.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;

public class GameLoopThread extends Thread {
    private ItemDrawer view;
    private boolean running ;
    private long animationTime;
    public GameLoopThread(ItemDrawer view) {
        this.animationTime = view.getL().getTimePerInst();
        this.view = view;
        this.running=true;
    }
    public void setRunning(boolean run) {
        this.running = run;
    }

    @Override
    public void run() {

        while (this.running) {
            Canvas c = null;
            try {

                c = view.getsV().getHolder().lockCanvas();

                synchronized (view.getsV().getHolder()) {
                    c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    for (MovableElement el : view.getElements()) {
                        if(el!=null) {
                            el.getSprite().update();
                            if(view.getsV().getHolder().getSurface().isValid()) el.getSprite().draw(c);
                        }
                    }
                }
            } finally {
                if (c != null) {
                    view.getsV().getHolder().unlockCanvasAndPost(c);
                }
            }
            try {
                sleep(animationTime/Values.nStepSprite);
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
