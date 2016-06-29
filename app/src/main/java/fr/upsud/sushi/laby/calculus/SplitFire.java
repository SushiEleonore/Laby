package fr.upsud.sushi.laby.calculus;

import android.os.Looper;
import android.widget.Toast;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Pair;

/**
 * Created by proval on 6/23/16.
 */
public class SplitFire  implements Instr {
    private Level l;

    private String id;

    public SplitFire(Level l, String id) {
        this.l = l;
        this.id=id;
    }


    public Pair<String, ListInstr> next(){
        if( this.l.getbWall().playerFacing()) l.getbWall().setState(false);
        else {
            Looper.prepare();
            Toast.makeText(l.getContext(), "Il n'y a rien à faire ici",
                    Toast.LENGTH_SHORT).show();
        }

        return new Pair(this.id,new ListInstr());
    }
}
