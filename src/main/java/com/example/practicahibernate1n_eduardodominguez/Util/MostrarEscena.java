package com.example.practicahibernate1n_eduardodominguez.Util;

import com.example.practicahibernate1n_eduardodominguez.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MostrarEscena {
    public static void mostrarScene(ActionEvent event, String ruta) throws IOException {
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(ruta));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("AGENCIA DE VIAJES ");
        stage.setScene(scene);
        stageActual.close();
        stage.show();
    }

}
