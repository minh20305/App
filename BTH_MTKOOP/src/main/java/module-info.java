module com.mycompany.bth_mtkoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires lombok;
    requires java.sql;

    opens com.mycompany.bth_mtkoop to javafx.fxml;
    exports com.mycompany.bth_mtkoop;
}
