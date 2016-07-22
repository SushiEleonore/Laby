package fr.upsud.sushi.laby.maze;

public class Cell {

    public enum  Type { WALL, END, PATH };
    private Type type;

    public Cell(Type t) {
        this.type = t;
    }

    public Type getType() { return this.type;}

}
