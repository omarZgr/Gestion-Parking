package com.application.parking.Gere_Parking.G_Menu.Acceuil;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AcceuilController {

    @FXML
    private TextField inputPadgeIN;

    @FXML
    private TextField inputPadgeOUT;

    @FXML
    private TextField outputFree;

    @FXML
    private TextField outputReserve;

    @FXML
    private TextField outputTotal;

    @FXML
    private TableView<?> tableListeParking;

    @FXML
    private TableColumn<?, ?> tableListeParking_Entre;

    @FXML
    private TableColumn<?, ?> tableListeParking_Nom;

    @FXML
    private TableColumn<?, ?> tableListeParking_Prenom;

    @FXML
    void inputPadgeINClicked(MouseEvent event) {

    }

    @FXML
    void inputPadgeOUTClicked(KeyEvent event) {

    }

}
