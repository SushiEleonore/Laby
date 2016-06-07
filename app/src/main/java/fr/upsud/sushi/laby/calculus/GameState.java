package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Player;

/**
 * Created by proval on 6/7/16.
 */
public class GameState {
    private String id;

    private Player p;

    public GameState(String id, Player p){
        this.id=id;
        this.p=p;
    }

    public String getId(){
        return this.id;
    }

    public Player getPlayer(){
        return this.p;
    }
}
