package fr.upsud.sushi.laby.utils;


public interface Observer<T> {
    void notify(boolean data, T id,boolean data2, int mv, boolean pDestroying);
}
