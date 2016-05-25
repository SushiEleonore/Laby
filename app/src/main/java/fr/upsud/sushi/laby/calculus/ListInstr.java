package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/25/16.
 */
public class ListInstr {

    private ArrayList<Instr> instr;

    public ListInstr(ArrayList<Instr> instr) {
        this.instr = instr;

    }

    //Evaluates the instructions one by one
    public void eval(){
        for (int i = 0; i<instr.size(); i++) {
            instr.get(i).eval();
        }
    }

}
