package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.maze.Player;

/**
 * Created by proval on 5/24/16.
 */
public class CheckIfPath extends Bool {
    private Level l;
    private Sens s;


    //Needs the current level and the direction (right or left)
    //where you want to check if there's a wall
    public CheckIfPath(Level l, Sens s) {
        this.l = l;
        this.s = s;
    }

    public CheckIfPath(Level l, String dir) {
        this.l = l;
        if(dir.equals("à droite")){
            this.s= Sens.R;
        }
        else if (dir.equals("à gauche")){
            this.s=Sens.L;
        }
        else {
            this.s = Sens.F;
        }
    }

    @Override
    public boolean  eval(){
        if (this.s == (Sens.R)) {
            return !this.l.getPlayer().wallOnTheR(this.l);
        }
        if (this.s == (Sens.L)) {
            return !this.l.getPlayer().wallOnTheL(this.l);
        }
        return this.l.getPlayer().facingWall(this.l);
    }



}