package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("#############   Exercice 1    #############");

        //Creation de produits
        ProduitService ps = new ProduitService();
        ps.begin();
        ps.create(new Produit("TOSHIBA","zzza123",new Date("2015/01/08"),6000,12));
        ps.create(new Produit("HP","zEEE163",new Date("2016/02/09"),2000,34));
        ps.create(new Produit("SONY VAIO","AQYUD",new Date("2015/09/23"),6000,56));
        ps.create(new Produit("DELL","AZERTY",new Date("2016/02/12"),6000,72));
        ps.create(new Produit("SONY","qsdert",new Date("2015/02/02"),6000,90));
        ps.envoie();

        // Information produit id=2
        ps.begin();
        Produit p = ps.findById(2);
        System.out.println(p.getId()+" , "+p.getMarque()+" , "+p.getReference()+" , "+p.getDateAchat()+" , "+p.getPrix());
        ps.envoie();

        //Suppression produit id=3
        ps.begin();
        ps.delete(ps.findById(3));
        ps.envoie();

        //Modification des informations du produit  dont id =1
        ps.begin();
        p = ps.findById(1);
        if(p != null){
            p.setMarque("HP");
            p.setReference("MMMMMPPPP");
            p.setDateAchat(new Date("2015/09/08"));
            p.setPrix(5000);
            ps.update(p);
        }
        ps.envoie();




        // Exercice pile

        Pile<String> produitPile = new Pile<String>(String[].class, 1);

        Pile<Produit> produitPile2 = new Pile<Produit>(Produit[].class, 1);

        System.out.println("#############   Exercice 2    #############");
        System.out.println("Tous les produits");
        ps.begin();
        for(Produit produit : ps.findAll()){
            System.out.println(produit.getId()+" , "+produit.getMarque()+" , "+produit.getReference()+" , "+produit.getDateAchat()+" , "+produit.getPrix());
        }
        ps.envoie();

        System.out.println("Tous les produits au dessus de 2100 euros : ");
        ps.begin();
        for(Produit produitpascher : ps.filterByPrice(2100)){
            System.out.println(produitpascher.getId()+" , "+produitpascher.getMarque()+" , "+produitpascher.getReference()+" , "+produitpascher.getDateAchat()+" , "+produitpascher.getPrix());
        }
        ps.envoie();
        System.out.println("Tous les produits acheté entre le 01/01/2016 et 30/12/2016");
        ps.begin();
        String madate1 ="O1/O1/2016";
       // Date date1= new SimpleDateFormat("dd/MM/yyyy").parse(madate1);
        Date date1 = new Date("2016/01/01");

        String madate2="30/12/2016";
       // Date date2= new SimpleDateFormat("dd/MM/yyyy").parse(madate2);
        Date date2=new Date("2016/30/12");

        List<Produit> produitsDate = ps.filterByDate(date1,date2);
        for(Produit pr : produitsDate){
            System.out.println(pr.getId()+" , "+pr.getMarque()+" , "+pr.getReference()+" , "+pr.getDateAchat()+" , "+pr.getPrix());
        }


        ps.envoie();

        ps.close();


    }
}