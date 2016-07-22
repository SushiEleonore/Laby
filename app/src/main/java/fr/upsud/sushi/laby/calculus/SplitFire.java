package fr.upsud.sushi.laby.calculus;

import android.os.Looper;
import android.widget.Toast;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Pair;

public class SplitFire  implements Instr {
    private Level l;

    private String id;

    public SplitFire(Level l, String id) {
        this.l = l;
        this.id=id;
    }


    public Pair<String, ListInstr> next(){
        if( this.l.getbWall().playerFacing() && this.l.getPlayer().hasChili()) l.getbWall().setState(false);
        else {
            Looper.prepare();
        }

        return new Pair(this.id,new ListInstr());
    }
}
