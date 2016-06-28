package fr.upsud.sushi.laby.maze;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.exceptions.wallCollisionException;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Sens;

/**
 * Created by proval on 6/23/16.
 */
public class Item extends MovableElement {
    private int x;
    private int y;
    Bitmap skinItem;
    //The direction faced by the player
    private Level l;


    public Item(int x, int y, Level l) {
        this.x = x;
        this.y = y;
        this.l = l;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources res=this.l.getContext().getResources();
        this.skinItem = BitmapFactory.decodeResource(res, R.drawable.piment, options);
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    public Bitmap getSkinItem(){ return this.skinItem;}

    public Level getLevel(){ return this.l;}

    public void setX(int pX) { this.x = pX;}
    public void setY(int pY) { this.y = pY;}
    public boolean playerOnCell(){
        return (this.x==this.l.getPlayer().getX())&&(this.y==this.l.getPlayer().getY());
    }
    public Bitmap[] getStaticBmp(){Bitmap[] bmp = new Bitmap[3];
        bmp[0]=this.skinItem;
        bmp[1]=this.skinItem;
        bmp[2]=this.skinItem;
        return bmp;
    }


    public Dir getDir(){return Dir.F;}


    public String toString() {
        return "item chili";
    }


}
