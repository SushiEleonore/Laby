package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/23/16.
 */
public interface ITerm {
    ITerm eval(Env<String, ITerm> env);
}
