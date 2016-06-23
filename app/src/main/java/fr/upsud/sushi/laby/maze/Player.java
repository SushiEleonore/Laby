package fr.upsud.sushi.laby.maze;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.utils.Dir;
import fr.upsud.sushi.laby.utils.Sens;
import fr.upsud.sushi.laby.exceptions.wallCollisionException;


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
    private Bitmap skin_g;
    private Bitmap skin_d;
    private Bitmap skin_face;
    private Bitmap skin_dos;
    private boolean hasChili;


    public Player(int x, int y, Dir d, Level l) { //prend un level
        this.dir=d;
        this.x = x;
        this.y = y;
        this.l = l;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources res = l.getContext().getResources();
        this.skin_g = BitmapFactory.decodeResource(res, R.drawable.mv_bebezilla_g, options);
        this.skin_d = BitmapFactory.decodeResource(res, R.drawable.mv_bebezilla_d, options);
        this.skin_dos = BitmapFactory.decodeResource(res, R.drawable.mv_bebezilla_dos, options);
        this.skin_face = BitmapFactory.decodeResource(res, R.drawable.mv_bebezilla_face, options);
        this.hasChili=false;
    }

    public Player(Player p) {
        this.dir=p.getDir();
        this.x = p.getX();
        this.y = p.getY();
        this.l = p.getLevel();
        this.hasChili=p.hasChili();
        this.skin_g = p.getSkin_g();
        this.skin_d = p.getSkin_d();
        this.skin_dos = p.getSkin_dos();
        this.skin_face = p.getSkin_face();
    }

    public int getX() { return this.x;}
    public int getY() { return this.y;}
    public boolean hasChili(){return this.hasChili;}
    public Level getLevel(){ return this.l;}

    public Bitmap getSkin_g(){return this.skin_g;}
    public Bitmap getSkin_d(){return this.skin_d;}
    public Bitmap getSkin_dos(){return this.skin_dos;}
    public Bitmap getSkin_face(){return this.skin_face;}

    public void setItem(){
        this.hasChili=true;
    }
    public void setX(int pX) { this.x = pX;}
    public void setY(int pY) { this.y = pY;}
    public void setDir(Dir d) { this.dir = d;}
    public Dir getDir() { return this.dir;}

    public Player copy() {
        return new Player(this);
    }

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
        BreakableWall b = l.getbWall();
        if(!(b!=null&&b.playerFacing())) {

            if (this.dir == (Dir.E)) {
                if (l.isWall(this.x + 1, this.y)) {
                    throw new wallCollisionException();
                } else {
                    this.x += 1;
                }
            } else if (this.dir == (Dir.W)) {
                if (l.isWall(this.x - 1, this.y)) {
                    throw new wallCollisionException();
                } else {
                    this.x -= 1;
                }
            } else if (this.dir == (Dir.S)) {
                if (l.isWall(this.x, this.y + 1)) {
                    throw new wallCollisionException();
                } else {
                    this.y += 1;
                }
            } else {
                if (l.isWall(this.x, this.y - 1)) {
                    throw new wallCollisionException();
                } else {
                    this.y -= 1;
                }
            }
        }
    }

    //Checks if the player is facing a wall.
    public boolean facingWall () {
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
    public boolean wallOnTheR () {
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
    public boolean wallOnTheL () {
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

    public boolean levelFinished(){
        return (this.x == this.l.getXend() && this.y == this.l.getYend());
    }

}
