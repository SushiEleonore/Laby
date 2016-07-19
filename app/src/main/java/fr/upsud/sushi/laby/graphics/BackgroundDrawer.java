package fr.upsud.sushi.laby.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.BitmapParser;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 6/14/16.
 */
public class BackgroundDrawer {
    SurfaceView bgSurface;
    Level l;
    Resources res;
    int h;
    int w;

    Bitmap bitmapWall;
    Bitmap bitmapEnd;
    Bitmap bitmapPath;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
    );

    public BackgroundDrawer( Level le, Resources res, SurfaceView bg) {

        bgSurface= bg;
        this.l = le;
        this.res = res;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        bitmapWall = BitmapParser.getWall(res);
        bitmapEnd = BitmapParser.getEnd(res);
        bitmapPath = BitmapParser.getPath(res);
        bg.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                h = holder.getSurfaceFrame().height();
                w = holder.getSurfaceFrame().width();
                l.setCellSize(h, w);
                drawBG();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
                drawBG();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                holder.removeCallback(this);
            }
        });
    }

    public void drawBG() {
        SurfaceView bg = bgSurface;
        SurfaceHolder h = bg.getHolder();
        if (h.getSurface().isValid()) {
            synchronized (h) {
                Canvas canvas = h.lockCanvas();
                Paint p = new Paint();
                p.setColor(Color.WHITE);
                Rect r = new Rect(0, 0, this.w, this.h);
                canvas.drawRect(r, p);
                for (int i = 0; i < l.getCells().length; i++) {
                    for (int j = 0; j < l.getCells()[i].length; j++) {
                        if (l.getCells()[i][j] != null) {
                            switch (l.getCells()[i][j].getType()) {
                                case WALL:
                                    draw(i, j, bitmapWall, canvas);
                                    break;
                                case PATH:
                                    draw(i, j, bitmapPath, canvas);
                                    break;
                                case END:
                                    draw(i, j, bitmapEnd, canvas);
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
    public  void draw(int x, int y, Bitmap b, Canvas canvas) {
        Bitmap bm = getResizedBitmap(b);
        float topx = x* Values.CELLSIZE;
        float topy = y* Values.CELLSIZE;
        RectF whereToDraw = new RectF(
                topx + Values.LSHIFT, topy+ Values.HSHIFT,
                topx + Values.CELLSIZE + Values.LSHIFT,
                topy + Values.CELLSIZE+ Values.HSHIFT);
        canvas.drawBitmap(bm, null, whereToDraw, paint);
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, Values.CELLSIZE, Values.CELLSIZE, false);
        return resizedBitmap;
    }

}



