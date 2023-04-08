package com.application.parking.Gere_Parking.G_Menu.User.gereUser;
import com.application.parking.CryptoData.myAES_App;
import com.application.parking.Gere_BaseDonne.*;
import com.application.parking.Gere_Parking.G_Menu.User.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.application.parking.ParentController;
import com.application.parking.Gere_Parking.G_Menu.User.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddUser {

    @FXML
    private CheckBox checkPhoto;

    @FXML
    private TextField inputCIN;

    @FXML
    private TextField inputNom;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputPrenom;

    @FXML
    private TextField inputTel;

    @FXML
    private Label notification;

    File imag = new File("") ;

    @FXML
    void ajouterPhoto(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );


        imag = fileChooser.showOpenDialog(new Stage());
        if (imag != null) {
            checkPhoto.setSelected(true);
        }
        else  checkPhoto.setSelected(false);



    }

    @FXML
    void enregister(MouseEvent event) throws SQLException, FileNotFoundException {

        notification.setVisible(false);
        final String secretKey = "donotspeakAboutThis" ;

        String nom = inputNom.getText() ;
        String prenom = inputPrenom.getText() ;
        String tel = inputTel.getText() ;
        String cin = inputCIN.getText() ;
        String password = inputPassword.getText() ;
        String hashPassword = myAES_App.encrypt(password,secretKey) ;

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || cin.isEmpty() || password.isEmpty() || !checkPhoto.isSelected())
        {
            notification.setText("Champs empty");
            notification.setVisible(true);
        }
        else
        if (check.checkCIN(cin))
        {
            notification.setText("CIN already Exist");
            notification.setVisible(true);
        }
        else
        if (check.checkTel(tel))
        {
            notification.setText("Tel already Exist");
            notification.setVisible(true);
        }
        else
        {
            insertInfoUser(nom,prenom,tel,cin,hashPassword,imag,event) ;
        }


    }

    private void insertInfoUser(String nom, String prenom, String tel, String cin, String hashPassword, File img,MouseEvent event) throws SQLException, FileNotFoundException {

        Connection etat = prepare.getConnection() ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateInscription = dtf.format(timeNow) ;
        InputStream inputStream = new FileInputStream(img);


        String insertPersone = "Insert into personne (Nom,Prenom,CIN,Tel,Date_Inscription,etat) values(?,?,?,?,?,true)" ;
        String insertUser = "Insert into userprogr (idUser ,passwordUser,admin,img) values(?,?,?,?)" ;

        PreparedStatement psPersone = etat.prepareStatement(insertPersone,Statement.RETURN_GENERATED_KEYS) ;
        PreparedStatement psUser = etat.prepareStatement(insertUser) ;

        psPersone.setString(1,nom);
        psPersone.setString(2,prenom);
        psPersone.setString(3,cin);
        psPersone.setString(4,tel);
        psPersone.setString(5,dateInscription);

        int peroneInsertedRows = psPersone.executeUpdate() ;

        if (peroneInsertedRows>0)
        {
            ResultSet personIDResult = psPersone.getGeneratedKeys();
            if (personIDResult.next())
            {

                int idUser = personIDResult.getInt(1) ;
                psUser.setInt(1,idUser);
                psUser.setString(2,hashPassword);
                psUser.setBoolean(3,false);
                psUser.setBlob(4, inputStream);

                int UserInsertedRows = psUser.executeUpdate() ;

                returnInsert(UserInsertedRows,event);
            }


        }


    }

    private void returnInsert(int result,MouseEvent event) throws SQLException {
       if (result >0)
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Succes");
           alert.setHeaderText("Add User Succes");
           alert.setContentText("");
           alert.showAndWait() ;
           Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
           stage.close();
       }
       else
       {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("WARNNING");
           alert.setHeaderText("Probleme in Add User ");
           alert.setContentText("");
           alert.showAndWait() ;
       }
    }




}
