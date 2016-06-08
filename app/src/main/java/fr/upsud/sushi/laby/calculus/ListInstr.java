package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/25/16.
 */
public class ListInstr{ //extends ITerm{

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
    public Instr getLastInstr(){
        return instr.get(instr.size()-1);
    }
    public Instr getHead(){
        return this.instr.get(0);
    }
    public ArrayList<Instr> getBody(){
       ArrayList<Instr> body = new ArrayList<Instr>();
        for(int i = 1; i<instr.size(); i++)
        body.add(instr.get(i));
        // this.instr.remove(0);
        return body;
    }
    public ArrayList<Instr> getInstr(){

        return this.instr;
    }
    public Instr get(int i){

        return this.instr.get(i);
    }
    public Couple eval(){

            Couple c = this.getHead().next();

        if(instr.size()>=1) {
            c.getListInstr().concat(this.getBody());
            instr= c.getListInstr().getInstr();
        }

       // this.concat(c.getListInstr().getInstr(), this.getBody());
        c= new Couple (c.getId(),c.getListInstr());
        return c;
    }

    //Evaluates the instructions one by one
  /*  public void eval(){
        for (int i = 0; i<instr.size(); i++) {

                // thread to sleep for 1000 milliseconds

            instr.get(i).eval();
        }
    }
*/
    //ajouter conxcaténation
    public void concat(ArrayList<Instr> sndPart){
        this.instr.addAll(sndPart);
    }
    public void concat(ArrayList<Instr> sndPart, ArrayList<Instr> frstPart){
        this.instr = new ArrayList<Instr>();
        instr.addAll(frstPart);
        instr.addAll(sndPart);

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
