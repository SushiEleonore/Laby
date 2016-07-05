package fr.upsud.sushi.laby.graphics;

/**
 * Created by proval on 6/27/16.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

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
        for(MovableElement el : listEl){
            if(el!=null) {
                Bitmap[] tempBmp = el.getStaticBmp();
                this.staticBmp = tempBmp.clone();
                this.mvingBmp = new Bitmap[4][3];
                //  this.mvingBmp= tempBmp;
                for (int i = 0; i < tempBmp.length; i++) {
                    staticBmp[i] = Bitmap.createBitmap(tempBmp[i], 0, 0, tempBmp[i].getWidth() / 3,
                            tempBmp[i].getHeight());
                    mvingBmp[i][0] = Bitmap.createBitmap(tempBmp[i], 0, 0, tempBmp[i].getWidth() / 3,
                            tempBmp[i].getHeight());
                    mvingBmp[i][1] = Bitmap.createBitmap(tempBmp[i], tempBmp[i].getWidth() / 3, 0,
                            tempBmp[i].getWidth() / 3, tempBmp[i].getHeight());
                    mvingBmp[i][2] = Bitmap.createBitmap(tempBmp[i], 2 * tempBmp[i].getWidth() / 3, 0,
                            tempBmp[i].getWidth() / 3, tempBmp[i].getHeight());
                }
                Bitmap tmp = el.getActionBmp("");
                this.actionBmp = new Bitmap[3];
                if (tmp != null) {
                    actionBmp[0] = Bitmap.createBitmap(tmp, 0, 0, tmp.getWidth() / 3, tmp.getHeight());
                    actionBmp[1] = Bitmap.createBitmap(tmp, tmp.getWidth() / 3, 0, tmp.getWidth() / 3,
                            tmp.getHeight());
                    actionBmp[2] =
                            Bitmap.createBitmap(tmp, 2 * tmp.getWidth() / 3, 0, tmp.getWidth() / 3,
                                    tmp.getHeight());
                }

            }
        }
        this.gm = new GameLoopThread(this);

        holder = sV.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
           public void surfaceCreated(SurfaceHolder holder) {
               l.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());
               holder.setFormat(PixelFormat.TRANSPARENT);
              // drawStatic(d);
                gm.start();
               gm.setRunning(true);


           }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
                holder.setFormat(PixelFormat.TRANSPARENT);
                gm.setRunning(true);
                gm.start();


            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                holder.removeCallback(this);
                gm.setRunning(false);
            }
        });

    }


    public SurfaceView getsV(){
        return this.sV;
    }
    public SurfaceViewDrawer getDrawer(){return this.drawer;}
    public void drawStatic(MovableElement el) {
        if (el != null) {
            if(Values.DEBUG_MODE) System.out.println("drawing " +el.toString());
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
                    if (Values.DEBUG_MODE) System.out.println("finished drawing ");
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

    public ArrayList<MovableElement> getElements(){
        return this.listEl;
    }

    public void draw(MovableElement el) {
        if (el != null && el.getState()!=false) {
            if (el.isMoving()) drawMving(el.getMotion(), el);
            else if (el.isActioning()) drawAction(el);
            else drawStatic(el);

        }
    }

    public void erase(){
        SurfaceHolder h = sV.getHolder();
        if (h.getSurface().isValid()) {
            Canvas canvas = h.lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            h.unlockCanvasAndPost(canvas);
        }
    }

    public void drawMving(int mv, MovableElement el) {
        if (el != null) {
        if(el instanceof Player){
            el=l.getPlayer();
        }
            Dir d = el.getDir();
            int kx, ky;
            Bitmap[] b= new Bitmap[3];
            switch (d) {
                case F:
                    b=mvingBmp[0];
                    kx=0;
                    ky=0;
                    break;
                case S:
                    b = mvingBmp[3];
                    kx=0;
                    ky=-1;
                    break;
                case E:
                    b = mvingBmp[0];
                    kx=1;
                    ky=0;
                    break;
                case W:
                    b = mvingBmp[1];
                    kx=-1;
                    ky=0;
                    break;
                case N:
                    b = mvingBmp[2];
                    kx=0;
                    ky=1;
                    break;
                default:
                    throw  new RuntimeException("impossible");
            }
            new Sprite(this, el, b, kx*mv, ky*mv);
        }
    }
    public void drawAction(MovableElement el) {
        if (el != null) {
            el.update();
            //new Sprite(this, el, this.actionBmp, 0, 0);
        }
    }


}