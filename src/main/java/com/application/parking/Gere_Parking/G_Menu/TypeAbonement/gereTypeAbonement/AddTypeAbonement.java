package com.application.parking.Gere_Parking.G_Menu.TypeAbonement.gereTypeAbonement;

import com.application.parking.CryptoData.myAES_App;
import com.application.parking.Gere_BaseDonne.check;
import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_BaseDonne.select;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTypeAbonement {


    @FXML
    private TextField inputDuree;

    @FXML
    private TextField inputNom;

    @FXML
    private TextField inputPrix;

    @FXML
    private Label notification;

    @FXML
    void enregister(MouseEvent event) throws SQLException {

        notification.setVisible(false);

        String nom = inputNom.getText() ;
        String duree = inputDuree.getText() ;
        String prix = inputPrix.getText() ;

        if (nom.isEmpty() || duree.isEmpty() || prix.isEmpty())
        {
            notification.setText("Champs empty");
            notification.setVisible(true);
        }
        else
        if (select.getIDbyNomAbment(nom) != -1)
        {
            notification.setText("Nom already Exist");
            notification.setVisible(true);
        }
        else
        {
            Float prix0 = Float.parseFloat(prix) ;
            insertInfoTypeAbnmt(nom,duree,prix0,event) ;
        }


    }

    private void insertInfoTypeAbnmt(String nom, String duree, Float prix, MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;
        String cmd = "Insert into typeabonment (NomAbnmt ,duree,prix,nbrUser,etat) values(?,?,?,0,true)" ;
        PreparedStatement ps = etat.prepareStatement(cmd) ;
        ps.setString(1,nom);
        ps.setString(2,duree);
        ps.setFloat(3,prix);

        int peroneInsertedRows = ps.executeUpdate() ;

        if (peroneInsertedRows>0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Add Type Abonement");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Probleme in Add User ");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }

}
