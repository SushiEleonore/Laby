package fr.upsud.sushi.laby.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    SurfaceViewDrawer listSurface;
    Level l;
    Resources res;

    Bitmap bitmapWall;
    Bitmap bitmapEnd;
    Bitmap bitmapPath;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
    );

    public BackgroundDrawer(SurfaceViewDrawer list, Level le, Resources res) {
        listSurface = list;
        this.l = le;
        this.res = res;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        bitmapWall = BitmapParser.getWall(res);
        bitmapEnd = BitmapParser
                .getEnd(res);// BitmapFactory.decodeResource(res, R.drawable.arrivee, options);
        bitmapPath = BitmapParser
                .getPath(res);//BitmapFactory.decodeResource(res, R.drawable.path, options);
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

    public void drawBG() {
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



