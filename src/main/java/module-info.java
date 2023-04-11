module com.onlycoach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.onlycoach to javafx.fxml;
    exports com.onlycoach;
}