package fr.upsud.sushi.laby.mainactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
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

    Bitmap bitmapPlayerN;
    Bitmap bitmapPlayerS;
    Bitmap bitmapPlayerE;
    Bitmap bitmapPlayerW;
    Bitmap bitmapEnd;
    Bitmap bitmapPath;
    Bitmap bitmapPlayerMvN;
    Bitmap bitmapPlayerMvW;
    Bitmap bitmapPlayerMvS;
    Bitmap bitmapPlayerMvE;
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


        for (SurfaceView sV: listSurface.getSurfaceViews())
            sV.getHolder().addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    drawBG();
                    drawPlayer();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    drawBG();
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    holder.removeCallback(this);

                }
            });

    }


    public void drawBG() {
        int id = 0;
        ArrayList<SurfaceView> list = listSurface.getSurfaceViews();
        SurfaceHolder h = list.get(0).getHolder();
        

        if (h.getSurface().isValid()) {

            synchronized (h) {
                Canvas canvas = h.lockCanvas();
                for (int i = 0; i < l.getCells().length; i++) {
                    for (int j = 0; j < l.getCells()[i].length; j++) {
                        if (l.getCells()[i][j] != null) {
                            switch (l.getCells()[i][j].getType()) {
                                case WALL:
                                    listSurface.draw(i, j, bitmapWall, id, canvas,0,0);

                                    break;
                                case PATH:
                                    listSurface.draw(i, j, bitmapPath, id, canvas,0,0);

                                    break;
                                case END:
                                    listSurface.draw(i, j, bitmapEnd, id, canvas,0,0);

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





    public void drawMvingPlayer(int mv) {
        int id = 1;
        Bitmap b;
        int xmove = 0;
        int ymove = 0;
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
                    listSurface.draw(l.getPlayer().getX(), l.getPlayer().getY(), imgs[k], id, canvas,
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
                b = l.getPlayer().getSkin_dos();;
                break;
        }
        b = Bitmap.createBitmap(b, 0, 0, b.getWidth() / 3, b.getHeight());

        SurfaceHolder h = listSurface.getSurfaceViews().get(id).getHolder();
        if (h.getSurface().isValid()) {
            h.setFormat(PixelFormat.TRANSPARENT);
            synchronized (h) {
                Canvas canvas = h.lockCanvas();
                listSurface.draw(l.getPlayer().getX(), l.getPlayer().getY(), b, id,canvas, 0, 0);
                h.unlockCanvasAndPost(canvas);
            }
        }
    }


    public void update(Level ll) {
        this.l = ll;
    }
}