package com.application.parking.Gere_Parking.G_Menu.Client;

import java.util.Date;

public class ClientSelected {


    int id ;
    String nom;
    String prenom;
    String CIN ;
    Date dateInscription ;
    String tel ;

    String matricule ;

    String padge ;

    public ClientSelected(int id, String nom, String prenom, String CIN, Date dateInscription, String tel, String matricule, String padge) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.dateInscription = dateInscription;
        this.tel = tel;
        this.matricule = matricule;
        this.padge = padge;
    }

    public ClientSelected()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPadge() {
        return padge;
    }

    public void setPadge(String padge) {
        this.padge = padge;
    }
}
