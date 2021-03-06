package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Pair;
import fr.upsud.sushi.laby.utils.Sens;

/**
 * Created by proval on 5/25/16.
 */
public class TurnRL implements Instr {
    private Level l;
    private Sens s;
    private String id;

    public TurnRL(Level l, Sens s, String id) {
        this.l = l;
        this.s = s;
        this.id=id;
    }
    public TurnRL(Level l, String dir, String id){
        this.l=l;
        this.id=id;
        if(dir.equals("R")){
            this.s= Sens.R;
        }
        else{
            this.s=Sens.L;
        }
    }
    public Pair<String, ListInstr> next(){
        this.l.getPlayer().rotate(this.s);
        return new Pair(this.id, new ListInstr());
    }

  /*  @Override
    public void eval (){
        try{
            Thread.sleep(waitingTime);
        }
        catch(Exception e){}
        this.l.getPlayer().rotate(this.s);

    }*/
}
