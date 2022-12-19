package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DemoLiaison {

    public static void main(String[] args){

        //Demo One to one bidirectionnel

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Demojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();

      //  transac.begin();

    }
}
