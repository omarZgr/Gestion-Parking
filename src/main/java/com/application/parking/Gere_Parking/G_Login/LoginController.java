package com.application.parking.Gere_Parking.G_Login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import  com.application.parking.Gere_BaseDonne.* ;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;


 public class LoginController  implements Initializable {

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUserName;

    @FXML
    private Label notification;


    @FXML
    void reset(MouseEvent event) {

        inputUserName.setText("");
        inputPassword.setText("");

        notification.setVisible(false);

    }

    public static Stage stageRoot = new Stage() ;
    @FXML
    void submit(MouseEvent event) throws IOException, SQLException {

        String CIN = inputUserName.getText() ;
        String password = inputPassword.getText() ;

        if (CIN.isEmpty() || password.isEmpty())
        {
            notification.setText("Champs Empty");
            notification.setStyle(" -fx-text-fill: red ;");
            notification.setVisible(true);

        }
        else
        {
           int idUser =  check.checkUser(CIN,password) ;


            if ( idUser == -1)
            {
                notification.setText("Compte Introuvable");
                notification.setStyle(" -fx-text-fill: red ;");
                notification.setVisible(true);

            }
            else
            {
                notification.setText("Bienevenue");
                notification.setStyle(" -fx-text-fill: green ;");
                notification.setVisible(true);
                prepareuser(idUser) ;

                stageRoot= (Stage) ((Button)event.getSource()).getScene().getWindow();
                URL fxmlfile = getClass().getResource("/com/application/parking/parent.fxml") ;
                Parent root = FXMLLoader.load(fxmlfile);
                stageRoot.setTitle("Gere Parking");
                stageRoot.setScene(new Scene(root));
                stageRoot.show();
            }
        }

    }

    public static UserLogin userAccteuil = new UserLogin() ;

    private void prepareuser(int id) throws SQLException {

        userAccteuil.setIdUser(id);
        userAccteuil.setNom(select.getNom(id));
        userAccteuil.setPrenom(select.getPrenom(id));
        userAccteuil.setAdmin(select.getAdmin(id));

        var buf =select.getImg(id)  ;

        if (buf != null)
        {
            Image img = new Image(new ByteArrayInputStream(buf)) ;
            userAccteuil.setImg(img);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeNow = LocalDateTime.now();
        String buff = dtf.format(timeNow) ;
        String dateEntre = buff.split(" ")[0] ;
        String timeEntre = buff.split(" ")[1] ;

        userAccteuil.setTimeEntre(timeEntre);
        userAccteuil.setDateEntre(dateEntre);

        markEntre(id); ;

    }

    private void markEntre(int id) throws SQLException {

        Connection etat = prepare.getConnection() ;

        String sql = "Insert into worktime (idUser,date_Work,time_Entre) values(?,?,?)" ;

        PreparedStatement ps ;

        try {
            ps = etat.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.setString(2, userAccteuil.getDateEntre());
            ps.setString(3, userAccteuil.getTimeEntre());

            int rowsAffected = ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int key = generatedKeys.getInt(1);
                    userAccteuil.setIdWorkTime(key);
                }

            }

          } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            checkConnectWithDB();
            checkClientExpireAbnmt() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void checkConnectWithDB() throws SQLException {
        Connection etat = prepare.getConnection() ;
        if (etat == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Connection Echouue avec Base Donne");
            alert.setContentText("");
            alert.showAndWait() ;
            System.exit(-1);
        }

    }

    private void checkClientExpireAbnmt() throws SQLException {



        ArrayList<Integer> data = select.getIDofClientExpire() ;

        if (data.size() != 0)
        {
            arretterClient(data) ;
            System.out.println("Test ");
        }
        else
            System.out.println("kulshi mkhlss");

    }

     private void arretterClient(ArrayList<Integer> data) throws SQLException {

        int id=-1 ;

        for (int i=0;i<data.size();i++)
        {
            id = data.get(i) ;
            System.out.println("in for");

            traitment(id) ;

        }


     }

     private void traitment(int id) throws SQLException {

         Connection etat = prepare.getConnection() ;

         String insertAbnmt = "UPDATE  abonement SET etat=false  where idAbonement=?" ;
         int idClient = select.getIDbyIDAbnmt(id);
         String updateClient = "UPDATE client SET exipre=true  WHERE idClient =?" ;

         PreparedStatement ps1 = etat.prepareStatement(insertAbnmt) ;
         PreparedStatement ps2 = etat.prepareStatement(updateClient) ;
         ps1.setInt(1,id);
         ps2.setInt(1,idClient);
         ps1.executeUpdate() ;
         ps2.executeUpdate() ;
         System.out.println("in update idAbnmt expire :" +id);


     }
 }
