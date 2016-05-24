package fr.upsud.sushi.laby;

/**
 * Created by proval on 5/23/16.
 */
public class Cell {

    public enum  Type { WALL, START, END };
    private float SIZE = 10;

    private int x;
    private int y;

    private Type type;

    public Cell(Type t, int posx, int posy) {
        this.type = t;
        this.x = posx;
        this.y = posy;
    }

    public Type getType() { return this.type;}



}
