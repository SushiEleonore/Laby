package fr.upsud.sushi.laby.calculus;


import android.os.Looper;
import android.widget.Toast;

import fr.upsud.sushi.laby.exceptions.wallCollisionException;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Pair;


public class MoveFwd implements Instr {
    private Level l;
    private String id;
    public MoveFwd(Level l, String id) {
        this.l = l;
        this.id=id;
    }

    public Pair<String, ListInstr> next(){
        try { this.l.getPlayer().move(this.l);
        } catch (wallCollisionException E) {
            Looper.prepare();
        }
        return new Pair(this.id,new ListInstr());
    }
}
