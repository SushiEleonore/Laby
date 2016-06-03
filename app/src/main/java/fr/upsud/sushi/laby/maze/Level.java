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
    * x\n
    * y\n
    *
    * w : wall
    * u : empty
    * s : start
    * e : end
    *
    *
    * */
    private String lv1 = "5\n10\n2\n2\nN\n\nwwwww\nwuuuw\nwusuw\nwwwww\nwwwww\nwwwww\nwwwww\nwwwww\nwwwww\nwwwww\n";


    //Generates a maze and a player depending on the level.
    //Replaced the arraylist by an array
    public Level (int lvl) {
        if (lvl == -1) {
            this.lvl = lvl;
            this.startDir = Dir.S;
            this.p = new Player(2, 2, this.startDir, this);

            cells = new Cell[20][14];

            this.xStart = 2;
            this.yStart = 2;
            this.xEnd = 8;
            this.yEnd = 11;

            cells[0][0] = new Cell(Cell.Type.WALL);
            cells[0][1] = new Cell(Cell.Type.WALL);
            cells[0][2] = new Cell(Cell.Type.WALL);
            cells[0][3] = new Cell(Cell.Type.WALL);
            cells[0][4] = new Cell(Cell.Type.WALL);
            cells[0][5] = new Cell(Cell.Type.WALL);
            cells[0][6] = new Cell(Cell.Type.WALL);
            cells[0][7] = new Cell(Cell.Type.WALL);
            cells[0][8] = new Cell(Cell.Type.WALL);
            cells[0][9] = new Cell(Cell.Type.WALL);
            cells[0][10] = new Cell(Cell.Type.WALL);
            cells[0][11] = new Cell(Cell.Type.WALL);
            cells[0][12] = new Cell(Cell.Type.WALL);
            cells[0][13] = new Cell(Cell.Type.WALL);

            cells[1][0] = new Cell(Cell.Type.WALL);
            cells[1][13] = new Cell(Cell.Type.WALL);

            cells[2][0] = new Cell(Cell.Type.WALL);
            cells[2][13] = new Cell(Cell.Type.WALL);

            cells[3][0] = new Cell(Cell.Type.WALL);
            cells[3][13] = new Cell(Cell.Type.WALL);

            cells[4][0] = new Cell(Cell.Type.WALL);
            cells[4][1] = new Cell(Cell.Type.WALL);
            cells[4][2] = new Cell(Cell.Type.WALL);
            cells[4][3] = new Cell(Cell.Type.WALL);
            cells[4][4] = new Cell(Cell.Type.WALL);
            cells[4][5] = new Cell(Cell.Type.WALL);
            cells[4][6] = new Cell(Cell.Type.WALL);
            cells[4][7] = new Cell(Cell.Type.WALL);
            cells[4][8] = new Cell(Cell.Type.WALL);
            cells[4][9] = new Cell(Cell.Type.WALL);
            cells[4][10] = new Cell(Cell.Type.WALL);
            cells[4][13] = new Cell(Cell.Type.WALL);

            cells[5][0] = new Cell(Cell.Type.WALL);
            cells[5][13] = new Cell(Cell.Type.WALL);

            cells[6][0] = new Cell(Cell.Type.WALL);
            cells[6][13] = new Cell(Cell.Type.WALL);

            cells[7][0] = new Cell(Cell.Type.WALL);
            cells[7][1] = new Cell(Cell.Type.WALL);
            cells[7][2] = new Cell(Cell.Type.WALL);
            cells[7][5] = new Cell(Cell.Type.WALL);
            cells[7][6] = new Cell(Cell.Type.WALL);
            cells[7][9] = new Cell(Cell.Type.WALL);
            cells[7][10] = new Cell(Cell.Type.WALL);
            cells[7][11] = new Cell(Cell.Type.WALL);
            cells[7][12] = new Cell(Cell.Type.WALL);
            cells[7][13] = new Cell(Cell.Type.WALL);

            cells[8][0] = new Cell(Cell.Type.WALL);
            cells[8][5] = new Cell(Cell.Type.WALL);
            cells[8][9] = new Cell(Cell.Type.WALL);
            cells[8][13] = new Cell(Cell.Type.WALL);

            cells[9][0] = new Cell(Cell.Type.WALL);
            cells[9][5] = new Cell(Cell.Type.WALL);
            cells[9][9] = new Cell(Cell.Type.WALL);
            cells[9][13] = new Cell(Cell.Type.WALL);

            cells[10][0] = new Cell(Cell.Type.WALL);
            cells[10][5] = new Cell(Cell.Type.WALL);
            cells[10][9] = new Cell(Cell.Type.WALL);
            cells[10][13] = new Cell(Cell.Type.WALL);

            cells[11][0] = new Cell(Cell.Type.WALL);
            cells[11][5] = new Cell(Cell.Type.WALL);
            cells[11][9] = new Cell(Cell.Type.WALL);
            cells[11][13] = new Cell(Cell.Type.WALL);

            cells[12][0] = new Cell(Cell.Type.WALL);
            cells[12][1] = new Cell(Cell.Type.WALL);
            cells[12][2] = new Cell(Cell.Type.WALL);
            cells[12][3] = new Cell(Cell.Type.WALL);
            cells[12][4] = new Cell(Cell.Type.WALL);
            cells[12][5] = new Cell(Cell.Type.WALL);
            cells[12][8] = new Cell(Cell.Type.WALL);
            cells[12][9] = new Cell(Cell.Type.WALL);
            cells[12][13] = new Cell(Cell.Type.WALL);

            cells[13][0] = new Cell(Cell.Type.WALL);
            cells[13][8] = new Cell(Cell.Type.WALL);
            cells[13][13] = new Cell(Cell.Type.WALL);

            cells[14][0] = new Cell(Cell.Type.WALL);
            cells[14][8] = new Cell(Cell.Type.WALL);
            cells[14][13] = new Cell(Cell.Type.WALL);

            cells[15][0] = new Cell(Cell.Type.WALL);
            cells[15][8] = new Cell(Cell.Type.WALL);
            cells[15][13] = new Cell(Cell.Type.WALL);

            cells[16][0] = new Cell(Cell.Type.WALL);
            cells[16][4] = new Cell(Cell.Type.WALL);
            cells[16][5] = new Cell(Cell.Type.WALL);
            cells[16][6] = new Cell(Cell.Type.WALL);
            cells[16][7] = new Cell(Cell.Type.WALL);
            cells[16][8] = new Cell(Cell.Type.WALL);
            cells[16][9] = new Cell(Cell.Type.WALL);
            cells[16][13] = new Cell(Cell.Type.WALL);

            cells[17][0] = new Cell(Cell.Type.WALL);
            cells[17][13] = new Cell(Cell.Type.WALL);

            cells[18][0] = new Cell(Cell.Type.WALL);
            cells[18][13] = new Cell(Cell.Type.WALL);

            cells[19][0] = new Cell(Cell.Type.WALL);
            cells[19][1] = new Cell(Cell.Type.WALL);
            cells[19][2] = new Cell(Cell.Type.WALL);
            cells[19][3] = new Cell(Cell.Type.WALL);
            cells[19][4] = new Cell(Cell.Type.WALL);
            cells[19][5] = new Cell(Cell.Type.WALL);
            cells[19][6] = new Cell(Cell.Type.WALL);
            cells[19][7] = new Cell(Cell.Type.WALL);
            cells[19][8] = new Cell(Cell.Type.WALL);
            cells[19][9] = new Cell(Cell.Type.WALL);
            cells[19][10] = new Cell(Cell.Type.WALL);
            cells[19][11] = new Cell(Cell.Type.WALL);
            cells[19][12] = new Cell(Cell.Type.WALL);
            cells[19][13] = new Cell(Cell.Type.WALL);

            cells[2][2] = new Cell(Cell.Type.START);
            cells[8][11] = new Cell(Cell.Type.END);

        } else if (lvl == 0) {
            this.lvl = lvl;
            this.startDir = Dir.S;
            this.p = new Player(2, 2, this.startDir, this);
            //this.cells = new ArrayList<Cell>();
            cells = new Cell[5][10];

            this.xStart = 2;
            this.yStart = 2;
            this.xEnd = 2;
            this.yEnd = 7;

            cells[2][2] = new Cell(Cell.Type.START);
            cells[2][7] = new Cell(Cell.Type.END);
            cells[0][0] = new Cell(Cell.Type.WALL);
            cells[1][0] = new Cell(Cell.Type.WALL);
            cells[2][0] = new Cell(Cell.Type.WALL);
            cells[3][0] = new Cell(Cell.Type.WALL);
            cells[4][0] = new Cell(Cell.Type.WALL);

            cells[0][9] = new Cell(Cell.Type.WALL);
            cells[1][9] = new Cell(Cell.Type.WALL);
            cells[2][9] = new Cell(Cell.Type.WALL);
            cells[3][9] = new Cell(Cell.Type.WALL);
            cells[4][9] = new Cell(Cell.Type.WALL);

            cells[0][1] = new Cell(Cell.Type.WALL);
            cells[0][2] = new Cell(Cell.Type.WALL);
            cells[0][3] = new Cell(Cell.Type.WALL);
            cells[0][4] = new Cell(Cell.Type.WALL);
            cells[0][5] = new Cell(Cell.Type.WALL);
            cells[0][6] = new Cell(Cell.Type.WALL);
            cells[0][7] = new Cell(Cell.Type.WALL);
            cells[0][8] = new Cell(Cell.Type.WALL);
            cells[0][9] = new Cell(Cell.Type.WALL);

            cells[4][1] = new Cell(Cell.Type.WALL);
            cells[4][2] = new Cell(Cell.Type.WALL);
            cells[4][3] = new Cell(Cell.Type.WALL);
            cells[4][4] = new Cell(Cell.Type.WALL);
            cells[4][5] = new Cell(Cell.Type.WALL);
            cells[4][6] = new Cell(Cell.Type.WALL);
            cells[4][7] = new Cell(Cell.Type.WALL);
            cells[4][8] = new Cell(Cell.Type.WALL);
            cells[4][9] = new Cell(Cell.Type.WALL);

        } else if (lvl == 1) {
            this.lvl = lvl;
            this.startDir = Dir.S;
            this.p = new Player(2, 2, this.startDir, this);
            //this.cells = new ArrayList<Cell>();
            cells = new Cell[5][10];

            this.xStart = 2;
            this.yStart = 2;
            this.xEnd = 2;
            this.yEnd = 7;

            cells[2][2] = new Cell(Cell.Type.START);
            cells[2][7] = new Cell(Cell.Type.END);
            cells[0][0] = new Cell(Cell.Type.WALL);
            cells[1][0] = new Cell(Cell.Type.WALL);
            cells[2][0] = new Cell(Cell.Type.WALL);
            cells[3][0] = new Cell(Cell.Type.WALL);
            cells[4][0] = new Cell(Cell.Type.WALL);

            cells[0][9] = new Cell(Cell.Type.WALL);
            cells[1][9] = new Cell(Cell.Type.WALL);
            cells[2][9] = new Cell(Cell.Type.WALL);
            cells[3][9] = new Cell(Cell.Type.WALL);
            cells[1][9] = new Cell(Cell.Type.WALL);
            cells[2][5] = new Cell(Cell.Type.WALL);
            cells[2][5] = new Cell(Cell.Type.WALL);

            cells[0][1] = new Cell(Cell.Type.WALL);
            cells[0][2] = new Cell(Cell.Type.WALL);
            cells[0][3] = new Cell(Cell.Type.WALL);
            cells[0][4] = new Cell(Cell.Type.WALL);
            cells[0][5] = new Cell(Cell.Type.WALL);
            cells[0][6] = new Cell(Cell.Type.WALL);
            cells[0][7] = new Cell(Cell.Type.WALL);
            cells[0][8] = new Cell(Cell.Type.WALL);
            cells[0][9] = new Cell(Cell.Type.WALL);

            cells[4][1] = new Cell(Cell.Type.WALL);
            cells[4][2] = new Cell(Cell.Type.WALL);
            cells[4][3] = new Cell(Cell.Type.WALL);
            cells[4][4] = new Cell(Cell.Type.WALL);
            cells[4][5] = new Cell(Cell.Type.WALL);
            cells[4][6] = new Cell(Cell.Type.WALL);
            cells[4][7] = new Cell(Cell.Type.WALL);
            cells[4][8] = new Cell(Cell.Type.WALL);
            cells[4][9] = new Cell(Cell.Type.WALL);


        }


    }

    //
    public Cell[][] scan(String s) {
//TODO
      return null;
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
