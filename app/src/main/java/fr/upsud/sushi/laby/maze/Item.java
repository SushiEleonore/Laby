package fr.upsud.sushi.laby.maze;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.exceptions.wallCollisionException;
import fr.upsud.sushi.laby.graphics.Sprite;
import fr.upsud.sushi.laby.utils.BitmapParser;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Sens;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 6/23/16.
 */
public class Item extends MovableElement {
    private int x;
    private int y;
    Bitmap skinItem;
    //The direction faced by the player
    private Level l;
    private boolean visible;

    private Sprite sprite;

    public Item(int x, int y, Level l) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.visible=true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources res=this.l.getContext().getResources();
        this.skinItem = BitmapParser.getChili(res);//BitmapFactory.decodeResource(res, R.drawable.piment, options);
        this.sprite = new Sprite(this);
    }

    public void restart(){
        this.visible=true;

    }
    public Sprite getSprite() {
        return this.sprite;
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    public Bitmap getSkinItem(){ return this.skinItem;}

    public Level getLevel(){ return this.l;}

    public void setX(int pX) { this.x = pX;}
    public void setY(int pY) { this.y = pY;}
    public void setVisible(boolean b){this.visible=b;}
    public boolean playerOnCell(){
        return (this.x==this.l.getPlayer().getX())&&(this.y==this.l.getPlayer().getY());
    }
    public Bitmap[] getStaticBmp(){Bitmap[] bmp = new Bitmap[3];
        bmp[0]=this.skinItem;
        bmp[1]=this.skinItem;
        bmp[2]=this.skinItem;
        return bmp;
    }

    public Bitmap getActionBmp(String s){
        return null;
    }

    public int getMotion(){return 0;}

    public Dir getDir(){return Dir.F;}

    public boolean getState(){
        return this.visible;
    }

    public String toString() {
        return "item chili";
    }

    public void update(){
        Item e = l.getItem();
        if (e!=null){
            this.visible= e.getState();
        }

    }
    public boolean isActioning(){return false;}
    public boolean isMoving(){return false;}
    public void setActioning(boolean b){}
}
