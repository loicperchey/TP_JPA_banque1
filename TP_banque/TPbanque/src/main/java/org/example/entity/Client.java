package org.example.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private Date naissance_d;

    @ManyToMany
    @JoinTable(name= "client_compte",
    joinColumns = @JoinColumn(name="client_id"),
            inverseJoinColumns = @JoinColumn(name="compte_id"))
    private List<Compte> comptes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getNaissance_d() {
        return naissance_d;
    }

    public void setNaissance_d(Date naissance_d) {
        this.naissance_d = naissance_d;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
