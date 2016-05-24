package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/23/16.
 */
public class Binop implements Expr {
    Expr left;
    Expr right;

    Binop(Expr e1, Expr e2) {
        this.left = e1;
        this.right = e2;
    }



}
