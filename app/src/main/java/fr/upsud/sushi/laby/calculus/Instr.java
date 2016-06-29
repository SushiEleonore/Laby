package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.utils.Pair;

/**
 * Created by proval on 5/23/16.
 */
public interface Instr extends ITerm {

    public Pair<String, ListInstr> next();

}
