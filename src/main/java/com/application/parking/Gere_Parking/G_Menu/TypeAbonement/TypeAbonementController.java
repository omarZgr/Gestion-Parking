package com.application.parking.Gere_Parking.G_Menu.TypeAbonement;

import com.application.parking.Gere_BaseDonne.select;
import com.application.parking.Gere_Parking.G_Menu.User.gereUser.tableauUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TypeAbonementController implements Initializable {

    @FXML
    private TableColumn<TypeAbonementSelected, String> Duree;

    @FXML
    private TableColumn<TypeAbonementSelected, Integer> NbrUser;

    @FXML
    private TableColumn<TypeAbonementSelected, String> NomAbnmt;

    @FXML
    private TableColumn<TypeAbonementSelected, Float> Prix;

    @FXML
    private TableView<TypeAbonementSelected> tableTypeAbnment;

    @FXML
    void add(MouseEvent event) throws IOException {
        preparePage(event,"/com/application/parking/Gere_Parking/TypeAbonement/gereTypeAbonement/addTypeAbonement.fxml","Add Type Abnmt") ;

    }

    @FXML
    void modifiy(MouseEvent event) throws IOException {
        if(prepareModfiy_Remove())
            preparePage(event,"/com/application/parking/Gere_Parking/TypeAbonement/gereTypeAbonement/modifiyTypeAbonement.fxml","Modifiy Type Abnmt") ;
        else
        notification_Doit_chosir_Une_Row() ;
    }

    @FXML
    void remove(MouseEvent event) throws IOException {
        if(prepareModfiy_Remove())
            preparePage(event,"/com/application/parking/Gere_Parking/TypeAbonement/gereTypeAbonement/removeTypeAbonement.fxml","Remove Type Abnmt") ;
        else
            notification_Doit_chosir_Une_Row() ;

    }

    public  static TypeAbonementSelected typeAbonementSelected = new TypeAbonementSelected() ;

    private boolean prepareModfiy_Remove() {

        int index = tableTypeAbnment.getSelectionModel().getSelectedIndex();
        if (index != -1) {

            TypeAbonementSelected rowSelected = tableTypeAbnment.getSelectionModel().getSelectedItem();
            String nom = rowSelected.getNom() ;

            int id = select.getIDbyNomAbment(nom) ;
            typeAbonementSelected.setID(id); ;
            typeAbonementSelected.setNom(select.getNomAbnmt(id));
            typeAbonementSelected.setDuree(select.getDureeAbnmt(id));
            typeAbonementSelected.setPrix(select.getPrixAbnmt(id));
            typeAbonementSelected.setNbrUser(select.getNbrUserAbnmt(id));

            return true ;
        }

        return  false  ;

    }
    private void notification_Doit_chosir_Une_Row() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNNING");
        alert.setHeaderText("Selecet row ");
        alert.setContentText("");
        alert.showAndWait() ;
    }
    public void prepareTable() throws SQLException {


        ArrayList<TypeAbonementSelected> Data = select.getDataForTypeAbonement();

        if (Data != null)
        {
            NomAbnmt.setCellValueFactory(new PropertyValueFactory<TypeAbonementSelected,String>("nom"));
            Duree.setCellValueFactory(new PropertyValueFactory<TypeAbonementSelected,String>("duree"));
            Prix.setCellValueFactory(new PropertyValueFactory<TypeAbonementSelected,Float>("prix"));
            NbrUser.setCellValueFactory(new PropertyValueFactory<TypeAbonementSelected,Integer>("nbrUser"));
            ObservableList<tableauUser> list = FXCollections.observableArrayList();
            tableTypeAbnment.getItems().setAll(Data) ;
        }


    }

    private void preparePage(MouseEvent event,String path,String tilte) throws IOException {

        Stage stage = new Stage() ;
        URL fxmlfile = getClass().getResource(path) ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle(tilte);
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void updateData(MouseEvent event) throws SQLException {

        prepareTable() ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            prepareTable() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
