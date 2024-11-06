package com.example.practicahibernate1n_eduardodominguez.Controller;

import com.example.practicahibernate1n_eduardodominguez.DAO.MultasDAO;
import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Model.Multa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MultasController implements Initializable {

    // COLUMNAS:
    @FXML
    private TableColumn<Multa, Integer> idMultaCol;
    @FXML
    private TableColumn<Multa, Double> precioCol;
    @FXML
    private TableColumn<Multa, LocalDate> fechaCol;

    // TABLE VIEW:
    @FXML
    private TableView<Multa> MultaTv;

    // TEXTS FIELDS Y DATE PICKER:
    @FXML
    private DatePicker fechaDP;
    @FXML
    private TextField idMatriculaTf;
    @FXML
    private TextField matriculaTf;
    @FXML
    private TextField precioTf;

    ObservableList<Multa> listadoMultas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        // Cargo los datos del TableView asignando cada propiedad a la columna
        idMultaCol.setCellValueFactory(new PropertyValueFactory<>("id_multa"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));


        List<Multa> listarMultas = MultasDAO.getInstance().obtenerMulta(); // Obtengo la lista de coches de la base de datos
        listadoMultas = FXCollections.observableArrayList(listarMultas); // Creo una lista observable con los coches
        MultaTv.setItems(listadoMultas); // Establezco la lista de coches en el TableView

         */
    }

    @FXML
    void onActualizarClic(ActionEvent event) {

    }

    @FXML
    void onBorrarClic(ActionEvent event) {

    }

    @FXML
    void onInsertarClic(ActionEvent event) {

    }

    @FXML
    void onLimpiarClic(ActionEvent event) {

    }

    @FXML
    void onSeleccionMultaClic(MouseEvent event) {

    }


}
