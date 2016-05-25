package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/25/16.
 */
public class IfThen extends Instr {
    private Bool cond;
    private ListInstr instr;

    public IfThen(Bool c, ListInstr l) {
        this.cond = c;
        this.instr = l;
    }

    @Override
    public void eval(){
        boolean b = cond.eval();
        if (b) {
            this.instr.eval();
        }

    }
}
