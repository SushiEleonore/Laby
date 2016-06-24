package fr.upsud.sushi.laby.maze;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;

/**
 * Created by proval on 6/23/16.
 */
public class BreakableWall {
    private int x;
    private int y;
    //state is true if wall is not broken
    private boolean state;
    Bitmap skinBWall;
    Bitmap skinBurnig;
    //The direction faced by the player
    private Level l;


    public BreakableWall(int x, int y, Level l) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.state=true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources res=this.l.getContext().getResources();
        this.skinBWall = BitmapFactory.decodeResource(res, R.drawable.breakablewall, options);
        this.skinBurnig = BitmapFactory.decodeResource(res, R.drawable.burningbwall, options);
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    public void restart(){
        this.state= true;
    }

    public Bitmap getSkinItem(){ return this.skinBWall;}
    public Bitmap getSkinBurning(){return this.skinBurnig;}

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
}