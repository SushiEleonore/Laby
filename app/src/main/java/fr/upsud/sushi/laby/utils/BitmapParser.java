package fr.upsud.sushi.laby.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;


public class BitmapParser {
    private static int caseSize ;
    private static Skins skin = Skins.BEBEZILLA;
    public static  Bitmap mainBitmap;

    public static void setSkin(Skins s){
        skin = s;
    }

    private static void setMainBitmap(Resources r){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        switch(skin){
            case DUCK:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.duckplayer, opts);
                caseSize = 24;
                break;
            case POULPE:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.poulpeplayer, opts);
                caseSize = 24;
                break;
            default:
                mainBitmap = BitmapFactory.decodeResource(r, R.drawable.bebezillaplayer, opts);
                caseSize = 24;
                break;
        }
    }


    public static Bitmap getSkinMvG(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize, 0, caseSize*3, caseSize);
    }
    public static Bitmap getSkinMvD(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*5, 0, caseSize*3, caseSize);
    }
    public static Bitmap getSkinMvDos(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*9, 0, caseSize*3, caseSize);
    }
    public static Bitmap getSkinMvFace(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*13, 0, caseSize*3, caseSize);
    }
    public static Bitmap getPDestroying(Resources r) {
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*17, 0, caseSize*3, caseSize);
    }


    public static Bitmap getStBreakableWall(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*20, 0, caseSize*3, caseSize);
    }
    public static Bitmap getMvBreakableWall(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*21, 0, caseSize*3, caseSize);
    }
    public static Bitmap getWall(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*25, 0, caseSize, caseSize);
    }
    public static Bitmap getChili(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*24, 0, caseSize*3, caseSize);
    }
    public static Bitmap getPath(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*26, 0, caseSize, caseSize);
    }
    public static Bitmap getEnd(Resources r){
        setMainBitmap(r);
        return Bitmap.createBitmap(mainBitmap, caseSize*27, 0, caseSize, caseSize);
    }

}
