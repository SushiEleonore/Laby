package fr.upsud.sushi.laby.graphics;

import android.graphics.Canvas;

import fr.upsud.sushi.laby.utils.Values;

public class GameLoopThread extends Thread {

    static final long FPS = Values.FPS;
    private ItemDrawer view;
    private boolean running = false;
    private Sprite s;

    public GameLoopThread(ItemDrawer view, Sprite s) {
        this.view = view;
        this.s=s;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getsV().getHolder().lockCanvas();
                synchronized (view.getsV().getHolder()) {
                   s.onDraw(c);
                }

            } finally {
                if (c != null) {

                    view.getsV().getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime >ticksPS)
                    sleep(sleepTime-ticksPS);
            } catch (Exception e) {}

        }

    }

}