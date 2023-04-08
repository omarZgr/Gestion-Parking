package com.application.parking.Gere_BaseDonne;
import java.sql.* ;
import com.application.parking.CryptoData.*;


public class check {

    public static int checkUser(String CIN,String password)
    {
        Connection etat = prepare.getConnection() ;
        final String secretKey = "donotspeakAboutThis" ;

       String encPassword =myAES_App.encrypt(password,secretKey);

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT userprogr.idUser from personne join userprogr on userprogr.idUser = personne.idPersone WHERE personne.CIN=? and userprogr.passwordUser=? and etat=true" ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,CIN);
                ps.setString(2,encPassword);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return Integer.parseInt(rs.getString(1));
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }

    public static boolean checkTel(String tel,int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from personne  WHERE personne.Tel=? and personne.idPersone !=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,tel);
                ps.setInt(2,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean checkTel(String tel)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from personne  WHERE personne.Tel=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,tel);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean checkCIN(String CIN,int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from personne  WHERE personne.CIN=? and personne.idPersone !=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,CIN);
                ps.setInt(2,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean checkCIN(String CIN)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from personne  WHERE personne.CIN=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,CIN);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean checkCINforClient(String CIN)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from personne join client on  client.idClient = personne.idPersone WHERE personne.CIN=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,CIN);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }
    public static boolean checkMatricule(String matricule,int id)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from client  WHERE client.matricule =? and client.idClient  !=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,matricule);
                ps.setInt(2,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean haveAbonment(String cin)
    {
        Connection etat = prepare.getConnection() ;

        int id = select.getIDbyCIN(cin) ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from abonement where abonement.idClient=? and  abonement.etat=true" ;
                ps = etat.prepareStatement(query) ;
                ps.setInt(1,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }


    public static boolean checkMatricule(String matricule)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from client  WHERE client.matricule =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,matricule);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static void main(String[] args) {

        System.out.println(check.haveAbonment("1234"));
    }



}
