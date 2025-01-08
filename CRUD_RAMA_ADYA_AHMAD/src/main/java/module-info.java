module com.example.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.crud_mvc to javafx.fxml;
    exports com.example.crud_mvc;
    exports com.example.crud_mvc.model;
    opens com.example.crud_mvc.model to javafx.fxml;
    exports com.example.crud_mvc.model.dao;
    opens com.example.crud_mvc.model.dao to javafx.fxml;
    exports com.example.crud_mvc.database;
    opens com.example.crud_mvc.database to javafx.fxml;
    exports com.example.crud_mvc.controller;
    opens com.example.crud_mvc.controller to javafx.fxml;
}
