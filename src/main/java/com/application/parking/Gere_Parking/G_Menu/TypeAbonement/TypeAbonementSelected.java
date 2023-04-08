package com.application.parking.Gere_Parking.G_Menu.TypeAbonement;

public class TypeAbonementSelected {

    int ID ;
    String nom;
    int duree ;
    Float prix ;
    int nbrUser ;

    public TypeAbonementSelected(int ID, String nom, int duree, Float prix, int nbUser) {
        this.ID = ID;
        this.nom = nom;
        this.duree = duree;
        this.prix = prix;
        this.nbrUser = nbUser;
    }

    public TypeAbonementSelected()
    {}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public int getNbrUser() {
        return nbrUser;
    }

    public void setNbrUser(int nbrUser) {
        this.nbrUser = nbrUser;
    }
}
