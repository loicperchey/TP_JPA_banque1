package org.example.domain;

import org.example.entity.Agence;
import org.example.entity.Client;
import org.example.entity.Compte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_tp1");

    private static EntityManager em = emf.createEntityManager();

    public static void aff_menu(){
        System.out.println("1 - Créer tout");
        System.out.println("2 - Créer un compte");
        System.out.println("3 - Ajouter un client à un compte");
        System.out.println("4 - Exit");
    }

    public static void exo() {
        System.out.println("######## TP Banque ######## ");
        em.getTransaction().begin();
        Scanner sc = new Scanner(System.in);
        int choix =0;
        do{
            try{
                aff_menu();
                choix = sc.nextInt();
                switch (choix){
                    case 1 :
                        creation();
                        break;
                    case 2 :
                        break;
                    case 3 :
                        break;
                    case 4 :
                        System.out.println("Aurevoir");
                        em.getTransaction().commit();
                        em.close();
                        emf.close();
                        break;
                    default:
                        System.out.println("Commande invalide réessayer");
                        break;
                }

            }catch (InputMismatchException ex){
                System.out.println("Veuillez entrer un entier");
                aff_menu();
            }

        }while(choix != 4);



    }

    public static void afficheAll(){
        System.out.println("##############  Affichage informations ############## ");
        Query query= em.createQuery("select a from Agence a");
        List<Agence> agences = query.getResultList();
        for(Agence a : agences){
            System.out.println("######################");
            System.out.println("Agence avec l'id : "+a.getId()+" a l'adresse : "+a.getAdresse());
            for(Compte c : a.getComptes()){
                System.out.println("\tCompte avec l'id : "+c.getId()+" libelle : "+c.getLibelle()+" solde : "+c.getSolde());
                System.out.println("\t\tProprietaire(s) du compte : ");
                for(Client cl : c.getClients()){
                    System.out.println("\t\tClient avec l'id : "+cl.getId()+" nom : "+cl.getNom()+" prenom : "+cl.getPrenom());
                }
            }
            System.out.println("######################");
        }
    }

    public static void creation(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer l'adresse de l'agence");
        String adresse = sc.nextLine();
        Agence agence = new Agence();
        agence.setAdresse(adresse);
        em.persist(agence);
        Client client = new Client();
        System.out.println("Entrer le nom");
        String nom = sc.next();
        System.out.println("Entrer le prenom");
        String prenom = sc.next();
        System.out.println("Entrer la date de naissance (format YYYY-MM-DD)");
        String date_s = sc.next();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(date_s);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setNaissance_d(date);
        em.persist(client);

        System.out.println("Entrer le libellé du compte : ");
        String libelle = sc.next();
        System.out.println("Entrer l'IBAN");
        String iban = sc.next();
        System.out.println("Entrer le solde du compte : ");
        double solde = sc.nextDouble();
        Compte compte = new Compte();
        compte.setLibelle(libelle);
        compte.setIban(iban);
        compte.setSolde(solde);
        compte.setAgence(agence);
        List<Client> malistedecient = new ArrayList<>();
        malistedecient.add(client);
       // compte.getClients().add(client);
        compte.setClients(malistedecient);
        List<Compte> malistedecompte = new ArrayList<>();
        malistedecompte.add(compte);
        client.setComptes(malistedecompte);
       // client.getComptes().add(compte);
        em.persist(compte);


    }
}
