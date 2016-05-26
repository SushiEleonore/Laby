package fr.upsud.sushi.laby.calculus;


import android.webkit.JavascriptInterface;
import fr.upsud.sushi.laby.utils.Observer;
import java.util.ArrayDeque;
import java.util.Deque;
import fr.upsud.sushi.laby.maze.Level;

/**
 * Created by proval on 5/25/16.
 */
public class TermBuilder {


    private ListInstr lInstr;
    private Observer<String> gui;
    private Level l;

    public TermBuilder(Observer<String> gui) {
        lInstr=null;
        Level l = new Level(1);
        this.gui = gui;
    }

    @JavascriptInterface
    public void pushMove() {

      lInstr.add(new MoveFwd(l));
    }

    @JavascriptInterface
    public void pushTurnRl(String v) {
        //TODO : Faire une méthode pour convertir le string en sens

        lInstr.add(new TurnRL(l, Sens.L));
    }

    @JavascriptInterface
    public void pushIfThen() {
       //TODO: ne pas mettre n'importe quoi
        //lInstr.add(new IfThen(,n));
    }

    @JavascriptInterface
    public void push() {
        //TODO: ne pas mettre n'importe quoi
        //lInstr.add(new IfThen(,n));
    }

    @JavascriptInterface
    public void pushIfThenElse() {
        //TODO: ne pas mettre n'importe quoi
       // lInstr.add(new IfThen());
    }

    @JavascriptInterface
    public void reset() {
        //TODO : CLEAR Linstr
    }


    public Instr getTerm() {
        return lInstr.getLastInstr();
    }

    @JavascriptInterface
    public void eval() {
        Instr t = lInstr.getLastInstr();
        if (t != null) {
            //TODO; faire
        }
    }
}

