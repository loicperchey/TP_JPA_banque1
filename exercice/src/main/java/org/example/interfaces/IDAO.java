package org.example.interfaces;

public interface IDAO<T> {

    void begin();

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    void envoie();

    void close();

}
