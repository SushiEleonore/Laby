package fr.upsud.sushi.laby.maze;

import fr.upsud.sushi.laby.calculus.Dir;

/**
 * Created by proval on 5/23/16.
 */
public class Level {
    //private ArrayList<Cell> cells;
    private Cell[][] cells;
    //Player ?
    private Player p;

    private int xEnd;
    private int yEnd;

    private int xStart;
    private int yStart;
    private Dir startDir;

    private int lvl;


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
    private String lv0 = "5\n10\n2\n2\nS\nwwwww\nwuuuw\nwusuw\nwuuuw\nwuuuw\nwuuuw\nwuuuw\nwueuw\nwuuuw\nwwwww";
    private String lv1 = "5\n10\n2\n2\nS\nwwwww\nwuuuw\nwusuw\nwuuuw\nwuuuw\nwuwuw\nwuuuw\nwueuw\nwuuuw\nwwwww";


    private void createLevel(String s){
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
                System.out.println(tab[i]);
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
                        this.xStart = i-5;
                        this.yStart = j;
                        break;
                    default : //=end
                        cells[j][i-5] = new Cell(Cell.Type.END);
                        this.xEnd = i-5;
                        this.yEnd = j;
                        break;
                }
            }
        }
        this.p = new Player(posX, posY, d, this);
    }

    //Generates a maze and a player depending on the level.
    //Replaced the arraylist by an array
    public Level (int lvl) {
         if (lvl == 0) {
            this.lvl = lvl;
            createLevel(lv0);


        } else if (lvl == 1) {
             this.lvl = lvl;
             createLevel(lv0);
        }


    }

    public Player getPlayer() { return this.p;}

    public int getLevel() { return this.lvl; }

    public Cell[][] getCells() {return this.cells;}

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

    public String printMaze () {
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
    }
/*
    public String printMaze () {
        String s = "";
        for (int i = 0; i<cells.length; i++) {
            for (int j = 0; j<cells[0].length; j++) {
                if (i == this.p.getX() && j == this.p.getY()) {s = s + "p";}
                else if (cells[i][j] == null) {s = s + "+";}
                else if (cells[i][j].getType() == (Cell.Type.WALL)) { s= s+"o"; }
                else if (cells[i][j].getType() == (Cell.Type.START)) {s = s + "s";}
                else if (cells[i][j].getType() == (Cell.Type.END)) {s = s + "e";}
                else s = s + "-";
            }
            s = s + "\n";
        }
        return s;
    }
*/


}
