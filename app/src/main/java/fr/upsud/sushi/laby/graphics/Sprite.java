package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.provider.SyncStateContract;
import android.view.SurfaceHolder;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.maze.Player;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Values;


public class Sprite {

    private Bitmap[] staticBmp;
    private Bitmap[][] mvingBmp;
    private Bitmap[] actionBmp;

    private MovableElement el;

    private int kx;
    private int ky;

    private static final int BMP_COLUMNS = 3;

    private int x;
    private int y;
    private int gapy = 0;
    private int ySpeed;
    private int xSpeed;
    private int gapx = 0;

    private ItemDrawer itemDrawer;

    private Bitmap[] bmp;
    private int currentFrame = 0;

    //Si le sprite en question doit faire un déplacement, gapToDo vaut 1; sinon 0
    private int gapToDo = 1;

    private SpriteLoopThread spriteLoop;


    public Sprite(ItemDrawer itemDrawer, MovableElement el, Bitmap[] bmp, int kx, int ky) {
        this.itemDrawer = itemDrawer;
        this.bmp = bmp;
        this.xSpeed = Values.CELLSIZE/Values.nStepSprite * -kx;
        this.ySpeed = Values.CELLSIZE/Values.nStepSprite * -ky;
        if (kx == 0 && ky == 0) {
            this.gapToDo = 0;
            this.xSpeed =Values.CELLSIZE/Values.nStepSprite;
            this.ySpeed = Values.CELLSIZE/Values.nStepSprite;
        }
        gapx=xSpeed;
        gapy=ySpeed;
        this.x = el.getX() + kx;
        this.y = el.getY() + ky;


        this.spriteLoop = new SpriteLoopThread(itemDrawer, this);
        spriteLoop.setRunning(true);
        spriteLoop.run();

    }

    public Sprite(MovableElement el) {
        this.el = el;

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

        this.kx = 0;
        this.ky = 0;
        this.x = el.getX() + kx;
        this.y = el.getY() + ky;

        this.xSpeed = Values.CELLSIZE/Values.nStepSprite * -kx;
        this.ySpeed = Values.CELLSIZE/Values.nStepSprite * -ky;
        if (kx == 0 && ky == 0) {
            this.gapToDo = 0;
            this.xSpeed =Values.CELLSIZE/Values.nStepSprite;
            this.ySpeed = Values.CELLSIZE/Values.nStepSprite;
        }
        gapx=xSpeed;
        gapy=ySpeed;
        bmp = mvingBmp[0];

    }

    public void updateBMP() {
        if (el != null) {

            Dir d = el.getDir();
            int mv = el.getMotion();

            switch (d) {
                case F:
                    this.bmp=mvingBmp[0];
                    kx=0;
                    ky=0;
                    break;
                case S:
                    this.bmp = mvingBmp[3];
                    kx=0;
                    ky=-1;
                    break;
                case E:
                    this.bmp = mvingBmp[0];
                    kx=1;
                    ky=0;
                    break;
                case W:
                    this.bmp = mvingBmp[1];
                    kx=-1;
                    ky=0;
                    break;
                case N:
                    this.bmp = mvingBmp[2];
                    kx=0;
                    ky=1;
                    break;
                default:
                    throw  new RuntimeException("impossible");
            }
            this.kx *= mv;
            this.ky *= mv;
            if( !el.isMoving()) {
                currentFrame = 0;
            } else {
                currentFrame = (currentFrame + 1) % (BMP_COLUMNS);
            }
        }

    }
/*
    public void updateStatic() {
        this.kx = 0;
        this.ky = 0;
        if (el != null) {
            if(Values.DEBUG_MODE) System.out.println("drawing " +el.toString());
            Dir d = el.getDir();
            switch (d) {
                case F:
                    this.bmp = staticBmp[0];
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
        }
        this.currentFrame = 0;

    }*/

    public void updateAction() {
        this.kx = 0;
        this.ky = 0;
    }

    public void update() {
        el.update();
         if (this.el.isActioning()){
            updateAction();
        } else {
            updateBMP();
        }
        gapy += ySpeed;
        gapx += xSpeed;
        currentFrame = (currentFrame +1 ) % (BMP_COLUMNS);

    }

    public Bitmap getCurrentFrame() {
        return this.bmp[currentFrame];
    }


    public void onDraw(Canvas canvas) {
        update();
        //if ((Math.abs(gapx) <= Values.CELLSIZE+this.xSpeed ) && (Math.abs(gapy) <= Values.CELLSIZE+this.ySpeed)) {
        itemDrawer.getDrawer().draw(x, y, bmp[currentFrame], true, canvas, gapx * gapToDo, gapy * gapToDo);
        //} else {
            //gameLoop.setRunning(false);
    }

    public  void draw( Canvas canvas) {
        int x = el.getX();
        int y = el.getY();
        Bitmap b = this.bmp[currentFrame];
        Bitmap bm = getResizedBitmap(b);
        int gap = (int) Values.CELLSIZE/4;
        float topx = x* Values.CELLSIZE-kx*gap;
        float topy = y* Values.CELLSIZE+ky;
        RectF whereToDraw = new RectF(
                topx + Values.LSHIFT, topy+ Values.HSHIFT,
                topx + Values.CELLSIZE + Values.LSHIFT,
                topy + Values.CELLSIZE+ Values.HSHIFT);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bm, null, whereToDraw, Values.paint);
    }
    public Bitmap getResizedBitmap(Bitmap bm) {
        Bitmap resizedBitmap =  Bitmap.createScaledBitmap(bm, Values.CELLSIZE, Values.CELLSIZE, false);
        return resizedBitmap;
    }

}