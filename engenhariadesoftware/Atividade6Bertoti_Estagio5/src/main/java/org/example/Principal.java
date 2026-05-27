package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Como o Farmacia.fxml está direto em src/main/resources
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Farmacia.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Sistema de Farmácia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}