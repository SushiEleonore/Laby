package fr.upsud.sushi.laby.mainactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Cell;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Observer;

/**
 * Created by proval on 6/14/16.
 */
public class GameRenderer {
    SurfaceViewDrawer listSurface;
    Level l;
    Resources res;

    Bitmap bitmapWall;
    Bitmap bitmapEnd;
    Bitmap bitmapPath;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
    );

    public GameRenderer(SurfaceViewDrawer list, Level le, Resources res) {
        listSurface = list;
        this.l = le;
        this.res = res;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;


        bitmapWall = BitmapFactory.decodeResource(res, R.drawable.mini_mur2, options);
       /* bitmapPlayerN = BitmapFactory.decodeResource(res, R.drawable.mini_canard_dos, options);
        bitmapPlayerS = BitmapFactory.decodeResource(res, R.drawable.mini_canard_face, options);
        bitmapPlayerE = BitmapFactory.decodeResource(res, R.drawable.mini_canard_d, options);
        bitmapPlayerW = BitmapFactory.decodeResource(res, R.drawable.mini_canard_g, options);*/
        bitmapEnd = BitmapFactory.decodeResource(res, R.drawable.arrivee, options);
        bitmapPath = BitmapFactory.decodeResource(res, R.drawable.path, options);
/*
        bitmapPlayerMvN= BitmapFactory.decodeResource(res, R.drawable.mv_mini_canard_dos, options);
        bitmapPlayerMvS= BitmapFactory.decodeResource(res, R.drawable.mv_mini_canard_face, options);
        bitmapPlayerMvE= BitmapFactory.decodeResource(res, R.drawable.mv_mini_canard_d, options);
        bitmapPlayerMvW= BitmapFactory.decodeResource(res, R.drawable.mv_mini_canard_g, options);
        */


        //  for (SurfaceView sV : listSurface.getSurfaceViews())

        listSurface.getBg().getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                l.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());

                drawBG();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
                //l.setCellSize(holder.getSurfaceFrame().height(), holder.getSurfaceFrame().width());
                drawBG();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                holder.removeCallback(this);
            }
        });
    }

    /*listSurface.getSurfaceViews().get(1).getHolder().addCallback(new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawPlayer();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                int height) {
            drawPlayer();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            holder.removeCallback(this);
        }
    });
    listSurface.getSurfaceViews().get(2).getHolder().addCallback(new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawChili();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                int height) {
            drawChili();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            holder.removeCallback(this);
        }
    });
    listSurface.getSurfaceViews().get(3).getHolder().addCallback(new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawBreakableWall();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                int height) {
            drawBreakableWall();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            holder.removeCallback(this);
        }
    });

}

public void drawChili() {
    int id = 2;
    ArrayList<SurfaceView> list = listSurface.getSurfaceViews();
    SurfaceHolder h = list.get(id).getHolder();
    if (h.getSurface().isValid()) {
        synchronized (h) {
            h.setFormat(PixelFormat.TRANSPARENT);
            Canvas canvas = h.lockCanvas();

            if(l.getItem()!=null){
            listSurface.draw(l.getItem().getX(), l.getItem().getY(), l.getItem().getSkinItem(), false, canvas,0,0);}
            h.unlockCanvasAndPost(canvas);}

    }
}

public void eraseChili() {
    int id = 2;
    ArrayList<SurfaceView> list = listSurface.getSurfaceViews();
    SurfaceHolder h = list.get(id).getHolder();
    if (h.getSurface().isValid()) {
        synchronized (h) {
            h.setFormat(PixelFormat.TRANSPARENT);
            Canvas canvas = h.lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            h.unlockCanvasAndPost(canvas);
        }
    }
}

public void drawBreakableWall() {
    int id = 3;
    ArrayList<SurfaceView> list = listSurface.getSurfaceViews();
    SurfaceHolder h = list.get(id).getHolder();
    if (h.getSurface().isValid()) {
        synchronized (h) {
            h.setFormat(PixelFormat.TRANSPARENT);
            Canvas canvas = h.lockCanvas();

            if(l.getItem()!=null){
                listSurface.draw(l.getbWall().getX(), l.getbWall().getY(), l.getbWall().getSkinItem(), false, canvas,0,0);}
            h.unlockCanvasAndPost(canvas);}

    }
}

public void erasebWall() {
    int id = 3;
    ArrayList<SurfaceView> list = listSurface.getSurfaceViews();
    SurfaceHolder h = list.get(id).getHolder();
    if (h.getSurface().isValid()) {
        synchronized (h) {
            h.setFormat(PixelFormat.TRANSPARENT);
            Canvas canvas = h.lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            h.unlockCanvasAndPost(canvas);
        }
    }
}
*/
    public void drawBG() {
        int id = 0;
        SurfaceView bg = listSurface.getBg();
        SurfaceHolder h = bg.getHolder();
        if (h.getSurface().isValid()) {
            synchronized (h) {
                Canvas canvas = h.lockCanvas();
                Rect r = new Rect(0, 0, 200, 200);
                canvas.drawRect(r, paint);
                for (int i = 0; i < l.getCells().length; i++) {
                    for (int j = 0; j < l.getCells()[i].length; j++) {
                        if (l.getCells()[i][j] != null) {
                            switch (l.getCells()[i][j].getType()) {
                                case WALL:
                                    listSurface.draw(i, j, bitmapWall, false, canvas, 0, 0);
                                    break;
                                case PATH:
                                    listSurface.draw(i, j, bitmapPath, false, canvas, 0, 0);

                                    break;
                                case END:
                                    listSurface.draw(i, j, bitmapEnd, false, canvas, 0, 0);

                                    break;
                                default:
                                    break;

                            }
                        }
                    }
                }
                h.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void drawPlayer() {
        listSurface.getGameViews().get(0).drawStatic();

    }
    public void drawMvingPlayer(int mv) {
        listSurface.getGameViews().get(0).drawMving();

    }
    public void drawPDestroying(){
        // TODO : DRAWDESTROYING
       // listSurface.getGameViews().get(0).drawStatic();
    }
    public void drawChili(){
        listSurface.getGameViews().get(1).drawStatic();
    }
    public void drawBreakableWall(){
        listSurface.getGameViews().get(2).drawStatic();
    }
    public void erasebWall() {
    }
    public void eraseChili() {}

/*

    public void drawMvingPlayer(int mv) {
        int id = 1;
        Bitmap b;
        int xmove;
        int ymove;
        switch (l.getPlayer().getDir()) {
            case S:
                b = l.getPlayer().getSkin_face();
                xmove = 0;
                ymove = mv;
                break;
            case E:
                b = l.getPlayer().getSkin_d();
                xmove = mv;
                ymove = 0;
                break;
            case W:
                b = l.getPlayer().getSkin_g();
                xmove = -mv;
                ymove = 0;
                break;
            default:
                b = l.getPlayer().getSkin_dos();
                xmove = 0;
                ymove = -mv;
                break;
        }
        Bitmap[] imgs = new Bitmap[3];
        imgs[0] = Bitmap.createBitmap(b, 0, 0, b.getWidth() / 3, b.getHeight());
        imgs[1] = Bitmap.createBitmap(b, b.getWidth() / 3, 0, b.getWidth() / 3, b.getHeight());
        imgs[2] = Bitmap.createBitmap(b, 2 * b.getWidth() / 3, 0, b.getWidth() / 3, b.getHeight());
        SurfaceHolder h = listSurface.getSurfaceViews().get(id).getHolder();
        for (int k = 2; k >= 0; k--) {
            if (h.getSurface().isValid()) {
                Canvas canvas = h.lockCanvas();
                synchronized (canvas) {
                    listSurface.draw(l.getPlayer().getX(), l.getPlayer().getY(), imgs[k], true, canvas,
                            k * xmove, k * ymove);
                    h.unlockCanvasAndPost(canvas);
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
        drawPlayer();

    }

    public void drawPDestroying() {
        int idP = 1;
        int idBWall=3;
        Bitmap bP, bW;
        bP = l.getPlayer().getpDestroying();
        bW= l.getbWall().getSkinBurning();
        Bitmap[] imgsP = new Bitmap[3];
        Bitmap[] imgsW = new Bitmap[3];
        imgsP[0] = Bitmap.createBitmap(bP, 0, 0, bP.getWidth() / 3, bP.getHeight());
        imgsP[1] = Bitmap.createBitmap(
        imgsP[2] = Bitmap.createBitmap(bP, 2 * bP.getWidth() / 3, 0, bP.getWidth() / 3, bP.getHeight());

        imgsW[0] = Bitmap.createBitmap(bW, 0, 0, bW.getWidth() / 3, bW.getHeight());
        imgsW[1] = Bitmap.createBitmap(bW, bW.getWidth() / 3, 0, bW.getWidth() / 3, bW.getHeight());
        imgsW[2] = Bitmap.createBitmap(bW, 2 * bW.getWidth() / 3, 0, bW.getWidth() / 3, bW.getHeight());
        SurfaceHolder hP = listSurface.getSurfaceViews().get(idP).getHolder();
        SurfaceHolder hW = listSurface.getSurfaceViews().get(idBWall).getHolder();
        for (int k = 2; k >= 0; k--) {
            if (hP.getSurface().isValid()&&hW.getSurface().isValid()) {
                Canvas canvas = hP.lockCanvas();
                Canvas canvasW= hW.lockCanvas();
                synchronized (canvas) {
                    listSurface.draw(l.getPlayer().getX(), l.getPlayer().getY(), imgsP[k], true, canvas,
                            0, 0);
                    listSurface.draw(l.getbWall().getX(), l.getbWall().getY(), imgsW[k], false, canvasW,
                            0, 0);
                    hP.unlockCanvasAndPost(canvas);
                    hW.unlockCanvasAndPost(canvasW);
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
        drawPlayer();

    }

    public void drawPlayer() {
        int id = 1;
        Bitmap b;

        switch (l.getPlayer().getDir()) {
            case S:
                b = l.getPlayer().getSkin_face();

                break;
            case E:
                b = l.getPlayer().getSkin_d();

                break;
            case W:
                b = l.getPlayer().getSkin_g();

                break;
            default:
                b = l.getPlayer().getSkin_dos();
                break;
        }
        Bitmap be = Bitmap.createBitmap(b, 0, 0, b.getWidth() / 3, b.getHeight());
        SurfaceHolder h = listSurface.getSurfaceViews().get(id).getHolder();
        if (h.getSurface().isValid()) {
            h.setFormat(PixelFormat.TRANSPARENT);
            synchronized (h) {
                Canvas canvas = h.lockCanvas();
                listSurface.draw(l.getPlayer().getX(), l.getPlayer().getY(), be, true,canvas, 0, 0);
                h.unlockCanvasAndPost(canvas);
            }
        }
    }

*/
    public void update(Level ll) {
        this.l = ll;
    }
}