package com.application.parking.Gere_Parking.G_Menu.User;

import java.util.Date;

public class TableUser {

    Date date  ;
    String time_Entre ;
    String time_Sortie ;

    public TableUser(Date date, String time_Entre, String time_Sortie) {
        this.date = date;
        this.time_Entre = time_Entre;
        this.time_Sortie = time_Sortie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime_Entre() {
        return time_Entre;
    }

    public void setTime_Entre(String time_Entre) {
        this.time_Entre = time_Entre;
    }

    public String getTime_Sortie() {
        return time_Sortie;
    }

    public void setTime_Sortie(String time_Sortie) {
        this.time_Sortie = time_Sortie;
    }
}
