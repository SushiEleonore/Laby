package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/23/16.
 */
public class InstrWhile extends Instr {
    Bool cond;
    ArrayList<Instr> instr;

    InstrWhile (Bool c,ArrayList<Instr> i) {
        this.cond = c;
        this.instr = i;
    }

    @Override
    public void  eval(){
        while (this.cond.eval()) {
            for(Instr i : instr)
                i.eval();
        }

    }
}
