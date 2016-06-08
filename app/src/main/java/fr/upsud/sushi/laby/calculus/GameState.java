package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Player;

/**
 * Created by proval on 6/7/16.
 */
public class GameState {
    private String id;

    private Player pDep;
    private Player pArr;

    public GameState(String id, Player pd, Player pa){
        this.id=id;
        this.pDep=pd;
        this.pArr = pa;
    }

    public String getId(){
        return this.id;
    }

    public Player getpDep(){
        return this.pDep;
    }
    public Player getpArr(){
        return this.pArr;
    }
}
