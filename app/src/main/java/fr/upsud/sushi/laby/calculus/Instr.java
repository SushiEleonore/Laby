package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.utils.Pair;

public interface Instr extends ITerm {

    public Pair<String, ListInstr> next();

}
