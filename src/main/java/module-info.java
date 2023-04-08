module com.application.parking {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    opens com.application.parking.Gere_Parking.G_Menu.Acceuil ;
    opens com.application.parking.Gere_Parking.G_Menu.Client ;
    opens com.application.parking.Gere_Parking.G_Menu.Abonement;
    opens com.application.parking.Gere_Parking.G_Menu.Paye ;
    opens com.application.parking.Gere_Parking.G_Menu.TypeAbonement ;
    opens com.application.parking.Gere_Parking.G_Menu.User;
    opens com.application.parking.Gere_Parking.G_Login ;
    opens com.application.parking.Gere_Parking.G_Menu.User.gereUser;
    opens com.application.parking.Gere_Parking.G_Menu.TypeAbonement.gereTypeAbonement;
    opens com.application.parking.Gere_Parking.G_Menu.Client.gereClient ;
    opens com.application.parking to javafx.fxml;
    exports com.application.parking;
}