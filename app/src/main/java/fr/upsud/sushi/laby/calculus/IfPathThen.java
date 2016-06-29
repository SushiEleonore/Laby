package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

import fr.upsud.sushi.laby.utils.Pair;

/**
 * Created by proval on 5/25/16.
 */
public class IfPathThen implements Instr {
    private Bool cond;
    private  ArrayList<Instr> lthen;
    private  ArrayList<Instr> lelse;
    private String id;

    /*
    public IfPathThen(Bool c, ListInstr l) {
        this.cond = c;
        this.lthen = l;
        this.lelse = null;
    }
*/
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

   /* @Override
    public void eval(){
        boolean b = cond.eval();
        if (b) {

            //remplacer par listinstr.eval !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            for(int i =(lthen.size()-1); i>=0;i--){
                lthen.get(i).eval();
            }


        } else if (this.lelse != null) {
           /* for(Instr i : lelse){
                i.eval();
            }

            for(int i = lelse.size()-1; i>=0;i--){
                lelse.get(i).eval();
            }
        }

    }
            */
}
