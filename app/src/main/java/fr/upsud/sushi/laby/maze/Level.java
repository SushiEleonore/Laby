package fr.upsud.sushi.laby.maze;

import java.io.InputStream;
import java.util.HashMap;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.utils.Dir;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Level {

    private Cell[][] cells;

    private Player p;

    private int xEnd;
    private int yEnd;

    private int xStart;
    private int yStart;
    private Dir startDir;

    private Context context;

    private int lvl;

    private HashMap<String, Boolean> authorizedBlocks;


    /*
    * format of the maze :
    *
    * First 2 lines, size of the maze :
    * x\n
    * y\n
    * then position and direction of the player :
    * px\n
    * py\n
    * Direction\n
    * then the maze
    * w : wall
    * u : empty
    * s : start
    * e : end
    *
    *
    * */
    private String lv1 = "3\n5\n1\n1\nS\nwww\nwsw\nwuw\nwew\nwww";
    private String lv2 = "5\n7\n2\n2\nS\nwwwww\nwuuuw\nwusuw\nwuuuw\nwueuw\nwuuuw\nwwwww";
    private String lv3 = "5\n10\n2\n2\nS\nwwwww\nwuuuw\nwusuw\nwuuuw\nwuuuw\nwuuuw\nwuuuw\nwueuw\nwuuuw\nwwwww";
    private String lv4 = "5\n10\n2\n2\nS\nwwwww\nwuuuw\nwusuw\nwuuuw\nwuuuw\nwuwuw\nwuuuw\nwueuw\nwuuuw\nwwwww";





    private void createLevel(String s){
        System.out.println(s);
        String[] tab = s.split("\n");
        int sizeX = Integer.parseInt(tab[0]);
        int sizeY = Integer.parseInt(tab[1]);
        int posX = Integer.parseInt(tab[2]);
        int posY = Integer.parseInt(tab[3]);
        this.cells = new Cell[sizeX][sizeY];
        String dir = tab[4];
        Dir d;
        switch (dir) {
            case "N" :
                d = Dir.N;
                break;
            case "S" :
                d = Dir.S;
                break;
            case "E" :
                d = Dir.E;
                break;
            default :
                d = Dir.W;
                break;
        }
        this.startDir = d;
        for (int i = 5; i<sizeY+5; i++){
            for (int j =0; j<sizeX; j++){
                char c = tab[i].charAt(j);
                switch (c) {
                    case 'w' :
                        cells[j][i-5] = new Cell(Cell.Type.WALL);
                        break;
                    case 'u' :
                        cells[j][i-5] = null;
                        break;
                    case 's' :
                        cells[j][i-5] = new Cell(Cell.Type.START);
                        this.xStart = j;
                        this.yStart =  i-5;
                        break;
                    default : //=end
                        cells[j][i-5] = new Cell(Cell.Type.END);
                        this.xEnd = j;
                        this.yEnd = i-5;
                        break;
                }
            }
        }
        this.p = new Player(posX, posY, d, this);
    }


    private void openFile   (int id){
        InputStream iS =  this.context.getResources().openRawResource(id);
        try {
            byte[] buffer = new byte[iS.available()];
            iS.read(buffer);
            ByteArrayOutputStream oS = new ByteArrayOutputStream();
            oS.write(buffer);
            oS.close();
            iS.close();

            createLevel(oS.toString());
        } catch (Exception e) {System.out.println("File problem");}
    }


    //Generates a maze and a player depending on the level.
    //Replaced the arraylist by an array
    public Level (int lvl, Context ctx) {
        this.context = ctx;
        this.authorizedBlocks = new HashMap<String, Boolean>();
         if (lvl == 1) {
            this.lvl = lvl;
             this.authorizedBlocks.put("turn", false);
             this.authorizedBlocks.put("move", true);
             this.authorizedBlocks.put("ifpath", false);
             this.authorizedBlocks.put("while", false);
             this.authorizedBlocks.put("ifelse", false);
             this.authorizedBlocks.put("ifelse", false);
             openFile( R.raw.level1);
             //createLevel(lv1);

         } else if (lvl == 2) {
             this.lvl = lvl;
             openFile( R.raw.level2);
         } else if (lvl == 3) {
             this.lvl = lvl;
             openFile( R.raw.level3);
         } else if (lvl == 4) {
             this.lvl = lvl;
             //openFile( R.raw.level4);
         }

    }


    public Dir getStartDir() {return this.startDir;}

    public int getXstart()  {return this.xStart;}
    public int getYstart()  {return this.yStart;}

    public Player getPlayer() { return this.p;}

    public int getLevel() { return this.lvl; }

    public Cell[][] getCells() {return this.cells;}

    public void setPlayer(Player pl) {this.p = pl;}

    //Checks if there's a wall at [i][j]
    public boolean isWall(int i, int j) {
        if (cells[i][j] == null) {return false;}
        if (cells[i][j].getType() == (Cell.Type.WALL)) { return true;}
        else {return false;}
    }

    public int getXend() { return this.xEnd;}
    public int getYend() { return this.yEnd;}

    public void restart () {
        this.p.setX(this.xStart);
        this.p.setY(this.yStart);
        this.p.setDir(this.startDir);
    }

    /**********Tests************/

  /*  public String printMaze () {
        String s = "";
        for (int i = 0; i<cells[0].length; i++) {
            for (int j = 0; j<cells.length; j++) {
                if (j == this.p.getX() && i == this.p.getY()) {s = s + "p";}
                else if (cells[j][i] == null) {s = s + "+";}
                else if (cells[j][i].getType() == (Cell.Type.WALL)) { s= s+"o"; }
                else if (cells[j][i].getType() == (Cell.Type.START)) {s = s + "s";}
                else if (cells[j][i].getType() == (Cell.Type.END)) {s = s + "e";}
                else s = s + "-";
            }
            s = s + "\n";
        }
        return s;
    }*/


}
