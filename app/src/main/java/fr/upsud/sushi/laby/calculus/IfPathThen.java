package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

import fr.upsud.sushi.laby.utils.Pair;

public class IfPathThen implements Instr {
    private Bool cond;
    private  ArrayList<Instr> lthen;
    private  ArrayList<Instr> lelse;
    private String id;

    public IfPathThen(CheckIfPath c, ArrayList<Instr> l, String id) {
        this.cond = c;
        this.lthen = l;
        this.lelse = null;
        this.id = id;
    }

    public IfPathThen(CheckIfPath c,  ArrayList<Instr> lThen, ArrayList<Instr> lElse, String id) {
        this.cond = c;
        this.lthen = lThen;
        this.lelse = lElse;
        this.id=id;
    }

    public Pair<String, ListInstr> next(){
        if(this.cond.eval()){
            ListInstr then = new ListInstr(lthen);
            then.reverse();
            return new Pair(this.id, then);
        }
        else if (this.lelse!=null){
            ListInstr lElse = new ListInstr(lelse);
            lElse.reverse();
            return new Pair(this.id, lElse);
        }
        else return new Pair (this.id, new ListInstr());
    }
}
