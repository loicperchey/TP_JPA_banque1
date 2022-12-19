package org.example;

import org.example.model.Personne;
import org.example.model.PersonneWithPK;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Demojpa");

        EntityManager em = emf.createEntityManager();

        EntityTransaction transac = em.getTransaction();

        transac.begin();

        //Création de Personne
        Personne nouvellePersonne;
        //pas de set de l'ID
        for(int i= 0; i<=20; i++){
            nouvellePersonne = new Personne();
            nouvellePersonne.setNom("nom"+(i+1));
            nouvellePersonne.setPrenom("prenom"+(i+1));
            em.persist(nouvellePersonne);
            transac.commit();
            transac.begin();
            nouvellePersonne = null;
        }



        PersonneWithPK personneWithPK = new PersonneWithPK();
        personneWithPK.setNom("titi");
        personneWithPK.setPrenom("tutu");
        em.merge(personneWithPK);
        transac.commit();

        // Récuperation de personne avec find et getReference
        transac.begin();

        Personne personne = em.find(Personne.class,1);
        System.out.println("Personne avec l'ID 1 (find) : "+personne.getNom()+" "+personne.getPrenom());

        Personne personne1 = em.getReference(Personne.class,1);
        System.out.println("Personne avec l'ID 1 (getReference) : "+personne1.getNom()+" "+personne1.getPrenom());


        transac.commit();

        // Reccuperation avec Query

        //createQuery single result
        Query query = em.createQuery("select p from Personne p where p.nom='nom12'");
        Personne personne2 =(Personne) query.getSingleResult();
        System.out.println("Personne avec le nom = nom12 a pour prenom "+personne2.getPrenom()+" et l'id : "+personne2.getId());


        //createQuery result list
        System.out.println("Liste des personnes avec l'id supérieur à 5");
        Query query1=em.createQuery("select  p from Personne p where p.id > 5");
        List noms = query1.getResultList();
        //on recupere une liste d'objet
        for(Object nom : noms){
            Personne tmp =(Personne) nom;
            System.out.println("nom = "+tmp.getNom());
        }

        // Utilisation du setParameter
        System.out.println("Liste de personne avec id superieur au parametre set");
        Query query2=em.createQuery("select p from Personne p where p.id > :id");
        query2.setParameter("id",15);
        List noms2 = query2.getResultList();
        //on recupere une liste d'objet
        for(Object nom : noms2){
            Personne tmp =(Personne) nom;
            System.out.println("nom = "+tmp.getNom());
        }

        //Modification
        transac.begin();
        System.out.println("Modifier une occurence");
        Personne personne3= em.find(Personne.class,1);
        System.out.println("Personne avec l'ID 1 (avant modif) : "+personne3.getNom()+" "+personne3.getPrenom());
        personne3.setNom("toto");
        personne3.setPrenom("tata");
        em.flush();
        transac.commit();

        Personne personne4 = em.find(Personne.class,1);
        System.out.println("Personne avec l'ID 1 (apres modif) : "+personne4.getNom()+" "+personne4.getPrenom());

        //Suppression
        transac.begin();
        System.out.println("Suppression d'une occurence (personne avec l'id 6)");
        Personne personne5= em.find(Personne.class,6);
        em.remove(personne5);
        transac.commit();
            // partie affichage pour confirmer suppression
        Query query3 = em.createQuery("select p from Personne p where p.id between 5 and 7");
        List noms1 = query3.getResultList();
        for(Object nom : noms1) {
            Personne tmp = (Personne) nom;
            System.out.println("nom = "+tmp.getNom());
        }

        //NativeQuery
        System.out.println("Native Query");
        List<Personne> results = em.createNativeQuery("SELECT * FROM personne",Personne.class).getResultList();
        for(Personne p : results) {
            System.out.println("nom = "+p.getNom());
        }


        em.close();
        emf.close();

    }
}