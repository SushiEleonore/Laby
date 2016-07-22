package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import fr.upsud.sushi.laby.maze.BreakableWall;
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
    private Bitmap[] bmp;
    private int currentFrame = 0;
    private int nStep=0;


    public Sprite(MovableElement el) {
        this.el = el;
        if (el != null) {
            Bitmap[] tempBmp = el.getStaticBmp();
            this.staticBmp = tempBmp.clone();
            this.mvingBmp = new Bitmap[4][3];
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
        this.x = el.getX();
        this.y = el.getY();
        this.xSpeed = Values.CELLSIZE / Values.nStepSprite ;
        this.ySpeed = Values.CELLSIZE / Values.nStepSprite;
        gapx = 0;
        gapy = 0;
        bmp = mvingBmp[0];
    }

    public void updateBMP() {
        if (el != null) {
            if(Math.abs(gapx) >Values.CELLSIZE||Math.abs(gapy)>Values.CELLSIZE || !el.isMoving()){
                gapy=0; gapx=0;
                currentFrame = 0;
                if(el instanceof Player){ ((Player)el).setMoving(false); ((Player) el).setMotion(0);}
            }
            else {
                if (Values.TESTSPRITE && el instanceof Player) {
                    currentFrame = (currentFrame + 1) % (BMP_COLUMNS*Values.frameTime);
                } else {
                    currentFrame = (currentFrame + 1) % (BMP_COLUMNS);
                }

                if (gapx != 0) gapx += xSpeed;
                else if (gapy != 0) gapy += ySpeed;
                else {
                    Dir d = el.getDir();
                    int mv = el.getMotion();
                    this.xSpeed = Values.CELLSIZE / Values.nStepSprite ;
                    this.ySpeed = Values.CELLSIZE / Values.nStepSprite;
                    if(Values.DEBUG_MODE) System.out.println("motion of the player : " + mv);
                    gapx=0;gapy=0;
                    kx=0;
                    ky=0;

                    switch (d) {
                        case F:
                            this.bmp = mvingBmp[0];
                            gapx = 0;
                            gapy = 0;
                            break;
                        case S:
                            this.bmp = mvingBmp[3];
                            gapx = 0;
                            ky=mv;
                            ySpeed*=mv;
                            gapy = ySpeed;
                            break;
                        case E:
                            this.bmp = mvingBmp[0];
                            xSpeed*=mv;
                            gapx = xSpeed;
                            kx=mv;
                            gapy= 0;
                            break;
                        case W:
                            this.bmp = mvingBmp[1];

                            kx=-mv;
                            this.xSpeed*=kx;
                            gapx = xSpeed;
                            gapy = 0;
                            break;
                        case N:
                            this.bmp = mvingBmp[2];
                            gapx = 0;
                            ky=-mv;
                            this.ySpeed*=ky;
                            gapy = ySpeed;
                            break;
                        default:
                            throw new RuntimeException("impossible");
                    }
                }
            }
        }

    }

    public void updateAction() {
        if(nStep>=Values.nStepSprite) {
            el.setActioning(false);
            nStep=0;
            if(el instanceof BreakableWall) ((BreakableWall) el).setState(false);

        }
        else {
            if(el instanceof BreakableWall) ((BreakableWall) el).setState(true);
            nStep++;
            if (Values.TESTSPRITE ) {
                currentFrame = (currentFrame + 1) % (BMP_COLUMNS*Values.frameTime);
            } else {
                currentFrame = (currentFrame + 1) % (BMP_COLUMNS);
            }
            this.bmp=actionBmp;
            this.kx = 0;
            this.ky = 0;
        }
    }

    public void update() {
        el.update();
        if(el.isMoving())updateBMP();
        if(!el.isMoving() && !el.isActioning()) updateStatic();
        else if (el.isActioning()) updateAction();
    }

    public void draw(Canvas canvas) {
        if (el.getState()) {
            int x = el.getX();
            int y = el.getY();
            Bitmap b = this.bmp[currentFrame/Values.frameTime];
            Bitmap bm = getResizedBitmap(b);
            float topx = (x - kx) * Values.CELLSIZE + gapx;
            float topy = (y - ky) * Values.CELLSIZE + gapy;
            RectF whereToDraw = new RectF(
                    topx + Values.LSHIFT, topy + Values.HSHIFT,
                    topx + Values.CELLSIZE + Values.LSHIFT,
                    topy + Values.CELLSIZE + Values.HSHIFT);


            canvas.drawBitmap(bm, null, whereToDraw, Values.paint);
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        Bitmap resizedBitmap =
                Bitmap.createScaledBitmap(bm, Values.CELLSIZE, Values.CELLSIZE, false);
        return resizedBitmap;
    }

    @Override
    public String toString() {
        return "<x:" + this.x + ", y:" + this.y + ">";
    }

    public void updateStatic() {
        this.kx = 0;
        this.ky = 0;
        if (el != null) {

            Dir d = el.getDir();
            switch (d) {
                case F:
                    this.bmp = mvingBmp[0];
                    break;
                case S:
                    bmp = mvingBmp[3];
                    break;
                case E:
                    bmp = mvingBmp[0];
                    break;
                case W:
                    bmp = mvingBmp[1];
                    break;
                default:
                    bmp = mvingBmp[2];
                    break;
            }
        }
        this.currentFrame = 0;
    }
}