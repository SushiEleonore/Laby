package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;


public class Sprite {

    private static final int BMP_COLUMNS = 3;

    private int x;
    private int y;
    private int gapy = 0;
    private int ySpeed = 30;
    private int xSpeed = 30;
    private int gapx = 0;

    private ItemDrawer itemDrawer;

    private Bitmap[] bmp;
    private int currentFrame = 0;

    //Si le sprite en question doit faire un déplacement, gapToDo vaut 1; sinon 0
    private int gapToDo = 1;

    private GameLoopThread gameLoop;


    public Sprite(ItemDrawer itemDrawer, MovableElement el, Bitmap[] bmp, int kx, int ky) {
        this.itemDrawer = itemDrawer;
        this.bmp = bmp;
        this.xSpeed = Values.XSPEED * -kx;
        this.ySpeed = Values.YSPEED * -ky;
        if (kx == 0 && ky == 0) {
            this.gapToDo = 0;
            this.xSpeed = Values.XSPEED;
            this.ySpeed = Values.YSPEED;
        }

        this.x = el.getX() + kx;
        this.y = el.getY() + ky;


        this.gameLoop = new GameLoopThread(
                itemDrawer,
                this);
        gameLoop.setRunning(true);
        gameLoop.start();

    }

    private void update() {
        gapy += ySpeed;
        gapx += xSpeed;
        currentFrame = (currentFrame + 1) % BMP_COLUMNS;

    }

    public void onDraw(Canvas canvas) {
        update();
        if ((Math.abs(gapx) <= Values.CELLSIZE+this.xSpeed ) && (Math.abs(gapy) <= Values.CELLSIZE+this.ySpeed)) {
            itemDrawer.getDrawer().draw(x, y, bmp[currentFrame], true, canvas, gapx * gapToDo, gapy * gapToDo);
        } else {
            gameLoop.setRunning(false);
        }
    }


}