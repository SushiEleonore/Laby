package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.utils.Pair;

/**
 * Created by proval on 6/3/16.
 */
public class Couple {//extends Pair {
    private String id;

    private ListInstr listInstr;

    public Couple(String id, ListInstr instr){
        this.id = id;
        this.listInstr = instr;
    }


    public String getId(){
        return this.id;
    }

    public ListInstr getListInstr(){
        return this.listInstr;
    }

}
