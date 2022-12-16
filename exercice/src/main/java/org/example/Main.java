package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

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

        ps.close();

    }
}