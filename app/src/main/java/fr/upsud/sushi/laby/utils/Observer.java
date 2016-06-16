package fr.upsud.sushi.laby.utils;

/**
 * Created by proval on 5/25/16.
 */

public interface Observer<T> {
    void notify(boolean data, T id,boolean data2, int mv);
 // void  highlightBlockById(T data);
}
