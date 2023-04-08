package com.application.parking.Gere_Parking.G_Menu.Client.gereClient;

import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_Parking.G_Menu.Client.ClientController;
import com.application.parking.Gere_Parking.G_Menu.User.UserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveClient implements Initializable {

    @FXML
    private Label notification;

    @FXML
    private TextField outputCIN;

    @FXML
    private TextField outputMatricule;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPrenom;

    @FXML
    private TextField outputTel;

    @FXML
    private DatePicker outputDateInscritpion;


    @FXML
    void enregister(MouseEvent event) throws SQLException {

        deleteClient(ClientController.clientSelected.getId(),event) ;
    }

    private void deleteClient(int id, MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;


        String sql1 = "UPDATE personne SET etat =false  WHERE idPersone=?" ;
        String sql2 = "UPDATE client SET exipre =true  WHERE idClient =?" ;
        PreparedStatement ps1 = etat.prepareStatement(sql1) ;
        PreparedStatement ps2 = etat.prepareStatement(sql2) ;
        ps1.setInt(1,id);
        ps2.setInt(1,id);
        int op1=  ps1.executeUpdate() ;
        int op2=  ps2.executeUpdate() ;

        if (op1>0 && op2>0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Remove Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in delet Client");
            alert.setContentText("");
            alert.showAndWait() ;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputNom.setText(ClientController.clientSelected.getNom());
        outputPrenom.setText(ClientController.clientSelected.getPrenom());
        outputTel.setText(ClientController.clientSelected.getTel());
        outputCIN.setText(ClientController.clientSelected.getCIN());
        outputMatricule.setText(ClientController.clientSelected.getMatricule());

        Date date = (Date) ClientController.clientSelected.getDateInscription();

        outputDateInscritpion.setValue(date.toLocalDate());
    }
}
