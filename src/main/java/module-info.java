module com.onlycoach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.baseEmpty;
    requires org.opentest4j;
    requires org.apiguardian.api;
    requires javafx.base;
    requires org.junit.jupiter.api;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires org.junit.jupiter.engine;
    requires javafx.fxmlEmpty;
    requires org.junit.platform.commons;
    requires org.junit.platform.engine;
    requires javafx.controlsEmpty;
    requires mysql.connector.java;


    opens com.onlycoach to javafx.fxml;
    exports com.onlycoach;
}