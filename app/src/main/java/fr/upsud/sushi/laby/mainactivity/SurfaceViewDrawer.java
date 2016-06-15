package fr.upsud.sushi.laby.mainactivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.security.spec.ECField;
import java.util.ArrayList;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Constants;

/**
 * Created by proval on 6/14/16.
 */
public class SurfaceViewDrawer {
    public static final int BG_LAYER = 0;
    public static final int PLAYER_LAYER = 1;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
             | Paint.ANTI_ALIAS_FLAG);

    private ArrayList<SurfaceView> surfaceViews;
    int scale;

    SurfaceViewDrawer(SurfaceView bg, SurfaceView player) {
        surfaceViews = new ArrayList<SurfaceView>();
        surfaceViews.add(bg);
        surfaceViews.add(player);
        this.scale = Constants.SCALE;
    }

    public ArrayList<SurfaceView> getSurfaceViews() {
        return this.surfaceViews;
    }

    public void drawTest(Level l, Bitmap bitmapWall, Bitmap bitmapEnd) {

        Bitmap tab[][] = new Bitmap[l.getCells().length][l.getCells()[0].length];
        int id = 0;
        Object lock = new Object();
        synchronized (lock) {
            for (int i = 0; i < l.getCells().length; i++) {
                Object lock2 = new Object();

                synchronized (lock2) {
                    for (int j = 0; j < l.getCells()[i].length; j++) {
                        if (l.getCells()[i][j] != null) {
                            switch (l.getCells()[i][j].getType()) {
                                case WALL:
                                   tab[i][j]=bitmapWall;
                                    break;
                                case END:
                                    tab[i][j]=bitmapEnd;
                                    break;

                                default:
                                    break;


                            }
                        }
                    }
                }
            }
        }

        SurfaceView v = surfaceViews.get(id);
        SurfaceHolder ourHolder = v.getHolder();


            if (ourHolder.getSurface().isValid()) {
                if (id == 1) v.getHolder().setFormat(PixelFormat.TRANSPARENT);

                Canvas canvas =ourHolder.lockCanvas();


                for (int i = 0; i < tab.length; i++) {
                    Object lock2 = new Object();

                    synchronized (lock2) {
                        for (int j = 0; j < tab[i].length; j++) {
                            if (tab[i][j] != null) {
                                Bitmap bm = getResizedBitmap(tab[i][j]);
                                int sizex = bm.getWidth();
                                int sizey = bm.getHeight();


                                try {
                                    System.out.println(sizex);


                                    if (id == 1) canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                                    System.out.println("Draw at " + j * sizey);
                                    canvas.drawBitmap(bm, i * sizex, j * sizey, paint);

                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
                    ourHolder.unlockCanvasAndPost(canvas);
                }
    }

    public  void draw(int x, int y, Bitmap b, int id) {

        Bitmap bm = getResizedBitmap(b);
        int sizex = bm.getWidth() ;
        int sizey=bm.getHeight();


        try {
            System.out.println(sizex);
            SurfaceView v = surfaceViews.get(id);
            SurfaceHolder ourHolder = v.getHolder();
            synchronized(ourHolder) {

                if (ourHolder.getSurface().isValid()) {
                     v.getHolder().setFormat(PixelFormat.TRANSPARENT);

                    Canvas canvas =ourHolder.lockCanvas();

                    if (id == 1) canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    System.out.println("Draw at "+y*sizey);
                    canvas.drawBitmap(bm, x *sizex, y * sizey, paint);
                    ourHolder.unlockCanvasAndPost(canvas);
                } else {
                    System.out.println("surfaceholder pas valide");
                }
            }
        } catch (Exception e) {
        }

    }


    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        int newWidth = width * Constants.SCALE;
        int newHeight = height * Constants.SCALE;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        //bm.recycle();
        return resizedBitmap;
    }

}

