package fr.upsud.sushi.laby.maze;

import fr.upsud.sushi.laby.calculus.Dir;
import fr.upsud.sushi.laby.calculus.Sens;
import fr.upsud.sushi.laby.exceptions.wallCollisionException;

/**
 * Created by proval on 5/23/16.
 */
public class Player {

    //Should be equal to the wall size
    //public final float step = 10;


    //Position of the player.
    //Top left corresponds to (0,0)
    //(0;0) --->
    //      |
    //      v
    private int x;
    private int y;

    //The direction faced by the player
    private Dir dir;
    private Level l;


    public Player(int x, int y, Dir d, Level l) { //prend un level
        this.dir=d;
        this.x = x;
        this.y = y;
        this.l = l;
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}

    public void setX(int pX) { this.x = pX;}
    public void setY(int pY) { this.y = pY;}
    public void setDir(Dir d) { this.dir = d;}
    public Dir getDir() { return this.dir;}

    //Changes the direction of the player
    //!!!!!!!changed it
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
    public void move (Level l) throws wallCollisionException{
        if (this.dir == (Dir.E)) {
            if (l.isWall(this.x+1, this.y)){
                throw new wallCollisionException();
            } else { this.x+=1;}
        } else if (this.dir == (Dir.W)) {
            if (l.isWall(this.x-1, this.y)){
                throw new wallCollisionException();
            } else {
                this.x-=1;}
        } else if (this.dir == (Dir.S)){
            if (l.isWall(this.x, this.y+1)){
                throw new wallCollisionException();
            } else {
                this.y+=1;}
        } else {
            if (l.isWall(this.x, this.y-1)){
                throw new wallCollisionException();
            } else {
                this.y-=1;}
        }
    }

    //Checks if the player is facing a wall.
    public boolean facingWall (Level l) {
        System.out.println("Checking front");
        if (this.dir == (Dir.E)) {
            System.out.println("Facing east");
            return (l.isWall(this.x+1, this.y));
        } else if (this.dir == (Dir.W)) {
            System.out.println("Facing west");
            return (l.isWall(this.x-1, this.y));
        } else if (this.dir == (Dir.S)){
            System.out.println("Facing south");
            return (l.isWall(this.x, this.y+1));
        } else {
            System.out.println("Facing north");
            return (l.isWall(this.x, this.y-1));
        }
    }

    //Checks if there is a wall on the right
    public boolean wallOnTheR (Level l) {
        System.out.println("Checking right");
        if (this.dir == (Dir.E)) {
            return (l.isWall(this.x, this.y+1));
        } else if (this.dir == (Dir.W)) {
            return (l.isWall(this.x, this.y-1));
        } else if (this.dir == (Dir.S)){
            return (l.isWall(this.x-1, this.y));
        } else {
            return (l.isWall(this.x+1, this.y));
        }
    }

    //Checks if there is a wall on the left
    public boolean wallOnTheL (Level l) {
        System.out.println("Checking left");
        if (this.dir == (Dir.E)) {
            return (l.isWall(this.x, this.y-1));
        } else if (this.dir == (Dir.W)) {
            return (l.isWall(this.x, this.y+1));
        } else if (this.dir == (Dir.S)){
            return (l.isWall(this.x+1, this.y));
        } else {
            return (l.isWall(this.x-1, this.y));
        }
    }

}
