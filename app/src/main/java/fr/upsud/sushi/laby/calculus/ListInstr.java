package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

import fr.upsud.sushi.laby.utils.Pair;

public class ListInstr{

    private ArrayList<Instr> instr;

    public ListInstr(ArrayList<Instr> instr) {
        this.instr = instr;

    }
    public ListInstr() {
        this.instr = new ArrayList<Instr>();

    }
    public void add(Instr i){
        instr.add(i);
    }

    public Instr getHead(){
        return this.instr.get(0);
    }

    public ArrayList<Instr> getBody(){
       ArrayList<Instr> body = new ArrayList<Instr>();
        for(int i = 1; i<instr.size(); i++)
        body.add(instr.get(i));
        return body;
    }

    public ArrayList<Instr> getInstr(){
        return this.instr;
    }

    public Instr get(int i){
        return this.instr.get(i);
    }


    public Pair<String, ListInstr> eval(){
            Pair<String, ListInstr> c = this.getHead().next();
        if(instr.size()>=1) {
            c.getSecond().concat(this.getBody());
            instr= c.getSecond().getInstr();
        }
        c= new Pair (c.getFirst(),c.getSecond());
        return c;
    }


    public void clear(){
        this.instr.clear();
    }

    public void concat(ArrayList<Instr> sndPart){
        this.instr.addAll(sndPart);
    }

    public void reverse(){
        ArrayList<Instr> instrs = new ArrayList<Instr>();
        for(int i = 0; i<this.instr.size(); i++){
            instrs.add(this.instr.get(this.instr.size()-(i+1)));
        }
        this.instr = instrs;
    }
    public void concat(Instr sndPart){
        this.instr.add(sndPart);
    }

    public boolean isEmpty(){
        return this.instr.isEmpty();
    }

}
