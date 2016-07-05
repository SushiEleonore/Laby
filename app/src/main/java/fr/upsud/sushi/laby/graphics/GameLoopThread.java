package fr.upsud.sushi.laby.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.SurfaceView;

import java.util.ArrayList;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;

public class GameLoopThread extends Thread {
    static final long FPS = Values.FPS;
    private ItemDrawer view;
    private boolean running = true;
    private ArrayList<ItemDrawer> lSViews;
    private long animationTime=1000;
    public GameLoopThread(ItemDrawer view) {
        this.view = view;

    }
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void start() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        long animationStart = System.currentTimeMillis();
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();

            // for (ImteDrawer itD : lSViews) {
            try {

                c = view.getsV().getHolder().lockCanvas();c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                synchronized (view.getsV().getHolder()) {
                    //erase

                    //c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);
                    for (MovableElement el : view.getElements()) {
                        if(el!=null) {
                            el.getSprite().update();
                            el.getSprite().draw(c);
                            //view.draw(el);
                            if(Values.DEBUG_MODE) System.out.println("try to draw");

                        }
                    }
                }

            } finally {
                if (c != null) {
                    //c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    view.getsV().getHolder().unlockCanvasAndPost(c);
                }
            }
            //sleepTime = (System.currentTimeMillis() - startTime);
            try {
                //if (sleepTime >ticksPS)
                //sleep(sleepTime-ticksPS);
                sleep(300);
                //sleep(200);

            } catch (Exception e) {
            }


        }


        }
    }
