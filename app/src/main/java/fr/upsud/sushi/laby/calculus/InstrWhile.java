package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/23/16.
 */
public class InstrWhile extends Instr {
    Bool cond;
    ListInstr instr;

    InstrWhile (Bool c, ListInstr i) {
        this.cond = c;
        this.instr = i;
    }

    @Override
    public void  eval(){
        while (this.cond.eval()) {
            instr.eval();
        }

    }
}
