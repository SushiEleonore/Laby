package fr.upsud.sushi.laby.calculus;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by proval on 5/23/16.
 */
public class Env<X,Y>  {
    private Map<X, Deque<Y>> map;


    Env() {
        map = new TreeMap<>();
    }

    public Y get(X key)  {

        Deque<Y> v = map.get(key);

        if (v == null || v.size() == 0)
            return null;
        else
            return v.peek();
    }

    public void set(X key, Y value) {
        Deque<Y> v = map.get(key);
        if (v == null)
            v = new ArrayDeque<>();

        v.push(value);
        map.put(key, v);
    }
    public void remove(X key) {
        Deque<Y> v = map.get(key);
        if (v == null) return;
        v.pop();
        if (v.size() == 0)
            map.remove(key);
    }
}
