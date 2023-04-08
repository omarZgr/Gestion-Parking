package com.application.parking.Gere_Parking.G_Menu.User.gereUser;

import com.application.parking.Gere_BaseDonne.check;
import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_Parking.G_Menu.Client.ClientController;
import com.application.parking.Gere_Parking.G_Menu.User.UserController;
import com.application.parking.Gere_Parking.G_Menu.User.UserSelected;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ModifiyUser implements Initializable {

    @FXML
    private Label notification;

    @FXML
    private TextField outputCIN;

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

        String tel = outputTel.getText();

        if (tel.isEmpty())
        {
            notification.setText("Champs Empty");
            notification.setVisible(true);
        }
        else
        {
            if (check.checkTel(tel,UserController.userController.getId()))
            {
                notification.setText("Tel already exist");
                notification.setVisible(true);
            }
            else
                updateValueTele(UserController.userController.getId(),tel,event);
        }

    }

    private void updateValueTele(int id,String tel,MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;

        String sql = "UPDATE personne SET Tel  =?  WHERE idPersone =?" ;
        PreparedStatement ps = etat.prepareStatement(sql) ;
        ps.setString(1,tel);
        ps.setInt(2,id);
        int op=  ps.executeUpdate() ;

        if (op>0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in update tele");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputNom.setText(UserController.userController.getNom());
        outputPrenom.setText(UserController.userController.getPrenom());
        outputTel.setText(UserController.userController.getTel());
        outputCIN.setText(UserController.userController.getCIN());

        Date date = (Date) UserController.userController.getDateInscription();
         outputDateInscritpion.setValue(date.toLocalDate());







    }
}
