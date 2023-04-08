package com.application.parking.Gere_Parking.G_Menu.User.gereUser;

import com.application.parking.Gere_BaseDonne.select;
import com.application.parking.Gere_Parking.G_Menu.User.TableUser;
import com.application.parking.Gere_Parking.G_Menu.User.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Suivi implements Initializable {

    @FXML
    private TableColumn<TableUser, java.util.Date> Date;

    @FXML
    private TableColumn<TableUser, String> Time_Entre;

    @FXML
    private TableColumn<TableUser, String> Time_Sortie;

    @FXML
    private TableView<TableUser> UserSuivi;

    @FXML
    private Label notification;

    @FXML
    private TextField outputPrenom;

    @FXML
    void ok(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputPrenom.setText(UserController.userController.getPrenom());

        try {
            prepareTable() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void prepareTable() throws SQLException {

        ArrayList<TableUser> Data = select.getDataUserSuivi(UserController.userController.getId());

        if (Data != null)
        {
            Date.setCellValueFactory(new PropertyValueFactory<TableUser,Date>("date"));
            Time_Entre.setCellValueFactory(new PropertyValueFactory<TableUser,String>("time_Entre"));
            Time_Sortie.setCellValueFactory(new PropertyValueFactory<TableUser,String>("time_Sortie"));
            ObservableList<tableauUser> list = FXCollections.observableArrayList();
            UserSuivi.getItems().setAll(Data) ;
        }
    }
}
