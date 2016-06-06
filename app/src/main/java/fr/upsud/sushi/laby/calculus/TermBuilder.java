package fr.upsud.sushi.laby.calculus;


import android.webkit.JavascriptInterface;

import fr.upsud.sushi.laby.R;
import fr.upsud.sushi.laby.mainactivity.MainActivity;
import fr.upsud.sushi.laby.utils.Observer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.NoSuchElementException;

import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 5/25/16.
 */
public class TermBuilder {


    //private ListInstr lInstr;
    private Observer<String> gui;
    private Level l;
    private Deque<ITerm> stack;


    public TermBuilder(Observer<String> gui, Level l) {
        stack = new ArrayDeque<>();
        this.l=l;
        this.gui = gui;
    }

    @JavascriptInterface
    public void pushMove(String id) {

        stack.push(new MoveFwd(l, id));
      //lInstr.add(new MoveFwd(l));
    }

    @JavascriptInterface
    public void pushTurnRL(String v, String id) {
        stack.push(new TurnRL(l, v, id));
    }

    @JavascriptInterface
    public void pushIfThen(String v, String id) {
        ArrayList<Instr> then = new ArrayList<Instr>();
        ITerm t = null;
        while((t=stack.pop()) instanceof Instr){
            then.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfPath cond = (new CheckIfPath(l,v));
        stack.add(new IfPathThen( cond, then, id));

    }

    @JavascriptInterface
    public void pushWhile(String id) {
        ArrayList<Instr> whileDo = new ArrayList<Instr>();
        ITerm t = null;
        while((t=stack.pop()) instanceof Instr){
            whileDo.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfEnd cond = (new CheckIfEnd(l));
        stack.add(new InstrWhile( cond, whileDo, id));
    }

    @JavascriptInterface
    public void pushIfThenElse(String v, String id) {
        ArrayList<Instr> elseBlock = new ArrayList<Instr>();
        ITerm t = null;
        //BEWARE : WE MAY INVERS ELSE END THEN BLOCKS
        while((t=stack.pop()) instanceof Instr){
            elseBlock.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        ArrayList<Instr> thenBlock = new ArrayList<Instr>();
        t = null;
        //BEWARE : WE MAY INVERSE ELSE END THEN BLOCKS
        while((t=stack.pop()) instanceof Instr){
            thenBlock.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfPath cond = (new CheckIfPath(l, v));
        stack.add(new IfPathThen( cond, thenBlock, elseBlock, id));

    }

    @JavascriptInterface
    public void pushBegin() {
        stack.push(new Flag());
    }
    @JavascriptInterface
    public void reset() {
        stack.clear();
    }


    public Instr getInstr() {
        try{ return  (Instr)stack.pop();} catch(NoSuchElementException e){ return null; }
    }
    @JavascriptInterface
    public void eval() {
        Instr t = getInstr();
        ArrayList<Instr> rInstrs = new ArrayList<Instr>();
        while(t!=null) {
            rInstrs.add(t);
            t=getInstr();
        }
        ArrayList<Instr> instrs = new ArrayList<Instr>();
        for(int i = 0; i<rInstrs.size(); i++){
            instrs.add(rInstrs.get(rInstrs.size()-1-i));
        }

        ListInstr lins= new ListInstr(instrs);
        while(!lins.isEmpty()){
            Couple c = lins.eval();
            lins = c.getListInstr();
            gui.notify(l.printMaze(), c.getId());

            gui.highlightBlockById(c.getId());
            try{
            Thread.sleep(1000);}
            catch(Exception e){}

        }
    }


    /*
    @JavascriptInterface
    public void eval() {
        Instr t = getInstr();
        ArrayList<Instr> instrs = new ArrayList<Instr>();
        while(t!=null) {
            instrs.add(t);
            t=getInstr();
        }
        for(int i = instrs.size()-1; i>=0; i--) {
            instrs.get(i).eval();
            gui.notify(l.printMaze());
        }

        CheckIfEnd b = new CheckIfEnd(l);
        if(!b.eval()){
            l.restart();
        }
    }
*/
}

