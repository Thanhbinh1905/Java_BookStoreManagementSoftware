module com.example.quanlybansach_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.quanlybansach_java to javafx.fxml;
    exports com.quanlybansach_java;
    exports com.quanlybansach_java.Model;
    opens com.quanlybansach_java.Model to javafx.fxml;
    exports com.quanlybansach_java.DATABASE;
    opens com.quanlybansach_java.DATABASE to javafx.fxml;
    exports com.quanlybansach_java.CONTROLLER;
    opens com.quanlybansach_java.CONTROLLER to javafx.fxml;
}