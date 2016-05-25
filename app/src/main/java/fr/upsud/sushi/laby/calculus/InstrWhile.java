package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/23/16.
 */
public class InstrWhile  {
    boolean bool;
    ListInstr instr;

    InstrWhile (boolean c, ListInstr i) {
        this.bool = c;
        this.instr = i;
    }

    public void  eval(){
        if (this.bool) {
            //Instr.eval();
        }

    }
}
