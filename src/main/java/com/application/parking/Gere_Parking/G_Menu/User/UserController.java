package com.application.parking.Gere_Parking.G_Menu.User;

import com.application.parking.Gere_Parking.G_Menu.User.gereUser.tableauUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.application.parking.Gere_BaseDonne.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private AnchorPane page;

    @FXML
    private TableColumn<tableauUser, String> Nom;

    @FXML
    private TableColumn<tableauUser, String> Prenom;

    @FXML
    private TableColumn<tableauUser, String> Tel;

    @FXML
     private TableView<tableauUser> tableListeUser;

    public static UserSelected userController = new UserSelected() ;

    @FXML
    void addUser(MouseEvent event) throws IOException {

        preparePage(event,"/com/application/parking/Gere_Parking/User/gereUser/addUser.fxml","Add User") ;

    }

    @FXML
    void modifiyUser(MouseEvent event) throws IOException {

        if (prepareModfiy_Remove_Suivi())
            preparePage(event,"/com/application/parking/Gere_Parking/User/gereUser/modifiyUser.fxml","Modifiy User") ;
        else
            notification_Doit_chosir_Une_Row() ;
    }



    @FXML
    void removeUser(MouseEvent event) throws IOException {
        if (prepareModfiy_Remove_Suivi())
            preparePage(event,"/com/application/parking/Gere_Parking/User/gereUser/removeUser.fxml","Remove User") ;
        else
            notification_Doit_chosir_Une_Row() ;

    }

    @FXML
    void suiviUser(MouseEvent event) throws IOException {
        if (prepareModfiy_Remove_Suivi())
            preparePage(event,"/com/application/parking/Gere_Parking/User/gereUser/suiviUser.fxml","Suivi User") ;
        else
            notification_Doit_chosir_Une_Row() ;



    }


    @FXML
    void updateData(MouseEvent event) throws SQLException {
        prepareTable() ;

    }
    private void preparePage(MouseEvent event,String path,String tilte) throws IOException {

        Stage stage = new Stage() ;
        URL fxmlfile = getClass().getResource(path) ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle(tilte);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void prepareTable() throws SQLException {


       ArrayList<tableauUser> Data = select.getDataForUserTable();

       if (Data != null)
       {
           Nom.setCellValueFactory(new PropertyValueFactory<tableauUser,String>("nom"));
           Prenom.setCellValueFactory(new PropertyValueFactory<tableauUser,String>("prenom"));
           Tel.setCellValueFactory(new PropertyValueFactory<tableauUser,String>("tel"));
           ObservableList<tableauUser> list = FXCollections.observableArrayList();
           tableListeUser.getItems().setAll(Data) ;
       }


    }

    private boolean prepareModfiy_Remove_Suivi() {

        int index = tableListeUser.getSelectionModel().getSelectedIndex();
        if (index != -1) {

            tableauUser rowSelected = tableListeUser.getSelectionModel().getSelectedItem();
            String tel = rowSelected.getTel() ;

            int id = select.getIDbyTel(tel) ;
            userController.setId(select.getIDbyTel(tel)) ;
            userController.setNom(select.getNom(id));
            userController.setPrenom(select.getPrenom(id));
            userController.setTel(tel);
            userController.setCIN(select.getCIN(id));
            userController.setDateInscription(select.getDateInscription(id));

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            prepareTable() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
