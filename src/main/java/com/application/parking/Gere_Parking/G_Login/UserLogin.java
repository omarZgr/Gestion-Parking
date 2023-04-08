package com.application.parking.Gere_Parking.G_Login;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class UserLogin {

    int idUser ;

    int idWorkTime ;
    String nom ;
    String prenom ;

    int admin ;

    String timeEntre ;

    String dateEntre ;

    Image img ;

    public String getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(String dateEntre) {
        this.dateEntre = dateEntre;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }


    public void setTimeEntre(String timeEntre) {
        this.timeEntre = timeEntre;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdWorkTime(int udWorkTime) {
        this.idWorkTime = udWorkTime;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdWorkTime() {
        return idWorkTime;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAdmin() {
        return admin;
    }

    public String getTimeEntre() {
        return timeEntre;
    }
}
