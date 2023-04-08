package com.application.parking.Gere_Parking.G_Menu.Abonement;

import com.application.parking.Gere_BaseDonne.check;
import com.application.parking.Gere_BaseDonne.prepare;
import com.application.parking.Gere_BaseDonne.select;
import com.application.parking.Gere_Parking.G_Login.LoginController;
import com.application.parking.Gere_Parking.G_Menu.TypeAbonement.TypeAbonementSelected;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbonementController implements Initializable {

    @FXML
    private DatePicker DateFin_Abnmt;

    @FXML
    private DatePicker DateNow;

    @FXML
    private TextField inputCIN;

    @FXML
    private ComboBox<String> listAbnment;

    @FXML
    private TextField outputMontant;
    @FXML
    private Label notification;

    LocalDateTime dateNow,dateFin ;
    @FXML
    void getData(ActionEvent event) throws SQLException {

        String nomSelect  = listAbnment.getValue() ;

       if (nomSelect.isEmpty())
       {
           outputMontant.setText("") ;
           DateNow.setValue(null);
           DateFin_Abnmt.setValue(null) ;
       }
       else
       {
           Float montant = select.getPrixAbnmt(nomSelect) ;

           outputMontant.setText(String.valueOf(montant));

           dateNow = LocalDateTime.now() ;
           DateNow.setValue(LocalDate.from(dateNow));

           int duree = select.getDureeAbnmt(nomSelect) ;

           dateFin = LocalDate.from(dateNow.plusMonths(duree)).atStartOfDay();

           DateFin_Abnmt.setValue(LocalDate.from(dateFin));
       }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            prepareComboBox() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void prepareComboBox() throws SQLException {

        ArrayList<TypeAbonementSelected> Data = select.getDataForTypeAbonement();

        ArrayList<String> nomTypeAbnmt = select.getNomTypeAbnmtForComboBoxAbnmt() ;

        listAbnment.getItems().setAll(nomTypeAbnmt) ;


    }

    @FXML
    void enregister(MouseEvent event) throws SQLException {

        notification.setVisible(false);

        String CIN = inputCIN.getText() ;
        String TypeAbnmt = listAbnment.getValue() ;

        if (CIN.isEmpty() || TypeAbnmt.isEmpty())
        {
            notification.setText("Champs Empty");
            notification.setVisible(true);
        }
        else
        {

             if (check.checkCINforClient(CIN))
             {
                 if (check.haveAbonment(CIN))
                 {
                     notification.setText("Already Have Abonment");
                     notification.setVisible(true);
                 }
                 else
                 {
                     newAbnmt(CIN,TypeAbnmt,event) ;
                 }

             }

             else
             {
                 notification.setText("CIN Introuvable");
                 notification.setVisible(true);
             }
        }


    }

    private void newAbnmt(String cin, String typeAbnmt,MouseEvent event) throws SQLException {

        int idTypeAbnmt  = select.getIDbyNomAbment(typeAbnmt) ;
        int idClient = select.getIDbyCIN(cin) ;
        int idUser = LoginController.userAccteuil.getIdUser();
        String padge = CreationPadge.generateRandomString() ;


        System.out.println("cin  --- :" +cin);
        System.out.println("idClient mn var --- :" +idClient);
        System.out.println("idClient mn Methode --- :" +select.getIDbyCIN(cin));
        System.out.println("idTypeAbnmt --- :" +idTypeAbnmt);
        System.out.println("idUser --- :" +idUser);
        System.out.println("idDateDebut --- :" +dateNow);
        System.out.println("idDateFin --- :" +dateFin);
        System.out.println("padge --- :" +padge);
        System.out.println("idClient --- :" +idClient);

      //  Date dateStart = (Date) Date.from(dateNow.atZone(ZoneId.systemDefault()).toInstant());
       // Date dateExpire = (Date) Date.from(dateFin.atZone(ZoneId.systemDefault()).toInstant());


        Connection etat = prepare.getConnection() ;
        String insertAbnmt = "Insert into abonement(idClient,idTypeAbnmt,idUser,date_Debut,date_Fin,etat) values(?,?,?,?,?,true)" ;
        String updateClient = "UPDATE client SET padge  =?,exipre=false  WHERE idClient =?" ;




       PreparedStatement psAbnmt = etat.prepareStatement(insertAbnmt) ;
       PreparedStatement psClient = etat.prepareStatement(updateClient) ;

        psAbnmt.setInt(1,idClient);
        psAbnmt.setInt(2,idTypeAbnmt);
        psAbnmt.setInt(3,idUser);
        psAbnmt.setString(4, String.valueOf(dateNow));
        psAbnmt.setString(5, String.valueOf(dateFin));

        psClient.setString(1,padge);
        psClient.setInt(2,idClient);

        int res1 = psAbnmt.executeUpdate() ;
        int res2 = psClient.executeUpdate() ;

        if (res1>0 && res2>0)
        {
            incrementnbrUserTypeAbnmt(idTypeAbnmt) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Start Abonemnt");
            alert.setContentText("");
            alert.showAndWait() ;

        }
        else
        {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Error in Start Abonemnt");
            alert.setContentText("");
            alert.showAndWait() ;
        }



    }

    private void incrementnbrUserTypeAbnmt(int idTypeAbnmt) throws SQLException {

        Connection etat = prepare.getConnection() ;

        int nbrAvant = select.getNbrUserTybeAbnmt(idTypeAbnmt) ;
        nbrAvant++ ;
        String updateNbrUser = "UPDATE  typeabonment SET nbrUser  =?  WHERE idTypeAbnmt  =?" ;
        PreparedStatement psTypeAbnmt = etat.prepareStatement(updateNbrUser) ;

        psTypeAbnmt.setInt(1,nbrAvant);
        psTypeAbnmt.setInt(2,idTypeAbnmt);

        psTypeAbnmt.executeUpdate() ;



    }
}
