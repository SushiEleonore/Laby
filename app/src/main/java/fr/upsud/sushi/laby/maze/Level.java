package fr.upsud.sushi.laby.maze;

import java.io.InputStream;
import java.util.ArrayList;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.utils.Values;
import fr.upsud.sushi.laby.utils.Dir;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Level {

    private Cell[][] cells;

    private Player p;

    private int xEnd;
    private int yEnd;

    private int levelMax;

    private int xStart;
    private int yStart;
    private Dir startDir;

    private Context context;

    private int cellSize;

    private int lShift;
    private int hShift;

    private int lvl;

    private Item item;
    private BreakableWall bWall;

    private ArrayList<String> authorizedBlocks;

    private int nbBlocks;

    private int timePerInst;


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
    * then the number of blocks you can place :
    *
    * n\n
    * then the maze :
    *
    * w : wall
    * u : empty
    * s : start
    * e : end
    *
    *
    *
    * finally, the authorized blocks, and the time per instruction
    *
    *
    * */

    private void createLevel(String s){
        if( Values.DEBUG_MODE) System.out.println(s);
        String[] tab = s.split("\n");
        int sizeX = Integer.parseInt(tab[0]);
        int sizeY = Integer.parseInt(tab[1]);
        this.cells = new Cell[sizeX][sizeY];
        String dir = tab[2];
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
        this.nbBlocks = Integer.parseInt(tab[3]);
        if( Values.DEBUG_MODE) System.out.println("nombre de blocs a placer : " + this.nbBlocks);
        for (int i = 4; i<sizeY+4; i++){
            for (int j =0; j<sizeX; j++){
                char c = tab[i].charAt(j);
                switch (c) {
                    case 'w' :
                        cells[j][i-4] = new Cell(Cell.Type.WALL);
                        break;
                    case 'u' :
                        cells[j][i-4] = new Cell(Cell.Type.PATH);
                        break;
                    case 's' :
                        cells[j][i-4] = new Cell(Cell.Type.PATH);
                        this.xStart = j;
                        this.yStart =  i-4;
                        break;
                    case 'p' :
                        cells[j][i-4] = new Cell(Cell.Type.PATH);
                        this.item=new Item(j, i-4, this);
                        break;
                    case 'b' :
                        cells[j][i-4] = new Cell(Cell.Type.PATH);
                        this.bWall=new BreakableWall(j, i-4, this);
                        break;
                    default : //=end
                        cells[j][i-4] = new Cell(Cell.Type.END);
                        this.xEnd = j;
                        this.yEnd = i-4;
                        break;
                }
            }
        }
        this.p = new Player(this.xStart, this.yStart, d, this);
        for (int i = sizeY+4; i<tab.length-1; i++) {
            this.authorizedBlocks.add(tab[i]);
        }
        this.timePerInst = Integer.parseInt(tab[tab.length-1]);
        //Values.stepTime = this.timePerInst;
       // computeCellSize();

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
        } catch (Exception e) {e.printStackTrace();System.out.println("File problem");}
    }


    //Generates a maze and a player depending on the level.
    //Replaced the arraylist by an array
    public Level (int lvl, Context ctx) {
        this.levelMax = Values.maxLvl;
        this.context = ctx;
        this.authorizedBlocks = new ArrayList<String>();
        if (lvl == 1) {
            this.lvl = lvl;
            openFile(R.raw.level1);
        } else if (lvl == 2) {
            this.lvl = lvl;
            openFile(R.raw.level2);
        } else if (lvl == 3) {
            this.lvl = lvl;
            openFile(R.raw.level3);
        } else if (lvl == 4) {
            this.lvl = lvl;
            openFile(R.raw.level4);
        } else if (lvl == 5) {
            this.lvl = lvl;
            openFile(R.raw.level5);
        } else if (lvl == 6) {
            this.lvl = lvl;
            openFile(R.raw.level6);

        } else if (lvl == 7) {
            this.lvl = lvl;
            openFile(R.raw.level7);

        } else if (lvl == 8) {
            this.lvl = lvl;
            openFile(R.raw.level8);
        }
        //computeCellSize();
    }




    public Context getContext(){
        return this.context;
    }

    private void computeCellSize() {
        int hauteur = 0;
        int horizontalitude = 0;
        LinearLayout lay = (LinearLayout)((Activity)this.context).findViewById(R.id.layout1);
        lay.measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        horizontalitude=lay.getMeasuredWidth();
        hauteur=lay.getMeasuredHeight();
        if( Values.DEBUG_MODE) System.out.println("hauteur : " + hauteur);
        if( Values.DEBUG_MODE) System.out.println("largeur :  " + horizontalitude);

        if( Values.DEBUG_MODE)System.out.println("taille du niveau en x : " + this.cells.length);

        float sizex = ((float)(horizontalitude)) / ((float)(this.cells.length));
        if( Values.DEBUG_MODE)System.out.println("TAILLE EN X : "+ sizex);
        float sizey = ((float)(hauteur) )/((float) (this.cells[0].length));
        if( Values.DEBUG_MODE)System.out.println("TAILLE EN Y : " + sizey);
        if (sizex<sizey) {

            this.cellSize =(int) (sizex);
        } else {
            this.cellSize = (int) (sizey);
        }
        Values.setCellSize(cellSize);


    }

    public void setCellSize(int h, int w){
        if( Values.DEBUG_MODE)System.out.println("h : " + h + "\nw : " + w);
        if( Values.DEBUG_MODE)System.out.println("appel à setcellsize ");
        float sizex = ((float)(w)) / ((float)(this.cells.length));
        if( Values.DEBUG_MODE)System.out.println("TAILLE EN X : "+ sizex);
        float sizey = ((float)(h) )/((float) (this.cells[0].length));
        if( Values.DEBUG_MODE)System.out.println("TAILLE EN Y : " + sizey);
        if (sizex<sizey) {

            this.cellSize =(int) (sizex);
        } else {
            this.cellSize = (int) (sizey);
        }

        this.hShift = (h - this.cells[0].length * this.cellSize)/2;
        Values.setHSHIFT(hShift);
        this.lShift= (w - this.cells.length * this.cellSize)/2;
        Values.setLSHIFT(lShift);
        if( Values.DEBUG_MODE) System.out.println( "Eccess width :" + (w - this.cells.length * this.cellSize) );
        if( Values.DEBUG_MODE) System.out.println( "Eccess height :" + (h - this.cells[0].length * this.cellSize) );
        Values.setCellSize(cellSize);
    }


    public int getTimePerInst() { return this.timePerInst;}

   // public int getCellSize() { return this.cellSize;}

    public int getNbBlocks() {return this.nbBlocks;}

    //public Dir getStartDir() {return this.startDir;}

    //public int getXstart()  {return this.xStart;}
    //public int getYstart()  {return this.yStart;}

    public int getLevelMax() { return this.levelMax;}

    public ArrayList<String> getAuthorizedBlocks() {return this.authorizedBlocks;}

    public Player getPlayer() { return this.p;}

    public int getLevel() { return this.lvl; }

    public Cell[][] getCells() {return this.cells;}

    public void setPlayer(Player pl) {
        this.p = pl;}


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
        this.p.setChili(false);
        this.p.setActioning(false);
        this.p.setMoving(false);
        if(item!=null){item.restart();}
        if(bWall!=null)bWall.restart();

    }

    public Item getItem(){return this.item;}

    public BreakableWall getbWall(){return this.bWall;}


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
