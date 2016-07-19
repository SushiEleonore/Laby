package fr.upsud.sushi.laby.utils;

import android.graphics.Paint;
import android.widget.LinearLayout;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 6/7/16.
 */
public class Values {

    public static final boolean DEBUG_MODE = false;

    public static final boolean TESTSPRITE = true;
    public static final int frameTime = 3;

    //public static int MVPLAYERTEST =0;

    //Game rendering
    public static final int FPS = 60;

    //public static int stepTime = 1000;

    //Images resizing
    public static final int SCALE = 4;
    public static final int IMAGE_SIZE =100;
    public static int CELLSIZE = 0;
    public static int LSHIFT = 0;
    public static int HSHIFT = 0;

    public static Paint paint = new Paint( 0 /*Paint.FILTER_BITMAP_FLAG |
            Paint.DITHER_FLAG
            | Paint.ANTI_ALIAS_FLAG*/);

    //Player movement
    public static final int MVFWD =1;
    public static final int MVBWD = -1;
    public static final int NOMV = 0;

    public static final int PLAYERID =0;
    public static final int BWALLID = 1;
    public static final int CHILIID = 2;


    public static final int XSPEED = 1;
    public static final int YSPEED = 1;

    public static final int nStepSprite=20;

    public static final int maxLvl = 8;

    public static void setCellSize(int cellSize){
       CELLSIZE=cellSize;
    }
    public static void setLSHIFT(int s){
        LSHIFT=s;
    }
    public static void setHSHIFT(int s){
        HSHIFT=s;
    }
    //Add sizeX and sizeY (width and height of the images)

}
