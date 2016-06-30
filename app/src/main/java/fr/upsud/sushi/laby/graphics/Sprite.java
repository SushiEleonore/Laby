package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import fr.upsud.sushi.laby.maze.MovableElement;
import fr.upsud.sushi.laby.utils.Values;


public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;

    private int x = 0;
    private int y = 0;
    private int gapy =0;
    private int ySpeed = 15;
    private int xSpeed = 15;
    private int gapx =0;
    private int life = 6;
    private GameView gameView;

    private Bitmap[] bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private MovableElement el;
    //Si le sprite en question doit faire un déplacement, gapToDo vaut 1; sinon 0
    private int gapToDo=1;

    private GameLoopThread gameLoop;


    public Sprite(GameView gameView, MovableElement el, Bitmap[] bmp, int kx, int ky) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp[0].getWidth();
        this.height = bmp[0].getHeight();
        this.el=el;
        this.xSpeed=5*-kx;
        this.ySpeed=5*-ky;
        if(kx==0&&ky==0){this.gapToDo=0;
        this.xSpeed=5;
        this.ySpeed=5;}

        this.x=el.getX()+kx;
        this.y=el.getY()+ky;

        this.gameLoop=new GameLoopThread(
                gameView,
                this);
        gameLoop.setRunning(true);
        gameLoop.run();

    }

    private void update() {
        gapy += ySpeed;
        gapx+= xSpeed;
        currentFrame = (currentFrame+1) % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas, String action) {
        update();
        if((Math.abs(gapx)<= Values.CELLSIZE) && (Math.abs(gapy)<=Values.CELLSIZE)) {
            gameView.getDrawer().draw(x,y,bmp[currentFrame],true,canvas, gapx*gapToDo,gapy*gapToDo);
        }
        else {
            gameLoop.setRunning(false);
        }
    }






}



