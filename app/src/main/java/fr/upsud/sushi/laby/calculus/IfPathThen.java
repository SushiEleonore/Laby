package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/25/16.
 */
public class IfPathThen extends Instr {
    private Bool cond;
    private  ArrayList<Instr> lthen;
    private  ArrayList<Instr> lelse;

    /*
    public IfPathThen(Bool c, ListInstr l) {
        this.cond = c;
        this.lthen = l;
        this.lelse = null;
    }
*/
    public IfPathThen(CheckIfPath c, ArrayList<Instr> l) {
        this.cond = c;
        this.lthen = l;
        this.lelse = null;
    }

    public IfPathThen(CheckIfPath c,  ArrayList<Instr> lThen, ArrayList<Instr> lElse) {
        this.cond = c;
        this.lthen = lThen;
        this.lelse = lElse;
    }


    //TODO: CHECK IF EVAL IS CORRECT
    @Override
    public void eval(){
        boolean b = cond.eval();
        if (b) {
            // WE MAY REVERSE THE ARRAYLISTS
            for(Instr i : lthen){
                i.eval();
            }

        } else if (this.lelse != null) {
            for(Instr i : lelse){
                i.eval();
            }
        }

    }
}
