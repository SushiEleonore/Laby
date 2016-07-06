package fr.upsud.sushi.laby.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;

/**
 * Created by proval on 6/29/16.
 */
public class BitmapParser {

    private static int sizeImgx = 768;
    private static int sizeImgy = 24;

    private static int caseSize ;
    private static Skins skin = Skins.BEBEZILLA;
    public static  Bitmap mainBitmap;



    public BitmapParser(Resources r) {
        switch(skin){
            case BEBEZILLA:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.bebezillaplayer);
                caseSize = 24;
                break;
            default:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.duck_player);
                caseSize = 24;
                break;
        }

    }

    public static void setSkin(Skins s){
        skin = s;
    }

    public Bitmap getMainBitmap (){
        return this.mainBitmap;
    }

    private static void setMainBitmap(Resources r){
        switch(skin){
            case BEBEZILLA:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.bebezillaplayer);
                caseSize = 24;
                break;
            default:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.duck_player);
                caseSize = 24;
                break;
        }
    }


    //Moving player
    public static Bitmap getSkinMvG(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getSkinMvD(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*5, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getSkinMvDos(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*9, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getSkinMvFace(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*13, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getPDestroying(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*17, 0, caseSize*6, caseSize*2);
    }

    /*
    //Static player
    public static Bitmap getSkinStG(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, 0, 0, caseSize, caseSize);
    }
    public static Bitmap getSkinStD(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, 0, caseSize, caseSize, caseSize);
    }
    public static Bitmap getSkinStDos(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, 0, caseSize*2, caseSize, caseSize);
    }
    public static Bitmap getSkinStFace(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, 0, caseSize*3, caseSize, caseSize);
    }*/

    //Other stuff
    public static Bitmap getStBreakableWall(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*20, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getMvBreakableWall(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*21, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getWall(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*25, 0, caseSize*2, caseSize*2);
    }
    public static Bitmap getChili(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*24, 0, caseSize*6, caseSize*2);
    }
    public static Bitmap getPath(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*26, 0, caseSize*2, caseSize*2);
    }
    public static Bitmap getEnd(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*2*27, 0, caseSize*2, caseSize*2);
    }

}
