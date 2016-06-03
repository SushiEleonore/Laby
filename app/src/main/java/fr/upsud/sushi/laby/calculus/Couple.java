package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 6/3/16.
 */
public class Couple {
    private String id;

    private ListInstr listInstr;

    public Couple(String id, ListInstr instr){
        this.id=id;
        this.listInstr=instr;
    }

    public String getId(){
        return this.id;
    }

    public ListInstr getListInstr(){
        return this.listInstr;
    }

}
