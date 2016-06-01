package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/25/16.
 */
public class ITerm {
    protected long waitingTime;
    public ITerm(){
        this.waitingTime = 2000;
    }
    public void setWaitingTime(long t) {this.waitingTime = t; }
}
