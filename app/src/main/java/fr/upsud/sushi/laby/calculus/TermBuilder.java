package fr.upsud.sushi.laby.calculus;


import android.webkit.JavascriptInterface;

import fr.upsud.sushi.laby.maze.Player;
import fr.upsud.sushi.laby.utils.Values;
import fr.upsud.sushi.laby.utils.Observer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.NoSuchElementException;

import fr.upsud.sushi.laby.maze.Level;
import fr.upsud.sushi.laby.utils.Pair;

/**
 * Created by proval on 5/25/16.
 */
public class TermBuilder {
    private Observer<String> gui;
    private Level l;
    private Deque<ITerm> stack;
    private int nStep;
    private ArrayList<GameState> gameStates;
    private ListInstr lins;
    private boolean play;

    public TermBuilder(Observer<String> gui, Level l) {
        stack = new ArrayDeque<>();
        this.l=l;
        this.gui = gui;
        this.nStep=-1;
        this.play=true;
        this.gameStates = new ArrayList<GameState>();
        this.lins = new ListInstr();
    }


    @JavascriptInterface
    public void pushMove(String id) { stack.push(new MoveFwd(l, id)); }

    @JavascriptInterface
    public void pushEatChili(String id) { stack.push(new EatChili(l, id)); }

    @JavascriptInterface
    public void pushSplitFire(String id) { stack.push(new SplitFire(l, id)); }

    @JavascriptInterface
    public void pushTurnRL(String v, String id) {
        stack.push(new TurnRL(l, v, id));
    }

    @JavascriptInterface
    public void pushIfThen(String v, String id) {
        ArrayList<Instr> then = new ArrayList<Instr>();
        ITerm t = null;

        while(!((t=stack.pop()) instanceof Flag)){
            then.add((Instr)t);
        }
        CheckIfPath cond = (new CheckIfPath(l,v));
        stack.push(new IfPathThen( cond, then, id));

    }

    @JavascriptInterface
    public void pushWhile(String id) {
        ArrayList<Instr> whileDo = new ArrayList<Instr>();
        ITerm t = null;

        while((!((t=stack.pop()) instanceof Flag))){
            whileDo.add((Instr)t);
        }
        CheckIfEnd cond = (new CheckIfEnd(l));
        stack.push(new InstrWhile( cond, whileDo, id));
    }



    @JavascriptInterface
    public void pushWhilePath(String v, String id) {
        ArrayList<Instr> whileDo = new ArrayList<Instr>();
        ITerm t = null;

        while((!((t=stack.pop()) instanceof Flag))){
            whileDo.add((Instr)t);
        }
        CheckIfPath cond = (new CheckIfPath(l,v));
        stack.push(new InstrWhilePath( cond, whileDo, id));
    }

    @JavascriptInterface
    public void pushIfThenElse(String v, String id) {
        ArrayList<Instr> elseBlock = new ArrayList<Instr>();
        ArrayList<Instr> thenBlock = new ArrayList<Instr>();
        ITerm t = null;

        while(!((t=stack.pop()) instanceof Flag)){
            thenBlock.add((Instr)t);
        }

        t = null;
        while(!((t=stack.pop()) instanceof Flag)){
            elseBlock.add((Instr)t);
        }
        CheckIfPath cond = (new CheckIfPath(l, v));
        stack.push(new IfPathThen( cond, thenBlock, elseBlock, id));
    }


    @JavascriptInterface
    public void pushBegin() { stack.push(new Flag()); }

    @JavascriptInterface
    public void reset() {
        stack.clear();
        nStep=-1;
        this.play=true;
        gameStates.clear();
        lins.clear();

    }


    public Instr getInstr() {
        try{ return  (Instr)stack.pop();} catch(NoSuchElementException e){ return null; }
    }

    @JavascriptInterface
    public void eval() {
        this.play=true;
        Thread t1 = new Thread() {

            public void run() {

                if(!stack.isEmpty()&&lins.isEmpty()) {
                    ArrayList<Instr> l = initListInstr();
                    lins = new ListInstr(l); lins.reverse();
                }

                    while (!lins.isEmpty()&&play) {
                        nextStep();
                        try { Thread.sleep(l.getTimePerInst()); } catch (Exception e){}
                    }

                    CheckIfEnd b = new CheckIfEnd(l);
                    //If the player doesn't stop the execution
                    if(play) {
                        if (!b.eval()) {
                             gui.notify(false, null, true, Values.NOMV, false);

                        } else {
                            gui.notify(true, null, false, Values.NOMV, false);
                        }
                    }


            }

      };
        t1.start();
    }

    public void stop(){ this.play=false; }

    private ArrayList<Instr> initListInstr() {
        Instr t = getInstr();
        ArrayList<Instr> rInstrs = new ArrayList<Instr>();
        while (t != null) {
            rInstrs.add(t);
            t = getInstr();
        }
        return rInstrs;
    }

    @JavascriptInterface
    public void nextStep() {
        if (nStep == gameStates.size()-1 ) {
            Thread t = new Thread() {

                public void run() {
                        if (nStep==-1 && lins.isEmpty()) {
                            ArrayList<Instr> l = initListInstr();
                            lins = new ListInstr(l);
                            lins.reverse();
                        }
                        if(!lins.isEmpty()) {
                            nStep++;
                            Player temp = new Player(l.getPlayer());
                            Pair<String, ListInstr> c = lins.eval();
                            gameStates.add(new GameState(c.getFirst(), temp, new Player(l.getPlayer())));
                            if(gameStates.get(nStep).playerMoving())
                                gui.notify(false, c.getFirst(), false, Values.MVFWD , gameStates.get(nStep).playerDestroying());
                            else gui.notify(false, c.getFirst(), false, Values.NOMV, gameStates.get(nStep).playerDestroying());
                            lins = c.getSecond();
                        }
                }
            };

            t.start();
            try{t.sleep(l.getTimePerInst());} catch (InterruptedException e) { e.printStackTrace(); System.out.println("Exception");}
        } else {

            Thread t = new Thread() {

                public void run() {

                        nStep++;
                        l.setPlayer(gameStates.get(nStep).getpArr());
                    if(gameStates.get(nStep).playerMoving()   )
                        gui.notify(false, gameStates.get(nStep).getId(), false, Values.MVFWD,gameStates.get(nStep).playerDestroying());

                    else
                        gui.notify(false, gameStates.get(nStep).getId(), false, Values.NOMV,gameStates.get(nStep).playerDestroying());



                }
            };

            t.start();
            try { Thread.sleep(l.getTimePerInst()); } catch (Exception e) {e.printStackTrace(); System.out.println("Exception");}

        }
    }

    public void prevStep(){
        Thread t = new Thread() {

            public void run() {

                if(nStep>-1) {
                    nStep--;
                    if(Values.DEBUG_MODE) System.out.println(nStep);
                    l.setPlayer(gameStates.get(nStep + 1).getpDep());
                    if(gameStates.get(nStep+1).playerMoving()   ){
                        gui.notify(false, gameStates.get(nStep+1).getId(), false, Values.MVBWD,false);
                    }
                    else { gui.notify(false, gameStates.get(nStep+1).getId(), false, Values.NOMV, false);}

                }

            }
        };
        t.start();
        try {
            Thread.sleep(l.getTimePerInst());}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    public int getnStep() {return this.nStep;}
}