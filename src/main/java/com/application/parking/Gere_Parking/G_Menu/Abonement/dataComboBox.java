package com.application.parking.Gere_Parking.G_Menu.Abonement;

public class dataComboBox {

    String nomAbnmt ;

    int duree ;

    public dataComboBox(String nomAbnmt, int duree) {
        this.nomAbnmt = nomAbnmt;
        this.duree = duree;
    }

    public dataComboBox()
    {}

    public String getNomAbnmt() {
        return nomAbnmt;
    }

    public void setNomAbnmt(String nomAbnmt) {
        this.nomAbnmt = nomAbnmt;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
