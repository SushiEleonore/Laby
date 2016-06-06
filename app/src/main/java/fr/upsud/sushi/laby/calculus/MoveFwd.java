package fr.upsud.sushi.laby.calculus;


import fr.upsud.sushi.laby.exceptions.wallCollisionException;
import fr.upsud.sushi.laby.maze.Level;

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
    public Couple next(){
        try { this.l.getPlayer().move(this.l);
        } catch (wallCollisionException E) {}
        return new Couple(this.id,new ListInstr());
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
