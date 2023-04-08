package com.application.parking.Gere_Parking.G_Menu.Client.gereClient;

import com.application.parking.Gere_BaseDonne.check;
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

public class ModifiyClient implements Initializable {

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

        String tel = outputTel.getText();
        String matricule = outputMatricule.getText();

      if (tel.isEmpty() || matricule.isEmpty()) {
            notification.setText("Champs Empty");
            notification.setVisible(true);
        }
      else
      {
          if (check.checkTel(tel,ClientController.clientSelected.getId()))
          {
              notification.setText("Tel already exist");
              notification.setVisible(true);
          }
          else
              if (check.checkMatricule(matricule,ClientController.clientSelected.getId())) {
                  notification.setText("Matricule already exist");
                  notification.setVisible(true);
                }
              else
                    updateValueTele(ClientController.clientSelected.getId(), tel, matricule, event);
            }
        }



    private void updateValueTele(int id, String tel, String matricule, MouseEvent event) throws SQLException {
        Connection etat = prepare.getConnection() ;


        String sqlTel = "UPDATE personne SET Tel  =?  WHERE idPersone =?" ;
        String sqlMatricule = "UPDATE  client SET matricule =?  WHERE idClient  =?" ;

        PreparedStatement psTel = etat.prepareStatement(sqlTel) ;
        PreparedStatement psMatricule = etat.prepareStatement(sqlMatricule) ;

        psTel.setString(1,tel);
        psTel.setInt(2,id);

        psMatricule.setString(1,matricule);
        psMatricule.setInt(2,id);

        int opTel=  psTel.executeUpdate() ;
        int opMatricule=  psTel.executeUpdate() ;

        if (opTel>0 && opMatricule>0)
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

        outputNom.setText(ClientController.clientSelected.getNom());
        outputPrenom.setText(ClientController.clientSelected.getPrenom());
        outputTel.setText(ClientController.clientSelected.getTel());
        outputCIN.setText(ClientController.clientSelected.getCIN());
        outputMatricule.setText(ClientController.clientSelected.getMatricule());

        Date date = (Date) ClientController.clientSelected.getDateInscription();

        outputDateInscritpion.setValue(date.toLocalDate());

    }
}
