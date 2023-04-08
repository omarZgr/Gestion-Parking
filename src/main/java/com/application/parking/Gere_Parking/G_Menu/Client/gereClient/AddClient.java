package com.application.parking.Gere_Parking.G_Menu.Client.gereClient;

import com.application.parking.Gere_BaseDonne.check;
import com.application.parking.Gere_BaseDonne.prepare;
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

public class AddClient {

    @FXML
    private TextField inputCIN;

    @FXML
    private TextField inputMatricule;

    @FXML
    private TextField inputNom;

    @FXML
    private TextField inputPrenom;

    @FXML
    private TextField inputTel;

    @FXML
    private Label notification;

    @FXML
    void enregister(MouseEvent event) throws SQLException {

        notification.setVisible(false);

        String nom = inputNom.getText() ;
        String prenom = inputPrenom.getText() ;
        String tel = inputTel.getText() ;
        String cin = inputCIN.getText() ;
        String matricule = inputMatricule.getText() ;

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || cin.isEmpty() || matricule.isEmpty() )
        {
            notification.setText("Champs empty");
            notification.setVisible(true);
        }
        else
        if (check.checkCIN(cin))
        {
            notification.setText("CIN already Exist");
            notification.setVisible(true);
        }
        else
        if (check.checkTel(tel))
        {
            notification.setText("Tel already Exist");
            notification.setVisible(true);
        }
        else
        {
            if (check.checkMatricule(matricule))
            {
                notification.setText("Matricule already Exist");
                notification.setVisible(true);
            }
            else
              insertIntoClient(nom,prenom,tel,cin,matricule,event) ;
        }


    }

    private void insertIntoClient(String nom, String prenom, String tel, String cin, String matricule, MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateInscription = dtf.format(timeNow) ;


        String insertPersone = "Insert into personne (Nom,Prenom,CIN,Tel,Date_Inscription,etat) values(?,?,?,?,?,true)" ;
        String insertClient = "Insert into  client(idClient,matricule,exipre) values(?,?,true)" ;

        PreparedStatement psPersone = etat.prepareStatement(insertPersone,Statement.RETURN_GENERATED_KEYS) ;
        PreparedStatement psClient = etat.prepareStatement(insertClient) ;

        psPersone.setString(1,nom);
        psPersone.setString(2,prenom);
        psPersone.setString(3,cin);
        psPersone.setString(4,tel);
        psPersone.setString(5,dateInscription);

        int peroneInsertedRows = psPersone.executeUpdate() ;

        if (peroneInsertedRows>0)
        {
            ResultSet personIDResult = psPersone.getGeneratedKeys();
            if (personIDResult.next())
            {

                int idClient = personIDResult.getInt(1) ;
                psClient.setInt(1,idClient);
                psClient.setString(2,matricule);

                int UserInsertedRows = psClient.executeUpdate() ;

                returnInsert(UserInsertedRows,event);
            }


        }
    }

    private void returnInsert(int result, MouseEvent event) {
        if (result >0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Add User Succes");
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
