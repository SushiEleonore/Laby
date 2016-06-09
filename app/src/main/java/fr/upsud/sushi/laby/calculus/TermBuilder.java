package fr.upsud.sushi.laby.calculus;


import android.webkit.JavascriptInterface;

import fr.upsud.sushi.laby.maze.Player;
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
    private int nStep;
    //private ArrayList<Instr> listInstr;
    private ArrayList<GameState> gameStates;
    private ListInstr lins;
    private boolean play;



    public TermBuilder(Observer<String> gui, Level l) {
        stack = new ArrayDeque<>();
        this.l=l;
        this.gui = gui;
        this.nStep=-1;
        this.play=true;
        //this.listInstr = new ArrayList<Instr>();
        this.gameStates = new ArrayList<GameState>();
        ListInstr lins = new ListInstr();

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
        while(!((t=stack.pop()) instanceof Flag)){
            then.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfEnd cond = (new CheckIfEnd(l));
        stack.push(new InstrWhile( cond, whileDo, id));
    }

    @JavascriptInterface
    public void pushIfThenElse(String v, String id) {
        ArrayList<Instr> elseBlock = new ArrayList<Instr>();
        ITerm t = null;
        //BEWARE : WE MAY INVERS ELSE END THEN BLOCKS
        while(!((t=stack.pop()) instanceof Flag)){
            elseBlock.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        ArrayList<Instr> thenBlock = new ArrayList<Instr>();
        t = null;
        //BEWARE : WE MAY INVERSE ELSE END THEN BLOCKS
        while(!((t=stack.pop()) instanceof Flag)){
            thenBlock.add((Instr)t);
        }
        //MAY PROVOKE A LOT OF BUGS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        CheckIfPath cond = (new CheckIfPath(l, v));
        stack.push(new IfPathThen( cond, thenBlock, elseBlock, id));

    }

    @JavascriptInterface
    public void pushBegin() {
        stack.push(new Flag());
    }

    @JavascriptInterface
    public void reset() {
        stack.clear();
        //listInstr.clear();
        nStep=-1;
        if (lins!=null)lins.clear();

    }


    public Instr getInstr() {
        try{ return  (Instr)stack.pop();} catch(NoSuchElementException e){ return null; }
    }
 /*   @JavascriptInterface
    public void eval() {
        Thread t = new Thread() {

            public void run() {


                Instr t = getInstr();
                ArrayList<Instr> rInstrs = new ArrayList<Instr>();
                while (t != null) {
                    rInstrs.add(t);
                    t = getInstr();
                }


                ListInstr lins = new ListInstr(rInstrs);
                lins.reverse();
                while (!lins.isEmpty()) {

                    Couple c = lins.eval();
                    System.out.println(c.getId());
                    lins = c.getListInstr();
                    gui.notify(l.printMaze(), c.getId());

                    //gui.highlightBlockById(c.getId());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }


                }


            }

        };

            t.start();
        }
        */

    @JavascriptInterface
    public void eval() {
        this.play=true;
        Thread t1 = new Thread() {

            public void run() {
                if(!stack.isEmpty()) {
                    ArrayList<Instr> l = initListInstr();
                    lins = new ListInstr(l); lins.reverse();
                }
                if(lins!=null) {
                    while (!lins.isEmpty()&&play) {
                        nextStep();
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e){}

                    }
                    CheckIfEnd b = new CheckIfEnd(l);
                    System.out.println(!b.eval());
                    if(!b.eval()){
                        l.restart();
                    }
                    else{
                       gui.notify(true, null);
                    }
                }
            }

      };
        t1.start();
    }
    public void stop(){
        this.play=false;
    }
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
                    try {

                        System.out.println("dans next "+nStep);
                        if (nStep==-1 && (lins==null ||lins.isEmpty())) {
                            ArrayList<Instr> l = initListInstr();
                            lins = new ListInstr(l);
                            lins.reverse();
                        }
                        if(!lins.isEmpty()) {
                            nStep++;
                            Player temp = new Player(l.getPlayer());
                            Couple c = lins.eval();
                            gameStates
                                    .add(new GameState(c.getId(), temp, new Player(l.getPlayer())));

                            //lins = c.getListInstr();
                            gui.notify(false, c.getId());
                            lins = c.getListInstr();
                            //gui.highlightBlockById(c.getId());
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            };

            t.start();
        } else {

            Thread t = new Thread() {

                public void run() {

                    try {

                        nStep++;
                        System.out.println("dans next bis "+nStep);

                        l.setPlayer(gameStates.get(nStep).getpArr());
                        gui.notify(false, gameStates.get(nStep).getId());

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    } catch (ArrayIndexOutOfBoundsException e){}

                }
            };

            t.start();

        }
    }


    public void prevStep(){
        Thread t = new Thread() {

            public void run() {

             try {
                if(nStep>-1) {
                    nStep--;
                    System.out.println(nStep);
                    l.setPlayer(gameStates.get(nStep + 1).getpDep());
                    gui.notify(false, gameStates.get(nStep).getId());////
                }
             try {
                Thread.sleep(1000);
              } catch (Exception e) {
            }
            } catch (ArrayIndexOutOfBoundsException e){}
            }
        };
        t.start();
    }

    public ArrayList<GameState> getGameStates() { return this.gameStates;}

    public int getnStep() {return this.nStep;}

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

