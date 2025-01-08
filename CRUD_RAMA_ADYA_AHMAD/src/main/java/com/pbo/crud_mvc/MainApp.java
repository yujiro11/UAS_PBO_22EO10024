package com.pbo.crud_mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL fxmlLocation = getClass().getResource("MainView.fxml");
        if (fxmlLocation == null) {
            System.out.println("FXML file not found! Please check the file location.");
            return;
        } else {
            System.out.println("FXML file found at: " + fxmlLocation);
        }


        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Sistem Informasi Data Mahasiswa");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
