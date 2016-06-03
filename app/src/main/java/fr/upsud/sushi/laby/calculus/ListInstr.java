package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/25/16.
 */
public class ListInstr{ //extends ITerm{

    private ArrayList<Instr> instr;

    public ListInstr(ArrayList<Instr> instr) {
        this.instr = instr;

    }
    public void add(Instr i){
        instr.add(i);
    }
    public Instr getLastInstr(){
        return instr.get(instr.size()-1);
    }
    public Instr getHead(){
        return this.instr.get(0);
    }
    public ArrayList<Instr> getBody(){
        this.instr.remove(0);
        return this.instr;
    }

    public Couple eval(){
        Couple c = this.getHead().next();
        c.getListInstr().concat(this.getBody());
        return c;
    }

    //Evaluates the instructions one by one
  /*  public void eval(){
        for (int i = 0; i<instr.size(); i++) {

                // thread to sleep for 1000 milliseconds

            instr.get(i).eval();
        }
    }
*/
    //ajouter conxcaténation
    public void concat(ArrayList<Instr> sndPart){
        this.instr.addAll(sndPart);
    }
    public void concat(Instr sndPart){
        this.instr.add(sndPart);
    }


    public boolean isEmpty(){
        return this.instr.isEmpty();
    }

}
