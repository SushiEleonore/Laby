package fr.upsud.sushi.laby.maze;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.graphics.ItemDrawer;
import fr.upsud.sushi.laby.graphics.Sprite;
import fr.upsud.sushi.laby.utils.BitmapParser;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 6/23/16.
 */
public class BreakableWall extends MovableElement{
    private int x;
    private int y;
    //state is true if wall is not broken
    private boolean state;
    Bitmap skinBWall;
    Bitmap skinBurnig;
    //The direction faced by the player
    private Level l;
    private Sprite sprite;
    private boolean isActioning=false;

    public BreakableWall(int x, int y, Level l) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.state=true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources res=this.l.getContext().getResources();
        this.skinBWall = BitmapParser.getStBreakableWall(res);//BitmapFactory.decodeResource(res, R.drawable.breakablewall, options);
        this.skinBurnig = BitmapParser.getMvBreakableWall(res);//BitmapFactory.decodeResource(res, R.drawable.burningbwall, options);
        this.sprite = new Sprite(this);
    }

    public Sprite getSprite() { return this.sprite; }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    @Override
    public int getMotion() {
        return 0;
    }

    public void restart(){
        this.state= true;
        this.isActioning=false;
    }

    public Bitmap[] getStaticBmp(){
        Bitmap[] bmp = new Bitmap[3];
        bmp[0]=this.skinBWall;
        bmp[1]=this.skinBWall;
        bmp[2]=this.skinBWall;
        return bmp;}

    public Dir getDir(){
        return Dir.F;
    }

    public Bitmap getActionBmp(String s){return this.skinBurnig;}

    public Level getLevel(){ return this.l;}

    public void setX(int pX) { this.x = pX;}
    public void setY(int pY) { this.y = pY;}
    public boolean playerFacing(){
        return (state==true)&&((this.x+1==this.l.getPlayer().getX())||
                (this.x-1==this.l.getPlayer().getX())||
                (this.y-1==this.l.getPlayer().getY())||
                (this.y+1==this.l.getPlayer().getY()));
    }
    public void setState(Boolean b){
        if(!b) l.getPlayer().setChili(false);
        this.state=b;}
    public boolean getState(){return this.state;}

    public String toString() {
        return"Breakable wall";
    }

    public void update(){
        BreakableWall b = l.getbWall();
        if(b!=null){
            this.state=b.getState();
        }
    }
    public boolean isActioning(){return this.isActioning;}
    public boolean isMoving(){return false;}
    public void setActioning(boolean b){this.isActioning=b;}

}
