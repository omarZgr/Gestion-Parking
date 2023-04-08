package com.application.parking;

import com.application.parking.Gere_BaseDonne.check;
import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_Parking.G_Login.* ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ParentController implements Initializable {


    @FXML
    private   BorderPane Parent;
    @FXML
    private ToggleButton Acceuil;

    @FXML
    private ToggleButton Commence_Abonement;

    @FXML
    private ToggleButton Gere_Client;

    @FXML
    private ToggleButton Gere_Type_Abonement;

    @FXML
    private ToggleButton Gere_User;

    @FXML
    private ToggleButton Paye;

    @FXML
    private ToggleButton exit;

    @FXML
    private AnchorPane zoneMenu;

    @FXML
    private Label userName;

    @FXML
    private Circle zoneImg;

    @FXML
    void swapMenu(ActionEvent event) throws IOException {

        if (event.getSource() == Acceuil)
            preparePage("/com/application/parking/Gere_Parking/Acceuil/acceuil.fxml");

        else
        if (event.getSource() == Gere_Client)
            preparePage("/com/application/parking/Gere_Parking/Client/client.fxml");
        else
        if (event.getSource() == Gere_User)
        {
            if (LoginController.userAccteuil.getAdmin() == 1)
                preparePage("/com/application/parking/Gere_Parking/User/User.fxml");
            else
                msgNoNAdmin() ;
        }
        else
        if (event.getSource() == Gere_Type_Abonement)
        {
            if (LoginController.userAccteuil.getAdmin() == 1)
                preparePage("/com/application/parking/Gere_Parking/TypeAbonement/typeAbonement.fxml");
            else
                msgNoNAdmin() ;
        }

        else
        if (event.getSource() == Commence_Abonement)
            preparePage("/com/application/parking/Gere_Parking/Abonement/abonement.fxml");
        else
        if (event.getSource() == Paye)
            preparePage("/com/application/parking/Gere_Parking/Paye/paye.fxml");
        else
        if (event.getSource() == exit)
            exitPrograme() ;

    }

    private void msgNoNAdmin()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Non Admin");
        alert.setContentText("");
        alert.showAndWait() ;
    }
    private void preparePage(String path) throws IOException {
        URL fxmlfile = getClass().getResource(path) ;
        AnchorPane view = FXMLLoader.load(fxmlfile);
        Parent.setCenter(view);

    }

    private void exitPrograme() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton) {

                try {
                    markSortie(LoginController.userAccteuil.getIdWorkTime()) ;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                System.exit(0);
            }
        });


    }

    private void markSortie(int id) throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime timeNow = LocalDateTime.now();
        String timeSortie = dtf.format(timeNow) ;

        Connection etat = prepare.getConnection() ;

        String sql = "UPDATE worktime SET time_Sortie =?  WHERE idWorkTime=?" ;

        PreparedStatement ps = etat.prepareStatement(sql) ;
        ps.setString(1,timeSortie);
        ps.setInt(2,id);

        int op=  ps.executeUpdate() ;


    }



    private void prepareUser()
    {

        userName.setText(LoginController.userAccteuil.getPrenom().toUpperCase());

        var img = LoginController.userAccteuil.getImg() ;
        if (img != null)
             zoneImg.setFill(new ImagePattern(LoginController.userAccteuil.getImg()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            preparePage("/com/application/parking/Gere_Parking/Acceuil/acceuil.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        prepareUser() ;

        Stage stageAcctuil = LoginController.stageRoot ;

        stageAcctuil.setOnCloseRequest(windowEvent -> {
            exitPrograme();
        }); ;








    }
}
