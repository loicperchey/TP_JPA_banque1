package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.IDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ProduitService implements IDAO<Produit> {

    private EntityManagerFactory emf;

    private EntityManager em;

    public ProduitService(){
        emf = Persistence.createEntityManagerFactory("Exercicejpa");

        em = emf.createEntityManager();
    }

    @Override
    public void begin() {
        em.getTransaction().begin();
        System.out.println("Demarrrage de la persistance");

    }

    @Override
    public boolean create(Produit o) {
        em.persist(o);
        return true;
    }

    @Override
    public boolean update(Produit o) {
        em.persist(o);
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        em.remove(o);
        return true;
    }

    @Override
    public Produit findById(int id) {
        return em.find(Produit.class,id);
    }

    @Override
    public void envoie() {
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public List<Produit> findAll() {
        Query query = em.createQuery("select p from Produit p");
        List<Produit> list = query.getResultList();
        return list;
    }
}
