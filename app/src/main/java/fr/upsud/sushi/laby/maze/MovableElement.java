package fr.upsud.sushi.laby.maze;

import android.graphics.Bitmap;

import fr.upsud.sushi.laby.graphics.Sprite;
import fr.upsud.sushi.laby.utils.Dir;

/**
 * Created by proval on 6/27/16.
 */
public abstract class MovableElement {
    Bitmap bmp;
    //public abstract Bitmap[] getMovingBmp(String action);

    public abstract Bitmap[] getStaticBmp();
    public abstract Dir getDir();
    public abstract int getX();
    public abstract int getY();
    public abstract String toString();
    public abstract int getMotion();
    public abstract Bitmap getActionBmp(String s);
    public abstract void update();
    public abstract boolean isMoving();
    public abstract boolean isActioning();
    public abstract boolean getState();
    public abstract Sprite getSprite();


}
