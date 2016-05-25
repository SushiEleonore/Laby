package fr.upsud.sushi.laby.calculus;


import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 5/24/16.
 */
public class MoveFwd extends Instr {
    private Level l;

    public MoveFwd(Level l) {
        this.l = l;
    }

    @Override
    public void  eval(){ this.l.getPlayer().move(this.l); }

}
