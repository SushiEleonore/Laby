package fr.upsud.sushi.laby.calculus;


import android.webkit.JavascriptInterface;
import fr.upsud.sushi.laby.utils.Observer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
    public void pushMove() {

        stack.push(new MoveFwd(l));
      //lInstr.add(new MoveFwd(l));
    }

    @JavascriptInterface
    public void pushTurnRL(String v) {
        stack.push(new TurnRL(l, v));
    }

    @JavascriptInterface
    public void pushIfThen(String v) {
        ArrayList<Instr> then = new ArrayList<Instr>();
        ITerm t = null;
        while((t=stack.pop()) instanceof Instr){
            then.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfPath cond = (new CheckIfPath(l,v));
        stack.add(new IfPathThen( cond, then));

    }

    @JavascriptInterface
    public void pushWhile() {
        ArrayList<Instr> whileDo = new ArrayList<Instr>();
        ITerm t = null;
        while((t=stack.pop()) instanceof Instr){
            whileDo.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfEnd cond = (new CheckIfEnd(l));
        stack.add(new InstrWhile( cond, whileDo));
    }

    @JavascriptInterface
    public void pushIfThenElse(String v) {
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
        stack.add(new IfPathThen( cond, thenBlock, elseBlock));

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
        return (Instr)stack.peek();
    }

    //MAY BE PROBLEMATIC
    @JavascriptInterface
    public void eval() {
        Instr t = getInstr();
        if (t != null) {
            t.eval();
            gui.notify(l.printMaze());
        }
    }

}

