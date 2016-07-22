package fr.upsud.sushi.laby.calculus;

import fr.upsud.sushi.laby.maze.Player;

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

    public boolean playerMoving(){
        if(this.pDep.getX()!=this.pArr.getX() || this.pDep.getY()!=this.pArr.getY()) return true;
        else return false;
    }

    public boolean playerDestroying(){
        return this.pDep.hasChili()&&!this.pArr.hasChili();
    }

}
