package fr.upsud.sushi.laby.graphics;

/**
 * Created by proval on 6/27/16.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.media.midi.MidiOutputPort;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.mainactivity.SurfaceViewDrawer;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Dir;


public class GameView {
    private Bitmap[] staticBmp;
    private Bitmap[][] mvingBmp;
    private SurfaceHolder holder;

    private Sprite sprite;
    private SurfaceViewDrawer drawer;
    private MovableElement el;
    private final int D=0;
    private final int G=1;
    private final int DOS=2;
    private final int FACE=3;
    private SurfaceView sV;
   // Level l;


    public GameView(SurfaceView sV, MovableElement el,final Level l, SurfaceViewDrawer drawer) {
        this.el=el;
        this.sV=sV;
        this.drawer=drawer;
        if(el!=null){
            Bitmap[] tempBmp=el.getStaticBmp();
            this.staticBmp= tempBmp.clone();

             this.mvingBmp=new Bitmap[4][3];
           //  this.mvingBmp= tempBmp;
            for(int i = 0; i<tempBmp.length;i++) {
                staticBmp[i] = Bitmap.createBitmap(tempBmp[i], 0, 0, tempBmp[i].getWidth() / 3, tempBmp[i].getHeight());
                mvingBmp[i][0] =Bitmap.createBitmap(tempBmp[i], 0, 0, tempBmp[i].getWidth() / 3, tempBmp[i].getHeight());
                mvingBmp[i][1] =Bitmap.createBitmap(tempBmp[i],tempBmp[i].getWidth() / 3, 0, tempBmp[i].getWidth() / 3, tempBmp[i].getHeight());
                mvingBmp[i][2] =Bitmap.createBitmap(tempBmp[i],2*tempBmp[i].getWidth() / 3, 0, tempBmp[i].getWidth() / 3, tempBmp[i].getHeight());

            }
        }



        holder = sV.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
           /* @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {

                    }

                }

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                    int width, int height) {
            }
        });
        */
           public void surfaceCreated(SurfaceHolder holder) {
               l.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());
               holder.setFormat(PixelFormat.TRANSPARENT);
               drawStatic();
           }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
                //l.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());
                drawStatic();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                holder.removeCallback(this);
            }
        });

    }


    public SurfaceView getsV(){
        return this.sV;
    }
    public SurfaceViewDrawer getDrawer(){return this.drawer;}
    public void drawStatic() {

        if (el != null) {
            System.out.println("drawing " +el.toString());
            Dir d = el.getDir();
            Bitmap b;
            switch (d) {
                case F:
                    b = staticBmp[0];
                    break;
                case S:
                    b = staticBmp[3];

                    break;
                case E:
                    b = staticBmp[0];

                    break;
                case W:
                    b = staticBmp[1];

                    break;
                default:
                    b = staticBmp[2];
                    break;
            }
            SurfaceHolder h = sV.getHolder();
            if (h.getSurface().isValid()) {
                synchronized (h) {
                    h.setFormat(PixelFormat.TRANSPARENT);
                    Canvas canvas = h.lockCanvas();
                    drawer.draw(
                            el.getX(), el.getY(), b, true,
                            canvas, 0, 0);
                    System.out.println("finished drawing ");
                    h.unlockCanvasAndPost(canvas);
                }
            }
        } else {
            SurfaceHolder h = sV.getHolder();
            if (h.getSurface().isValid()) {
                Canvas canvas = h.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                h.unlockCanvasAndPost(canvas);
            }
        }

    }
    public void drawMving() {
        if (el != null) {

            Dir d = el.getDir();
            Bitmap[] b= new Bitmap[3];
            switch (d) {
                case S:
                    b = mvingBmp[3];

                    break;
                case E:
                    b = mvingBmp[0];

                    break;
                case W:
                    b = mvingBmp[1];

                    break;
                default:
                    b = mvingBmp[2];
                    break;
            }
            new Sprite(this, this.el, b);

        }
    }

}