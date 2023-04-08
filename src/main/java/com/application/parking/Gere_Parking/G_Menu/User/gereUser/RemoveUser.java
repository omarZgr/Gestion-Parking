package com.application.parking.Gere_Parking.G_Menu.User.gereUser;

import com.application.parking.Gere_BaseDonne.check;
import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_Parking.G_Login.LoginController;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class RemoveUser implements Initializable {

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


          deleteUser(UserController.userController.getId(),event) ;

    }

    private void deleteUser(int id,MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;

        String sql = "UPDATE personne SET etat =false  WHERE idPersone=?" ;
        PreparedStatement ps = etat.prepareStatement(sql) ;
        ps.setInt(1,id);
        int op=  ps.executeUpdate() ;

        if (op>0)
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
