package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;


public class Sprite {

    private static final int BMP_COLUMNS = 3;

    private int x = 0;
    private int y = 0;
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
        this.xSpeed = 10 * -kx;
        this.ySpeed = 10 * -ky;
        if (kx == 0 && ky == 0) {
            this.gapToDo = 0;
            this.xSpeed = 10;
            this.ySpeed = 10;
        }

        this.x = el.getX() + kx;
        this.y = el.getY() + ky;


        this.gameLoop = new GameLoopThread(
                itemDrawer,
                this);
        gameLoop.setRunning(true);
        gameLoop.run();

    }

    private void update() {
        gapy += ySpeed;
        gapx += xSpeed;
        currentFrame = (currentFrame + 1) % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        update();
        if ((Math.abs(gapx) <= Values.CELLSIZE + 10) && (Math.abs(gapy) <= Values.CELLSIZE + 10)) {
            itemDrawer.getDrawer().draw(x, y, bmp[currentFrame], true, canvas, gapx * gapToDo, gapy * gapToDo);
        } else {
            gameLoop.setRunning(false);
        }
    }


}