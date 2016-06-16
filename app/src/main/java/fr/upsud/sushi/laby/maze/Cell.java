package fr.upsud.sushi.laby.maze;

/**
 * Created by proval on 5/23/16.
 */
public class Cell {

    public enum  Type { WALL, END, PATH };
    private int SIZE = 10;

    private Type type;

    public Cell(Type t) {
        this.type = t;
    }

    public Type getType() { return this.type;}

}
