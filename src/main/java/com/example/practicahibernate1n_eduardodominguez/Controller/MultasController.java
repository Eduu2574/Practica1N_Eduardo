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
import java.util.Date;
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
    Multa multasSeleccionada;
    Coche cocheSeleccionado;
    MultasDAO multasDAO = new MultasDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Cargo los datos del TableView asignando cada propiedad a la columna
        idMultaCol.setCellValueFactory(new PropertyValueFactory<>("id_multa"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    @FXML
    void onInsertarClic(ActionEvent event) {
        // Compruebo que el usuario haya rellenado los 2 campos necesarios para crear una multa
        if (fechaDP.getValue() == null || precioTf.getText().isEmpty()) {
            Alerta.mostrarError("Tienes que rellenar todos los datos"); // Muestra un mensaje de error si falta algún datoç
            return;
        }

        // Almaceno los datos de mis textfield en unas variables
        double precio = Double.parseDouble(precioTf.getText());
        LocalDate fecha = fechaDP.getValue();

        // Inserto en mi base de datos la nueva multa
        Multa multa1 = new Multa(cocheSeleccionado, precio, fecha);
        if (multasDAO.insertarMulta(multa1)) {
            actualizarTabla(); // Llamo al método para que actualice los datos en la tabla
            onLimpiarClic(event); // Llamo al método para que limpie los datos
            Alerta.mostrarInformacion("Multa creada correctamente"); // Muestra un mensaje de éxito si la inserción fue correcta
        } else {
            Alerta.mostrarError("No se ha podido insertar la multa"); // Muestra un mensaje de error si la inserción no se ha podido realizar
        }
    }

    @FXML
    void onActualizarClic(ActionEvent event) {
        Multa multaSeleccionada = MultaTv.getSelectionModel().getSelectedItem();

        // En el caso de que no haya seleccionado nada muestro una alerta
        if (multaSeleccionada == null) {
            Alerta.mostrarError("Debes seleccionar una multa"); // Muestra un mensaje de error si no se selecciona una multa
            return;
        }

        // Almaceno los datos de mis textfield en unas variables
        double precio = Double.parseDouble(precioTf.getText());
        LocalDate fecha = fechaDP.getValue();

        // Modifico los datos del coche
        multaSeleccionada.setPrecio(precio);
        multaSeleccionada.setFecha(fecha);

        // Actualizo en la base de datos y muestro mensaje
        if (multasDAO.actualizarMulta(multaSeleccionada)) {
            Alerta.mostrarInformacion("Multa actualizada correctamente"); // Muestra mensaje de éxito si la actualización se ha hecho correctamente
            actualizarTabla(); // Llamo al método para actualizar la tabla para reflejar los cambios
            onLimpiarClic(event);
        } else {
            Alerta.mostrarError("No se ha podido actualizar la multa"); // Muestro mensaje de error si la actualización falla
        }
    }

    @FXML
    void onBorrarClic(ActionEvent event) {
        // Obtengo y almaceno en unas variables la multa seleccionado por el usuario
        Multa multaSeleccionada = MultaTv.getSelectionModel().getSelectedItem();

        // Verifico si no se ha seleccionado ninguna multa
        if (multaSeleccionada == null) {
            Alerta.mostrarError("Debes seleccionar una multa"); // Muestra un mensaje de error si no se selecciona una multa
            return; // Salgo del método si no hay coche seleccionado
        }

        // Elimino la multa de mi base de datos
        if (multasDAO.borrarMulta(multaSeleccionada)) {
            actualizarTabla(); // Actualizo la tabla para reflejar los cambios
            Alerta.mostrarInformacion("Multa eliminada correctamente"); // Muestra un mensaje de éxito si la eliminación se ha hecho correctamente
        } else {
            Alerta.mostrarError("No se ha podido eliminar la multa"); // Muestra un mensaje de error si la eliminación no se ha podido realizar
        }
        // Llamo al método para limpiar los datos del textfield
        onLimpiarClic(event);
    }

    @FXML
    void onLimpiarClic(ActionEvent event) {
        idMultaTf.clear();
        precioTf.clear();
        fechaDP.setValue(null);
    }

    void cargarCoche(Coche coche) {
        cocheSeleccionado = coche;
        matriculaTf.setText(coche.getMatricula());
        try {
            List<Multa> listarMultas = multasDAO.obtenerMulta(cocheSeleccionado);
            listadoMultas = FXCollections.observableArrayList(listarMultas);
            MultaTv.setItems(listadoMultas);
        } catch (Exception e) {
            System.err.println("Error al cargar las multas: " + e.getMessage());
        }
    }

    @FXML
    void onSeleccionMultaClic(MouseEvent event) {
        matriculaTf.setText(cocheSeleccionado.getMatricula());
        Multa multas = MultaTv.getSelectionModel().getSelectedItem();
        if (multas != null) {
            multasSeleccionada = multas;
            idMultaTf.setText(String.valueOf(multas.getId_multa()));
            fechaDP.setValue(multas.getFecha());
            precioTf.setText(String.valueOf(multas.getPrecio()));
        }
    }

    void actualizarTabla() {
        // Método para actualizar los datos de la tabla trás hacer cambios
        List<Multa> listadoMultas = multasDAO.obtenerMulta(cocheSeleccionado); // Obtengo la lista de multas desde la base de datos
        MultaTv.getItems().setAll(listadoMultas); // Actualizo el TableView con la nueva lista de multas
    }

}