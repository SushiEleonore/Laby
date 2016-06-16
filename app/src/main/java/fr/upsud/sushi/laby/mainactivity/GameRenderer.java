package fr.upsud.sushi.laby.mainactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    Bitmap bitmapStart;
    Bitmap bitmapPlayerN;
    Bitmap bitmapPlayerS;
    Bitmap bitmapPlayerE;
    Bitmap bitmapPlayerW;
    Bitmap bitmapEnd;
    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
            | Paint.ANTI_ALIAS_FLAG);

    public GameRenderer(SurfaceViewDrawer list, Level le, Resources res) {
        listSurface = list;
        this.l = le;
        this.res = res;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;


        bitmapWall = BitmapFactory.decodeResource(res, R.drawable.mini_mur2, options);
        bitmapPlayerN = BitmapFactory.decodeResource(res, R.drawable.mini_canard_dos, options);
        bitmapPlayerS = BitmapFactory.decodeResource(res, R.drawable.mini_canard_face, options);
        bitmapPlayerE = BitmapFactory.decodeResource(res, R.drawable.bigduck_d, options);
        bitmapPlayerW = BitmapFactory.decodeResource(res, R.drawable.mini_canard_g, options);
        bitmapEnd = BitmapFactory.decodeResource(res, R.drawable.arrivee, options);


    }


    public void drawBG() {
        Bitmap tab[][] = new Bitmap[l.getCells().length][l.getCells()[0].length];
            for (int i = 0; i < l.getCells().length; i++) {

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

        SurfaceView v = listSurface.getSurfaceViews().get(0);
        SurfaceHolder ourHolder = v.getHolder();
        if (ourHolder.getSurface().isValid()) {
            Canvas canvas =ourHolder.lockCanvas();
            for (int i = 0; i < tab.length; i++) {
                    for (int j = 0; j < tab[i].length; j++) {
                        if (tab[i][j] != null) {
                            Bitmap bm = listSurface.getResizedBitmap(tab[i][j]);
                            int sizex = bm.getWidth();
                            int sizey = bm.getHeight();
                            
                            canvas.drawBitmap(bm, i * sizex, j * sizey, paint);
                        }
                    }
            }
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }



   /*

    public void drawBG() {
        int id = 0;
        for (int i = 0; i < l.getCells().length; i++) {
            for (int j = 0; j < l.getCells()[i].length; j++) {
                if (l.getCells()[i][j] != null) {
                    whichDraw(i,j, l.getCells()[i][j]);
                }
            }


        }
    }


    private void whichDraw(int i, int j, Cell c){
        int id =0;
        switch (c.getType()) {
            case WALL:
                listSurface.draw(i, j, bitmapWall, id); break;
            case END:
                listSurface.draw(i, j, bitmapEnd, id); break;
            default:
                break;

        }


    */

    public void drawPlayer() {
        int id = 1;
        Bitmap b;

        switch (l.getPlayer().getDir()) {
            case S:
                b = bitmapPlayerS;
                break;
            case E:
                b = bitmapPlayerE;
                break;
            case W:
                b = bitmapPlayerW;
                break;
            default:
                b = bitmapPlayerN;
                break;
        }
        listSurface.draw(l.getPlayer().getX(), l.getPlayer().getY(), b, id);

    }


    public void update(Level ll) {
        this.l = ll;
    }
}