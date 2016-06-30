package fr.upsud.sushi.laby.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.BitmapParser;

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



        bitmapWall = BitmapParser.getWall(res);

        bitmapEnd =BitmapParser.getEnd(res);// BitmapFactory.decodeResource(res, R.drawable.arrivee, options);
        bitmapPath =BitmapParser.getPath(res);//BitmapFactory.decodeResource(res, R.drawable.path, options);

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
        listSurface.getItemDrawers().get(0).drawStatic();

    }
    public void drawMvingPlayer(int mv) {
        listSurface.getItemDrawers().get(0).drawMving( mv);

    }
    public void drawPDestroying(){
        listSurface.getItemDrawers().get(0).drawAction();
        listSurface.getItemDrawers().get(2).drawAction();
    }
    public void drawChili(){
        listSurface.getItemDrawers().get(1).drawStatic();
    }
    public void drawBreakableWall(){
        listSurface.getItemDrawers().get(2).drawStatic();
    }
    public void erasebWall() {
        listSurface.getItemDrawers().get(2).erase();
    }
    public void eraseChili() { listSurface.getItemDrawers().get(1).erase();}

}




