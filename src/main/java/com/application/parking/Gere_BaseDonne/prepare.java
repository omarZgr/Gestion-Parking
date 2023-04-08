package com.application.parking.Gere_BaseDonne;
import java.sql.* ;

public class prepare {

    public static  Connection getConnection()
    {
        Connection con = null ;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkinghmuda","root","") ;
        } catch (SQLException e) {

        }
        return con ;
    }


}
