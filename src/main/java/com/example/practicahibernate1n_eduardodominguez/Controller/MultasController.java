package com.example.practicahibernate1n_eduardodominguez.Controller;

import com.example.practicahibernate1n_eduardodominguez.DAO.CocheDAO;
import com.example.practicahibernate1n_eduardodominguez.DAO.MultasDAO;
import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Model.Multa;
import com.example.practicahibernate1n_eduardodominguez.Util.Alerta;
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
    private TextField idMultaTf;
    @FXML
    private TextField matriculaTf;
    @FXML
    private TextField precioTf;

    ObservableList<Multa> listadoMultas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Cargo los datos del TableView asignando cada propiedad a la columna
        idMultaCol.setCellValueFactory(new PropertyValueFactory<>("id_multa"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        try {
            List<Multa> listarMultas = MultasDAO.getInstance().obtenerMulta(); // Obtengo la lista de coches de la base de datos
            listadoMultas = FXCollections.observableArrayList(listarMultas); // Creo una lista observable con los coches
            MultaTv.setItems(listadoMultas); // Establezco la lista de coches en el TableView
        } catch (Exception e) {
            // Maneja la excepción adecuadamente
            System.err.println("Error al cargar las multas: " + e.getMessage());
        }
    }



    @FXML
    void onActualizarClic(ActionEvent event) {

    }

    @FXML
    void onBorrarClic(ActionEvent event) {

    }

    @FXML
    void onInsertarClic(ActionEvent event) {
        String matricula=matriculaTf.getText();
        int idMulta= Integer.parseInt(idMultaTf.getText());
        double precio= Double.parseDouble(precioTf.getText());
        LocalDate fecha=fechaDP.getValue();
        /*
        if (matricula.isEmpty() || idMulta.isEmpty() || precio.isEmpty() || fecha==null) {
            Alerta.mostrarError("Tienes que rellenar todos los datos"); // Muestra un mensaje de error si falta algún datoç
            return;
        }

         */
        /*
        // Compruebo que la matrícula SI QUE EXISTE
        if (!MultasDAO.getInstance().verificarExisteMatricula(matricula)) {
            Alerta.mostrarError("La matricula no coincide con ningun vehiculo de nuestra base de datos!"); // Muestra un mensaje de error si la matrícula ya existe
            return; // Salgo si la matrícula no existe
            // Si los datos son válidos y la matrícula SI existe, procedo con la inserción
            // Creo el nuevo objeto con los datos introducidos por el usuario
        }
        Multa multa1=new Multa(matricula,precio,fecha);
        if (MultasDAO.getInstance().insertarMulta(multa1)) {
            actualizarTabla(); // Llamo al método para que actualice los datos en la tabla
            onLimpiarClic(event); // Llamo al método para que limpie los datos
            Alerta.mostrarInformacion("Coche insertado correctamente"); // Muestra un mensaje de éxito si la inserción fue correcta
        } else {
            Alerta.mostrarError("No se ha podido insertar el coche."); // Muestra un mensaje de error si la inserción no se ha podido realizar
        }

         */

    }


    @FXML
    void onLimpiarClic(ActionEvent event) {

    }

    @FXML
    void onSeleccionMultaClic(MouseEvent event) {

    }
    void actualizarTabla() {
        // Método para actualizar los datos de la tabla trás hacer cambios
        List<Multa> listadoMultas = MultasDAO.getInstance().obtenerMulta(); // Obtengo la lista de coches desde la base de datos
        MultaTv.getItems().setAll(listadoMultas); // Actualizo el TableView con la nueva lista de coches
    }

}
