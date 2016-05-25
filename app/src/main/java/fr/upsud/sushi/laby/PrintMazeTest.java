package fr.upsud.sushi.laby;

/**
 * Created by proval on 5/25/16.
 */

    import java.util.List;

    import android.content.Context;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.Paint;
    import android.graphics.RectF;
    import android.os.Bundle;
    import android.view.SurfaceHolder;
    import android.view.SurfaceView;

    import fr.upsud.sushi.laby.maze.Level;

public class PrintMazeTest extends SurfaceView implements SurfaceHolder.Callback {
        Level l;

    public final int size = 35;

        SurfaceHolder mSurfaceHolder;
        DrawingThread mThread;
        Paint mPaint;



        public PrintMazeTest(Context pContext, Level l) {
            super(pContext);
            mSurfaceHolder = getHolder();
            mSurfaceHolder.addCallback(this);
            mThread = new DrawingThread();

            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.FILL);

            this.l = l;
        }

        //@Override
        public void ondraw(Canvas pCanvas) {
            // Dessiner le fond de l'écran en premier
            pCanvas.drawColor(Color.CYAN);
            if(this.l.getCells().length !=0) {
                // Dessiner tous les blocs du labyrinthe
                for (int i = 0; i< this.l.getCells().length; i++) {
                    for (int j = 0; j< this.l.getCells()[i].length; j++) {
                        if (this.l.getCells()[i][j] == null ) {
                            mPaint.setColor(Color.WHITE);
                        } else {
                            switch (this.l.getCells()[i][j].getType()) {
                                case START :
                                    mPaint.setColor(Color.GREEN);
                                    break;
                                case END :
                                    mPaint.setColor(Color.RED);
                                    break;
                                default :
                                    mPaint.setColor(Color.BLACK);
                                    break;
                            }
                        }
                        pCanvas.drawRect(new RectF((float) (i*size),(float) (j*size),(float) (i*size+size), (float)(j*size+size)), mPaint);
                    }
                }
            }

            // Dessiner la boule
            if(this.l.getPlayer() != null) {
                mPaint.setColor(Color.BLUE);
                pCanvas.drawCircle(this.l.getPlayer().getX()*size, this.l.getPlayer().getY()*size, size/2, mPaint);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder pHolder, int pFormat, int pWidth, int pHeight) {
            //
        }

        @Override
        public void surfaceCreated(SurfaceHolder pHolder) {
            mThread.keepDrawing = true;
            mThread.start();
        }


        @Override
        public void surfaceDestroyed(SurfaceHolder pHolder) {
            mThread.keepDrawing = false;
            boolean retry = true;
            while (retry) {
                try {
                    mThread.join();
                    retry = false;
                } catch (InterruptedException e) {}
            }

        }

        private class DrawingThread extends Thread {
            boolean keepDrawing = true;

            @Override
            public void run() {
                Canvas canvas;
                while (keepDrawing) {
                    canvas = null;

                    try {
                        canvas = mSurfaceHolder.lockCanvas();
                        synchronized (mSurfaceHolder) {
                            //TODO Check if well modified
                            ondraw(canvas);
                        }
                    } finally {
                        if (canvas != null)
                            mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }

                    // Pour dessiner à 50 fps
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {}
                }
            }
        }
    }


