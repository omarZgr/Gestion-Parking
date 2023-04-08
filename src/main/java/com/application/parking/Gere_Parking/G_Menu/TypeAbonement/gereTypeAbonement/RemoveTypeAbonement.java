package com.application.parking.Gere_Parking.G_Menu.TypeAbonement.gereTypeAbonement;

import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_Parking.G_Menu.TypeAbonement.TypeAbonementController;
import com.application.parking.Gere_Parking.G_Menu.User.UserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveTypeAbonement implements Initializable {



    @FXML
    private TextField outputDuree;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPrix;


    @FXML
    void enregister(MouseEvent event) throws SQLException {

        deleteTypeAbnmt(TypeAbonementController.typeAbonementSelected.getID(),event) ;


    }

    private void deleteTypeAbnmt(int id, MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;

        String sql = "UPDATE typeabonment SET etat =false  WHERE idTypeAbnmt=?" ;
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
            alert.setHeaderText("Error in remove");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputNom.setText(TypeAbonementController.typeAbonementSelected.getNom());
        outputPrix.setText(String.valueOf(TypeAbonementController.typeAbonementSelected.getPrix()));
        outputDuree.setText(String.valueOf(TypeAbonementController.typeAbonementSelected.getDuree()));
    }
}
