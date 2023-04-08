package com.application.parking.Gere_Parking.G_Menu.Client.gereClient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SuiviClient {

    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> Time_Entre;

    @FXML
    private TableColumn<?, ?> Time_Sortie;

    @FXML
    private TableView<?> UserSuivi;

    @FXML
    private Label notification;

    @FXML
    private TextField outputPrenom;

    @FXML
    void ok(MouseEvent event) {

    }

}
