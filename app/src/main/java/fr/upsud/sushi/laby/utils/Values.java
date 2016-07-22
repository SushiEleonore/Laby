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

    //Images resizing
    public static int CELLSIZE = 0;
    public static int LSHIFT = 0;
    public static int HSHIFT = 0;

    public static Paint paint = new Paint(0);

    //Player movement
    public static final int MVFWD =1;
    public static final int MVBWD = -1;
    public static final int NOMV = 0;


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

}
