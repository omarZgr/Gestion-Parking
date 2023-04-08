package com.application.parking.Gere_Parking.G_Menu.Client;

import com.application.parking.Gere_BaseDonne.select;
import com.application.parking.Gere_Parking.G_Menu.User.UserSelected;
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

public class ClientController implements Initializable {

    @FXML
    private TableColumn<TableClient, String> Nom;

    @FXML
    private TableColumn<TableClient, String> Prenom;

    @FXML
    private TableColumn<TableClient, String> Tel;

    @FXML
    private TableView<TableClient> tableListeClient;

    @FXML
    void AddClient(MouseEvent event) throws IOException {

        preparePage(event,"/com/application/parking/Gere_Parking/Client/gereClient/addClient.fxml","Add Client") ;

    }

    @FXML
    void ModifiyClient(MouseEvent event) throws IOException {
        if (prepareModfiy_Remove_Suivi())
            preparePage(event,"/com/application/parking/Gere_Parking/Client/gereClient/modifiyClient.fxml","Modifiy Client") ;
        else
            notification_Doit_chosir_Une_Row() ;
    }

    @FXML
    void RemoveClient(MouseEvent event) throws IOException {
        if (prepareModfiy_Remove_Suivi())
            preparePage(event,"/com/application/parking/Gere_Parking/Client/gereClient/removeClient.fxml","Remove Client") ;
        else
            notification_Doit_chosir_Une_Row() ;

    }

    @FXML
    void SuiviClient(MouseEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNNING");
        alert.setHeaderText("Mazal msybta");
        alert.setContentText("");
        alert.showAndWait() ;
        return;


      /*  if (prepareModfiy_Remove_Suivi())
            preparePage(event,"/com/application/parking/Gere_Parking/Client/gereClient/suiviClient.fxml","Remove Client") ;
        else
            notification_Doit_chosir_Une_Row() ;

       */

    }

    public static ClientSelected clientSelected = new ClientSelected() ;

    private boolean prepareModfiy_Remove_Suivi() {

        int index = tableListeClient.getSelectionModel().getSelectedIndex();
        if (index != -1) {


            TableClient rowSelected = tableListeClient.getSelectionModel().getSelectedItem();
            String tel = rowSelected.getTel() ;

            int id = select.getIDbyTel(tel) ;
            clientSelected.setId(select.getIDbyTel(tel)) ;
            clientSelected.setNom(select.getNom(id));
            clientSelected.setPrenom(select.getPrenom(id));
            clientSelected.setTel(tel);
            clientSelected.setCIN(select.getCIN(id));
            clientSelected.setDateInscription(select.getDateInscription(id));
            clientSelected.setMatricule(select.getMatricule(id));
            clientSelected.setPadge(select.getPadge(id));
            clientSelected.setDateInscription(select.getDateInscription(id));



            return true ;
        }

        return  false  ;

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
    void UpdateData(MouseEvent event) throws SQLException {

        prepareTable() ;
    }

    public void prepareTable() throws SQLException {


        ArrayList<TableClient> Data = select.getDataForClientTable();

        if (Data != null)
        {
            Nom.setCellValueFactory(new PropertyValueFactory<TableClient,String>("nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<TableClient,String>("prenom"));
            Tel.setCellValueFactory(new PropertyValueFactory<TableClient,String>("tel"));
            ObservableList<tableauUser> list = FXCollections.observableArrayList();
            tableListeClient.getItems().setAll(Data) ;
        }


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
