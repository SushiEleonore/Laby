package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;
import fr.upsud.sushi.laby.utils.Pair;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 5/23/16.
 */
public class InstrWhile implements Instr {
    Bool isEnd;
    String id;
    //remplacer par listinstr
    ArrayList<Instr> instr;

    InstrWhile (CheckIfEnd c,ArrayList<Instr> i, String id) {
        this.isEnd = c;
        this.instr = i;
        this.id=id;
    }
    public Pair<String, ListInstr> next(){
        if (!this.isEnd.eval()){
            ListInstr body = new ListInstr(instr);
            body.reverse();
            body.concat(this);
            return  new Pair(this.id,body);
        }
        else {
            return new Pair(this.id, new ListInstr());
        }

    }


    public void  eval(){
         int cpt=0;
        if(Values.DEBUG_MODE)
        System.out.println("fait");
        while (!this.isEnd.eval()) {
            cpt++;
            for(int i =instr.size()-1 ; i>=0; i--) {
                instr.get(i).next();
                //A DEPLACER DANS LE MVFORWARD
                if (this.isEnd.eval()){
                    if (Values.DEBUG_MODE) System.out.println("break");
                    break;}
            }
            if (Values.DEBUG_MODE)
            System.out.println("fait");
            //if (cpt>20) break;
        }

    }
}
