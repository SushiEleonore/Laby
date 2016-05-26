package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 5/26/16.
 */
public class CheckIfEnd extends Bool {
    Level l;
    public CheckIfEnd(Level l){
        this.l=l;
    }
    @Override
    public boolean  eval() {
        int px = l.getPlayer().getX();
        int py = l.getPlayer().getY();
        return ((l.getXend()==px) && (l.getYend()==py));
    }
}
