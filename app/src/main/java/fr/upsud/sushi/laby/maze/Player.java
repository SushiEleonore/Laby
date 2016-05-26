package fr.upsud.sushi.laby.maze;

import fr.upsud.sushi.laby.calculus.Dir;
import fr.upsud.sushi.laby.calculus.Sens;

/**
 * Created by proval on 5/23/16.
 */
public class Player {

    //Should be equal to the wall size
    //public final float step = 10;

    //TODO : change the coordinates so the player moves the right way

    //Position of the player.
    //Top left corresponds to (0,0)
    //(0;0) --->
    //      |
    //      v
    private int x;
    private int y;

    //The direction faced by the player
    private Dir dir;



    public Player(int x, int y, Dir d) {
        this.dir=d;
        this.x = x;
        this.y = y;
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    //Changes the direction of the player
    public void rotate(Sens s){
        if (s == (Sens.L) && this.dir == (Dir.E)
                || s == (Sens.R) && this.dir == (Dir.W)) { this.dir = Dir.N;}
        else if (s == (Sens.L) && this.dir == (Dir.N)
                || s == (Sens.R) && this.dir == (Dir.S)) { this.dir = Dir.W;}
        else if (s == (Sens.L) && this.dir == (Dir.W)
                || s == (Sens.R) && this.dir == (Dir.E)) { this.dir = Dir.S;}
        else { this.dir = Dir.E;}
    }


    //The player moves forward by one step.  checks
    //if there's a wall
    public void move (Level l){
        if (this.dir == (Dir.E)) {
            if (!l.isWall(this.x+1, this.y)) this.x+=1;
        } else if (this.dir == (Dir.W)) {
            if (!l.isWall(this.x-1, this.y)) this.x-=1;
        } else if (this.dir == (Dir.S)){
            if (!l.isWall(this.x, this.y-1)) this.y-=1;
        } else {
            if (!l.isWall(this.x, this.y+1)) this.y+=1;
        }
    }

    //Checks if the player is facing a wall.
    public boolean facingWall (Level l) {
        if (this.dir == (Dir.E)) {
            return (l.isWall(this.x+1, this.y));
        } else if (this.dir == (Dir.W)) {
            return (l.isWall(this.x-1, this.y));
        } else if (this.dir == (Dir.S)){
            return (l.isWall(this.x, this.y-1));
        } else {
            return (l.isWall(this.x, this.y+1));
        }
    }

    //Checks if there is a wall on the right
    public boolean wallOnTheR (Level l) {
        if (this.dir == (Dir.E)) {
            return (l.isWall(this.x, this.y-1));
        } else if (this.dir == (Dir.W)) {
            return (l.isWall(this.x, this.y+1));
        } else if (this.dir == (Dir.S)){
            return (l.isWall(this.x-1, this.y));
        } else {
            return (l.isWall(this.x+1, this.y));
        }
    }

    //Checks if there is a wall on the left
    public boolean wallOnTheL (Level l) {
        if (this.dir == (Dir.E)) {
            return (l.isWall(this.x, this.y+1));
        } else if (this.dir == (Dir.W)) {
            return (l.isWall(this.x, this.y-1));
        } else if (this.dir == (Dir.S)){
            return (l.isWall(this.x+1, this.y));
        } else {
            return (l.isWall(this.x-1, this.y));
        }
    }

}
