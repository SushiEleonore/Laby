package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Values;


public class CheckIfEnd extends Bool {
    Level l;
    public CheckIfEnd(Level l){
        this.l=l;
    }
    @Override
    public boolean  eval() {
        int px = l.getPlayer().getX();
        int py = l.getPlayer().getY();
        boolean isend = (l.getXend()==px) && (l.getYend()==py);
        if (Values.DEBUG_MODE) {
            System.out.println("Player en x : " + l.getPlayer().getX() + "Player en y : " +
                    l.getPlayer().getY());
            System.out.println(
                    "Fin du niveau x : " + l.getXend() + " Fin du niveau en y : " + l.getYend());
            System.out.println("Detection fin de niveau    " + isend);
        }
        return ((l.getXend()==px) && (l.getYend()==py));
    }
}
