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
    public static final int IMAGE_SIZE = 14;
    public static final int CELLSIZE = 80;

    //TODO : put the maze in the middle of the frame
    public static final int MVFWD =1;
    public static final int MVBWD = -1;
    public static final int NOMV = 0;

    public static float getScale(LinearLayout lay, Level l) {
        int hauteur = lay.getHeight();
        int horizontalitude = lay.getWidth();

        return 2;

    }



    //Add sizeX and sizeY (width and height of the images)

}
