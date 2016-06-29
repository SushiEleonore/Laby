package fr.upsud.sushi.laby.calculus;


import android.os.Looper;
import android.widget.Toast;

import fr.upsud.sushi.laby.exceptions.wallCollisionException;
import fr.upsud.sushi.laby.mainactivity.MainActivity;
import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Pair;

/**
 * Created by proval on 5/24/16.
 */
public class MoveFwd implements Instr {
    private Level l;
    private String id;
    public MoveFwd(Level l, String id)
    {

        this.l = l;
        this.id=id;
    }
    public Pair<String, ListInstr> next(){
        try { this.l.getPlayer().move(this.l);
        } catch (wallCollisionException E) {
            Looper.prepare();
            Toast.makeText(l.getContext(), "Bim, tu t'es cogné contre un mur", Toast.LENGTH_SHORT).show();
        }
        return new Pair(this.id,new ListInstr());
    }


/*
   // @Override
    public void  eval() {

        try{
            Thread.sleep(waitingTime);
        }
        catch(Exception e){}
        try { this.l.getPlayer().move(this.l);
        } catch (wallCollisionException E) {}
    }
*/
}
