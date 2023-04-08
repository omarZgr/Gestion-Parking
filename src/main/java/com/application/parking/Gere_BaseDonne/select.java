package com.application.parking.Gere_BaseDonne;

import com.application.parking.Gere_Parking.G_Menu.Abonement.dataComboBox;
import com.application.parking.Gere_Parking.G_Menu.Client.TableClient;
import com.application.parking.Gere_Parking.G_Menu.TypeAbonement.TypeAbonementSelected;
import com.application.parking.Gere_Parking.G_Menu.User.UserSelected;
import com.application.parking.Gere_Parking.G_Menu.User.TableUser;
import com.application.parking.Gere_Parking.G_Menu.User.gereUser.tableauUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class select {

    public static String getNom(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.Nom from personne  WHERE personne.idPersone=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
        }

        return "id : " + " introuvable Nom" ;

    }

    public static String getPrenom(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.Prenom from personne  WHERE personne.idPersone=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
        }

        return "id : " + " introuvable Nom" ;

    }

    public static String getMatricule(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT client.matricule  from client  WHERE client.idClient =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
        }

        return "id : " + " introuvable matricule" ;

    }

    public static String getPadge(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT client.padge   from client  WHERE client.idClient =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
        }

        return "id : " + " introuvable padge" ;

    }



    public static String getNomAbnmt(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT typeabonment.NomAbnmt from typeabonment  WHERE typeabonment.idTypeAbnmt =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
        }

        return "id : " + " introuvable Nom" ;

    }

    public static int getDureeAbnmt(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT typeabonment.duree from typeabonment  WHERE typeabonment.idTypeAbnmt =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }


    public static Float getPrixAbnmt(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT typeabonment.prix from typeabonment  WHERE typeabonment.idTypeAbnmt =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getFloat(1);
                }
            }
        } catch (SQLException e) {
        }

        return (float) -1;

    }

    public static int getNbrUserAbnmt(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT typeabonment.nbrUser from typeabonment  WHERE typeabonment.idTypeAbnmt =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1;

    }


    public static int getIDbyTel(String tel)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.idPersone  from personne  WHERE personne.Tel=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,tel);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }

    public static int getIDbyCIN(String cin)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.idPersone  from personne  WHERE personne.CIN=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,cin);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }
    public static int getIDbyNomAbment(String nom)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT typeabonment.idTypeAbnmt  from typeabonment  WHERE typeabonment.NomAbnmt=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,nom);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }

    public static String  getCIN(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.CIN  from personne  WHERE personne.idPersone =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
        }

        return null ;

    }

    public static Date getDateInscription(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.Date_Inscription  from personne  WHERE personne.idPersone =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getDate(1);
                }
            }
        } catch (SQLException e) {
        }

        return null ;
    }

    public static int getAdmin(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT userprogr.admin from userprogr  WHERE userprogr.idUser=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return 0 ;

    }

    public static byte[] getImg(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT userprogr.img from userprogr  WHERE userprogr.idUser=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    byte[] imgData = rs.getBytes(1);
                    if (imgData != null)
                        return imgData ;
                }
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public static int getIDWork(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT worktime.idWorkTime  from worktime where  worktime.idUser =?" ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return Integer.parseInt(rs.getString(1));
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }

    public static ArrayList<UserSelected> getDataForUser(String tel)
    {
        Connection etat = prepare.getConnection() ;

        ArrayList<UserSelected> Data = new ArrayList<>() ;

        try {
            if(  !etat.isClosed())
            {
                int id ;
                String nom,prenom,cin,tele;
                Date dateInscription ;
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT personne.idPersone ,personne.Nom,personne.Prenom,personne.CIN,personne.Tel,personne.Date_Inscription from personne  WHERE personne.Tel=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,tel);
                rs = ps.executeQuery() ;
                if (rs.next())
                {
                    System.out.println("ici");

                    while (rs.next())
                    {

                        id = rs.getInt(1);
                        nom = rs.getString(2);
                        prenom=rs.getString(3);
                        cin =rs.getString(4);
                        tele= rs.getString(5);
                        dateInscription=rs.getDate(6);
                        Data.add(new UserSelected(id,nom,prenom,cin,tele,dateInscription)) ;
                    }

                    return  Data ;

                }
                else
                {
                    System.out.println("Tableau vide");
                }

            }
            else
            {
                System.out.println("bd close");

            }
        } catch (SQLException e) {
            System.out.println("bd close");
        }

        return null ;

    }

    public static ArrayList<tableauUser> getDataForUserTable() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<tableauUser> Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            String nom, prenom, cin, tele;
            Date dateInscription;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT personne.Nom,personne.Prenom,personne.Tel from personne join userprogr on userprogr.idUser= personne.idPersone where personne.etat = true ";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();


            while (rs.next()) {
                nom = rs.getString(1);
                prenom = rs.getString(2);
                tele = rs.getString(3);
                Data.add(new tableauUser(nom, prenom, tele));
            }
        }
        return Data;

    }

    public static ArrayList<TableClient> getDataForClientTable() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<TableClient> Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            String nom, prenom, cin, tele;
            Date dateInscription;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT personne.Nom,personne.Prenom,personne.Tel from personne join client on client.idClient = personne.idPersone where personne.etat = true and client.exipre=false ";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                nom = rs.getString(1);
                prenom = rs.getString(2);
                tele = rs.getString(3);
                Data.add(new TableClient(nom, prenom, tele));
            }
        }
        return Data;

    }

    public static ArrayList<TableUser> getDataUserSuivi(int id) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<TableUser> Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            Date date ;
            String  timeEntre,timeSortie;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT worktime.date_Work,worktime.time_Entre,worktime.time_Sortie from worktime where worktime.idUser  =? ";
            ps = etat.prepareStatement(query) ;
            ps.setInt(1,id);
            rs = ps.executeQuery();


            while (rs.next()) {
                date = rs.getDate(1);
                timeEntre = rs.getString(2);
                timeSortie = rs.getString(3);
                Data.add(new TableUser(date, timeEntre, timeSortie));
            }
        }
        return Data;

    }

    public static ArrayList<TypeAbonementSelected> getDataForTypeAbonement() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<TypeAbonementSelected> Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            String nom;
            int nbrUser,duree;
            float prix ;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT typeabonment.idTypeAbnmt,typeabonment.NomAbnmt,typeabonment.duree,typeabonment.prix,typeabonment.nbrUser from typeabonment where etat = true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1) ;
                nom = rs.getString(2);
                duree = rs.getInt(3);
                prix = rs.getFloat(4);
                nbrUser=rs.getInt(5) ;
                Data.add(new TypeAbonementSelected(id,nom,duree,prix,nbrUser));
            }
        }
        return Data;

    }

    public static ArrayList<String> getNomTypeAbnmtForComboBoxAbnmt() throws SQLException {

        Connection etat = prepare.getConnection() ;

        ArrayList<String > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            String nom;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT typeabonment.NomAbnmt from typeabonment where etat = true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                nom = rs.getString(1);
                Data.add(nom);
            }
        }
        return Data;
    }

    public static Float getPrixAbnmt(String nomAbnmt) throws SQLException {

        Connection etat = prepare.getConnection() ;

        ArrayList<String > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT typeabonment.prix from typeabonment where etat = true and typeabonment.NomAbnmt=?";
            ps = etat.prepareStatement(query);
            ps.setString(1, nomAbnmt);
            rs = ps.executeQuery();

            if (rs.next()) return rs.getFloat(1);

        }

        return -1f ;
    }

    public static int getDureeAbnmt(String nomAbnmt) throws SQLException {

        Connection etat = prepare.getConnection() ;

        ArrayList<String > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT typeabonment.duree from typeabonment where etat = true and typeabonment.NomAbnmt=?";
            ps = etat.prepareStatement(query);
            ps.setString(1, nomAbnmt);
            rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        }

        return -1 ;
    }

    public static int getNbrUserTybeAbnmt(int id) throws SQLException {

        Connection etat = prepare.getConnection() ;

        ArrayList<String > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT typeabonment.nbrUser from typeabonment where etat = true and typeabonment.idTypeAbnmt =?";
            ps = etat.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        }

        return -1 ;
    }

    public static ArrayList<Integer> getIDofClientExpire() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<Integer > Data = new ArrayList<>() ;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DateNow = now.format(formatter);
       // java.sql.Date d = new java.sql.Date() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT idAbonement from abonement  where date_Fin <? and  etat=true";
            ps = etat.prepareStatement(query);
           // ps.setDate(1, java.sql.Date.valueOf(DateNow));
            ps.setDate(1, java.sql.Date.valueOf(DateNow));
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
                Data.add(id);
            }
        }
        return Data;
    }

    public static int getIDbyIDAbnmt(int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT abonement.idClient  from abonement join client on  client.idClient = abonement.idClient WHERE abonement.idAbonement =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;
    }

    public static void main(String[] args) throws SQLException {

        ArrayList<Integer> data = select.getIDofClientExpire() ;

       if (data.size() == 0)
       {
           System.out.println("Empty");
       }






    }


}
