package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pile<T> {

    private T[] elements;

    private int compteur;

    public Pile(Class<T[]> clazz, int length) {
        elements = clazz.cast(Array.newInstance(clazz.getComponentType(), length));
    }

    public boolean Empiler(T element){
        if(compteur < elements.length){
            elements[++compteur] = element;
            return true;
        }
        return false;
    }

    public boolean Depiler(){
        if(compteur >0){
            elements[--compteur] = null;
            return true;
        }
        return false;
    }

    public T GetElement(int id) throws Exception {
        if(id >=0 && id < elements.length && elements[id] != null) {
            return elements[id];
        }
        throw new Exception("Not Found");
    }

}
