package fr.upsud.sushi.laby.utils;

import android.widget.LinearLayout;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 6/7/16.
 */
public class Constants {

    //Agrandissement des images
    public static final int SCALE = 4;
    public static final int IMAGE_SIZE =100;
    public static int CELLSIZE = 2;
    public static int LSHIFT = 0;
    public static int HSHIFT = 0;

    //TODO : put the maze in the middle of the frame
    public static final int MVFWD =1;
    public static final int MVBWD = -1;
    public static final int NOMV = 0;

    public static final int maxLvl = 7;

    public static float getScale(LinearLayout lay, Level l) {
        int hauteur = lay.getHeight();
        int horizontalitude = lay.getWidth();

        return 2;

    }
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
