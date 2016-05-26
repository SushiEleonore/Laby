package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.maze.Player;

/**
 * Created by proval on 5/25/16.
 */
public class TurnRL extends Instr {
    private Level l;
    private Sens s;

    public TurnRL(Level l, Sens s) {
        this.l = l;
        this.s = s;
    }
    public TurnRL(Level l, String dir){
        this.l=l;
        if(dir.equals("droite")){
            this.s= Sens.R;
        }
        else{
            this.s=Sens.L;
        }
    }

    @Override
    public void eval (){
        this.l.getPlayer().rotate(this.s);

    }
}
