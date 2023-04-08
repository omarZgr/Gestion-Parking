package com.application.parking.Gere_Parking.G_Menu.User;

import java.util.Date;

public class UserSelected {

    int id ;
    String nom;
    String prenom;
    String CIN ;
    Date dateInscription ;
    String tel ;

    public UserSelected(int id, String nom, String prenom, String CIN, String tel,Date dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.dateInscription = dateInscription;
        this.tel = tel;
    }

    public UserSelected()
    {
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public String getTel() {
        return tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
