package fr.upsud.sushi.laby.graphics;

import android.graphics.Canvas;

public class GameLoopThread extends Thread {

    static final long FPS = 5;
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
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(100);
            } catch (Exception e) {}

        }

    }
    @Override
    public void start() {
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
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(100);
            } catch (Exception e) {}

        }

    }

}