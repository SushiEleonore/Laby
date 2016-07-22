package fr.upsud.sushi.laby.calculus;

import java.util.ArrayList;

import fr.upsud.sushi.laby.utils.Pair;
import fr.upsud.sushi.laby.utils.Values;

/**
 * Created by proval on 7/18/16.
 package fr.upsud.sushi.laby.calculus;

 import java.util.ArrayList;
 import fr.upsud.sushi.laby.utils.Pair;
 import fr.upsud.sushi.laby.utils.Values;

 /**
 * Created by proval on 5/23/16.
 */
public class InstrWhilePath implements Instr {
    Bool condPath;
    String id;
    ArrayList<Instr> instr;

    InstrWhilePath (CheckIfPath c,ArrayList<Instr> i, String id) {
        this.condPath = c;
        this.instr = i;
        this.id=id;
    }
    public Pair<String, ListInstr> next(){
            ListInstr body = new ListInstr(instr);
            body.reverse();
            body.concat(this);
            return  new Pair(this.id,body);
    }


    public void  eval(){
        while (this.condPath.eval()) {
            for(int i =instr.size()-1 ; i>=0; i--) {
                instr.get(i).next();
            }
        }

    }


}
