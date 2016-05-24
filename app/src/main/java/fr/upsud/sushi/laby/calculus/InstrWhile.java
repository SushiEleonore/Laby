package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/23/16.
 */
public class InstrWhile extends Instr {
    Bool bool;
    Instr instr;

    InstrWhile (Bool c, Instr i) {
        this.bool = c;
        this.instr = i;
    }
}
