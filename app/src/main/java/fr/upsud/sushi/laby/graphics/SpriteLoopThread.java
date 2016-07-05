package fr.upsud.sushi.laby.graphics;

import android.graphics.Canvas;
import fr.upsud.sushi.laby.utils.Values;

public class SpriteLoopThread extends Thread {
    static final long FPS = Values.FPS;
    private ItemDrawer view;
    private boolean running = false;
    private Sprite s;
    private long animationTime=1000;
    public SpriteLoopThread(ItemDrawer view, Sprite s) {
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
        long animationStart= System.currentTimeMillis();
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
                //if (sleepTime >ticksPS)
                //sleep(sleepTime-ticksPS);

                sleep(animationTime/Values.nStepSprite);

            } catch (Exception e) {}

            if( System.currentTimeMillis()-animationStart>animationTime){ running=false;}

        }

    }

}