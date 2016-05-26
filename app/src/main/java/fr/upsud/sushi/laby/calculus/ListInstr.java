package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/25/16.
 */
public class ListInstr extends ITerm{

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

    //Evaluates the instructions one by one
    public void eval(){
        for (int i = 0; i<instr.size(); i++) {
            instr.get(i).eval();
        }
    }

}
