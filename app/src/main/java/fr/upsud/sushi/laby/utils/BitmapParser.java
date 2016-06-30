package fr.upsud.sushi.laby.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;

/**
 * Created by proval on 6/29/16.
 */
public class BitmapParser {

    private static int sizeImgx = 92;
    private static int sizeImgy = 192;

    private int caseSize ;
    private static Skins skin = Skins.BEBEZILLA;
    private Resources res;
    private Bitmap mainBitmap;


    public BitmapParser(Resources r) {
        this.res = r;
        switch(skin){
            case BEBEZILLA:
                this.mainBitmap = BitmapFactory.decodeResource(res, R.drawable.bebezilla_player);
                this.caseSize = 24;
                break;
            default:
                this.mainBitmap = BitmapFactory.decodeResource(res, R.drawable.duck_player);
                this.caseSize = 24;
                break;
        }

    }

    public static void setSkin(Skins s){
        skin = s;
        //if s = bebezilla then size = ...
    }

    public Bitmap getMainBitmap (){
        return this.mainBitmap;
    }


    //Moving player
    public Bitmap getSkinMvG() {
        return Bitmap.createBitmap(mainBitmap, caseSize*2, 0, caseSize*6, caseSize*2);
    }
    public Bitmap getSkinMvD() {
        return Bitmap.createBitmap(mainBitmap, caseSize*2, caseSize*2, caseSize*6, caseSize*2);
    }
    public Bitmap getSkinMvDos() {
        return Bitmap.createBitmap(mainBitmap, caseSize*2, caseSize*4, caseSize*6, caseSize*2);
    }
    public Bitmap getSkinMvFace() {
        return Bitmap.createBitmap(mainBitmap, caseSize*2, caseSize*6, caseSize*6, caseSize*2);
    }
    public Bitmap getPDestroying() {
        return Bitmap.createBitmap(mainBitmap, caseSize*2, caseSize*8, caseSize*6, caseSize*2);
    }

    //Static player
    public Bitmap getSkinStG() {
        return Bitmap.createBitmap(mainBitmap, 0, 0, caseSize, caseSize);
    }
    public Bitmap getSkinStD() {
        return Bitmap.createBitmap(mainBitmap, 0, caseSize, caseSize, caseSize);
    }
    public Bitmap getSkinStDos() {
        return Bitmap.createBitmap(mainBitmap, 0, caseSize*2, caseSize, caseSize);
    }
    public Bitmap getSkinStFace() {
        return Bitmap.createBitmap(mainBitmap, 0, caseSize*3, caseSize, caseSize);
    }

    //Other stuff
    public Bitmap getStBreakableWall(){
        return Bitmap.createBitmap(mainBitmap, 0, caseSize*5, caseSize, caseSize);
    }
    public Bitmap getMvBreakableWall(){
        return Bitmap.createBitmap(mainBitmap, caseSize, caseSize*5, caseSize*3, caseSize);
    }
    public Bitmap getWall(){
        return Bitmap.createBitmap(mainBitmap, caseSize, caseSize*6, caseSize*3, caseSize);
    }
    public Bitmap getChili(){
        return Bitmap.createBitmap(mainBitmap, 0, caseSize*6, caseSize, caseSize);
    }
    public Bitmap getPath(){
        return Bitmap.createBitmap(mainBitmap, caseSize*2, caseSize*6, caseSize, caseSize);
    }
    public Bitmap getEnd(){
        return Bitmap.createBitmap(mainBitmap, caseSize*3, caseSize*6, caseSize, caseSize);
    }

}
