package org.example;

import java.lang.reflect.Array;

public class Pile<T> {

    private T[] elements;

    private int compteur;

    public Pile(int taille){
        elements =(T[]) Array.newInstance(elements.getClass(), taille);
        compteur = 0;
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
