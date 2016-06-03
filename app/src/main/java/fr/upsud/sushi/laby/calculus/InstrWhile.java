package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

/**
 * Created by proval on 5/23/16.
 */
public class InstrWhile extends Instr {
    Bool cond;
    //remplacer par listinstr
    ArrayList<Instr> instr;

    InstrWhile (CheckIfEnd c,ArrayList<Instr> i) {
        this.cond = c;
        this.instr = i;
    }

    @Override
    public void  eval(){
         int cpt=0;
        System.out.println("fait");
        while (!this.cond.eval()) {
            cpt++;
            for(int i =instr.size()-1 ; i>=0; i--) {
                instr.get(i).eval();
                //A DEPLACER DANS LE MVFORWARD
                if (this.cond.eval()) break;
            }
            System.out.println("fait");
            if (cpt>20) break;
        }

    }
}
