package fr.upsud.sushi.laby.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import fr.upsud.sushi.laby.maze.MovableElement;


public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;

    private int x = 0;
    private int y = 0;
    private int ky =0;
    private int xSpeed = 10;
    private int life = 6;
    private GameView gameView;

    private Bitmap[] bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private MovableElement el;

    private GameLoopThread gameLoop;


    public Sprite(GameView gameView, MovableElement el, Bitmap[] bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp[0].getWidth();
        this.height = bmp[0].getHeight();
        this.x=el.getX();
        this.y=el.getY();
        this.gameLoop=new GameLoopThread(
                gameView,
                this);
        gameLoop.setRunning(true);
        gameLoop.run();



    }

    private void update() {
        life--;
        ky += xSpeed;
        currentFrame = (currentFrame++) % BMP_COLUMNS;

    }

 /*   public void draw(){
    update();

    if(life<1&&bmp!=null)

    {
        int srcX = currentFrame * width;
        int srcY = height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        this.gameView.getsV().getHolder().lockCanvas().drawBitmap(bmp, src, dst, null);
    }
        else gameLoop.setRunning(false);

}
*/
    public void onDraw(Canvas canvas, String action) {
        update();
        if(life>1) {
            System.out.println("Tour "+life);
            int srcX = currentFrame * width;
            int srcY = height;
           Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
            Rect dst = new Rect(x, y, x + width, y + height);

           // canvas.drawBitmap(bmp[currentFrame], src, dst, null);
            gameView.getDrawer().draw(x,y,bmp[currentFrame],true,canvas, 0,ky);
        }
        else gameLoop.setRunning(false);
    }






}



