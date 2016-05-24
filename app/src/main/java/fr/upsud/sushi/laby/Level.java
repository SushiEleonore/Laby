package fr.upsud.sushi.laby;

import java.util.ArrayList;

/**
 * Created by proval on 5/23/16.
 */
public class Level {
    //private ArrayList<Cell> cells;
    private Cell[][] cells;
    //Player ?
    private Player p;


    //Generates a maze and a player depending on the level.
    //Replaced the arraylist by an array
    public Level (int lvl) {
        if (lvl == 1) {
            this.p = new Player(2, 2, Player.Dir.S);
            //this.cells = new ArrayList<Cell>();
            cells = new Cell[20][14];

            cells[0][0] = new Cell(Cell.Type.WALL, 0, 0);
            cells[0][1] = new Cell(Cell.Type.WALL, 0, 1);
            cells[0][2] = new Cell(Cell.Type.WALL, 0, 2);
            cells[0][3] = new Cell(Cell.Type.WALL, 0, 3);
            cells[0][4] = new Cell(Cell.Type.WALL, 0, 4);
            cells[0][5] = new Cell(Cell.Type.WALL, 0, 5);
            cells[0][6] = new Cell(Cell.Type.WALL, 0, 6);
            cells[0][7] = new Cell(Cell.Type.WALL, 0, 7);
            cells[0][8] = new Cell(Cell.Type.WALL, 0, 8);
            cells[0][9] = new Cell(Cell.Type.WALL, 0, 9);
            cells[0][10] = new Cell(Cell.Type.WALL, 0, 10);
            cells[0][11] = new Cell(Cell.Type.WALL, 0, 11);
            cells[0][12] = new Cell(Cell.Type.WALL, 0, 12);
            cells[0][13] = new Cell(Cell.Type.WALL, 0, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 0, 0));
            cells.add(new Cell(Cell.Type.WALL, 0, 1));
            cells.add(new Cell(Cell.Type.WALL, 0, 2));
            cells.add(new Cell(Cell.Type.WALL, 0, 3));
            cells.add(new Cell(Cell.Type.WALL, 0, 4));
            cells.add(new Cell(Cell.Type.WALL, 0, 5));
            cells.add(new Cell(Cell.Type.WALL, 0, 6));
            cells.add(new Cell(Cell.Type.WALL, 0, 7));
            cells.add(new Cell(Cell.Type.WALL, 0, 8));
            cells.add(new Cell(Cell.Type.WALL, 0, 9));
            cells.add(new Cell(Cell.Type.WALL, 0, 10));
            cells.add(new Cell(Cell.Type.WALL, 0, 11));
            cells.add(new Cell(Cell.Type.WALL, 0, 12));
            cells.add(new Cell(Cell.Type.WALL, 0, 13));*/

            cells[1][0] = new Cell(Cell.Type.WALL, 1, 0);
            cells[1][13] = new Cell(Cell.Type.WALL, 1, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 1, 0));
            cells.add(new Cell(Cell.Type.WALL, 1, 13));*/

            cells[2][0] = new Cell(Cell.Type.WALL, 2, 0);
            cells[2][13] = new Cell(Cell.Type.WALL, 2, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 2, 0));
            cells.add(new Cell(Cell.Type.WALL, 2, 13));*/

            cells[3][0] = new Cell(Cell.Type.WALL, 2, 0);
            cells[3][13] = new Cell(Cell.Type.WALL, 2, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 3, 0));
            cells.add(new Cell(Cell.Type.WALL, 3, 13));*/

            cells[4][0] = new Cell(Cell.Type.WALL, 4, 0);
            cells[4][1] = new Cell(Cell.Type.WALL, 4, 1);
            cells[4][2] = new Cell(Cell.Type.WALL, 4, 2);
            cells[4][3] = new Cell(Cell.Type.WALL, 4, 3);
            cells[4][4] = new Cell(Cell.Type.WALL, 4, 4);
            cells[4][5] = new Cell(Cell.Type.WALL, 4, 5);
            cells[4][6] = new Cell(Cell.Type.WALL, 4, 6);
            cells[4][7] = new Cell(Cell.Type.WALL, 4, 7);
            cells[4][8] = new Cell(Cell.Type.WALL, 4, 8);
            cells[4][9] = new Cell(Cell.Type.WALL, 4, 9);
            cells[4][10] = new Cell(Cell.Type.WALL, 4, 10);
            cells[4][13] = new Cell(Cell.Type.WALL, 4, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 4, 0));
            cells.add(new Cell(Cell.Type.WALL, 4, 1));
            cells.add(new Cell(Cell.Type.WALL, 4, 2));
            cells.add(new Cell(Cell.Type.WALL, 4, 3));
            cells.add(new Cell(Cell.Type.WALL, 4, 4));
            cells.add(new Cell(Cell.Type.WALL, 4, 5));
            cells.add(new Cell(Cell.Type.WALL, 4, 6));
            cells.add(new Cell(Cell.Type.WALL, 4, 7));
            cells.add(new Cell(Cell.Type.WALL, 4, 8));
            cells.add(new Cell(Cell.Type.WALL, 4, 9));
            cells.add(new Cell(Cell.Type.WALL, 4, 10));
            cells.add(new Cell(Cell.Type.WALL, 4, 13));*/

            cells[5][0] = new Cell(Cell.Type.WALL, 5, 0);
            cells[5][13] = new Cell(Cell.Type.WALL, 5, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 5, 0));
            cells.add(new Cell(Cell.Type.WALL, 5, 13));*/

            cells[6][0] = new Cell(Cell.Type.WALL, 6, 0);
            cells[6][13] = new Cell(Cell.Type.WALL, 6, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 6, 0));
            cells.add(new Cell(Cell.Type.WALL, 6, 13));*/

            cells[7][0] = new Cell(Cell.Type.WALL, 7, 0);
            cells[7][1] = new Cell(Cell.Type.WALL, 7, 1);
            cells[7][2] = new Cell(Cell.Type.WALL, 7, 2);
            cells[7][5] = new Cell(Cell.Type.WALL, 7, 5);
            cells[7][6] = new Cell(Cell.Type.WALL, 7, 6);
            cells[7][9] = new Cell(Cell.Type.WALL, 7, 9);
            cells[7][10] = new Cell(Cell.Type.WALL, 7, 10);
            cells[7][11] = new Cell(Cell.Type.WALL, 7, 11);
            cells[7][12] = new Cell(Cell.Type.WALL, 7, 12);
            cells[7][13] = new Cell(Cell.Type.WALL, 7, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 7, 0));
            cells.add(new Cell(Cell.Type.WALL, 7, 1));
            cells.add(new Cell(Cell.Type.WALL, 7, 2));
            cells.add(new Cell(Cell.Type.WALL, 7, 5));
            cells.add(new Cell(Cell.Type.WALL, 7, 6));
            cells.add(new Cell(Cell.Type.WALL, 7, 9));
            cells.add(new Cell(Cell.Type.WALL, 7, 10));
            cells.add(new Cell(Cell.Type.WALL, 7, 11));
            cells.add(new Cell(Cell.Type.WALL, 7, 12));
            cells.add(new Cell(Cell.Type.WALL, 7, 13));*/

            cells[8][0] = new Cell(Cell.Type.WALL, 8, 0);
            cells[8][5] = new Cell(Cell.Type.WALL, 8, 5);
            cells[8][9] = new Cell(Cell.Type.WALL, 8, 9);
            cells[8][13] = new Cell(Cell.Type.WALL, 8, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 8, 0));
            cells.add(new Cell(Cell.Type.WALL, 8, 5));
            cells.add(new Cell(Cell.Type.WALL, 8, 9));
            cells.add(new Cell(Cell.Type.WALL, 8, 13));*/

            cells[9][0] = new Cell(Cell.Type.WALL, 9, 0);
            cells[9][5] = new Cell(Cell.Type.WALL, 9, 5);
            cells[9][9] = new Cell(Cell.Type.WALL, 9, 9);
            cells[9][13] = new Cell(Cell.Type.WALL, 9, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 9, 0));
            cells.add(new Cell(Cell.Type.WALL, 9, 5));
            cells.add(new Cell(Cell.Type.WALL, 9, 9));
            cells.add(new Cell(Cell.Type.WALL, 9, 13));*/

            cells[10][0] = new Cell(Cell.Type.WALL, 10, 0);
            cells[10][5] = new Cell(Cell.Type.WALL, 10, 5);
            cells[10][9] = new Cell(Cell.Type.WALL, 10, 9);
            cells[10][13] = new Cell(Cell.Type.WALL, 10, 13);

            /*
            cells.add(new Cell(Cell.Type.WALL, 10, 0));
            cells.add(new Cell(Cell.Type.WALL, 10, 5));
            cells.add(new Cell(Cell.Type.WALL, 10, 9));
            cells.add(new Cell(Cell.Type.WALL, 10, 13));
            */

            cells[11][0] = new Cell(Cell.Type.WALL, 11, 0);
            cells[11][5] = new Cell(Cell.Type.WALL, 11, 5);
            cells[11][9] = new Cell(Cell.Type.WALL, 11, 9);
            cells[11][13] = new Cell(Cell.Type.WALL, 11, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 11, 0));
            cells.add(new Cell(Cell.Type.WALL, 11, 5));
            cells.add(new Cell(Cell.Type.WALL, 11, 9));
            cells.add(new Cell(Cell.Type.WALL, 11, 13));*/

            cells[12][0] = new Cell(Cell.Type.WALL, 12, 0);
            cells[12][1] = new Cell(Cell.Type.WALL, 12, 1);
            cells[12][2] = new Cell(Cell.Type.WALL, 12, 2);
            cells[12][3] = new Cell(Cell.Type.WALL, 12, 3);
            cells[12][4] = new Cell(Cell.Type.WALL, 12, 4);
            cells[12][5] = new Cell(Cell.Type.WALL, 12, 5);
            cells[12][8] = new Cell(Cell.Type.WALL, 12, 8);
            cells[12][9] = new Cell(Cell.Type.WALL, 12, 9);
            cells[12][13] = new Cell(Cell.Type.WALL, 12, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 12, 0));
            cells.add(new Cell(Cell.Type.WALL, 12, 1));
            cells.add(new Cell(Cell.Type.WALL, 12, 2));
            cells.add(new Cell(Cell.Type.WALL, 12, 3));
            cells.add(new Cell(Cell.Type.WALL, 12, 4));
            cells.add(new Cell(Cell.Type.WALL, 12, 5));
            cells.add(new Cell(Cell.Type.WALL, 12, 8));
            cells.add(new Cell(Cell.Type.WALL, 12, 9));
            cells.add(new Cell(Cell.Type.WALL, 12, 13));*/

            cells[13][0] = new Cell(Cell.Type.WALL, 13, 0);
            cells[13][8] = new Cell(Cell.Type.WALL, 13, 8);
            cells[13][13] = new Cell(Cell.Type.WALL, 13, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 13, 0));
            cells.add(new Cell(Cell.Type.WALL, 13, 8));
            cells.add(new Cell(Cell.Type.WALL, 13, 13));*/

            cells[14][0] = new Cell(Cell.Type.WALL, 14, 0);
            cells[14][8] = new Cell(Cell.Type.WALL, 14, 8);
            cells[14][13] = new Cell(Cell.Type.WALL, 14, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 14, 0));
            cells.add(new Cell(Cell.Type.WALL, 14, 8));
            cells.add(new Cell(Cell.Type.WALL, 14, 13));*/

            cells[15][0] = new Cell(Cell.Type.WALL, 15, 0);
            cells[15][8] = new Cell(Cell.Type.WALL, 15, 8);
            cells[15][13] = new Cell(Cell.Type.WALL, 15, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 15, 0));
            cells.add(new Cell(Cell.Type.WALL, 15, 8));
            cells.add(new Cell(Cell.Type.WALL, 15, 13));*/

            cells[16][0] = new Cell(Cell.Type.WALL, 16, 0);
            cells[16][4] = new Cell(Cell.Type.WALL, 16, 4);
            cells[16][5] = new Cell(Cell.Type.WALL, 16, 5);
            cells[16][6] = new Cell(Cell.Type.WALL, 16, 6);
            cells[16][7] = new Cell(Cell.Type.WALL, 16, 7);
            cells[16][8] = new Cell(Cell.Type.WALL, 16, 8);
            cells[16][9] = new Cell(Cell.Type.WALL, 16, 9);
            cells[16][13] = new Cell(Cell.Type.WALL, 16,13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 16, 0));
            cells.add(new Cell(Cell.Type.WALL, 16, 4));
            cells.add(new Cell(Cell.Type.WALL, 16, 5));
            cells.add(new Cell(Cell.Type.WALL, 16, 6));
            cells.add(new Cell(Cell.Type.WALL, 16, 7));
            cells.add(new Cell(Cell.Type.WALL, 16, 8));
            cells.add(new Cell(Cell.Type.WALL, 16, 9));
            cells.add(new Cell(Cell.Type.WALL, 16, 13));*/

            cells[17][0] = new Cell(Cell.Type.WALL, 17, 0);
            cells[17][13] = new Cell(Cell.Type.WALL, 17, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 17, 0));
            cells.add(new Cell(Cell.Type.WALL, 17, 13));*/

            cells[18][0] = new Cell(Cell.Type.WALL, 18, 0);
            cells[18][13] = new Cell(Cell.Type.WALL, 18, 13);
            /*
            cells.add(new Cell(Cell.Type.WALL, 18, 0));
            cells.add(new Cell(Cell.Type.WALL, 18, 13));*/

            cells[19][0] = new Cell(Cell.Type.WALL, 19, 0);
            cells[19][1] = new Cell(Cell.Type.WALL, 19, 1);
            cells[19][2] = new Cell(Cell.Type.WALL, 19, 2);
            cells[19][3] = new Cell(Cell.Type.WALL, 19, 3);
            cells[19][4] = new Cell(Cell.Type.WALL, 19, 4);
            cells[19][5] = new Cell(Cell.Type.WALL, 19, 5);
            cells[19][6] = new Cell(Cell.Type.WALL, 19, 6);
            cells[19][7] = new Cell(Cell.Type.WALL, 19, 7);
            cells[19][8] = new Cell(Cell.Type.WALL, 19, 8);
            cells[19][9] = new Cell(Cell.Type.WALL, 19, 9);
            cells[19][10] = new Cell(Cell.Type.WALL, 19, 10);
            cells[19][11] = new Cell(Cell.Type.WALL, 19, 11);
            cells[19][12] = new Cell(Cell.Type.WALL, 19, 12);
            cells[19][13] = new Cell(Cell.Type.WALL, 19, 13);

            /*
            cells.add(new Cell(Cell.Type.WALL, 19, 0));
            cells.add(new Cell(Cell.Type.WALL, 19, 1));
            cells.add(new Cell(Cell.Type.WALL, 19, 2));
            cells.add(new Cell(Cell.Type.WALL, 19, 3));
            cells.add(new Cell(Cell.Type.WALL, 19, 4));
            cells.add(new Cell(Cell.Type.WALL, 19, 5));
            cells.add(new Cell(Cell.Type.WALL, 19, 6));
            cells.add(new Cell(Cell.Type.WALL, 19, 7));
            cells.add(new Cell(Cell.Type.WALL, 19, 8));
            cells.add(new Cell(Cell.Type.WALL, 19, 9));
            cells.add(new Cell(Cell.Type.WALL, 19, 10));
            cells.add(new Cell(Cell.Type.WALL, 19, 11));
            cells.add(new Cell(Cell.Type.WALL, 19, 12));
            cells.add(new Cell(Cell.Type.WALL, 19, 13));*/

            cells[2][2] = new Cell(Cell.Type.START, 2, 2);
            cells[8][11] = new Cell(Cell.Type.END, 8, 11);

            /*cells.add(new Cell(Cell.Type.START, 2, 2));
            cells.add(new Cell(Cell.Type.END, 8, 11));*/
        }
    }

    public Player getPlayer() { return this.p;}

    public Cell[][] getCells() {return this.cells;}

    //Checks if there's a wall at [i][j]
    public boolean isWall(int i, int j) {
        if (cells[i][j].getType() == (Cell.Type.WALL)) { return true;}
        else {return false;}
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


}
