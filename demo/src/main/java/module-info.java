module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    opens controllers to javafx.fxml;
    exports controllers to javafx.fxml;
    opens Models to javafx.base;
    exports Models to javafx.base;
}